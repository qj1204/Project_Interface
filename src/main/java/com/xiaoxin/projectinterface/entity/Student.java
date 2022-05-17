package com.xiaoxin.projectinterface.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 学生表
 *
 * @author 14290
 * @since 2022-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Student {
    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;

    private String studentAccount;

    private String studentPassword;

    private String studentName;

    private Integer studentSex;

    private String studentAvatar;

    private String studentClass;

    private String studentFace;

    private String studentPhone;

    private String studentEmail;

    public Student(){}

    public Student(String studentAccount, String studentPassword, String studentName, Integer studentSex, String studentAvatar, String studentClass, String studentPhone, String studentEmail) {
        this.studentAccount = studentAccount;
        this.studentPassword = studentPassword;
        this.studentName = studentName;
        this.studentSex = studentSex;
        this.studentAvatar = studentAvatar;
        this.studentClass = studentClass;
        this.studentPhone = studentPhone;
        this.studentEmail = studentEmail;
    }
}
