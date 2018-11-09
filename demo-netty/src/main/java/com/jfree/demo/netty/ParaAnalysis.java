package com.jfree.demo.netty;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParaAnalysis {

    public static Map<String, String> parse(FullHttpRequest req) throws IOException {
        Map<String, String> res = new HashMap<>();
        HttpMethod hd = req.method();
        if (HttpMethod.GET.equals(hd)) {
            QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
            for (Map.Entry<String, List<String>> entry : decoder.parameters().entrySet()) {
                res.put(entry.getKey(), entry.getValue().get(0));
            }
        } else if (HttpMethod.POST.equals(hd)) {
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(req);
            decoder.offer(req);
            List<InterfaceHttpData> pts = decoder.getBodyHttpDatas();
            for (InterfaceHttpData ia : pts) {
                Attribute data = (Attribute) ia;
                res.put(data.getName(), data.getValue());
            }
        } else {
            throw new IOException("http方法不支持");
        }
        return res;
    }

}
