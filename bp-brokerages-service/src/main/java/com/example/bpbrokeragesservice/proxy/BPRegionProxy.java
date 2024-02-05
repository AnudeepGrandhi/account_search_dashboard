package com.example.bpbrokeragesservice.proxy;

import com.example.bpbrokeragesservice.model.BPRegion;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="bp-regions"/*, url="localhost:8100"*/)
public interface BPRegionProxy {

    @GetMapping("/regions/brokerageId={brokerageId}")
    public List<BPRegion> getRegionsByBrokerageId(@PathVariable Long brokerageId);
}
