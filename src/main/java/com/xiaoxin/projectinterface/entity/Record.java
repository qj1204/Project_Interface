package com.xiaoxin.projectinterface.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 考勤记录表
 *
 * @author 14290
 * @since 2022-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Record implements Serializable {

    private Integer attendId;

    private Integer studentId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp recordTime;

    private String recordLocation;

    /**
     * 0代表成功，1代表失败，2代表缺勤,3代表请假
     */
    private Integer recordResult;

    private String recordPhoto;

    @TableField(exist = false)
    private Student student;
    @TableField(exist = false)
    private Attend attend;

    public Record() {}

    public Record(Integer attendId, Integer studentId, Timestamp recordTime, String recordLocation, String recordPhoto) {
        this.attendId = attendId;
        this.studentId = studentId;
        this.recordTime = recordTime;
        this.recordLocation = recordLocation;
        this.recordPhoto = recordPhoto;
    }

    public Record(Integer attendId, Integer studentId, Integer recordResult) {
        this.attendId = attendId;
        this.studentId = studentId;
        this.recordResult = recordResult;
    }
}
