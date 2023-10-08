package com.colatina.app.service.entrypoint.api;

import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.TransactionDomain;
import com.colatina.app.service.core.usecase.GetAccountStatementByValue;
import com.colatina.app.service.core.usecase.GetAccountStatementUseCase;
import com.colatina.app.service.core.usecase.GetAmountAccountStatementByPeriod;
import com.colatina.app.service.core.usecase.MakeTransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final GetAccountStatementUseCase getAccountStatementUseCase;

    private final GetAccountStatementByValue getAccountStatementByValue;

    private final GetAmountAccountStatementByPeriod getAmountAccountStatementByPeriod;

    private final MakeTransactionUseCase makeTransactionUseCase;

    @GetMapping("/account-statement/{account_id}")
    public ResponseEntity<List<TransactionDomain>> getAccountStatement(@PathVariable("account_id") Integer accountId,
                                                                        @RequestHeader("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startDate,
                                                                        @RequestHeader("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endDate) {
        final List<TransactionDomain> accountStatement = getAccountStatementUseCase.getAccountStatement(accountId, startDate, endDate);
        return new ResponseEntity<>(accountStatement, accountStatement.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/account-statement-by-value/{account_id}")
    public ResponseEntity<List<TransactionDomain>> getAccountStatementByValue(@PathVariable("account_id") Integer accountId,
                                                                       @RequestHeader("value")BigDecimal value) {
        final List<TransactionDomain> accountStatement = getAccountStatementByValue.getAccountStatementByValue(accountId, value);
        return new ResponseEntity<>(accountStatement, accountStatement.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/account-statement-amount/{account_id}")
    public ResponseEntity<BigDecimal> getAmountAccountStatementByPeriod(@PathVariable("account_id") Integer accountId,
                                                        @RequestHeader("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startDate,
                                                        @RequestHeader("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endDate) {
        return new ResponseEntity<>(getAmountAccountStatementByPeriod.getAmountAccountStatementByPeriod(accountId, startDate, endDate), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid TransactionDomain transaction) {
        makeTransactionUseCase.execute(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
