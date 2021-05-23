package com.iss.cms.web.controller;

import com.iss.cms.core.domain.PaperReview;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.service.IPaperReviewService;
import com.iss.cms.web.converter.PaperReviewConverter;
import com.iss.cms.web.converter.UserConverter;
import com.iss.cms.web.dto.PaperReviewDTO;
import com.iss.cms.web.dto.PaperReviewsDTO;
import com.iss.cms.web.dto.UsersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaperReviewController {
    private static final Logger logger = LoggerFactory.getLogger(PaperReviewController.class);

    @Autowired
    private PaperReviewConverter paperReviewConverter;

    @Autowired
    private IPaperReviewService paperReviewService;


    @Autowired
    private UserConverter userConverter;

    @GetMapping("/paperReviews")
    public PaperReviewsDTO getPaperReviews() {
        return new PaperReviewsDTO(paperReviewConverter.convertModelsToDTOs(paperReviewService.getPaperReviews()));
    }

    @GetMapping("/reviewersForPaper/{id}")
    public UsersDTO getReviewersForPaper(@PathVariable("id") int id) {
        return new UsersDTO(userConverter.convertModelsToDTOs(paperReviewService.getReviewersForPaper(id)));
    }

    @GetMapping("/paperReviews/{id}")
    public PaperReviewDTO findPaperReviewByID(@PathVariable("id") int id) {
        return paperReviewConverter.convertModelToDTO(paperReviewService.findPaperReviewByID(id));
    }

    @PostMapping("/addPaperReview")
    public void addPaperReview(@RequestBody PaperReviewDTO paperReviewDTO) {
        PaperReview paperReview = paperReviewConverter.convertDTOToModel(paperReviewDTO);
        try {
            paperReviewService.addPaperReview(
                    paperReview.getReviewerId(),
                    paperReview.getPaperId(),
                    paperReview.getRecommendation(),
                    paperReview.getQualifier()
            );
        } catch (CMSException e) {
            System.out.println(e.getMessage());
            logger.trace(e.toString());
        }
    }

    @RequestMapping(value="/updatePaperReview", method = RequestMethod.PUT)
    public void updatePaperReview(@RequestBody PaperReviewDTO paperReviewDTO) {
        PaperReview paperReview = paperReviewConverter.convertDTOToModel(paperReviewDTO);
        paperReviewService.updatePaperReview(paperReview.getId(), paperReview.getReviewerId(), paperReview.getPaperId(),
                paperReview.getRecommendation(), paperReview.getQualifier());
    }

    @RequestMapping(value="/findPaperReviewByReviewerIdAndPaperId/{reviewerId}/{paperId}")
    public PaperReviewDTO findPaperReviewByReviewerIdAndPaperId(@PathVariable int reviewerId, @PathVariable int paperId) {
        PaperReview paperReview = paperReviewService.findPaperReviewByReviewerIdAndPaperId(reviewerId, paperId);

        PaperReviewDTO paperReviewDTO;
        if(paperReview == null) paperReviewDTO = null;
        else
            paperReviewDTO = paperReviewConverter.convertModelToDTO(paperReview);

        return paperReviewDTO;
    }
}
