package com.xiaoxin.projectinterface.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Course;
import com.xiaoxin.projectinterface.entity.CourseStudent;
import com.xiaoxin.projectinterface.mapper.CourseMapper;
import com.xiaoxin.projectinterface.mapper.CourseStudentMapper;
import com.xiaoxin.projectinterface.mapper.TeacherMapper;
import com.xiaoxin.projectinterface.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.projectinterface.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Resource
    private CourseStudentMapper courseStudentMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public List<Course> findCourseByMap(Map<String, Object> map) {
        return courseMapper.selectByMap(map);
    }

    @Override
    public ResultVO<String> addCourse(Course course) {
        String code;
        while (true) {
            code = Utils.courseCode();
            Course result = courseMapper.selectOne(new QueryWrapper<Course>().eq("course_code", code));
            if (Utils.isEmpty(result)) {
                break;
            }
        }
        course.setCourseCode(code);
        courseMapper.insert(course);
        if (course.getCourseId() != null) {
            //修改课程头像名称并重写入数据库
            String s = Utils.renameImage(course.getCourseAvatar(), "c" + course.getCourseId());
            course.setCourseAvatar(s);
            courseMapper.updateById(course);
            return ResultVO.successResponse(code);
        }
        return ResultVO.failedResponse("添加失败");
    }

    @Override
    public List<Course> findCourseByColumn(String column, Object value) {
        return courseMapper.selectList(new QueryWrapper<Course>().like(column, value));
    }

    @Override
    public List<Course> findCourseByStudentId(Integer studentId) {
        List<CourseStudent> courseIdList = courseStudentMapper.selectList(new QueryWrapper<CourseStudent>().eq("student_id", studentId));
        List<Course> courseList = new ArrayList<>();
        for (CourseStudent courseStudent : courseIdList) {
            Course course = courseMapper.selectById(courseStudent.getCourseId());
            course.setTeacher(teacherMapper.selectById(course.getTeacherId()));
            course.setJoinTime(courseStudent.getJoinTime());
            courseList.add(course);
        }
        return courseList;
    }

    @Override
    public List<Course> findCourseByStudentIdAndName(Integer studentId, String name) {
        List<CourseStudent> courseIdList = courseStudentMapper.selectList(new QueryWrapper<CourseStudent>().eq("student_id", studentId));
        List<Course> courseList = new ArrayList<>();
        for (CourseStudent courseStudent : courseIdList) {
            Course course = courseMapper.selectOne(new QueryWrapper<Course>().eq("course_id", courseStudent.getCourseId()).like("course_name", name));
            if (Utils.isEmpty(course)) {
                continue;
            }
            course.setTeacher(teacherMapper.selectById(course.getTeacherId()));
            course.setJoinTime(courseStudent.getJoinTime());
            courseList.add(course);
        }
        return courseList;
    }

    @Override
    public Course findCourseByCode(String code) {
        return courseMapper.findCourseByCode(code);
    }

    @Override
    public List<Course> findCourseByTeacherId(Integer teacherId) {
        return courseMapper.findCourseByTeacherId(teacherId);
    }

    @Override
    public List<Course> findCourseByTeacherIdAndName(Integer teacherId, String name) {
        return courseMapper.findCourseByTeacherIdAndName(teacherId, name);
    }

    @Override
    public List<Course> findAllCourse() {
        return courseMapper.findAllCourse();
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseMapper.updateById(course) == 1;
    }

    @Override
    public boolean deleteCourse(Integer courseId) {
        return courseMapper.deleteById(courseId) == 1;
    }
}
