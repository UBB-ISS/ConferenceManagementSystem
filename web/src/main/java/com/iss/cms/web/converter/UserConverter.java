package com.iss.cms.web.converter;

import com.iss.cms.core.domain.AppUser;
import com.iss.cms.web.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends BaseConverter<AppUser, UserDTO> {
    private static final Logger logger = LoggerFactory.getLogger(UserConverter.class);

    @Override
    public AppUser convertDTOToModel(UserDTO dto) {
        logger.trace("UserConverter - convertDTOToModel: method entered -> " + dto.toString());

        AppUser model = new AppUser();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setEmail(dto.getEmail());
        model.setAffiliation(dto.getAffiliation());
        model.setWebsite(dto.getWebsite());
        model.setUsername(dto.getUsername());
        model.setPassword(dto.getPassword());

        logger.trace("UserConverter - convertDTOToModel: method finished -> " + model.toString());
        return model;
    }

    @Override
    public UserDTO convertModelToDTO(AppUser model) {
        logger.trace("UserConverter - convertModelToDTO: method entered -> " + model.toString());

        UserDTO dto = new UserDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setEmail(model.getEmail());
        dto.setAffiliation(model.getAffiliation());
        dto.setWebsite(model.getWebsite());
        dto.setUsername(model.getUsername());
        dto.setPassword(model.getPassword());

        logger.trace("UserConverter - convertModelToDTO: method finished -> " + dto.toString());
        return dto;
    }
}
