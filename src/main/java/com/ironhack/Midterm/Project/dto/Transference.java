package com.ironhack.Midterm.Project.dto;

import com.ironhack.Midterm.Project.model.Money;

public class Transference {

    private String receiverName;
    private Integer receiverId;
    private Money amount;

    public Transference(String receiverName, Integer receiverId, Money amount) {
        this.receiverName = receiverName;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public Transference() {
    }

    public String getReceiverName() {
        return receiverName;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public Money getAmount() {
        return amount;
    }
}
