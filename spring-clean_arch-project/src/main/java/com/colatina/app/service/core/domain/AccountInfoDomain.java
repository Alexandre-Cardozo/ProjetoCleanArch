package com.colatina.app.service.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AccountInfoDomain {

    private Integer id;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 100)
    private String lastName;

    @NotNull
    private AccountDomain account;

}
