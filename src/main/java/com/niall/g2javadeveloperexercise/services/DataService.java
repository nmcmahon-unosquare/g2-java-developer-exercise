package com.niall.g2javadeveloperexercise.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DataService {

    @Autowired
    protected ModelMapper mapper;

}
