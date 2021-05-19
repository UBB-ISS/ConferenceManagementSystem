package com.iss.cms.web.controller;

import com.iss.cms.core.domain.Paper;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.service.IPaperService;
import com.iss.cms.web.converter.PaperConverter;
import com.iss.cms.web.dto.PaperDTO;
import com.iss.cms.web.dto.PapersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaperController {
    private static final Logger logger = LoggerFactory.getLogger(PaperController.class);

    @Autowired
    private PaperConverter paperConverter;

    @Autowired
    private IPaperService paperService;

    @GetMapping("/papers")
    public PapersDTO getPapers(){
        return new PapersDTO(paperConverter.convertModelsToDTOs(paperService.getPapers()));
    }

    @GetMapping("/papers/{id}")
    public PaperDTO findPaperById(@PathVariable("id") int id){
        return paperConverter.convertModelToDTO(paperService.findPaperById(id));
    }

    @PostMapping("/papers")
    public void addPaper(@RequestBody PaperDTO paperDTO){
        Paper paper = paperConverter.convertDTOToModel(paperDTO);
        try{
            paperService.addPaper(
                    paper.getUserConferenceId(),
                    paper.getTitle(),
                    paper.getKeywords(),
                    paper.getPaperText(),
                    paper.getAbstractText(),
                    paper.isFinalized(),
                    paper.isAccepted()
            );
        }
        catch (CMSException e)
        {
            logger.trace(e.toString());
        }
    }
}
