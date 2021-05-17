package com.iss.cms.web.converter;

public interface Converter<Model, DTO> {
    Model convertDTOToModel(DTO dto);

    DTO convertModelToDTO(Model model);
}
