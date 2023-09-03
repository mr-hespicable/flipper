package com.websafe.flipper;

public class DebugTimer {
    private long startTime;

    public void Start() {
        startTime = System.currentTimeMillis();
    }

    public long Stop() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }
}
