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
public class PaperReview implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private int reviewerId;
    private int paperId;
    private String recommendation;
    @Enumerated(EnumType.STRING)
    private Qualifier qualifier;

    public PaperReview(int reviewerId, int paperId, String recommendation, Qualifier qualifier) {
        this.reviewerId = reviewerId;
        this.paperId = paperId;
        this.recommendation = recommendation;
        this.qualifier = qualifier;
    }
}
