package com.xiaoxin.projectinterface.controller;


import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Record;
import com.xiaoxin.projectinterface.entity.Statistics;
import com.xiaoxin.projectinterface.service.RecordService;
import com.xiaoxin.projectinterface.utils.Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 考勤记录控制器
 *
 * @author 14290
 * @since 2022-01-26
 */
@RestController
@RequestMapping("/record")
public class RecordController {
    @Resource
    private RecordService recordService;

    /**
     * 进行签到操作，如果数据库没有该学生记录，则调用addRecord；若已经存在，则调用modifyRecord进行修改
     *
     * @param attendId
     * @param studentId
     * @param time
     * @param location
     * @return
     */
    @GetMapping("/doRecord")
    public ResultVO<String> doRecord(
            @RequestParam("attendId") Integer attendId,
            @RequestParam("studentId") Integer studentId,
            @RequestParam("time") String time,
            @RequestParam("location") String location
    ) {
        /**
         * 从数据库查看当前学生的人脸信息位置，并与签到上传的图片进行比对识别，返回true或false
         */
        String path2 = "src/main/resources/static/image/check/" + studentId + "_" + attendId + ".png";
        String path1 = "src/main/resources/static/image/face/" + studentId + ".png";
        String path = "image/check/" + studentId + "_" + attendId + ".png";
        Integer result = Utils.doIdentify(path1, path2);
        System.out.println("confidence = " + result);
        Record record = new Record(attendId, studentId, Timestamp.valueOf(time), location, path);
        record.setRecordResult(result);

        Map<String,Object> map = new HashMap<>();
        map.put("attend_id",attendId);
        map.put("student_id",studentId);
        List<Record> records = recordService.findRecordByMap(map);
        if(records.size() == 0) {
            boolean flag = recordService.addRecord(record);
            return flag ? ResultVO.identifyErrorResponse("人脸识别未通过") : ResultVO.successResponse("签到成功");
        } else {
            boolean flag  = recordService.updateRecord(record);
            return flag ? ResultVO.identifyErrorResponse("人脸识别未通过") : ResultVO.successResponse("签到成功");
        }
    }

    /**
     * 修改签到信息
     *
     * @param attendId
     * @param studentId
     * @param result
     * @param time
     * @param location
     * @return
     */
    @GetMapping("/updateRecord")
    public ResultVO<String> updateRecord(
            @RequestParam("attendId") Integer attendId,
            @RequestParam("studentId") Integer studentId,
            @RequestParam("result") Integer result,
            @RequestParam(value = "time", required = false) Timestamp time,
            @RequestParam(value = "location", required = false) String location
    ) {
        Record record = new Record();
        record.setAttendId(attendId);
        record.setStudentId(studentId);
        record.setRecordResult(result);
        record.setRecordTime(time);
        record.setRecordLocation(location);
        boolean flag  = recordService.updateRecord(record);
        return flag ? ResultVO.identifyErrorResponse("人脸识别未通过") : ResultVO.successResponse("修改成功");
    }

    /**
     * 删除
     *
     * @param attendId
     * @param studentId
     * @return
     */
    @GetMapping("/deleteRecord")
    public ResultVO<String> deleteRecord(
            @RequestParam("attendId") Integer attendId,
            @RequestParam("studentId") Integer studentId
    ) {
        return recordService.deleteRecord(attendId, studentId);
    }

    /**
     * 查找
     *
     * @param column
     * @param value
     * @return
     */
    @GetMapping("/findRecordByColumn")
    public ResultVO<List<Record>> findRecordByColumn(
            @RequestParam("column") String column,
            @RequestParam("value") Object value
    ) {
        return ResultVO.successResponse(recordService.findRecordByColumn(column, value));
    }

    @GetMapping("/findAllRecord")
    public ResultVO<List<Record>> findAllRecord(
            @RequestParam("attendId") Integer attendId
    ) {
        return ResultVO.successResponse(recordService.findAllRecord(attendId));
    }

    @GetMapping("/findRecordByMap")
    public ResultVO<List<Record>> findRecordByMap(
            @RequestParam Map<String, Object> map
    ) {
        return ResultVO.successResponse(recordService.findRecordByMap(map));
    }

    @GetMapping("/findRecordByTime")
    public ResultVO<List<Record>> findRecordByTime(
            @RequestParam("time") String time
    ) {
        return ResultVO.successResponse(recordService.findRecordByTime(time));
    }

    /**
     * 统计所有学生的考勤记录
     *
     * @param courseId
     * @return
     */
    @GetMapping("/findAllStudentRecord")
    public ResultVO<List<Statistics>> findAllStudentRecord(
            @RequestParam("courseId") Integer courseId
    ) {
        return ResultVO.successResponse(recordService.findAllStudentRecord(courseId));
    }

    @GetMapping("/findAllRecordWithAttend")
    public ResultVO<List<Record>> findAllRecordWithAttend() {
        return ResultVO.successResponse(recordService.findAllRecordWithAttend());
    }
}
