package com.xiaoxin.projectinterface.controller;


import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.CourseStudent;
import com.xiaoxin.projectinterface.service.CourseStudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 学生选课控制器
 *
 * @author 14290
 * @since 2022-01-26
 */
@RestController
@RequestMapping("/courseStudent")
public class CourseStudentController {

    @Resource
    private CourseStudentService courseStudentService;

    /**
     * 学生添加课程
     *
     * @param courseCode
     * @param studentId
     * @return
     */
    @GetMapping("/addCourseStudent")
    public ResultVO<?> addCourseStudent(
            @RequestParam("courseCode") String courseCode,
            @RequestParam("studentId") Integer studentId
    ) {
        return courseStudentService.addCourseStudent(courseCode, studentId);
    }

    /**
     * 查询
     *
     * @param map
     * @return
     */
    @GetMapping("/findCourseStudentByMap")
    public ResultVO<List<CourseStudent>> findCourseStudent(@RequestParam Map<String, Object> map) {
        return ResultVO.successResponse(courseStudentService.findCourseStudentByMap(map));
    }

    /**
     * 模糊查询
     *
     * @param column
     * @param value
     * @return
     */
    @GetMapping("/findCourseStudentByColumn")
    public ResultVO<List<CourseStudent>> findCourseStudentByColumn(
            @RequestParam("column") String column,
            @RequestParam("value") Object value
    ) {
        return ResultVO.successResponse(courseStudentService.findCourseStudentByColumn(column, value));
    }

    /**
     * 查找属于某个课程的所有学生
     *
     * @param courseId
     * @return
     */
    @GetMapping("/findAllStudentByCourseId")
    public ResultVO<List<CourseStudent>> findAllStudentByCourseId(
            @RequestParam("courseId") Integer courseId
    ) {
        return ResultVO.successResponse(courseStudentService.findAllStudentByCourseId(courseId));
    }

    /**
     * 修改
     *
     * @param courseId
     * @param studentId
     * @return
     */
    @GetMapping("/updateCourseStudent")
    public ResultVO<String> updateCourseStudent(
            @RequestParam("courseId") Integer courseId,
            @RequestParam("studentId") Integer studentId
    ) {
        CourseStudent cs = new CourseStudent(courseId, studentId);
        return courseStudentService.updateCourseStudent(cs) ? ResultVO.successResponse("修改成功") : ResultVO.failedResponse("修改失败");
    }

    /**
     * 删除
     */
    @GetMapping("/deleteCourseStudent")
    public ResultVO<String> deleteCourseStudent(
            @RequestParam("courseId") Integer courseId,
            @RequestParam("studentId") Integer studentId
    ) {
        return courseStudentService.deleteCourseStudent(courseId, studentId) ? ResultVO.successResponse("删除成功") : ResultVO.failedResponse("删除失败");
    }
}
