package com.example.bankingapi.account;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    AccountType type;
    private String nickname;
    @Column(nullable = false)
    private Integer rewards;
    @Column(nullable = false)
    private Double balance;
    @Column(nullable = false)
    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getRewards() {
        return rewards;
    }

    public void setRewards(Integer rewards) {
        this.rewards = rewards;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}

//get account balance
//check account balance against withdrawal amount -- we do this by:
//using comparison operator --
//balance < withdrawal
//think about how to check this... if statement!
//if (balance > withdrawal) then return true
//WE DONE
//what do we do with this code now? we want to use this boolean in our create method
// ok... this is the real tricky part
//