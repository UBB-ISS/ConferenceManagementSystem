package com.iss.cms.web.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class ConferenceDTO {
    private int id;
    private String name;
    private DateDTO date;
    private int entryFee;
    private DateDTO biddingPhaseDeadline;
    private DateDTO submitPaperDeadline;
    private DateDTO reviewPaperDeadline;
}
