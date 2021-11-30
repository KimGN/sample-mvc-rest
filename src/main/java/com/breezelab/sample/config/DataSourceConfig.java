package com.breezelab.sample.config;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
//        spring.datasource 연결이 되지 않는다. 바로 값을 집어 넣자;



    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() {
        System.out.println("--------------------------- DataSourceBuilder --------------------------------");
        System.out.println(DataSourceBuilder.create().build());
        System.out.println("------------------------------------------------------------------------------");
        return DataSourceBuilder.create().build();
    }
//
//    @Bean
//    public DataSource dataSource(){
//        // 러닝할때 DataSource를 거치치 않고 넘어가나...???? println 잘나옴;;
//        return DataSourceBuilder
//                .create()
//                .username("root")
//                .password("141201aaaaa")
//                .url("jdbc:mysql://127.0.0.1:3306/remoto?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false")
//                .driverClassName("com.mysql.cj.jdbc.Driver")
//                .build();
//    }

    //    hikari 로 잡아보자
//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource.hikari")
//    public HikariConfig hikariConfig() {
//        return new HikariConfig();
//    }

}
