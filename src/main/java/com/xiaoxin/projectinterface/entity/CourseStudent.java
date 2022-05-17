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
 * 学生课程统计表
 *
 * @author 14290
 * @since 2022-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseStudent{
    private Integer courseId;

    private Integer studentId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp joinTime;

    @TableField(exist = false)
    private Student student;

    public CourseStudent(){}

    public CourseStudent(Integer courseId, Integer studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }
}
