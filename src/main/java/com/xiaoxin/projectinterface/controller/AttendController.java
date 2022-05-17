package com.xiaoxin.projectinterface.controller;


import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Attend;
import com.xiaoxin.projectinterface.service.AttendService;
import com.xiaoxin.projectinterface.utils.Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 考勤任务控制器
 *
 * @author 14290
 * @since 2022-01-26
 */
@RestController
@RequestMapping("/attend")
public class AttendController {

    @Resource
    private AttendService attendService;

    /**
     * 添加考勤任务
     *
     * @param courseId
     * @param start
     * @param end
     * @param longitude
     * @param latitude
     * @param location
     * @param type
     * @param gesture
     * @return
     */
    @GetMapping("/addAttend")
    public ResultVO<String> addAttend(
            @RequestParam("courseId") Integer courseId,
            @RequestParam("start") Timestamp start,
            @RequestParam("end") Timestamp end,
            @RequestParam("longitude") Double longitude,
            @RequestParam("latitude") Double latitude,
            @RequestParam("location") String location,
            @RequestParam("type") Integer type,
            @RequestParam(value = "gesture", required = false) String gesture
    ) {
        Attend attend = new Attend(courseId, start, end, longitude, latitude, location, type);
        if (type == 2) {
            attend.setAttendGesture(gesture);
        }
        return attendService.addAttend(attend);
    }

    /**
     * 修改考勤任务
     *
     * @param attendId
     * @param courseId
     * @param start
     * @param end
     * @param longitude
     * @param latitude
     * @param location
     * @return
     */
    @GetMapping("/updateAttend")
    public ResultVO<String> updateAttend(
            @RequestParam("attendId") Integer attendId,
            @RequestParam(value = "courseId", required = false) Integer courseId,
            @RequestParam(value = "start", required = false) Timestamp start,
            @RequestParam(value = "end", required = false) Timestamp end,
            @RequestParam(value = "longitude", required = false) Double longitude,
            @RequestParam(value = "latitude", required = false) Double latitude,
            @RequestParam(value = "location", required = false) String location
    ) {
        Attend attend = new Attend();
        attend.setAttendId(attendId);
        attend.setCourseId(Utils.isEmpty(courseId) ? null : courseId);
        attend.setAttendStart(Utils.isEmpty(start) ? null : start);
        attend.setAttendEnd(Utils.isEmpty(end) ? null : end);
        attend.setAttendLongitude(Utils.isEmpty(longitude) ? null : longitude);
        attend.setAttendLatitude(Utils.isEmpty(latitude) ? null : latitude);
        attend.setAttendLocation(Utils.isEmpty(location) ? null : location);
        return attendService.updateAttend(attend) ? ResultVO.successResponse("修改成功") : ResultVO.failedResponse("修改失败");
    }

    /**
     * 查询考勤任务
     *
     * @param column
     * @param value
     * @return
     */
    @GetMapping("/findAttendByColumn")
    public ResultVO<List<Attend>> findAttendByColumn(
            @RequestParam("column") String column,
            @RequestParam("value") Object value
    ) {
        List<Attend> attends = attendService.findAttendByColumn(column, value);
        return Utils.isEmpty(attends) ? ResultVO.dataEmptyResponse(attends) : ResultVO.successResponse(attends);
    }


    @GetMapping("/findAttendByMap")
    public ResultVO<List<Attend>> findAttendByMap(
            @RequestParam Map<String, Object> map
    ) {
        List<Attend> attends = attendService.findAttendByMap(map);
        return Utils.isEmpty(attends) ? ResultVO.dataEmptyResponse(attends) : ResultVO.successResponse(attends);
    }

    @GetMapping("/findAttendByTime")
    public ResultVO<List<Attend>> findAttendByTime(
            @RequestParam("courseId") Integer courseId,
            @RequestParam("time") String time
    ) {
        List<Attend> attends = attendService.findAttendByTime(courseId,time);
        return Utils.isEmpty(attends) ? ResultVO.dataEmptyResponse(attends) : ResultVO.successResponse(attends);
    }

    @GetMapping("/findAttendByCourseId")
    public ResultVO<List<Attend>> findAttendByCourseId(
            @RequestParam("courseId") Integer courseId
    ) {
        List<Attend> attends = attendService.findAttendByCourseId(courseId);
        return Utils.isEmpty(attends) ? ResultVO.dataEmptyResponse(attends) : ResultVO.successResponse(attends);
    }

    @GetMapping("/findStudentAttend")
    public ResultVO<List<Attend>> findStudentAttend(
            @RequestParam("courseId") Integer courseId,
            @RequestParam("joinTime") Timestamp joinTime
    ) {
        List<Attend> attends = attendService.findStudentAttend(courseId,joinTime);
        return Utils.isEmpty(attends) ? ResultVO.dataEmptyResponse(attends) : ResultVO.successResponse(attends);
    }

    @GetMapping("/findStudentAttendByTime")
    public ResultVO<List<Attend>> findStudentAttendByTime(
            @RequestParam("courseId") Integer courseId,
            @RequestParam("joinTime") Timestamp joinTime,
            @RequestParam("time") String time
    ) {
        List<Attend> attends = attendService.findStudentAttendByTime(courseId, joinTime, time);
        return Utils.isEmpty(attends) ? ResultVO.dataEmptyResponse(attends) : ResultVO.successResponse(attends);
    }

    /**
     * 删除考勤任务
     *
     * @param attendId
     * @return
     */
    @GetMapping("/deleteAttend")
    public ResultVO<String> deleteAttend(
            @RequestParam("attendId") Integer attendId
    ) {
        return attendService.deleteAttend(attendId) ? ResultVO.successResponse("删除成功") : ResultVO.failedResponse("删除失败");
    }
}
