package com.hackerrank.tradingplatform.dto;

import com.hackerrank.tradingplatform.model.Trader;

public class TraderDTO {

    private Long id;
    private String name;
    private String email;
    private String country;
    private String accountType;
    private String phone;
    private double balance;

    public TraderDTO(Trader trader) {
        this.id = trader.getId();
        this.name = trader.getName();
        this.email = trader.getEmail();
        this.country = trader.getCountry();
        this.accountType = trader.getAccountType();
        this.phone = trader.getPhone();
        this.balance = trader.getBalance();
    }

    // Getters only (read-only DTO)
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCountry() { return country; }
    public String getAccountType() { return accountType; }
    public String getPhone() { return phone; }
    public double getBalance() { return balance; }
}
