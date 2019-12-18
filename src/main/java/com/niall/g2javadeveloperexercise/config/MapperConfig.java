package com.niall.g2javadeveloperexercise.config;

import com.niall.g2javadeveloperexercise.dtos.TransactionDto;
import com.niall.g2javadeveloperexercise.entities.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }
}
