package com.colatina.app.service.core.usecase;

import com.colatina.app.service.core.domain.TransactionDomain;
import com.colatina.app.service.core.gateway.TransactionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAccountStatementByValue {

    private final TransactionGateway transactionGateway;

    public List<TransactionDomain> getAccountStatementByValue(Integer accountId, BigDecimal value) {
        return transactionGateway.getAccountStatementByValue(accountId, value);
    }
}
