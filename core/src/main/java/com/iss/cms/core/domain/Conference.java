package com.iss.cms.core.domain;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Conference implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private LocalDate date;
    private int entryFee;
    private LocalDate biddingPhaseDeadline;
    private LocalDate submitPaperDeadline;
    private LocalDate reviewPaperDeadline;

    public Conference(String name, LocalDate date, int entryFee, LocalDate biddingPhaseDeadline, LocalDate submitPaperDeadline, LocalDate reviewPaperDeadline) {
        this.name = name;
        this.date = date;
        this.entryFee = entryFee;
        this.biddingPhaseDeadline = biddingPhaseDeadline;
        this.submitPaperDeadline = submitPaperDeadline;
        this.reviewPaperDeadline = reviewPaperDeadline;
    }
}
