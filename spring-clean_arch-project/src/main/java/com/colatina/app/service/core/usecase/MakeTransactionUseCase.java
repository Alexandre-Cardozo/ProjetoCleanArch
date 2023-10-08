package com.colatina.app.service.core.usecase;

import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.TransactionDomain;
import com.colatina.app.service.core.domain.enumeration.TransactionStatus;
import com.colatina.app.service.core.exception.BusinessException;
import com.colatina.app.service.core.exception.InactiveAccountStatusException;
import com.colatina.app.service.core.gateway.AccountGateway;
import com.colatina.app.service.core.gateway.TransactionGateway;
import com.colatina.app.service.core.gateway.WalletGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MakeTransactionUseCase {

    private final TransactionGateway transactionGateway;

    private final AccountGateway accountGateway;

    private final WalletGateway walletGateway;

    public TransactionDomain execute(TransactionDomain transactionDomain){
        AccountDomain creditAccount = accountGateway.findById(transactionDomain.getAccountOrigin().getId());
        AccountDomain debitAccount = accountGateway.findById(transactionDomain.getAccountDestination().getId());

        if(!creditAccount.isAccountActive()){
            throw new InactiveAccountStatusException("crediting");
        }

        if(!debitAccount.isAccountActive()){
            throw new InactiveAccountStatusException("debiting");
        }

        BigDecimal creditingAccountBalance = walletGateway.getAccountBalance(creditAccount.getId());

        if(creditingAccountBalance.compareTo(transactionDomain.getValue()) <= 0){
            throw new BusinessException("The credit account balance has insufficient credit");
        }

        transactionGateway.create(creditAccount, debitAccount, transactionDomain.getValue());

        try {
            updateTransactionAndBalance(creditAccount, debitAccount, transactionDomain);
            return transactionGateway.persistStatus(transactionDomain);
        }catch (Exception e){
            transactionDomain.setStatus(TransactionStatus.REFUSED);
            throw new BusinessException("Can not process the transaction");
        }
    }

    private void updateTransactionAndBalance(AccountDomain creditAccount, AccountDomain debitAccount, TransactionDomain transactionDomain){
            creditAccount.getWallet().setBalance(creditAccount.getWallet().getBalance().subtract(transactionDomain.getValue()));
            creditAccount.getWallet().setBalance(debitAccount.getWallet().getBalance().add(transactionDomain.getValue()));
            transactionDomain.setStatus(TransactionStatus.PROCESSED);
    }
}
