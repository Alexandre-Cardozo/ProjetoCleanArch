package com.colatina.app.service.dataprovider.adapter;

import com.colatina.app.service.configuration.mapper.AccountMapper;
import com.colatina.app.service.configuration.mapper.TransactionMapper;
import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.TransactionDomain;
import com.colatina.app.service.core.domain.enumeration.TransactionStatus;
import com.colatina.app.service.core.exception.BusinessException;
import com.colatina.app.service.core.gateway.TransactionGateway;
import com.colatina.app.service.dataprovider.entity.AccountEntity;
import com.colatina.app.service.dataprovider.entity.TransactionEntity;
import com.colatina.app.service.dataprovider.repository.AccountRepository;
import com.colatina.app.service.dataprovider.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionAdapter implements TransactionGateway {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public List<TransactionDomain> getAccountStatement(final Integer accountId, final LocalDateTime startDate, final LocalDateTime endDate) {
        return transactionMapper.toDto(transactionRepository.findAllByAccountOriginIdAndCreatedAtBetween(accountId, startDate, endDate));
    }

    @Override
    public List<TransactionDomain> getAccountStatementByValue(final Integer accountId, final BigDecimal value) {
        return transactionMapper.toDto(transactionRepository.findAllByAccountOriginIdAndValue(accountId, value));
    }

    @Override
    public TransactionDomain create(AccountDomain credidAccount, AccountDomain debitAccount, BigDecimal value) {
        AccountEntity credid = accountMapper.toEntity(credidAccount);
        AccountEntity debit = accountMapper.toEntity(debitAccount);

        TransactionEntity transaction = new TransactionEntity();

        transaction.setAccountOrigin(credid);
        transaction.setAccountDestination(debit);
        transaction.setValue(value);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setStatus("WAITING_PROCESSING");
        transaction.setType("Transaction");

        transactionRepository.save(transaction);

        return transactionMapper.toDto(transaction);
    }

    @Override
    public TransactionDomain persistStatus(TransactionDomain transactionDomain) {
        TransactionEntity transactionEntity = transactionMapper.toEntity(transactionDomain);
        transactionRepository.save(transactionEntity);
        return transactionDomain;
    }

}
