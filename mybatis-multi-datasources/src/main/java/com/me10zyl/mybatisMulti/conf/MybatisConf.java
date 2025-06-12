package com.me10zyl.mybatisMulti.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.*;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.me10zyl.mybatisMulti.dao.account", sqlSessionFactoryRef = "accountSqlSessionFactory")
public class MybatisConf {
    @Bean(name = "accountDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.account")
    public DataSource accountDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(@Qualifier("accountDataSource") DataSource dataSource){
        return new JdbcTransactionManager(dataSource);
    }

    @Bean(name = "accountSqlSessionFactory")
    @Primary
    public SqlSessionFactoryBean accountSqlSessionFactory(@Qualifier("accountDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean;
    }

}
