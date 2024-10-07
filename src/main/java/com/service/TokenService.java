package com.service;

import com.model.UserTokens;
import com.repository.TokenRepository;

public class TokenService {

    private TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public UserTokens getTokensForUser(Long userId) {
        return tokenRepository.findTokensByUser(userId);
    }

    public void useDailyToken(Long userId) {
        UserTokens tokens = tokenRepository.findTokensByUser(userId);
        if (tokens.getDailyTokens() > 0) {
            tokens.setDailyTokens(tokens.getDailyTokens() - 1);
            tokenRepository.updateUserTokens(tokens);
        }
    }

    public void resetTokens(UserTokens tokens) {
        tokens.setDailyTokens(2);
        tokens.setMonthlyTokens(1);
        tokenRepository.updateUserTokens(tokens);
    }
}
