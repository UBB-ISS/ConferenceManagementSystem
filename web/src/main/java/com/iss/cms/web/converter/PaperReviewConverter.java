package com.iss.cms.web.converter;

import com.iss.cms.core.domain.PaperReview;
import com.iss.cms.web.dto.PaperReviewDTO;

public class PaperReviewConverter extends BaseConverter<PaperReview, PaperReviewDTO>{
    @Override
    public PaperReview convertDTOToModel(PaperReviewDTO dto) {
        PaperReview model = new PaperReview();

        model.setId(dto.getId());
        model.setReviewerId(dto.getReviewerId());
        model.setReview(dto.getReview());
        model.setQualifier(dto.getQualifier());

        return model;
    }

    @Override
    public PaperReviewDTO convertModelToDTO(PaperReview model) {
        PaperReviewDTO dto = new PaperReviewDTO();

        dto.setId(model.getId());
        dto.setReviewerId(model.getReviewerId());
        dto.setReview(model.getReview());
        dto.setQualifier(model.getQualifier());

        return dto;
    }
}
