package com.colatina.app.service.dataprovider.adapter;

import com.colatina.app.service.configuration.mapper.AccountMapper;
import com.colatina.app.service.configuration.mapper.WalletMapper;
import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.WalletDomain;
import com.colatina.app.service.core.gateway.WalletGateway;
import com.colatina.app.service.dataprovider.entity.AccountEntity;
import com.colatina.app.service.dataprovider.entity.WalletEntity;
import com.colatina.app.service.dataprovider.repository.AccountRepository;
import com.colatina.app.service.dataprovider.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletAdapter implements WalletGateway {

    private final WalletMapper mapper;
    private final WalletRepository repository;

    @Override
    public WalletDomain create(AccountDomain account) {
        WalletEntity entity = new WalletEntity();

        WalletDomain newerWallet = mapper.toDto(entity);

        account.setWallet(newerWallet);
        repository.save(entity);

        return mapper.toDto(entity);
    }
}
