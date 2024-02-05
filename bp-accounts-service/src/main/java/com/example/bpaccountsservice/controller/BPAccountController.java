package com.example.bpaccountsservice.controller;

import com.example.bpaccountsservice.model.BPAccount;
import com.example.bpaccountsservice.repository.BPAccountRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BPAccountController {

    @Autowired
    private BPAccountRepository bpAccountRepository;

    @Autowired
    private Environment environment;

    @GetMapping("/accounts")
    public List<BPAccount> getAccounts(){
        return bpAccountRepository.findBPAccountByActiveInd(true);
    }

    @GetMapping("/accounts/brokerageId={brokerageId}/officeId={officeId}")
    public List<BPAccount> getAccountsBySearch(@PathVariable Long brokerageId, @PathVariable Long officeId){
        return bpAccountRepository.findBPAccountByBrokerageIdAndOwnerOfficeId(brokerageId, officeId).stream()
                .map(account -> {
                    String port = environment.getProperty("local.server.port");
                    account.setEnvironment(port);
                    return account;
                }).collect(Collectors.toList());
    }
}
