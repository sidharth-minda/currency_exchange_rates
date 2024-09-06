package com.arenema.demo.dtos;

public class ConversionRate {
    private String target;
    private String value;

    public String getTarget() {
        return target;
    }

    public ConversionRate(String target, String value) {
        this.target = target;
        this.value = value;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
