package com.iss.cms.web.converter;

import com.iss.cms.core.domain.Conference;
import com.iss.cms.web.dto.ConferenceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConferenceConverter extends BaseConverter<Conference, ConferenceDTO> {
    private static final Logger logger = LoggerFactory.getLogger(ConferenceConverter.class);

    @Override
    public Conference convertDTOToModel(ConferenceDTO dto) {
        logger.trace("ConferenceConverter - convertDTOToModel: method entered -> " + dto.toString());

        Conference model = new Conference();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setEntryFee(dto.getEntryFee());
        model.setDate(dto.getDate());
        model.setBiddingPhaseDeadline(dto.getBiddingPhaseDeadline());
        model.setReviewPaperDeadline(dto.getReviewPaperDeadline());
        model.setSubmitPaperDeadline(dto.getSubmitPaperDeadline());

        logger.trace("ConferenceConverter - convertDTOToModel: method finished -> " + model.toString());
        return model;
    }

    @Override
    public ConferenceDTO convertModelToDTO(Conference model) {
        logger.trace("ConferenceConverter - convertModelToDTO: method entered -> " + model.toString());

        ConferenceDTO dto = new ConferenceDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setEntryFee(model.getEntryFee());
        dto.setDate(model.getDate());
        dto.setBiddingPhaseDeadline(model.getBiddingPhaseDeadline());
        dto.setReviewPaperDeadline(model.getReviewPaperDeadline());
        dto.setSubmitPaperDeadline(model.getSubmitPaperDeadline());

        logger.trace("ConferenceConverter - convertModelToDTO: method finished -> " + dto.toString());
        return dto;
    }
}
