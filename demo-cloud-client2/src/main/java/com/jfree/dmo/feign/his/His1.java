package com.jfree.dmo.feign.his;

import com.jfree.dmo.feign.ClientServiceFeign;

public class His1  implements ClientServiceFeign {
    @Override
    public double math() {
        return 1000;
    }
}
