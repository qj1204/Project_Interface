package com.xiaoxin.projectinterface.service;

import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Course;
import com.xiaoxin.projectinterface.entity.CourseStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.projectinterface.entity.Student;

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
public interface CourseStudentService extends IService<CourseStudent> {

    ResultVO<?> addCourseStudent(String courseCode, Integer studentId);

    List<CourseStudent> findCourseStudentByMap(Map<String, Object> map);

    List<CourseStudent> findCourseStudentByColumn(String column, Object value);

    List<CourseStudent> findAllStudentByCourseId(Integer courseId);

    boolean updateCourseStudent(CourseStudent cs);

    boolean deleteCourseStudent(Integer courseId, Integer studentId);
}
