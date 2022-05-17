package com.xiaoxin.projectinterface.controller;


import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Leave;
import com.xiaoxin.projectinterface.service.CourseService;
import com.xiaoxin.projectinterface.service.LeaveService;
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
 * 请假控制器
 *
 * @author 14290
 * @since 2022-01-26
 */
@RestController
@RequestMapping("/leave")
public class LeaveController {
    @Resource
    LeaveService leaveService;

    /**
     * 学生创建请假申请
     * result= 0正在审批,1拒绝请假,2批准请假
     *
     * @param studentId
     * @param courseId
     * @param leaveTime
     * @param backTime
     * @param leaveReason
     * @return
     */
    @GetMapping("/addLeave")
    public ResultVO<String> addLeave(
            @RequestParam("studentId") Integer studentId,
            @RequestParam("courseId") Integer courseId,
            @RequestParam("leaveTime") String leaveTime,
            @RequestParam("backTime") String backTime,
            @RequestParam("leaveReason") String leaveReason
    ) {
        Leave leave = new Leave(studentId, courseId, Timestamp.valueOf(leaveTime), Timestamp.valueOf(backTime), leaveReason);
        leave.setApprovalResult(0);
        if(leaveService.addLeave(leave)){
            return ResultVO.successResponse("创建成功");
        }
        return ResultVO.failedResponse("创建失败");
    }

    /**
     * 查看课程中的请假申请
     *
     * @param map
     * @return
     */
    @GetMapping("/findLeaveByMap")
    public ResultVO<List<Leave>> findLeaveByMap(
            @RequestParam Map<String, Object> map
    ) {
        return ResultVO.successResponse(leaveService.findLeaveByMap(map));
    }

    /**
     * 查询所有该课程的请假申请
     *
     * @param courseId
     * @return
     */
    @GetMapping("/findAllLeave")
    public ResultVO<List<Leave>> findAllLeave(
            @RequestParam("courseId") Integer courseId
    ) {
        List<Leave> leaves = leaveService.findAllLeave(courseId);
        return Utils.isEmpty(leaves) ? ResultVO.dataEmptyResponse(leaves) : ResultVO.successResponse(leaves);
    }

    @GetMapping("/findAllLeaveWithStudent")
    public ResultVO<List<Leave>> findAllLeaveWithStudent() {
        List<Leave> leaves = leaveService.findAllLeaveWithStudent();
        return Utils.isEmpty(leaves) ? ResultVO.dataEmptyResponse(leaves) : ResultVO.successResponse(leaves);
    }

    @GetMapping("/findAllLeaveByStudentId")
    public ResultVO<List<Leave>> findAllLeaveByStudentId(
            @RequestParam("courseId") Integer courseId,
            @RequestParam("studentId") Integer studentId
    ) {
        List<Leave> leaves = leaveService.findAllLeaveByStudentId(courseId, studentId);
        return Utils.isEmpty(leaves) ? ResultVO.dataEmptyResponse(leaves) : ResultVO.successResponse(leaves);
    }

    @GetMapping("/findLeaveByColumn")
    public ResultVO<List<Leave>> findLeaveByColumn(
            @RequestParam("column") String column,
            @RequestParam("value") String value
    ) {
        List<Leave> leaves = leaveService.findLeaveByColumn(column, value);
        return Utils.isEmpty(leaves) ? ResultVO.dataEmptyResponse(leaves) : ResultVO.successResponse(leaves);
    }

    /**
     * 教师审批课程中的请假申请
     *
     * @param leaveId
     * @param time
     * @param result
     * @param remark
     * @return
     */
    @GetMapping("/updateLeave")
    public ResultVO<String> updateLeave(
            @RequestParam("leaveId") Integer leaveId,
            @RequestParam("time") String time,
            @RequestParam("result") Integer result,
            @RequestParam("remark") String remark
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("leave_id", leaveId);
        Leave leave = leaveService.findLeaveByMap(map).get(0);
        leave.setApprovalRemark(remark);
        leave.setApprovalResult(result);
        leave.setApprovalTime(Timestamp.valueOf(time));
        return leaveService.updateLeave(leave) ? ResultVO.successResponse("审批成功") : ResultVO.failedResponse("操作失败");
    }

    /**
     * 学生撤销申请
     */
    @GetMapping("/deleteLeave")
    public ResultVO<String> deleteLeave(
            @RequestParam("leaveId") Integer leaveId
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("leave_id", leaveId);
        Integer approvalResult = leaveService.findLeaveByMap(map).get(0).getApprovalResult();
        if (approvalResult != 0) {
            return ResultVO.failedResponse("已审批，无法撤销");
        }
        return leaveService.deleteLeave(leaveId) ? ResultVO.successResponse("删除成功") : ResultVO.failedResponse("删除失败");
    }
}
