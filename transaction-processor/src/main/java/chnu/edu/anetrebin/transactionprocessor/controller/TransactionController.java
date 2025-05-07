package chnu.edu.anetrebin.transactionprocessor.controller;

import chnu.edu.anetrebin.transactionprocessor.dto.request.TransactionRequest;
import chnu.edu.anetrebin.transactionprocessor.kafka.messages.TransactionMessage;
import chnu.edu.anetrebin.transactionprocessor.model.Transaction;
import chnu.edu.anetrebin.transactionprocessor.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/")
    public ResponseEntity<Void> createTransaction(@RequestBody @Valid TransactionRequest transactionRequest) {
        transactionService.createTransaction(transactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<Void> sendMessage(@RequestBody String message) {
        transactionService.sendMessage(message);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/sendObject")
    public ResponseEntity<Void> sendObject(@RequestBody @Valid TransactionMessage transactionMessage) {
        transactionService.sendObject(transactionMessage);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
