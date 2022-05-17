package com.xiaoxin.projectinterface.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 考勤任务表
 *
 * @author 14290
 * @since 2022-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Attend {

    @TableId(value = "attend_id", type = IdType.AUTO)
    private Integer attendId;

    private Integer courseId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp attendStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp attendEnd;

    private Double attendLongitude;

    private Double attendLatitude;

    private String attendLocation;

    private Integer attendType;

    private String attendGesture;

    @TableField(exist = false)
    private List<Record> records;

    public Attend(){}

    public Attend(Integer courseId, Timestamp start, Timestamp end, Double longitude, Double latitude, String location, Integer type) {
        this.courseId = courseId;
        this.attendStart = start;
        this.attendEnd = end;
        this.attendLongitude = longitude;
        this.attendLatitude = latitude;
        this.attendLocation = location;
        this.attendType = type;
    }
}
