package com.xiaoxin.projectinterface.controller;


import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Student;
import com.xiaoxin.projectinterface.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 学生控制器
 *
 * @author 14290
 * @since 2022-01-26
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 修改学生信息
     *
     * @param studentId
     * @param account
     * @param password
     * @param name
     * @param sex
     * @param avatar
     * @param classname
     * @param face
     * @param phone
     * @param email
     * @return ResultVO<String>
     */
    @GetMapping("/update")
    public ResultVO<String> update(
            @RequestParam("studentId") Integer studentId,
            @RequestParam(value = "account", required = false) String account,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "sex", required = false) Integer sex,
            @RequestParam(value = "avatar", required = false) String avatar,
            @RequestParam(value = "major", required = false) String classname,
            @RequestParam(value = "face", required = false) String face,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "email", required = false) String email
    ) {
        Student student = new Student(account, password, name, sex, avatar, classname, phone, email);
        student.setStudentId(studentId);
        student.setStudentFace(face);
        return studentService.updateStudent(student) ? ResultVO.successResponse("修改成功") : ResultVO.failedResponse("修改失败");
    }
}
