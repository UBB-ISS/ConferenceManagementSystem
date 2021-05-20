package com.iss.cms.web.controller;

import com.iss.cms.core.domain.PaperSection;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.service.PaperSectionService;
import com.iss.cms.web.converter.PaperSectionConverter;
import com.iss.cms.web.dto.PaperSectionDTO;
import com.iss.cms.web.dto.PaperSectionsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaperSectionController {
    private static final Logger logger = LoggerFactory.getLogger(PaperSectionController.class);

    @Autowired
    private PaperSectionConverter paperSectionConverter;

    @Autowired
    private PaperSectionService paperSectionService;

    @GetMapping("/paperSections")
    public PaperSectionsDTO getPaperSections() {
        return new PaperSectionsDTO(paperSectionConverter.convertModelsToDTOs(paperSectionService.getPaperSections()));
    }

    @GetMapping("/paperSections/{id}")
    public PaperSectionDTO findPaperSectionByID(@PathVariable("id") int id) {
        return paperSectionConverter.convertModelToDTO(paperSectionService.findPaperSectionByID(id));
    }

    @PutMapping("/paperSections")
    public void addPaperSection(@RequestBody PaperSectionDTO paperSectionDTO) {
        PaperSection paperSection = paperSectionConverter.convertDTOToModel(paperSectionDTO);
        try {
            paperSectionService.addPaperSection(
                    paperSection.getPaperId(),
                    paperSection.getSectionId()
            );
        }
        catch (CMSException e){
            logger.trace(e.toString());
        }
    }
}
