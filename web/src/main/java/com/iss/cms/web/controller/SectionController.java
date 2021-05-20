package com.iss.cms.web.controller;

import com.iss.cms.core.domain.Section;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.service.SectionService;
import com.iss.cms.web.converter.SectionConverter;
import com.iss.cms.web.dto.SectionDTO;
import com.iss.cms.web.dto.SectionsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SectionController {
    private static final Logger logger = LoggerFactory.getLogger(SectionController.class);

    @Autowired
    private SectionConverter sectionConverter;

    @Autowired
    private SectionService sectionService;

    @GetMapping("/sections")
    public SectionsDTO getSections() {
        return new SectionsDTO(sectionConverter.convertModelsToDTOs(sectionService.getSections()));
    }

    @GetMapping("/sections/{id}")
    public SectionDTO findSectionByID(@PathVariable("id") int id) {
        return sectionConverter.convertModelToDTO(sectionService.findSectionByID(id));
    }

    @PutMapping("/sections")
    public void addSection(@RequestBody SectionDTO sectionDTO) {
        Section section = sectionConverter.convertDTOToModel(sectionDTO);
        try {
            sectionService.addSection(
                    section.getName(),
                    section.getConferenceID()
            );
        } catch (CMSException e) {
            logger.trace(e.toString());
        }
    }
}
