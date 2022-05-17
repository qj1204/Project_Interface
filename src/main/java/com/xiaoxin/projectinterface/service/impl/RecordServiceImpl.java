package com.xiaoxin.projectinterface.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.*;
import com.xiaoxin.projectinterface.mapper.AttendMapper;
import com.xiaoxin.projectinterface.mapper.RecordMapper;
import com.xiaoxin.projectinterface.service.AttendService;
import com.xiaoxin.projectinterface.service.CourseStudentService;
import com.xiaoxin.projectinterface.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Resource
    RecordMapper recordMapper;

    @Resource
    AttendMapper attendMapper;

    @Resource
    CourseStudentService courseStudentService;

    @Override
    public List<Record> findRecordByMap(Map<String, Object> map) {
        return recordMapper.selectByMap(map);
    }

    @Override
    public boolean addRecord(Record record) {
        recordMapper.insert(record);
        return record.getRecordResult() == 1;
    }

    @Override
    public boolean updateRecord(Record record) {
        /**
         * 修改后，要检查是否通过人脸识别
         */
        recordMapper.update(record, new QueryWrapper<Record>().eq("attend_id", record.getAttendId()).eq("student_id", record.getStudentId()));
        return record.getRecordResult() == 1;
    }

    @Override
    public ResultVO<String> deleteRecord(Integer attendId, Integer studentId) {
        recordMapper.delete(new QueryWrapper<Record>().eq("attend_id", attendId).eq("student_id", studentId));
        return ResultVO.successResponse("删除成功");
    }

    @Override
    public List<Record> findRecordByColumn(String column, Object value) {
        return recordMapper.selectList(new QueryWrapper<Record>().like(column, value));
    }

    @Override
    public List<Record> findAllRecord(Integer attendId) {
        return recordMapper.findAllRecord(attendId);
    }

    @Override
    public List<Record> findRecordByTime(String time) {
        return recordMapper.findRecordByTime(time);
    }

    @Override
    public List<Statistics> findAllStudentRecord(Integer courseId) {
        List<CourseStudent> allStudent = courseStudentService.findAllStudentByCourseId(courseId);
        List<Attend> attends = attendMapper.selectList(new QueryWrapper<Attend>().eq("course_id", courseId).orderByDesc("attend_start"));
        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        List<Statistics> list = new ArrayList<>();
        for (CourseStudent courseStudent : allStudent) {
            Student student = courseStudent.getStudent();
            int successNum = 0;
            int failNum = 0;
            int absentNum = 0;
            int leaveNum = 0;
            for (Attend attend : attends) {
                successNum = successNum + recordMapper.selectCount(wrapper.eq("attend_id", attend.getAttendId()).eq("student_id", student.getStudentId()).eq("record_result", 0));
                wrapper.clear();
                failNum = failNum + recordMapper.selectCount(wrapper.eq("attend_id", attend.getAttendId()).eq("student_id", student.getStudentId()).eq("record_result", 1));
                wrapper.clear();
                absentNum = absentNum + recordMapper.selectCount(wrapper.eq("attend_id", attend.getAttendId()).eq("student_id", student.getStudentId()).eq("record_result", 2));
                wrapper.clear();
                leaveNum = leaveNum + recordMapper.selectCount(wrapper.eq("attend_id", attend.getAttendId()).eq("student_id", student.getStudentId()).eq("record_result", 3));
                wrapper.clear();
            }
            list.add(new Statistics(student.getStudentName(), student.getStudentAccount(), successNum, failNum, absentNum, leaveNum));
        }
        return list;
    }

    @Override
    public List<Record> findAllRecordWithAttend() {
        return recordMapper.findAllRecordWithAttend();
    }
}
