package com.iss.cms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private String review;
    private Qualifier paperState;
}
