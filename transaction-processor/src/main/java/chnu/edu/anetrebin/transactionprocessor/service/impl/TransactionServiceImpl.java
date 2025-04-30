package chnu.edu.anetrebin.transactionprocessor.service.impl;

import chnu.edu.anetrebin.transactionprocessor.dto.request.external.CurrencyExchangeRequest;
import chnu.edu.anetrebin.transactionprocessor.dto.request.TransactionRequest;
import chnu.edu.anetrebin.transactionprocessor.dto.response.external.AccountResponse;
import chnu.edu.anetrebin.transactionprocessor.enums.TransactionStatus;
import chnu.edu.anetrebin.transactionprocessor.exceptions.TransactionException;
import chnu.edu.anetrebin.transactionprocessor.model.Transaction;
import chnu.edu.anetrebin.transactionprocessor.repository.TransactionRepository;
import chnu.edu.anetrebin.transactionprocessor.service.TransactionService;
import chnu.edu.anetrebin.transactionprocessor.service.external.AccountService;
import chnu.edu.anetrebin.transactionprocessor.service.external.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CurrencyExchangeService currencyExchangeService;
    private final AccountService accountService;

    @Transactional
    @Override
    public void createTransaction(TransactionRequest transactionRequest) {
        AccountResponse sender = accountService.getAccountById(transactionRequest.senderAccountId());

        AccountResponse receiver = accountService.getAccountById(transactionRequest.receiverAccountId());

        validateTransaction(sender, receiver, transactionRequest.amount());

        Transaction transaction = Transaction.builder()
                .senderAccountId(sender.id())
                .receiverAccountId(receiver.id())
                .amount(transactionRequest.amount())
                .description(transactionRequest.description())
                .build();

        transaction = transactionRepository.save(transaction);
        try {
            processTransaction(sender, receiver, transactionRequest.amount());

            transaction.setStatus(TransactionStatus.COMPLETED);
            transactionRepository.save(transaction);
        } catch (Exception e) {
            transaction.setStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);

            throw new TransactionException("Transaction processing failed: " + e.getMessage());
        }
    }

    private void validateTransaction(AccountResponse sender, AccountResponse receiver, BigDecimal amount) {
        if (!sender.status().isActive() || !receiver.status().isActive()) {
            throw new TransactionException("One of the accounts is not active");
        }

        if (sender.id().equals(receiver.id())) {
            throw new TransactionException("Sender and Receiver accounts are the same");
        }

        if (sender.balance().compareTo(amount) < 0) {
            throw new TransactionException("Insufficient funds");
        }
    }

    @Transactional
    protected void processTransaction(AccountResponse sender, AccountResponse receiver, BigDecimal amount) {
        BigDecimal newAmount = amount;
        if (!sender.currency().equals(receiver.currency())) {
            newAmount = currencyExchangeService.getExchangedValue(new CurrencyExchangeRequest(sender.currency(), receiver.currency(), newAmount));
        }

        accountService.subtractBalance(sender.id(), amount);
        accountService.addBalance(receiver.id(), newAmount);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
