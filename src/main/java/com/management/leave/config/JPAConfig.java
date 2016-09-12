//package com.management.leave.config;
//
//import org.apache.tomcat.jdbc.pool.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
///**
// * Created by Medion on 2016-09-12.
// */
//@Configuration
//@EnableJpaRepositories
//public class JPAConfig {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
//        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
//        lef.setDataSource(dataSource);
//        lef.setJpaVendorAdapter(jpaVendorAdapter);
//        lef.setPackagesToScan("hello");
//        return lef;
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
//        hibernateJpaVendorAdapter.setShowSql(false);
//        hibernateJpaVendorAdapter.setGenerateDdl(true);
//        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
//        return hibernateJpaVendorAdapter;
//    }
//}
