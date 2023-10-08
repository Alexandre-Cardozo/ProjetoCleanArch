package com.colatina.app.service.core.usecase;

import com.colatina.app.service.core.gateway.TransactionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GetAmountAccountStatementByPeriod {

    private final TransactionGateway transactionGateway;

    public BigDecimal getAmountAccountStatementByPeriod(Integer accountId, LocalDateTime startDate, LocalDateTime endDate){
        return transactionGateway.getAmountAccountStatementByPeriod(accountId, startDate, endDate);
    }
}
