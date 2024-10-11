package com.repository;

import com.model.UserTokens;

public interface userTokensRepository {
    void createUserTokens(UserTokens userTokens);
    public void resetMonthlyTokens();
    public void resetDailyTokens();
}
