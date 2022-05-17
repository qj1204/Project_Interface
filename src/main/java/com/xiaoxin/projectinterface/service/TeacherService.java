package com.xiaoxin.projectinterface.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.projectinterface.entity.Teacher;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 14290
 * @since 2022-01-26
 */
public interface TeacherService extends IService<Teacher> {

    boolean updateTeacher(Teacher teacher);
}
