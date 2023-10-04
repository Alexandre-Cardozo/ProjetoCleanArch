package com.colatina.app.service.core.gateway;

import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.TransactionDomain;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionGateway {

    List<TransactionDomain> getAccountStatement(Integer accountId, LocalDateTime startDate, LocalDateTime endDate);

    TransactionDomain makeTransaction(TransactionDomain transactionDomain, AccountDomain creditAccount, AccountDomain debitAccount);
}
