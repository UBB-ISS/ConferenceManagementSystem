package com.iss.cms.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Conference {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private LocalDate date;
    private int entryFee;
//    private ArrayList<String> sections;
}
