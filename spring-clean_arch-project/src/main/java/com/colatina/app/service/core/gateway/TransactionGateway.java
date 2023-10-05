package com.colatina.app.service.core.gateway;

import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.TransactionDomain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionGateway {

    List<TransactionDomain> getAccountStatement(Integer accountId, LocalDateTime startDate, LocalDateTime endDate);

    List<TransactionDomain> getAccountStatementByValue(Integer accountId, BigDecimal value);

    TransactionDomain create(AccountDomain creditAccount, AccountDomain debitAccount, BigDecimal value);

    TransactionDomain persistStatus(TransactionDomain transactionDomain);
}
