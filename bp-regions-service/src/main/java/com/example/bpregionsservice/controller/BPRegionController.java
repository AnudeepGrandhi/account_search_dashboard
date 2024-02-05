package com.example.bpregionsservice.controller;

import com.example.bpregionsservice.model.BPOffice;
import com.example.bpregionsservice.model.BPRegion;
import com.example.bpregionsservice.proxy.BPOfficeProxy;
import com.example.bpregionsservice.repository.BPRegionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class BPRegionController {

    @Autowired
    private BPRegionRepository bpRegionsRepository;

    @Autowired
    private BPOfficeProxy proxy;

    @Autowired
    private Environment environment;

    @GetMapping("/regions")
    public List<BPRegion> getRegions() {
        return bpRegionsRepository.findAll();
    }

    @GetMapping("/regions/brokerageId={brokerageId}")
    public List<BPRegion> getRegionsByBrokerageId(@PathVariable Long brokerageId) {

        List<BPRegion> regions = bpRegionsRepository.findBpRegionByBrokerageId(brokerageId).stream()
                .map(region -> {
                    String port = environment.getProperty("local.server.port");
                    region.setEnvironment(port);
                    return region;
                }).collect(Collectors.toList());
        return regions;
    }

    ////Using Rest Template
    @GetMapping("/offices/brokerageId={brokerageId}/regionId={regionId}")
    public List<BPOffice> getOfficesByBrokerageAndRegion(@PathVariable Long brokerageId, @PathVariable Long regionId){

        HashMap<String, Long> map = new HashMap<>();
        map.put("brokerageId", brokerageId);
        map.put("regionId", regionId);
        BPOffice[] offices = new RestTemplate()
                .getForObject("http://localhost:8200/offices/brokerageId={brokerageId}/regionId={regionId}", BPOffice[].class, map);
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(offices)
                .map(office -> mapper.convertValue(office, BPOffice.class))
                .collect(Collectors.toList());
    }

    ////Using Feign Client
    @GetMapping("/feign/offices/brokerageId={brokerageId}/regionId={regionId}")
    public List<BPOffice> getOfficesByBrokerageAndRegionFeign(@PathVariable Long brokerageId, @PathVariable Long regionId){

        List<BPOffice> offices = proxy.getOfficesByBrokerageAndRegion(brokerageId, regionId);
        return offices;
    }

}
