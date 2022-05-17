package com.xiaoxin.projectinterface.controller;


import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Course;
import com.xiaoxin.projectinterface.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  课程控制器
 *
 * @author 14290
 * @since 2022-01-26
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @GetMapping("/addCourse")
    public ResultVO<String> addCourse(
            @RequestParam("teacherId") Integer teacherId,
            @RequestParam("name") String name,
            @RequestParam("avatar") String avatar,
            @RequestParam("introduce") String introduce
    ) {
        Course course = new Course(teacherId, name, avatar, introduce);
        Map<String, Object> map = new HashMap<>();
        map.put("teacher_id", teacherId);
        map.put("course_name", name);
        if (courseService.findCourseByMap(map).size() != 0) {
            return ResultVO.dataExistedResponse("创建课程已存在");
        }
        return courseService.addCourse(course);
    }

    /**
     * 通过模糊查询课程表
     *
     * @return 返回数组
     */
    @GetMapping("/findCourseByColumn")
    public ResultVO<List<Course>> findCourseByColumn(
            @RequestParam("column") String column,
            @RequestParam("value") Object value
    ) {
        return ResultVO.successResponse(courseService.findCourseByColumn(column, value));
    }

    /**
     * 通过学生id查询课程
     *
     * @param studentId
     * @return
     */
    @GetMapping("/findCourseByStudentId")
    public ResultVO<List<Course>> findCourseByStudentId(
            @RequestParam("studentId") Integer studentId
    ) {
        return ResultVO.successResponse(courseService.findCourseByStudentId(studentId));
    }

    /**
     * 通过学生id和课程name模糊查询课程
     *
     * @param studentId
     * @param name
     * @return
     */
    @GetMapping("/findCourseByStudentIdAndName")
    public ResultVO<List<Course>> findCourseByStudentIdAndName(
            @RequestParam("studentId") Integer studentId,
            @RequestParam("name") String name
    ) {
        return ResultVO.successResponse(courseService.findCourseByStudentIdAndName(studentId, name));
    }

    /**
     * 通过课程码查询课程
     *
     * @param code
     * @return
     */
    @GetMapping("/findCourseByCode")
    public ResultVO<Course> findCourseByCode(
            @RequestParam("code") String code
    ) {
        return ResultVO.successResponse(courseService.findCourseByCode(code));
    }

    /**
     * 通过教师id查询课程
     *
     * @param teacherId
     * @return
     */
    @GetMapping("/findCourseByTeacherId")
    public ResultVO<List<Course>> findCourseByTeacherId(
            @RequestParam("teacherId") Integer teacherId
    ) {
        return ResultVO.successResponse(courseService.findCourseByTeacherId(teacherId));
    }

    /**
     * 通过教师id和课程name模糊查询课程
     *
     * @param teacherId
     * @param name
     * @return
     */
    @GetMapping("/findCourseByTeacherIdAndName")
    public ResultVO<List<Course>> findCourseByTeacherIdAndName(
            @RequestParam("teacherId") Integer teacherId,
            @RequestParam("name") String name
    ) {
        return ResultVO.successResponse(courseService.findCourseByTeacherIdAndName(teacherId, name));
    }

    /**
     * 查询所有课程
     *
     * @return
     */
    @GetMapping("/findAllCourse")
    public ResultVO<List<Course>> findAllCourse() {
        return ResultVO.successResponse(courseService.findAllCourse());
    }

    /**
     * 修改课程，如果修改后的名称在数据库重复，取消修改操作
     *
     * @param courseId
     * @param courseName
     * @param courseAvatar
     * @param courseIntroduce
     * @return
     */
    @GetMapping("/updateCourse")
    public ResultVO<String> updateCourse(
            @RequestParam("courseId") Integer courseId,
            @RequestParam(value = "courseName", required = false) String courseName,
            @RequestParam(value = "courseAvatar", required = false) String courseAvatar,
            @RequestParam(value = "courseIntroduce", required = false) String courseIntroduce
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("course_id", courseId);
        Course courseOld = courseService.findCourseByMap(map).get(0);
        map.clear();
        //如果参数中有课程名称且数据库中有该课程名称，不能修改
        if (courseName != null && !courseName.equals(courseOld.getCourseName())) {
            map.put("teacher_id", courseOld.getTeacherId());
            map.put("course_name", courseName);
            if (courseService.findCourseByMap(map).size() != 0) {
                return ResultVO.paramInvalidResponse("修改课程名称重复");
            }
        }
        Course course = new Course(courseOld.getTeacherId(), courseName, courseAvatar, courseIntroduce);
        course.setCourseId(courseId);
        return courseService.updateCourse(course) ? ResultVO.successResponse("修改成功") : ResultVO.failedResponse("修改失败");
    }

    /**
     * 删除课程
     *
     * @param courseId 课程的id
     */
    @GetMapping("/delete")
    public ResultVO<String> deleteCourse(
            @RequestParam("id") Integer courseId
    ) {
        return courseService.deleteCourse(courseId) ? ResultVO.successResponse("删除成功") : ResultVO.failedResponse("删除失败");
    }
}
