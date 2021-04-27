package com.iss.cms.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Conference {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private LocalDate date;
    private int entryFee;
}
