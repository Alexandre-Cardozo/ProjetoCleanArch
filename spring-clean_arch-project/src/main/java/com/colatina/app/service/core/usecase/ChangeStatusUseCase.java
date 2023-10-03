package com.colatina.app.service.core.usecase;

import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.enumeration.AccountStatus;
import com.colatina.app.service.core.exception.BusinessException;
import com.colatina.app.service.core.gateway.AccountGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeStatusUseCase {

    private final AccountGateway accountGateway;

    public void execute(AccountDomain account, AccountStatus status){
        try {
            accountGateway.changeStatus(account, status);
        }catch (Exception e){
            throw new BusinessException("Error to change account status");
        }
    }
}
