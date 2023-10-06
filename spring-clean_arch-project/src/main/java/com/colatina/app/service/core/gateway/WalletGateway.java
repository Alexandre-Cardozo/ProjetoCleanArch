package com.colatina.app.service.core.gateway;

import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.WalletDomain;

import java.math.BigDecimal;

public interface WalletGateway {

    WalletDomain create(AccountDomain account);

    BigDecimal getAccountBalance(Integer accountId);

}
