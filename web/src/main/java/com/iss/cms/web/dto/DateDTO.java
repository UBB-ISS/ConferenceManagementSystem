package com.iss.cms.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class DateDTO {
    private int day;
    private int month;
    private int year;
}
