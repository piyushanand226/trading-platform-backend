package com.hackerrank.tradingplatform.dto;

import javax.validation.constraints.*;

public class AddMoneyTraderDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @DecimalMin(value = "0.01", message = "Amount must be positive")
    private double amount;

    // Getters & Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
