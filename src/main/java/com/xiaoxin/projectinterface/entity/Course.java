package com.xiaoxin.projectinterface.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 课程表
 *
 * @author 14290
 * @since 2022-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Course {

    @TableId(value = "course_id", type = IdType.AUTO)
    private Integer courseId;

    private Integer teacherId;

    private String courseName;

    private String courseAvatar;

    private String courseIntroduce;

    private String courseCode;

    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp joinTime;

    //测试连表查询
    @TableField(exist = false)
    private Teacher teacher;

    public Course(){}

    public Course(Integer teacherId, String name, String avatar, String introduce) {
        this.teacherId = teacherId;
        this.courseName = name;
        this.courseAvatar = avatar;
        this.courseIntroduce = introduce;
    }
}
