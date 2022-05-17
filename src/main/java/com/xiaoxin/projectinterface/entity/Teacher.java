package com.xiaoxin.projectinterface.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 教师表
 *
 * @author 14290
 * @since 2022-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Teacher {
    @TableId(value = "teacher_id", type = IdType.AUTO)
    private Integer teacherId;

    private String teacherAccount;

    private String teacherPassword;

    private String teacherName;

    private Integer teacherSex;

    private String teacherPhone;

    private String teacherEmail;

    private String teacherAvatar;

    public Teacher(){}

    public Teacher(String account, String password, String name, Integer sex, String phone, String email, String avatar) {
        this.teacherAccount = account;
        this.teacherPassword = password;
        this.teacherName = name;
        this.teacherSex = sex;
        this.teacherPhone = phone;
        this.teacherEmail = email;
        this.teacherAvatar = avatar;
    }
}
