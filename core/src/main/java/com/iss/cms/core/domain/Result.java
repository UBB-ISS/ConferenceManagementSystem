package com.iss.cms.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {
    @Id
    @GeneratedValue
    private int id;

    private int authorId;
    private int paperId;
    @Enumerated(EnumType.STRING)
    private Qualifier qualifier;

    public Result(int authorId, int paperId, Qualifier qualifier) {
        this.authorId = authorId;
        this.paperId = paperId;
        this.qualifier = qualifier;
    }
}
