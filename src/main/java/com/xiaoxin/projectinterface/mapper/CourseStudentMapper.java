package com.xiaoxin.projectinterface.mapper;

import com.xiaoxin.projectinterface.entity.CourseStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoxin.projectinterface.entity.Student;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 14290
 * @since 2022-01-26
 */
public interface CourseStudentMapper extends BaseMapper<CourseStudent> {

    List<CourseStudent> findAllStudentByCourseId(Integer courseId);
}
