package com.niall.g2javadeveloperexercise.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRestController {
    @Autowired
    protected ModelMapper mapper;
}
