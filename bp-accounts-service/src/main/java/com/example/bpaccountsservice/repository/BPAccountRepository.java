package com.example.bpaccountsservice.repository;

import com.example.bpaccountsservice.model.BPAccount;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BPAccountRepository extends JpaRepository<BPAccount, Long> {

    List<BPAccount> findBPAccountByBrokerageIdAndOwnerOfficeId(Long brokerageId, Long officeId);
    List<BPAccount> findBPAccountByActiveInd(boolean activeInd);
}
