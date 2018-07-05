package com.example.pojos;

public class WelcomePageOutput {
    private int WelcomePageId;
    private long worksBegin;
    private long worksEnd;
    private int status;
    private int type;
    private String sourceUrl;
    private String functionUrl;

    public int getWelcomePageId() {
        return WelcomePageId;
    }

    public void setWelcomePageId(int welcomePageId) {
        WelcomePageId = welcomePageId;
    }

    public long getWorksBegin() {
        return worksBegin;
    }

    public void setWorksBegin(long worksBegin) {
        this.worksBegin = worksBegin;
    }

    public long getWorksEnd() {
        return worksEnd;
    }

    public void setWorksEnd(long worksEnd) {
        this.worksEnd = worksEnd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getFunctionUrl() {
        return functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }
}
