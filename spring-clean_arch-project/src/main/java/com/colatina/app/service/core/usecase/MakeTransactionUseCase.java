package com.colatina.app.service.core.usecase;

import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.TransactionDomain;
import com.colatina.app.service.core.exception.BusinessException;
import com.colatina.app.service.core.gateway.TransactionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MakeTransactionUseCase {

    private final TransactionGateway transactionGateway;

    public TransactionDomain makeTransaction(TransactionDomain transactionDomain, AccountDomain creditAccount, AccountDomain debitAccount){
        if(!creditAccount.isAccountActive()){
            throw new BusinessException("The crediting account isn't active");
        }

        if(!debitAccount.isAccountActive()){
            throw new BusinessException("The debiting account isn't active");
        }

        //if(walletGateway.getAccountBalance(creditAccount.getId()) != 0)
        if(creditAccount.getWallet().getBalance().compareTo(BigDecimal.ZERO) > 0){
            return transactionGateway.makeTransaction(transactionDomain, creditAccount, debitAccount);
        }
        throw new BusinessException("The credit account balance must contain a balance");
    }
}
