package com.example.bpofficesservice;

import com.example.bpofficesservice.model.BPAccount;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="bp-accounts"/*, url="localhost:8300"*/)
public interface BPAccountProxy {

    @GetMapping("/accounts/brokerageId={brokerageId}/officeId={officeId}")
    public List<BPAccount> getAccountsBySearch(@PathVariable Long brokerageId, @PathVariable Long officeId);
}
