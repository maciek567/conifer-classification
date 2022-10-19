package com.baeldung.drools.model;


public class Group {
    private NeedleGroup needleGroup;

    public NeedleGroup getNeedleGroup() {
        return needleGroup;
    }

    public void setNeedleGroup(NeedleGroup needleGroup) {
        this.needleGroup = needleGroup;
    }

    enum NeedleGroup {
        SOFT_NEEDLE, SHARP_NEEDLE
    }
}
