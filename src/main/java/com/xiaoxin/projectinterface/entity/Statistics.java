package com.xiaoxin.projectinterface.entity;

import lombok.Data;

@Data
public class Statistics {
    private String studentName;
    private String studentAccount;
    private Integer successCount;
    private Integer failedCount;
    private Integer absentCount;
    private Integer leaveCount;

    public Statistics() {}

    public Statistics(String studentName, String studentAccount, Integer successCount, Integer failedCount, Integer absentCount, Integer leaveCount) {
        this.studentName = studentName;
        this.studentAccount = studentAccount;
        this.successCount = successCount;
        this.failedCount = failedCount;
        this.absentCount = absentCount;
        this.leaveCount = leaveCount;
    }
}
