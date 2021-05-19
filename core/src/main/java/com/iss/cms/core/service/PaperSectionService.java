package com.iss.cms.core.service;

import com.iss.cms.core.domain.Section;
import com.iss.cms.core.domain.Paper;
import com.iss.cms.core.domain.PaperSection;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.repository.PaperRepository;
import com.iss.cms.core.repository.PaperSectionRepository;
import com.iss.cms.core.repository.SectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaperSectionService implements IPaperSectionService{
    private static final Logger logger = LoggerFactory.getLogger(PaperSectionService.class);
    @Autowired
    PaperSectionRepository paperSectionRepository;

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Override
    public List<PaperSection> getPaperSections() {
        logger.trace("PaperSectionService - getPaperSections(): method entered");
        List<PaperSection> paperSections = paperSectionRepository.findAll();
        logger.trace("PaperSectionService - getPaperSections(): method finished");
        return paperSections;
    }

    @Override
    public PaperSection findPaperSectionByID(int id) {
        logger.trace("PaperSectionService - findPaperSectionById(): method entered");
        Optional<PaperSection> paperSection = paperSectionRepository.findById(id);
        if(paperSection.isEmpty()) {
            logger.trace("PaperSectionService: PaperSection id does not exist!");
            throw new CMSException("PaperSection id does not exist!");
        }
        logger.trace("PaperSectionService - findPaperSectionById(): method finished");
        return paperSection.get();
    }

    @Override
    public void addPaperSection(int paperId, int sectionId) {
        logger.trace("PaperSectionService - addPaperSection(): method entered ->" +
                "paperId = " + paperId + ", sectionId = " + sectionId);

        Optional<Section> section = sectionRepository.findById(sectionId);
        if(section.isEmpty()) {
            logger.trace("PaperSectionService: Section id does not exist!");
            throw new CMSException("Section id does not exist!");
        }
        Optional<Paper> paper = paperRepository.findById(paperId);
        if(paper.isEmpty()) {
            logger.trace("PaperSectionService: Paper id does not exist!");
            throw new CMSException("Paper id does not exist!");
        }

        PaperSection paperSection = new PaperSection(paperId, sectionId);
        System.out.println(paperSection.toString());
        paperSectionRepository.save(paperSection);

        logger.trace("PaperSectionService - addPaperSection(): method finished");
    }
}
