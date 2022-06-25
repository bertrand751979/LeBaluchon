package com.example.lebaluchon.modelsConverter;

public class Query {
    private int amount;
    private String from;
    //@JsonProperty("to")
    private String myto;

    public Query(int amount, String from, String myto) {
        this.amount = amount;
        this.from = from;
        this.myto = myto;
    }

    public Query() {}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMyto() {
        return myto;
    }

    public void setMyto(String myto) {
        this.myto = myto;
    }
}
