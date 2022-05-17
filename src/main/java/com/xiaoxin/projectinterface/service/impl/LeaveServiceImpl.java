package com.xiaoxin.projectinterface.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Attend;
import com.xiaoxin.projectinterface.entity.Leave;
import com.xiaoxin.projectinterface.entity.Record;
import com.xiaoxin.projectinterface.mapper.AttendMapper;
import com.xiaoxin.projectinterface.mapper.LeaveMapper;
import com.xiaoxin.projectinterface.mapper.RecordMapper;
import com.xiaoxin.projectinterface.service.LeaveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements LeaveService {
    @Resource
    LeaveMapper leaveMapper;

    @Resource
    RecordMapper recordMapper;

    @Resource
    AttendMapper attendMapper;

    @Override
    public boolean addLeave(Leave leave) {
        return leaveMapper.insert(leave) == 1;
    }

    @Override
    public List<Leave> findLeaveByMap(Map<String, Object> map) {
        return leaveMapper.selectByMap(map);
    }

    @Override
    public List<Leave> findAllLeave(Integer courseId) {
        return leaveMapper.findAllLeave(courseId);
    }

    @Override
    public List<Leave> findAllLeaveWithStudent() {
        return leaveMapper.findAllLeaveWithStudent();
    }

    @Override
    public List<Leave> findAllLeaveByStudentId(Integer courseId, Integer studentId) {
        return leaveMapper.selectList(new QueryWrapper<Leave>().eq("course_id", courseId).eq("student_id", studentId).orderByDesc("leave_time"));
    }

    @Override
    public List<Leave> findLeaveByColumn(String column, String value) {
        return leaveMapper.selectList(new QueryWrapper<Leave>().like(column, value));
    }

    @Override
    public boolean updateLeave(Leave leave) {
        if (leave.getApprovalResult() == 2) {
            Record record = new Record();
            record.setStudentId(leave.getStudentId());
            /**
             * 首先通过课程id找到该课程下的所有考勤任务
             * 再选择考勤时间在请假时间内的部分，获得他们的attendID
             * 将这些任务中该学生的状态设为请假
             */
            List<Attend> needLeaveAttend = attendMapper.findNeedLeaveAttend(leave.getCourseId(), leave.getLeaveTime(), leave.getBackTime());
            for (Attend attend : needLeaveAttend) {
                record.setAttendId(attend.getAttendId());
                record.setRecordResult(3);
                recordMapper.update(record, new UpdateWrapper<Record>().eq("attend_id", record.getAttendId()).eq("student_id", record.getStudentId()));
            }
        }
        return leaveMapper.updateById(leave) == 1;
    }

    @Override
    public boolean deleteLeave(Integer leaveId) {
        return leaveMapper.deleteById(leaveId) == 1;
    }
}
