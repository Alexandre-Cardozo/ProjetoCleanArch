package com.colatina.app.service.dataprovider.adapter;

import com.colatina.app.service.configuration.mapper.TransactionMapper;
import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.TransactionDomain;
import com.colatina.app.service.core.gateway.TransactionGateway;
import com.colatina.app.service.dataprovider.entity.AccountEntity;
import com.colatina.app.service.dataprovider.entity.TransactionEntity;
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

    @Override
    public List<TransactionDomain> getAccountStatement(final Integer accountId, final LocalDateTime startDate, final LocalDateTime endDate) {
        return transactionMapper.toDto(transactionRepository.findAllByAccountOriginIdAndCreatedAtBetween(accountId, startDate, endDate));
    }

    @Override
    public List<TransactionDomain> getAccountStatementByValue(final Integer accountId, final BigDecimal value) {
        return transactionMapper.toDto(transactionRepository.findAllByAccountOriginIdAndValue(accountId, value));
    }

    @Override
    public TransactionDomain makeTransaction(TransactionDomain transactionDomain, AccountDomain credidAccount, AccountDomain debitAccount) {
        transactionDomain.setAccountOrigin(credidAccount.getAccountInfo());
        transactionDomain.setAccountDestination(debitAccount.getAccountInfo());

        TransactionEntity entity = transactionMapper.toEntity(transactionDomain);
        transactionRepository.save(entity);

        return transactionMapper.toDto(entity);
    }

}
