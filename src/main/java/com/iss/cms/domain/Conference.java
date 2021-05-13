package com.iss.cms.domain;

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
}
