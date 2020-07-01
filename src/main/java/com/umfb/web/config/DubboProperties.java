package com.umfb.web.config;

import com.alibaba.dubbo.config.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by zcah on 18-5-25.
 */
@ConfigurationProperties(prefix = "dubbo")
public class DubboProperties {
    protected static Logger log = LoggerFactory.getLogger(DubboProperties.class);
    /**
     * 应用配置，用于配置当前应用信息，不管该应用是提供者还是消费者。
     */
    protected ApplicationConfig application;
    /**
     * 注册中心配置，用于配置连接注册中心相关信息。
     */
    protected RegistryConfig registry;
    /**
     * 协议配置，用于配置提供服务的协议信息，协议由提供方指定，消费方被动接受。
     */
    protected ProtocolConfig protocol;
    /**
     * 提供方的缺省值，当ProtocolConfig和ServiceConfig某属性没有配置时，采用此缺省值，可选。
     */
    protected ProviderConfig provider;
    /**
     * 模块配置，用于配置当前模块信息，可选。
     */
    protected ModuleConfig module;

    protected ConsumerConfig consumer;
    /**
     * 监控中心配置，用于配置连接监控中心相关信息，可选。
     */
    protected MonitorConfig monitor;

    public DubboProperties() {
        log.info("-----初始化dubbo默认配置信息------");
        protocol = new ProtocolConfig("dubbo");
        protocol.setSerialization("hessian2");
        registry = new RegistryConfig("${dubbo.registry.address}");
        // registry.setCheck(false);
        consumer = new ConsumerConfig();
        provider = new ProviderConfig();
        module = new ModuleConfig();
        monitor = new MonitorConfig();
    }

    public ApplicationConfig getApplication() {
        return application;
    }

    public void setApplication(ApplicationConfig application) {
        this.application = application;
    }

    public RegistryConfig getRegistry() {
        return registry;
    }

    public void setRegistry(RegistryConfig registry) {
        this.registry = registry;
    }

    public ProtocolConfig getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolConfig protocol) {
        this.protocol = protocol;
    }

    public ProviderConfig getProvider() {
        return provider;
    }

    public void setProvider(ProviderConfig provider) {
        this.provider = provider;
    }

    public ModuleConfig getModule() {
        return module;
    }

    public void setModule(ModuleConfig module) {
        this.module = module;
    }

    public MonitorConfig getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorConfig monitor) {
        this.monitor = monitor;
    }

    public ConsumerConfig getConsumer() {
        return consumer;
    }

    public void setConsumer(ConsumerConfig consumer) {
        this.consumer = consumer;
    }
}
