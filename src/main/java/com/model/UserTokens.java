package com.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_tokens")
public class UserTokens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "tokens_daily", nullable = false)
    private int dailyTokens = 2;

    @Column(name = "tokens_monthly", nullable = false)
    private int monthlyTokens = 1;

    @Column(name = "reset_date", nullable = false)
    private LocalDateTime resetDate = LocalDateTime.now();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDailyTokens() {
        return dailyTokens;
    }

    public void setDailyTokens(int dailyTokens) {
        this.dailyTokens = dailyTokens;
    }

    public int getMonthlyTokens() {
        return monthlyTokens;
    }

    public void setMonthlyTokens(int monthlyTokens) {
        this.monthlyTokens = monthlyTokens;
    }

    public LocalDateTime getResetDate() {
        return resetDate;
    }

    public void setResetDate(LocalDateTime resetDate) {
        this.resetDate = resetDate;
    }
}
