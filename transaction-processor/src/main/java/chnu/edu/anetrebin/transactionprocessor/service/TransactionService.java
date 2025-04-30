package chnu.edu.anetrebin.transactionprocessor.service;

import chnu.edu.anetrebin.transactionprocessor.dto.request.TransactionRequest;
import chnu.edu.anetrebin.transactionprocessor.model.Transaction;

import java.util.List;

public interface TransactionService {
    void createTransaction(TransactionRequest transactionRequest);

    List<Transaction> getAllTransactions();
}
