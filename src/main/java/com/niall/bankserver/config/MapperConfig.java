package com.niall.bankserver.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Converter<Double, BigDecimal> doubleToBigDecimal = context -> new BigDecimal(context.getSource());
        Converter<BigDecimal, Double> bigDecimalToDouble = context -> context.getSource() != null ? context.getSource().doubleValue() : null;
        mapper.addConverter(doubleToBigDecimal);
        mapper.addConverter(bigDecimalToDouble);
        return mapper;
    }
}
