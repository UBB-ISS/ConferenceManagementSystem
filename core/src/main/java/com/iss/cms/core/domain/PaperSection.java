package com.iss.cms.core.domain;

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
public class PaperSection implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private int paperId;
    private int sectionId;

    public PaperSection(int paperId, int sectionId) {
        this.paperId = paperId;
        this.sectionId = sectionId;
    }
}
