package com.mashirro.elasticsearch_demo.pojo;

import java.util.Date;

public class Xfjxx {
    private String id;

    private String xfjbh;

    private String djjgdm;

    private String djr;

    private String xfxs;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date djsj;

    /**
     * 对应glxfjbhs
     */
    private String[] glxfjbhArray;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXfjbh() {
        return xfjbh;
    }

    public void setXfjbh(String xfjbh) {
        this.xfjbh = xfjbh;
    }

    public String getDjjgdm() {
        return djjgdm;
    }

    public void setDjjgdm(String djjgdm) {
        this.djjgdm = djjgdm;
    }

    public String getDjr() {
        return djr;
    }

    public void setDjr(String djr) {
        this.djr = djr;
    }

    public String getXfxs() {
        return xfxs;
    }

    public void setXfxs(String xfxs) {
        this.xfxs = xfxs;
    }

    public Date getDjsj() {
        return djsj;
    }

    public void setDjsj(Date djsj) {
        this.djsj = djsj;
    }

    public String[] getGlxfjbhArray() {
        return glxfjbhArray;
    }

    public void setGlxfjbhArray(String[] glxfjbhArray) {
        this.glxfjbhArray = glxfjbhArray;
    }
}