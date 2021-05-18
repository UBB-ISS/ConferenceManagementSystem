package com.iss.cms.web.controller;

import com.iss.cms.core.domain.Conference;
import com.iss.cms.core.service.ConferenceService;
import com.iss.cms.web.converter.ConferenceConverter;
import com.iss.cms.web.dto.ConferencesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConferenceController {
    private static final Logger logger = LoggerFactory.getLogger(ConferenceController.class);

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ConferenceConverter conferenceConverter;

    @RequestMapping(value="/conferences")
    ConferencesDTO getAllConferences() {
        logger.trace("ConferenceController - getAllConferences(): method entered");
        List<Conference> conferences = conferenceService.getAllConferences();
        ConferencesDTO conferencesDTO = new ConferencesDTO(conferenceConverter.convertModelsToDTOs(conferences));
        logger.trace("ConferenceController - getAllConferences(): method finished -> " + conferencesDTO.toString());
        return conferencesDTO;
    }
}
