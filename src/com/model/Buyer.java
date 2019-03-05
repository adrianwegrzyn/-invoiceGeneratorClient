package com.model;

import java.io.Serializable;

public class Buyer implements Serializable {
    private String buyerName;
    private String buyerAdress;
    private String buyerPostcode;
    private String buyerCity;

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setBuyerAdress(String buyerAdress) {
        this.buyerAdress = buyerAdress;
    }

    public void setBuyerPostcode(String buyerPostcode) {
        this.buyerPostcode = buyerPostcode;
    }

    public void setBuyerCity(String buyerCity) {
        this.buyerCity = buyerCity;
    }

}
