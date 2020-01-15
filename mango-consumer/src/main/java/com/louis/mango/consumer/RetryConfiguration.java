package com.louis.mango.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

/**
 * 客户端需要在 config server 无响应时进行重试，以给 config server 时间进行恢复。利用 spring 提供的重试组件，配置重试机制。RetryConfiguration类实现细粒度控制重试机制。
 * 作用：自定义重试拦截器解决异常（consul作为注册中心时）：NoSuchBeanDefinitionException:No bean named 'configServerRetryInterceptor' available
 *
 * Created by liyue
 * Time 2019-09-27 14:51
 */
public class RetryConfiguration {

    private static Logger log = LoggerFactory.getLogger(RetryConfiguration.class);

    //@ConditionalOnMissingBean:当 BeanFactory 中没有名为 configServerRetryInterceptor 的 bean 时才匹配此 Bean，对应到bean名称，此为细粒度控制
    @ConditionalOnMissingBean(name = "configServerRetryInterceptor")
    @Bean
    public RetryOperationsInterceptor configServerRetryInterceptor(){

        log.info(String.format(
                "configServerRetryInterceptor: Changing backOffOptions " +
                        "to initial: %s, multiplier: %s, maxInterval: %s",
                1000, 1.2, 5000));
        return RetryInterceptorBuilder.stateless()
                //#最初重试间隔为 1000 毫秒 #每次重试失败后，重试间隔所增加的倍数 #最长重试间隔为 5000 毫秒
                .backOffOptions(1000,1.2,5000)
                //#最多重试 10 次
                .maxAttempts(10)
                .build();
    }
}
