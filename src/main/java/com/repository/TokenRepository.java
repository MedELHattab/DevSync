package com.repository;

import com.model.UserTokens;

public interface TokenRepository {

    UserTokens findTokensByUser(Long userId);

    void updateUserTokens(UserTokens tokens);
}
