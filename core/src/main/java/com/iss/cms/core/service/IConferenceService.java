package com.iss.cms.core.service;

import com.iss.cms.core.domain.Conference;
import com.iss.cms.core.exceptions.CMSException;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public interface IConferenceService {
    List<Conference> getAllConferences();

    Conference findConferenceById(int id) throws CMSException;

    void addConference(String name, LocalDate date, int entryFee, LocalDate biddingPhaseDeadline,
                       LocalDate submitPaperDeadline, LocalDate reviewPaperDeadline) throws CMSException;

    void changeDeadlines(int id, LocalDate newBiddingPhaseDeadline,
                         LocalDate newSubmitPaperDeadline, LocalDate newReviewPaperDeadline) throws CMSException;
}
