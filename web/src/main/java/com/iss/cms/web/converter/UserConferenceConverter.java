package com.iss.cms.web.converter;

import com.iss.cms.core.domain.UserConference;
import com.iss.cms.web.dto.UserConferenceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserConferenceConverter extends BaseConverter<UserConference, UserConferenceDTO> {
    private static final Logger logger = LoggerFactory.getLogger(UserConferenceConverter.class);

    @Override
    public UserConference convertDTOToModel(UserConferenceDTO dto) {
        logger.trace("UserConferenceConverter - convertDTOToModel(): method entered -> " + dto.toString());

        UserConference model = new UserConference();
        model.setUserID(dto.getUserID());
        model.setConferenceID(dto.getConferenceID());
        model.setId(dto.getId());
        model.setPaid(dto.isPaid());
        model.setRole(dto.getRole());

        logger.trace("UserConferenceConverter - convertDTOToModel(): method finished -> " + model.toString());
        return model;
    }

    @Override
    public UserConferenceDTO convertModelToDTO(UserConference model) {
        logger.trace("UserConferenceConverter - convertModelToDTO(): method entered -> " + model.toString());

        UserConferenceDTO dto = new UserConferenceDTO();
        dto.setUserID(model.getUserID());
        dto.setConferenceID(model.getConferenceID());
        dto.setId(model.getId());
        dto.setPaid(model.isPaid());
        dto.setRole(model.getRole());

        logger.trace("UserConferenceConverter - convertModelToDTO(): method finished -> " + dto.toString());
        return dto;
    }
}
