package com.colatina.app.service.entrypoint.api;

import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.enumeration.AccountStatus;
import com.colatina.app.service.core.usecase.ChangeStatusUseCase;
import com.colatina.app.service.core.usecase.CreateAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;

    private final ChangeStatusUseCase changeStatusUseCase;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid AccountDomain account) {
        createAccountUseCase.execute(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/account-change-status/{account_id}")
    public ResponseEntity<Void> changeStatus(@RequestBody AccountStatus status, @PathVariable(name = "account_id") Integer account_id){
        changeStatusUseCase.accountChangeStatus(account_id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
