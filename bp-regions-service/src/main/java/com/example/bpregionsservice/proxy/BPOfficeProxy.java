package com.example.bpregionsservice.proxy;


import com.example.bpregionsservice.model.BPOffice;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="bp-offices"/*, url="localhost:8200"*/)
public interface BPOfficeProxy {

    @GetMapping("/offices/brokerageId={brokerageId}/regionId={regionId}")
    public List<BPOffice> getOfficesByBrokerageAndRegion(@PathVariable Long brokerageId, @PathVariable Long regionId);
}
