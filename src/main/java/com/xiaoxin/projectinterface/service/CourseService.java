package com.xiaoxin.projectinterface.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Course;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 14290
 * @since 2022-01-26
 */
public interface CourseService extends IService<Course> {

    List<Course> findCourseByMap(Map<String, Object> map);

    ResultVO<String> addCourse(Course course);

    List<Course> findCourseByColumn(String column, Object value);

    List<Course> findCourseByStudentId(Integer studentId);

    List<Course> findCourseByStudentIdAndName(Integer studentId, String name);

    Course findCourseByCode(String code);

    List<Course> findCourseByTeacherId(Integer teacherId);

    List<Course> findCourseByTeacherIdAndName(Integer teacherId, String name);

    List<Course> findAllCourse();

    boolean updateCourse(Course course);

    boolean deleteCourse(Integer courseId);
}
