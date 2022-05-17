package com.xiaoxin.projectinterface.controller;

import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Teacher;
import com.xiaoxin.projectinterface.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 教师控制器
 *
 * @author 14290
 * @since 2022-01-26
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    /**
     * 修改教师信息
     *
     * @param teacherId
     * @param account
     * @param password
     * @param name
     * @param sex
     * @param phone
     * @param email
     * @param avatar
     * @return ResultVO<String>
     */
    @GetMapping("/update")
    public ResultVO<String> update(
            @RequestParam("teacherId") Integer teacherId,
            @RequestParam(value = "account", required = false) String account,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "sex", required = false) Integer sex,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "avatar", required = false) String avatar
    ) {
        Teacher teacher = new Teacher(account, password, name, sex, phone, email, avatar);
        teacher.setTeacherId(teacherId);
        return teacherService.updateTeacher(teacher) ? ResultVO.successResponse("修改成功") : ResultVO.failedResponse("修改失败");
    }

}
