package com.iss.cms.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class SectionDTO {
    private int id;
    private String name;
    private int conferenceID;
}
