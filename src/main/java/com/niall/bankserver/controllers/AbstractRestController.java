package com.niall.bankserver.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRestController {
    @Autowired
    protected ModelMapper mapper;
}
