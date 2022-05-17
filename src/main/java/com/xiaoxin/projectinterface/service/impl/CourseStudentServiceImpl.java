package com.xiaoxin.projectinterface.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Course;
import com.xiaoxin.projectinterface.entity.CourseStudent;
import com.xiaoxin.projectinterface.entity.Student;
import com.xiaoxin.projectinterface.mapper.CourseMapper;
import com.xiaoxin.projectinterface.mapper.CourseStudentMapper;
import com.xiaoxin.projectinterface.mapper.StudentMapper;
import com.xiaoxin.projectinterface.service.CourseStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.projectinterface.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 14290
 * @since 2022-01-26
 */
@Service
public class CourseStudentServiceImpl extends ServiceImpl<CourseStudentMapper, CourseStudent> implements CourseStudentService {

    @Resource
    private CourseStudentMapper courseStudentMapper;

    @Resource
    private CourseMapper courseMapper;

    @Override
    public ResultVO<?> addCourseStudent(String courseCode, Integer studentId) {
        Course course = courseMapper.selectOne(new QueryWrapper<Course>().eq("course_code", courseCode));
        if (Utils.isEmpty(course)) {
            return ResultVO.dataEmptyResponse("课程码不存在");
        }
        QueryWrapper<CourseStudent> query = new QueryWrapper<CourseStudent>().eq("course_id", course.getCourseId()).eq("student_id", studentId);
        Integer count = courseStudentMapper.selectCount(query);
        if (count != 0) {
            return ResultVO.dataExistedResponse("已加入该课程");
        }
        System.out.println("course.getCourseId()=" + course.getCourseId());
        courseStudentMapper.insert(new CourseStudent(course.getCourseId(), studentId));
        CourseStudent courseStudent = courseStudentMapper.selectOne(query);
        return ResultVO.successResponse(courseStudent);
    }

    @Override
    public List<CourseStudent> findCourseStudentByMap(Map<String, Object> map) {
        return courseStudentMapper.selectByMap(map);
    }

    @Override
    public List<CourseStudent> findCourseStudentByColumn(String column, Object value) {
        return courseStudentMapper.selectList(new QueryWrapper<CourseStudent>().like(column,value));
    }

    @Override
    public List<CourseStudent> findAllStudentByCourseId(Integer courseId) {
        return courseStudentMapper.findAllStudentByCourseId(courseId);
    }

    @Override
    public boolean updateCourseStudent(CourseStudent cs) {
        return courseStudentMapper.update(cs,new QueryWrapper<CourseStudent>().eq("course_id", cs.getCourseId()).eq("student_id", cs.getStudentId())) == 1;
    }

    @Override
    public boolean deleteCourseStudent(Integer courseId, Integer studentId) {
        return courseStudentMapper.delete(new QueryWrapper<CourseStudent>().eq("course_id", courseId).eq("student_id", studentId)) == 1;
    }
}
