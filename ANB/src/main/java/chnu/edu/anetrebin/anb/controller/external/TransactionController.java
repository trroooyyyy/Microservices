package chnu.edu.anetrebin.anb.controller.external;

import chnu.edu.anetrebin.anb.dto.requests.external.TransactionRequest;
import chnu.edu.anetrebin.anb.service.external.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
@Validated
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/")
    public ResponseEntity<Void> createTransaction(@RequestBody @Valid TransactionRequest transactionRequest) {
        transactionService.createTransaction(transactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
