package com.iss.cms.web.converter;

import com.iss.cms.core.domain.Section;
import com.iss.cms.web.dto.SectionDTO;
import org.springframework.stereotype.Component;

@Component
public class SectionConverter extends BaseConverter<Section, SectionDTO>{
    @Override
    public Section convertDTOToModel(SectionDTO dto) {
        Section model = new Section();

        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setConferenceID(dto.getConferenceID());

        return model;
    }

    @Override
    public SectionDTO convertModelToDTO(Section model) {
        SectionDTO dto = new SectionDTO();

        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setConferenceID(model.getConferenceID());

        return dto;
    }
}
