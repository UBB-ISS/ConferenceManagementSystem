package com.iss.cms.core.service;

import com.iss.cms.core.domain.Conference;
import com.iss.cms.core.repository.ConferenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceService implements IConferenceService {
    private static final Logger logger = LoggerFactory.getLogger(ConferenceService.class);

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Override
    public List<Conference> getAllConferences() {
        logger.trace("ConferenceService - getAllConferences(): method entered");
        List<Conference> conferences = conferenceRepository.findAll();
        logger.trace("ConferenceService - getAllConferences(): method finished -> " + conferences.toString());
        return conferences;
    }
}
