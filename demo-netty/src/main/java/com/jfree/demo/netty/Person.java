package com.jfree.demo.netty;


import javax.validation.constraints.NotNull;


public class Person {

    @NotNull(message = "null---")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
