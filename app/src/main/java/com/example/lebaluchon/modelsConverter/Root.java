package com.example.lebaluchon.modelsConverter;

public class Root {
    private String date;
    private String historical;
    private Info info;
    private Query query;
    private double result;
    private boolean success;

    public Root(String date, String historical, Info info, Query query, double result, boolean success) {
        this.date = date;
        this.historical = historical;
        this.info = info;
        this.query = query;
        this.result = result;
        this.success = success;
    }

    public Root(){}
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHistorical() {
        return historical;
    }

    public void setHistorical(String historical) {
        this.historical = historical;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
