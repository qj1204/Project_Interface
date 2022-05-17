package com.xiaoxin.projectinterface.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoxin.projectinterface.common.ResultCode;
import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Student;
import com.xiaoxin.projectinterface.mapper.StudentMapper;
import com.xiaoxin.projectinterface.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.projectinterface.utils.Utils;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public boolean updateStudent(Student student) {
        return studentMapper.updateById(student) == 1;
    }
}
