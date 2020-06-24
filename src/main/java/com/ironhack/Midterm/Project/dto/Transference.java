package com.ironhack.Midterm.Project.dto;

import com.ironhack.Midterm.Project.model.Money;

public class Transference {

    private String senderName;
    private Integer receiverId;
    private Money amount;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }
}
