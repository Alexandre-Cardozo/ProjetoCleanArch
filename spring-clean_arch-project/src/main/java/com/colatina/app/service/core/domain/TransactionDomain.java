package com.colatina.app.service.core.domain;

import com.colatina.app.service.core.domain.enumeration.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDomain {

    private Integer id;

    @NotNull
    private AccountInfoDomain accountOrigin;

    @NotNull
    private AccountInfoDomain accountDestination;

    @NotNull
    private BigDecimal value;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private TransactionStatus status;

    @NotNull
    private String type;

}
