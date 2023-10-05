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

    public void accountChangeStatus(Integer account_id, AccountStatus status){
        try {
            accountGateway.changeStatus(account_id, status);
        }catch (Exception e){
            throw new BusinessException("Error to change account status");
        }
    }
}
