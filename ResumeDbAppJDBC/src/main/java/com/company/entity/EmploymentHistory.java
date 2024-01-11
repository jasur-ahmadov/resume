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

    public EmploymentHistory(Integer id, User user, String header, Date beginDate, Date endDate, String jobDescription) {
        this.id = id;
        this.header = header;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.jobDescription = jobDescription;
        this.user = user;
    }
}