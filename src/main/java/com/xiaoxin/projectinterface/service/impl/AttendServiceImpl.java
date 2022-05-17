package com.xiaoxin.projectinterface.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Attend;
import com.xiaoxin.projectinterface.entity.CourseStudent;
import com.xiaoxin.projectinterface.entity.Leave;
import com.xiaoxin.projectinterface.entity.Record;
import com.xiaoxin.projectinterface.mapper.AttendMapper;
import com.xiaoxin.projectinterface.mapper.CourseStudentMapper;
import com.xiaoxin.projectinterface.mapper.LeaveMapper;
import com.xiaoxin.projectinterface.mapper.RecordMapper;
import com.xiaoxin.projectinterface.service.AttendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.sql.Timestamp;
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
public class AttendServiceImpl extends ServiceImpl<AttendMapper, Attend> implements AttendService {

    @Resource
    private AttendMapper attendMapper;

    @Resource
    private LeaveMapper leaveMapper;

    @Resource
    private RecordMapper recordMapper;

    @Resource
    private CourseStudentMapper courseStudentMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<String> addAttend(Attend attend) {
        try {
            attendMapper.insert(attend);

            List<CourseStudent> courseStudents = courseStudentMapper.selectList(new QueryWrapper<CourseStudent>().eq("course_id", attend.getCourseId()));
            Record record = new Record();
            record.setAttendId(attend.getAttendId());
            //将所有参与考勤学生结果初始化为2
            for (CourseStudent courseStudent : courseStudents) {
                record.setStudentId(courseStudent.getStudentId());
                record.setRecordResult(2);
                recordMapper.insert(record);
            }
            //再去查看是否有请假学生，在申请请假表时也会查看考勤表
            List<Leave> leavedStudent = leaveMapper.findLeavedStudent(attend.getCourseId(), attend.getAttendStart(), attend.getAttendEnd());
            for (Leave leave : leavedStudent) {
                record.setStudentId(leave.getStudentId());
                record.setRecordResult(3);
                recordMapper.update(record, new UpdateWrapper<Record>().eq("student_id", record.getStudentId()).eq("attend_id", record.getAttendId()));
            }
            return ResultVO.successResponse("创建成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return ResultVO.failedResponse("创建错误，请重试");
        }
    }

    @Override
    public boolean updateAttend(Attend attend) {
        return attendMapper.updateById(attend) == 1;
    }

    @Override
    public List<Attend> findAttendByColumn(String column, Object value) {
        return attendMapper.selectList(new QueryWrapper<Attend>().like(column,value));
    }

    @Override
    public List<Attend> findAttendByMap(Map<String, Object> map) {
        return attendMapper.selectByMap(map);
    }

    @Override
    public List<Attend> findAttendByTime(Integer courseId, String time) {
        return attendMapper.findAttendByTime(courseId, time);
    }

    @Override
    public List<Attend> findAttendByCourseId(Integer courseId) {
        return attendMapper.selectList(new QueryWrapper<Attend>().eq("course_id", courseId).orderByDesc("attend_start"));
    }

    @Override
    public List<Attend> findStudentAttend(Integer courseId, Timestamp joinTime) {
        return attendMapper.findStudentAttend(courseId, joinTime);
    }

    @Override
    public List<Attend> findStudentAttendByTime(Integer courseId, Timestamp joinTime, String time) {
        return attendMapper.findStudentAttendByTime(courseId, joinTime, time);
    }

    @Override
    public boolean deleteAttend(Integer attendId) {
        return attendMapper.deleteById(attendId) == 1;
    }
}
