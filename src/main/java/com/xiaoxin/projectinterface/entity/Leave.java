package com.xiaoxin.projectinterface.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请假表
 *
 * @author 14290
 * @since 2022-01-26
 */
@Data
@TableName("leave_attend")
@EqualsAndHashCode(callSuper = false)
public class Leave {
    @TableId(value = "leave_id", type = IdType.AUTO)
    private Integer leaveId;

    private Integer studentId;

    private Integer courseId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp leaveTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp backTime;

    private String leaveReason;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp approvalTime;

    /**
     * 0正在审批,1拒绝请假,2批准请假
     */
    private Integer approvalResult;

    private String approvalRemark;

    @TableField(exist = false)
    private Student student;

    public Leave(){}

    public Leave(Integer studentId, Integer courseId, Timestamp leaveTime, Timestamp backTime, String leaveReason) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.leaveTime = leaveTime;
        this.backTime = backTime;
        this.leaveReason = leaveReason;
    }
}
