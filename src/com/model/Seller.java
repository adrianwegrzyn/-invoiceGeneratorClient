package com.model;

import java.io.Serializable;

public class Seller implements Serializable{
    private String sellerCompany;
    private String sellerName;
    private String sellerAdress;
    private String sellerPostcode;
    private String sellerCity;

    public Seller() {
    }
    public void setSellerCompany(String sellerCompany) {
        this.sellerCompany = sellerCompany;
    }
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setSellerAdress(String sellerAdress) {
        this.sellerAdress = sellerAdress;
    }

    public void setSellerPostcode(String sellerPostcode) {
        this.sellerPostcode = sellerPostcode;
    }

    public void setSellerCity(String sellerCity) {
        this.sellerCity = sellerCity;
    }
}
