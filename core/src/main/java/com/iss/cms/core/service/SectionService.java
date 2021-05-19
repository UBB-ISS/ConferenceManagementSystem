package com.iss.cms.core.service;

import com.iss.cms.core.domain.Conference;
import com.iss.cms.core.domain.Section;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.repository.SectionRepository;
import com.iss.cms.core.repository.ConferenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SectionService implements ISectionService{
    private static final Logger logger = LoggerFactory.getLogger(SectionService.class);

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    ConferenceRepository conferenceRepository;

    @Override
    public List<Section> getSections() {
        logger.trace("SectionService - getSections(): method entered");
        List<Section> sections = sectionRepository.findAll();
        logger.trace("SectionService - getSections(): method finished");
        return sections;
    }

    @Override
    public Section findSectionByID(int id) throws CMSException {
        logger.trace("SectionService - findSectionById(): method entered -> id");
        Optional<Section> section = sectionRepository.findById(id);
        if(section.isEmpty()) {
            logger.trace("SectionService: Section id does not exist!");
            throw new CMSException("Section id does not exist!");
        }
        logger.trace("SectionService - getSections(): method finished");
        return section.get();
    }

    public void addSection(String name, int conferenceId) throws CMSException {
        logger.trace("SectionService - addSection: method entered ->" + "name = " + name + ", conferenceId = " + conferenceId);

        Optional<Conference> conference = conferenceRepository.findById(conferenceId);
        if(conference.isEmpty()) {
            logger.trace("SectionService: Conference id does not exist!");
            throw new CMSException("Conference id does not exist!");
        }
        List<Section> sections = sectionRepository.findAll();
        for(Section section: sections) {
            if(section.getName().equals(name)){
                logger.trace("SectionService: Section name already taken!");
                throw new CMSException("Section name already taken!");
            }
        }

        Section section = new Section(name, conferenceId);
        System.out.println(section.toString());
        sectionRepository.save(section);

        logger.trace("SectionService - addSection: method finished");
    }
}
