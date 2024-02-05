package com.example.bpregionsservice.repository;

import com.example.bpregionsservice.model.BPRegion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BPRegionRepository extends JpaRepository<BPRegion, Long> {

    public List<BPRegion> findBpRegionByBrokerageId(Long brokerageid);
}
