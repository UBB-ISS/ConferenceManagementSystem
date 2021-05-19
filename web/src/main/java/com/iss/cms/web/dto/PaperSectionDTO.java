package com.iss.cms.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class PaperSectionDTO {
    private int id;
    private int paperId;
    private int sectionId;
}
