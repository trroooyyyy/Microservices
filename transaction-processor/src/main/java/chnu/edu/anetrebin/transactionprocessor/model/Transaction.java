package chnu.edu.anetrebin.transactionprocessor.model;

import chnu.edu.anetrebin.transactionprocessor.enums.TransactionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_account_id", nullable = false)
    private Long senderAccountId;

    @Column(name = "receiver_account_id", nullable = false)
    private Long receiverAccountId;

    @Column(nullable = false, precision = 19, scale = 2)
    @DecimalMin("0.00")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private TransactionStatus status;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.status = TransactionStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }
}
