package com.xiaoxin.projectinterface.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.projectinterface.entity.Teacher;
import com.xiaoxin.projectinterface.mapper.TeacherMapper;
import com.xiaoxin.projectinterface.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 14290
 * @since 2022-01-26
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;


    @Override
    public boolean updateTeacher(Teacher teacher) {
        return teacherMapper.updateById(teacher) == 1;
    }
}
