package com.colatina.app.service.core.usecase;

import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.exception.BusinessException;
import com.colatina.app.service.core.gateway.AccountGateway;
import com.colatina.app.service.core.gateway.NegativeCpfGateway;
import com.colatina.app.service.core.gateway.WalletGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAccountUseCase {

    private final AccountGateway accountGateway;

    private final WalletGateway walletGateway;

    private final NegativeCpfGateway negativeCpfGateway;

    public void execute(AccountDomain account) {
        if (account.isUnderAge()){
            throw new BusinessException("The user is under age, cannot create an account");
        }
        if (negativeCpfGateway.isNegativeCpf(account.getDocument())){
            throw new BusinessException("The user have the cpf negative");
        }

        try {
            AccountDomain NewerAccount = accountGateway.create(account);
            walletGateway.create(NewerAccount);
        }catch (Exception exception){
            throw new BusinessException("Error to create new account");
        }
    }
}
