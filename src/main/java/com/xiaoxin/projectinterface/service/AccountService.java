package com.xiaoxin.projectinterface.service;

import com.xiaoxin.projectinterface.common.ResultVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.projectinterface.entity.Student;
import com.xiaoxin.projectinterface.entity.Teacher;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 14290
 * @since 2022-01-26
 */
public interface AccountService extends IService<Teacher> {
    ResultVO<?> accountLogin(Integer type, String account, String password);

    boolean registerTeacher(Teacher teacher);

    boolean registerStudent(Student student);

    boolean delete(Integer type, Integer id);

    List<Teacher> findAllTeacher();

    List<Student> findAllStudent();

    List<Teacher> findTeacher(String column, Object value);

    List<Student> findStudent(String column, Object value);

    ResultVO<String> confirmAccount(Integer type, String account);

    ResultVO<String> confirmAccount(Integer type, String account, String phone);

}
