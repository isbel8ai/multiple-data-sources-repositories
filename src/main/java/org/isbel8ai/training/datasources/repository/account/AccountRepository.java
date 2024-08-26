package org.isbel8ai.training.datasources.repository.account;

import org.isbel8ai.training.datasources.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
