package com.example.bpofficesservice.controller;

import com.example.bpofficesservice.BPAccountProxy;
import com.example.bpofficesservice.model.BPAccount;
import com.example.bpofficesservice.model.BPOffice;
import com.example.bpofficesservice.repository.BPOfficeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class BPOfficeController {

    @Autowired
    private BPOfficeRepository bpOfficeRepository;

    @Autowired
    private BPAccountProxy proxy;

    @Autowired
    private Environment environment;

    @GetMapping("/offices")
    public List<BPOffice> getOffices() {
        return bpOfficeRepository.findAll();
    }

    @GetMapping("/offices/brokerageId={brokerageId}/regionId={regionId}")
    public List<BPOffice> getOfficesByBrokerageAndRegion(@PathVariable Long brokerageId, @PathVariable Long regionId) {
        return bpOfficeRepository.findBPOfficeByBrokerageIdAndRegionId(brokerageId, regionId).stream()
                .map(office -> {
                    String port = environment.getProperty("local.server.port");
                    office.setEnvironment(port);
                    return office;
                }).collect(Collectors.toList());    }

    /////Using Rest Template
    @GetMapping("/accounts/brokerageId={brokerageId}/officeId={officeId}")
    public List<BPAccount> getAccountByBrokerageAndOffice(@PathVariable Long brokerageId, @PathVariable Long officeId) {

        HashMap<String, Long> map = new HashMap<>();
        map.put("brokerageId", brokerageId);
        map.put("officeId", officeId);
        BPAccount[] accounts = new RestTemplate().getForObject("http://localhost:8300/accounts/brokerageId={brokerageId}/officeId={officeId}", BPAccount[].class, map);
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(accounts).map(account -> mapper.convertValue(account, BPAccount.class)).collect(
                Collectors.toList());
    }

    /////Using Fiegn Client
    @GetMapping("/feign/accounts/brokerageId={brokerageId}/officeId={officeId}")
    public List<BPAccount> getAccountByBrokerageAndOfficeFeign(@PathVariable Long brokerageId, @PathVariable Long officeId) {

        List<BPAccount> accounts = proxy.getAccountsBySearch(brokerageId, officeId);
        return accounts;
    }
}
