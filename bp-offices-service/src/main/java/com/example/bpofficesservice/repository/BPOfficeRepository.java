package com.example.bpofficesservice.repository;

import com.example.bpofficesservice.model.BPOffice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BPOfficeRepository extends JpaRepository<BPOffice, Long> {

    public List<BPOffice> findBPOfficeByBrokerageIdAndRegionId(Long brokerageId, Long regionId);
}
