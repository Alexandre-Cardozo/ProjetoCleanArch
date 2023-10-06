package com.colatina.app.service.dataprovider.adapter;

import com.colatina.app.service.configuration.mapper.WalletMapper;
import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.WalletDomain;
import com.colatina.app.service.core.gateway.WalletGateway;
import com.colatina.app.service.dataprovider.entity.WalletEntity;
import com.colatina.app.service.dataprovider.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class WalletAdapter implements WalletGateway {

    private final WalletMapper walletMapper;
    private final WalletRepository walletRepository;

    @Override
    public WalletDomain create(AccountDomain account) {
        WalletEntity entity = new WalletEntity();

        WalletDomain newerWallet = walletMapper.toDto(entity);

        account.setWallet(newerWallet);
        walletRepository.save(entity);

        return walletMapper.toDto(entity);
    }

    @Override
    public BigDecimal getAccountBalance(Integer accountId) {
        return walletRepository.findBalanceByAccountId(accountId);
    }

}
