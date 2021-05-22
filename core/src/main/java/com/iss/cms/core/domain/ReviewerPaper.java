package com.iss.cms.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewerPaper implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private int userId;
    private int paperId;
    private boolean assigned;
    @Enumerated(EnumType.STRING)
    private Availability availability;

    public ReviewerPaper(int userId, int paperId, boolean assigned, Availability availability) {
        this.userId = userId;
        this.paperId = paperId;
        this.assigned = assigned;
        this.availability = availability;
    }
}
