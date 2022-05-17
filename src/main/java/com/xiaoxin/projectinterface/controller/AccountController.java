package com.xiaoxin.projectinterface.controller;

import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Student;
import com.xiaoxin.projectinterface.entity.Teacher;
import com.xiaoxin.projectinterface.service.AccountService;
import com.xiaoxin.projectinterface.service.StudentService;
import com.xiaoxin.projectinterface.service.TeacherService;
import com.xiaoxin.projectinterface.utils.Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 *  账户控制器，实现对老师和学生账户的一些通用操作
 *
 * @author 14290
 * @since 2022-01-26
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    /**
     * 用户登录
     *
     * @param type     用户角色
     * @param account  账号
     * @param password 密码
     * @return 登录成功返回用户信息，失败返回201
     */
    @GetMapping("/login")
    public ResultVO<?> login(
            @RequestParam("type") Integer type,
            @RequestParam("account") String account,
            @RequestParam("password") String password
    ) {
        return accountService.accountLogin(type, account, password);
    }

    /**
     * 教师账户注册
     *
     * @param
     * @param account
     * @param password
     * @param name
     * @param sex
     * @param phone
     * @param email
     * @return ResultVO<String>
     */
    @GetMapping("/registerTeacher")
    public ResultVO<String> registerTeacher(
            @RequestParam("teacherAccount") String account,
            @RequestParam("teacherPassword") String password,
            @RequestParam("teacherName") String name,
            @RequestParam("teacherSex") Integer sex,
            @RequestParam("teacherPhone") String phone,
            @RequestParam("teacherEmail") String email
    ) {
        String avatar = "image/avatars/user-default.png";
        if (!Utils.isPhone(phone)) {
            return ResultVO.paramInvalidResponse("电话填写有误");
        } else if(!Utils.IsEmail(email)){
            return ResultVO.paramInvalidResponse("邮箱填写有误");
        } else {
            Teacher teacher = new Teacher(account, password, name + "老师", sex, phone, email, avatar);
            return accountService.registerTeacher(teacher) ? ResultVO.successResponse("账号注册成功") : ResultVO.userRegErrorResponse("该账号已被注册");
        }
    }

    /**
     * 学生账户注册
     *
     * @param account
     * @param password
     * @param name
     * @param sex
     * @param classname
     * @param phone
     * @param email
     * @return ResultVO<String>
     */
    @GetMapping("/registerStudent")
    public ResultVO<String> registerStudent(
            @RequestParam("account") String account,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("sex") Integer sex,
            @RequestParam("major") String classname,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email
    ) {
        String avatar = "image/avatars/user-default.png";
        if (!Utils.isPhone(phone)) {
            return ResultVO.paramInvalidResponse("电话填写有误");
        } else if(!Utils.IsEmail(email)){
            return ResultVO.paramInvalidResponse("邮箱填写有误");
        } else {
            Student student = new Student(account, password, name, sex, avatar, classname, phone, email);
            return accountService.registerStudent(student) ? ResultVO.successResponse("账号注册成功") : ResultVO.userRegErrorResponse("该账号已被注册");
        }
    }

    /**
     * 注销账户
     *
     * @param type
     * @param id
     * @return ResultVO<String>
     */
    @GetMapping("/delete")
    public ResultVO<String> deleteAccount(
            @RequestParam("type") Integer type,
            @RequestParam("id") Integer id
    ) {
        return accountService.delete(type,id) ? ResultVO.successResponse("删除成功") : ResultVO.failedResponse("删除失败");
    }

    /**
     * 查询所有用户账号
     *
     * @param type
     * @return ResultVO<?>
     */
    @GetMapping("/findAllAccount")
    public ResultVO<?> findAllAccount(
            @RequestParam("type") Integer type
    ) {
        switch (type) {
            case 1:
                return ResultVO.successResponse(accountService.findAllTeacher());
            case 2:
                return ResultVO.successResponse(accountService.findAllStudent());
            default:
                return ResultVO.paramErrorResponse("角色类型错误");
        }
    }

    /**
     * 通过模糊查询查找用户
     *
     * @param type
     * @param column
     * @param value
     * @return
     */
    @GetMapping("/findAccountByColumn")
    public ResultVO<?> findAccountByColumn(
            @RequestParam("type") Integer type,
            @RequestParam("column") String column,
            @RequestParam("value") Object value
    ) {
        switch (type) {
            case 1:
                return ResultVO.successResponse(accountService.findTeacher(column, value));
            case 2:
                return ResultVO.successResponse(accountService.findStudent(column, value));
            default:
                return ResultVO.paramErrorResponse("角色类型错误");
        }
    }

    /**
     * 验证账户是否存在
     *
     * @param type
     * @param account
     * @return
     */
    @GetMapping("/confirmAccount")
    public ResultVO<String> confirmAccount(
            @RequestParam("type") Integer type,
            @RequestParam("account") String account,
            @RequestParam(value = "phone", required = false) String phone
    ) {
        if (Utils.isEmpty(phone)) {
            return accountService.confirmAccount(type, account);
        } else {
            return accountService.confirmAccount(type, account, phone);
        }
    }
}
