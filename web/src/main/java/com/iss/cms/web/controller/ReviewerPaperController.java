package com.iss.cms.web.controller;

import com.iss.cms.core.domain.ReviewerPaper;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.service.IReviewerPaperService;
import com.iss.cms.web.converter.ReviewerPaperConverter;
import com.iss.cms.web.dto.ReviewerPaperDTO;
import com.iss.cms.web.dto.ReviewerPapersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewerPaperController {
    private static final Logger logger = LoggerFactory.getLogger(ReviewerPaperController.class);

    @Autowired
    private IReviewerPaperService reviewerPaperService;

    @Autowired
    private ReviewerPaperConverter reviewerPaperConverter;

    @RequestMapping(value="/availabilities")
    public ReviewerPapersDTO getAll() {
        logger.trace("ReviewerPaperController - getAll: method entered");

        List<ReviewerPaper> all = reviewerPaperService.getAll();
        ReviewerPapersDTO allDTO = new ReviewerPapersDTO(reviewerPaperConverter.convertModelsToDTOs(all));

        logger.trace("ReviewerPaperController - getAll: method finished");
        return allDTO;
    }

    @RequestMapping(value="/allAvailabilitiesFromAGivenConference/{conferenceId}")
    public ReviewerPapersDTO getAllFromAGivenConference(@PathVariable int conferenceId) {
        logger.trace("ReviewerPaperController - getAllFromAGivenConference: method entered");

        List<ReviewerPaper> all = reviewerPaperService.getAllFromAGivenConference(conferenceId);
        ReviewerPapersDTO allDTO = new ReviewerPapersDTO(reviewerPaperConverter.convertModelsToDTOs(all));

        logger.trace("ReviewerPaperController - getAllFromAGivenConference: method finished");
        return allDTO;
    }

    @RequestMapping(value="/availabilities", method = RequestMethod.POST)
    public void addAvailability(@RequestBody ReviewerPaperDTO reviewerPaperDTO) throws CMSException {
        logger.trace("ReviewerPaperController - addAvailability: method entered");

        System.out.println("controller: " + reviewerPaperDTO.toString());
        ReviewerPaper reviewerPaper = reviewerPaperConverter.convertDTOToModel(reviewerPaperDTO);
        System.out.println("controller: " + reviewerPaper.toString());
        reviewerPaperService.addAvailability(reviewerPaper.getUserId(), reviewerPaper.getPaperId(), reviewerPaper.isAssigned(),
                reviewerPaper.getAvailability());

        logger.trace("ReviewerPaperController - addAvailability: method finished");
    }

    @RequestMapping(value="/availabilities", method = RequestMethod.PUT)
    public void changeStatus(@RequestBody ReviewerPaperDTO reviewerPaperDTO) throws CMSException {
        logger.trace("ReviewerPaperController - changeStatus: method entered");
        reviewerPaperService.changeStatus(reviewerPaperDTO.getId());
        logger.trace("ReviewerPaperController - changeStatus: method finished");
    }

    @RequestMapping(value="/availabilityByUserIdAndPaperId/{userId}/{paperId}")
    public ReviewerPaperDTO findAvailabilityByUserIdAndPaperId(@PathVariable int userId, @PathVariable int paperId) {
        logger.trace("ReviewerPaperController - findAvailabilityByUserIdAndPaperId: method entered");

        ReviewerPaper reviewerPaper = reviewerPaperService.findByUserIdAndPaperId(userId, paperId);
        ReviewerPaperDTO reviewerPaperDTO;
        if(reviewerPaper == null) reviewerPaperDTO = null;
        else reviewerPaperDTO = reviewerPaperConverter.convertModelToDTO(reviewerPaper);

        logger.trace("ReviewerPaperController - findAvailabilityByUserIdAndPaperId: method finished");
        return reviewerPaperDTO;
    }
}
