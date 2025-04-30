package chnu.edu.anetrebin.anb.controller;

import chnu.edu.anetrebin.anb.dto.requests.AccountRequest;
import chnu.edu.anetrebin.anb.dto.responses.AccountResponse;
import chnu.edu.anetrebin.anb.service.account.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Validated
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountRequest accountRequest) {
        return ResponseEntity.ok(accountService.updateAccount(id, accountRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/addBalance/{id}")
    public ResponseEntity<Void> addBalance(@PathVariable Long id, @Valid @RequestBody BigDecimal request) {
        accountService.addBalance(id, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/subtractBalance/{id}")
    public ResponseEntity<Void> subtractBalance(@PathVariable Long id, @Valid @RequestBody BigDecimal request) {
        accountService.subtractBalance(id, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
