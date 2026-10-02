package com.mpytc.navin.order;


import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("nacos/config")
@RequiredArgsConstructor
public class NacosConfigController {

    private final NacosConfigProps nacosConfigProps;

//    @Value("${service.name}")
//    private String serviceName;
//
//    @Value("${service.info}")
//    private String serviceInfo;
//
//    @Value("${service.version}")
//    private String serviceVersion;

    @GetMapping
    public Map<String, Object> getConfig() {
        return Map.of(
                "serviceName",nacosConfigProps.getName()
                ,"serviceInfo",nacosConfigProps.getBasicInfo()
                ,"serviceVersion",nacosConfigProps.getVersion()
        );
    }
}
