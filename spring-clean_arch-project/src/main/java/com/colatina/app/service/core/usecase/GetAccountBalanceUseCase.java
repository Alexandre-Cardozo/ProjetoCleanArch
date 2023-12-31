package com.colatina.app.service.core.usecase;

import com.colatina.app.service.core.gateway.WalletGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class GetAccountBalanceUseCase {

    private final WalletGateway walletGateway;

    public BigDecimal getAccountBalance(Integer accountId) {
        return walletGateway.getAccountBalance(accountId);
    }

}
