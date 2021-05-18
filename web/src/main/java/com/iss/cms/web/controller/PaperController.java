package com.iss.cms.web.controller;

import com.iss.cms.web.converter.PaperConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaperController {
    private static final Logger logger = LoggerFactory.getLogger(PaperController.class);

    @Autowired
    private PaperConverter paperConverter;

    @Autowired
    private IPaperService paperService;
}
