package com.iss.cms.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaperReviewsDTO {
    private Set<PaperReviewDTO> paperReviewsDTO;
}
