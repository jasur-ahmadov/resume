package com.company.entity;

import java.sql.Date;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmploymentHistory {

    private Integer id;
    private String header;
    private Date beginDate;
    private Date endDate;
    private String jobDescription;
    private User user;
}