package com.colatina.app.service.dataprovider.adapter;

import com.colatina.app.service.configuration.mapper.AccountMapper;
import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.enumeration.AccountStatus;
import com.colatina.app.service.core.exception.BusinessException;
import com.colatina.app.service.core.gateway.AccountGateway;
import com.colatina.app.service.dataprovider.repository.AccountRepository;
import com.colatina.app.service.dataprovider.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountAdapter implements AccountGateway {

    private final AccountMapper mapper;
    private final AccountRepository repository;

    @Override
    public AccountDomain create(AccountDomain account) {

        AccountEntity entity = mapper.toEntity(account);
        repository.save(entity);

        return mapper.toDto(entity);
    }

    @Override
    public void changeStatus(Integer account_id, AccountStatus status) {
        AccountEntity account = findByAccount(account_id);
        account.setStatus(String.valueOf(status));
    }

    private AccountEntity findByAccount(Integer account_id) {
        return repository.findById(account_id).orElseThrow(() -> new BusinessException("User not found"));
    }

}
