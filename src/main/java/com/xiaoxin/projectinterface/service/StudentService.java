package com.xiaoxin.projectinterface.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.projectinterface.entity.Student;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 14290
 * @since 2022-01-26
 */
public interface StudentService extends IService<Student> {

    boolean updateStudent(Student student);

}
