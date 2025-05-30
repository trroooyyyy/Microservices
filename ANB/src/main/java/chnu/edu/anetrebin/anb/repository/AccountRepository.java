package chnu.edu.anetrebin.anb.repository;

import chnu.edu.anetrebin.anb.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByOrderByIdAsc();

    boolean existsByAccountNumber(String accountNumber);
}