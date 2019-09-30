package com.louis.mango.admin.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * mybatis扫描dao
 * Created by liyue
 * Time 2019-09-02 11:09
 */
@Configuration
@MapperScan("com.louis.mango.**.dao")
public class MybatisConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        //扫描model
        sessionFactoryBean.setTypeAliasesPackage("com.louis.mango.**.model");
        PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
        //扫描映射文件
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:**/sqlmap/*.xml"));

        return sessionFactoryBean.getObject();
    }
}
