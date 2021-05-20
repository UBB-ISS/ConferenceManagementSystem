package com.iss.cms.web.converter;

import com.iss.cms.web.dto.DateDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateConverter extends BaseConverter<LocalDate, DateDTO> {
    @Override
    public LocalDate convertDTOToModel(DateDTO dto) {
        return LocalDate.of(dto.getYear(), dto.getMonth(), dto.getDay());
    }

    @Override
    public DateDTO convertModelToDTO(LocalDate model) {
        DateDTO dateDTO = new DateDTO();
        dateDTO.setDay(model.getDayOfMonth());
        dateDTO.setMonth(model.getMonthValue());
        dateDTO.setYear(model.getYear());

        return dateDTO;
    }
}
