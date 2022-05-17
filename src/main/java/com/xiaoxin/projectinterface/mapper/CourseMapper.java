package com.xiaoxin.projectinterface.mapper;

import com.xiaoxin.projectinterface.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 14290
 * @since 2022-01-26
 */
public interface CourseMapper extends BaseMapper<Course> {

    Course findCourseByCode(String code);

    List<Course> findCourseByTeacherId(Integer teacherId);

    List<Course> findCourseByTeacherIdAndName(Integer teacherId, String name);

    List<Course> findAllCourse();
}
