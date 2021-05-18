package com.iss.cms.core.service;

import com.iss.cms.core.domain.Conference;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.repository.ConferenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public Conference findConferenceByID(int id) throws CMSException{
        logger.trace("ConferenceService - findConferenceByID(): method entered");
        Optional<Conference> conference = conferenceRepository.findById(id);
        if(conference.isEmpty()) {
            logger.trace("ConferenceService: This id does not exist!");
            throw new CMSException("This id does not exist!");
        }
        logger.trace("ConferenceService - findConferenceByID(): method finished");
        return conference.get();
    }

    public void addConference(String name, LocalDate date, int entryFee, LocalDate biddingPhaseDeadline, LocalDate submitPaperDeadline, LocalDate reviewPaperDeadline) throws CMSException{
        logger.trace("ConferenceService - addConference(): method entered -> " +
                "name = " + name + ", date = " + date + ", entryFee = " + entryFee +
                ", biddingPhaseDeadline = " + biddingPhaseDeadline + ", submitPaperDeadline = " +
                submitPaperDeadline + ", reviewPaperDeadline = " + reviewPaperDeadline);

        List<Conference> allConferences = conferenceRepository.findAll();
        for (Conference conference : allConferences) {
            if (conference.getName().equals(name) && conference.getDate().equals(date)) {
                logger.trace("ConferenceService: This conference already exists!");
                throw new CMSException("This conference already exists!");
            }
        }

        Conference conference = new Conference(name, date, entryFee, biddingPhaseDeadline, submitPaperDeadline, reviewPaperDeadline);
        System.out.println(conference.toString());
        conferenceRepository.save(conference);

        logger.trace("ConferenceService - addConference(): method finished");
    }
}
