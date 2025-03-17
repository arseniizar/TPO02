package com.example.tpo02;

public class Entry {
    private String enName;
    private String plName;
    private String deName;

    public Entry(String enName, String plName, String deName) {
        this.enName = enName;
        this.plName = plName;
        this.deName = deName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getPlName() {
        return plName;
    }

    public void setPlName(String plName) {
        this.plName = plName;
    }

    public String getDeName() {
        return deName;
    }

    public void setDeName(String deName) {
        this.deName = deName;
    }
}
