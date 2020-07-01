package com.umfb.web.config;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.rpc.Exporter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * Created by zcah on 18-5-25.
 */

@Configuration
@ConditionalOnClass(Exporter.class)
@EnableConfigurationProperties({ DubboProperties.class })
public class DubboAutoConfiguration {
    public  static  final Logger log = LoggerFactory.getLogger(DubboAutoConfiguration.class);

    private final DubboProperties dubboProperties;

    @Autowired
    public DubboAutoConfiguration(DubboProperties dubboProperties) {
        this.dubboProperties = dubboProperties;
    }

    @Bean
    public static AnnotationBean annotationBean(@Value("${dubbo.annotation.package}") String packageName) {
        AnnotationBean annotationBean = new AnnotationBean();
        if (StringUtils.isNotBlank(packageName)) {
            annotationBean.setPackage(packageName);
            log.info("[DubboAutoConfiguration.annotationBean] " + packageName);
        }
        return annotationBean;
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        log.info("[DubboAutoConfiguration.applicationConfig]" + dubboProperties.getApplication());
        return dubboProperties.getApplication();
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        log.info("[DubboAutoConfiguration.protocolConfig]" + dubboProperties.getProtocol());
        return dubboProperties.getProtocol();
    }

    @Bean
    public ProviderConfig providerConfig(ApplicationConfig applicationConfig, RegistryConfig registryConfig,
                                         ProtocolConfig protocolConfig) {
        ProviderConfig providerConfig = dubboProperties.getProvider();
        providerConfig.setApplication(applicationConfig);
        providerConfig.setRegistry(registryConfig);
        providerConfig.setProtocol(protocolConfig);

        log.info("[DubboAutoConfiguration.providerConfig] " + providerConfig);
        return providerConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        log.info("[DubboAutoConfiguration.registryConfig] " + dubboProperties.getRegistry());
        return dubboProperties.getRegistry();
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        log.info("[DubboAutoConfiguration.consumerConfig] " + dubboProperties.getConsumer());
        return dubboProperties.getConsumer();
    }

    @Bean
    public ModuleConfig moduleConfig(ApplicationConfig applicationConfig) {
        ModuleConfig moduleConfig = dubboProperties.getModule();
        if (null == moduleConfig.getName()) {
            moduleConfig.setName(applicationConfig.getName());
        }
        log.info("[DubboAutoConfiguration.moduleConfig] " + moduleConfig);
        return moduleConfig;
    }

    @Bean
    public MonitorConfig monitorConfig() {
        log.info("[DubboAutoConfiguration.monitorConfig] " + dubboProperties.getMonitor());
        return dubboProperties.getMonitor();
    }

    @PreDestroy
    public void shutdownDestroy() {
        ProtocolConfig.destroyAll();
    }
}
