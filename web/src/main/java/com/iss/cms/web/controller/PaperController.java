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

import java.util.List;

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

    @RequestMapping(value="/papersOfAUserInAConference/{userId}/{conferenceId}")
    public PapersDTO getPapersOfAUserInAConference(@PathVariable int userId, @PathVariable int conferenceId) {
        logger.trace("PaperController - getPapersOfAUserInAConference(): method entered");

        List<Paper> papers = paperService.getPapersOfAUserInAConference(userId, conferenceId);
        PapersDTO papersDTO = new PapersDTO(paperConverter.convertModelsToDTOs(papers));

        logger.trace("PaperController - getPapersOfAUserInAConference(): method finished");
        return papersDTO;
    }

    @RequestMapping(value="/finalPapersFromAConference/{conferenceId}")
    public PapersDTO getFinalPapersFromAConference(@PathVariable int conferenceId) {
        logger.trace("PaperController - getFinalPapersFromAConference(): method entered");

        List<Paper> papers = paperService.getFinalPapersFromAConference(conferenceId);
        PapersDTO papersDTO = new PapersDTO(paperConverter.convertModelsToDTOs(papers));

        logger.trace("PaperController - getFinalPapersFromAConference(): method finished");
        return papersDTO;
    }

    @PostMapping("/papers/update")
    public void updatePaper(@RequestBody PaperDTO paperDTO){
        logger.info(paperDTO.toString());
        System.out.println(paperDTO.toString());
        Paper paper = paperConverter.convertDTOToModel(paperDTO);
        logger.info(paper.toString());
        paperService.updatePaper(
                    paper.getId(),
                    paper.getTitle(),
                    paper.getKeywords(),
                    paper.getPaperText(),
                    paper.getAbstractText(),
                    paper.isFinalized(),
                    paper.isAccepted()
            );
    }
}
