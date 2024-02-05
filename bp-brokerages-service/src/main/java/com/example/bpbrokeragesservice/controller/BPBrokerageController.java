package com.example.bpbrokeragesservice.controller;

import com.example.bpbrokeragesservice.model.BPBrokerage;
import com.example.bpbrokeragesservice.model.BPRegion;
import com.example.bpbrokeragesservice.proxy.BPRegionProxy;
import com.example.bpbrokeragesservice.repository.BpBrokeragesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class BPBrokerageController {

    private Logger logger = LoggerFactory.getLogger(BPBrokerageController.class);

    @Autowired
    private BpBrokeragesRepository bpBrokeragesRepository;
    @Autowired
    private BPRegionProxy proxy;

    @GetMapping("/brokerages")
    public List<BPBrokerage> getBrokerages() {
        return bpBrokeragesRepository.findBPBrokeragesByActiveInd(1);
    }

    /////Using Rest Template
    @GetMapping("/regions/brokerageId={brokerageId}")
    //@Retry(name="default")
    //@Retry(name="bp-regions", fallbackMethod = "hardcodedResponse")
    @CircuitBreaker(name="default", fallbackMethod = "hardcodedResponse")
    //@RateLimiter(name="bp-regions")
    //@Bulkhead(name="bp-regions")
    public List<BPRegion> getRegionsByBrokerage(@PathVariable Long brokerageId){

        logger.info("Regions API call is received from Brokerage");

        BPRegion[] regions = new RestTemplate()
                .getForObject("http://localhost:8100/regions/brokerageId={brokerageId}", BPRegion[].class, brokerageId);

        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(regions)
                .map(region -> mapper.convertValue(region, BPRegion.class))
                .collect(Collectors.toList());
    }

    ////Using Fiegn Client
    @GetMapping("/feign/regions/brokerageId={brokerageId}")
    public List<BPRegion> getRegionsByBrokerageFeign(@PathVariable Long brokerageId){
        List<BPRegion> bpRegions = proxy.getRegionsByBrokerageId(brokerageId);
        return bpRegions;
    }

    public List<BPRegion> hardcodedResponse(Throwable exception) throws JSONException, JsonProcessingException {
        logger.info("Default fallback response");
        String regionsJson = "{\"regions\" : \r\n        [{\r\n            \"id\": 0001,\r\n                \"brokerageId\": 0001,\r\n                \"code\": null,\r\n                \"environment\": \"8100\",\r\n                \"name\": \"Dummy Region 1\"\r\n        },\r\n        {\r\n            \"id\": 0002,\r\n                \"brokerageId\": 0002,\r\n                \"code\": null,\r\n                \"environment\": \"8100\",\r\n                \"name\": \"Dummy Region 2\"\r\n        }]\r\n}";
        JSONObject jsonObject = new JSONObject(regionsJson);
        JSONArray jsonArray = jsonObject.getJSONArray("regions");
        if(jsonArray != null) {
            ObjectMapper mapper = new ObjectMapper();
            return Arrays.asList(mapper.readValue(jsonArray.toString(), BPRegion[].class));
        }
        return null;
    }
}
