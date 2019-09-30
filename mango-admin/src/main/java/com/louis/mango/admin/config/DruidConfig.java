//package com.louis.mango.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.http.StatViewServlet;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.web.server.ErrorPage;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.boot.web.servlet.support.ErrorPageFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.Filter;
//import javax.servlet.Servlet;
//import javax.sql.DataSource;
//
/**
 * druid的配置类，主要是注入属性和配置连接池相关的配置，如白名单、监控管理后台的账号和密码等
 * Created by liyue
 * Time 2019-09-16 9:13
 * 因新版druid配置方式有变，DruidConfig已弃用，配置在application.yml中实现
 */
//@Configuration
//public class DruidConfig {
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource druidDataSource(){
//        return new DruidDataSource();
//    }
//
//    //注册servlet信息，配置监控视图
//    @Bean
//    @ConditionalOnMissingBean
//    public ServletRegistrationBean<Servlet> druidServlet(){
//        ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
//        //白名单
//        servletServletRegistrationBean.addInitParameter("allow","127.0.0.1");
//        //黑名单（存在共同时，deny优于allow）
//        //如果满足deny的话提示：sorry,you are not permitted to view this page.
//        servletServletRegistrationBean.addInitParameter("deny","192.168.1.119");
//        //登陆查看信息的账号和密码，用于登陆Druid的监控后台
//        servletServletRegistrationBean.addInitParameter("loginUsername","admin");
//        servletServletRegistrationBean.addInitParameter("loginPassword","admin");
//        //时候能够重置数据
//        servletServletRegistrationBean.addInitParameter("resetEnable","true");
//        return servletServletRegistrationBean;
//    }
//
//    //注册filter信息，监控拦截器
//    @Bean
//    @ConditionalOnMissingBean
//    public FilterRegistrationBean<Filter> filterFilterRegistrationBean(){
//        FilterRegistrationBean<Filter> filterRegistrationBean=new FilterRegistrationBean<>();
//        //注册逻辑start
////        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
////        filterRegistrationBean.addUrlPatterns("/*");
////        String errUrl = "login.html";
////        filterRegistrationBean.addUrlPatterns("/*");
////        filterRegistrationBean.addInitParameter("ERR_URL", errUrl);
////        filterRegistrationBean.setName("WebAccessAuthorizeFilterMvc");
//        filterRegistrationBean.setFilter(errorPageFilter());
//        //注册逻辑end
//        return filterRegistrationBean;
//    }
//
//    @Bean
//    public Filter errorPageFilter(){
//        ErrorPageFilter errorPageFilter = new ErrorPageFilter();
//        ErrorPage notFind = new ErrorPage("/404");
//        errorPageFilter.addErrorPages(notFind);
//        return errorPageFilter;
//    }
//}
