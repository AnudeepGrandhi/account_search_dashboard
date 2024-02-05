package com.example.bpbrokeragesservice.repository;

import com.example.bpbrokeragesservice.model.BPBrokerage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BpBrokeragesRepository extends JpaRepository<BPBrokerage, Long> {

    List<BPBrokerage> findBPBrokeragesByActiveInd(int activeInd);
}
