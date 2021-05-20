package com.iss.cms.web.controller;

import com.iss.cms.core.domain.Conference;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.service.ConferenceService;
import com.iss.cms.core.service.IConferenceService;
import com.iss.cms.web.converter.ConferenceConverter;
import com.iss.cms.web.dto.ConferenceDTO;
import com.iss.cms.web.dto.ConferencesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConferenceController {
    private static final Logger logger = LoggerFactory.getLogger(ConferenceController.class);

    @Autowired
    private IConferenceService conferenceService;

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

    @RequestMapping(value="/conference/{id}")
    public ConferenceDTO findConferenceById(@PathVariable("id") int id){
        return conferenceConverter.convertModelToDTO(conferenceService.findConferenceById(id));
    }

    @PostMapping(value="/conferences")
    public void addConference(@RequestBody ConferenceDTO conferenceDTO){
        Conference conference = conferenceConverter.convertDTOToModel(conferenceDTO);
        try{
            conferenceService.addConference(
                    conference.getName(),
                    conference.getDate(),
                    conference.getEntryFee(),
                    conference.getBiddingPhaseDeadline(),
                    conference.getSubmitPaperDeadline(),
                    conference.getReviewPaperDeadline()
            );
        }catch (CMSException e){
            logger.trace(conference.toString());
        }
    }

    @RequestMapping(value="/changeDeadlines", method = RequestMethod.PUT)
    public void changeDeadlines(@RequestBody ConferenceDTO conferenceDTO) throws CMSException {
        Conference conference = conferenceConverter.convertDTOToModel(conferenceDTO);
        conferenceService.changeDeadlines(conference.getId(), conference.getBiddingPhaseDeadline(),
                conference.getSubmitPaperDeadline(), conference.getReviewPaperDeadline());
    }
}
