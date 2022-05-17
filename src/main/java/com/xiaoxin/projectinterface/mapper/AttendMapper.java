package com.xiaoxin.projectinterface.mapper;

import com.xiaoxin.projectinterface.entity.Attend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 14290
 * @since 2022-01-26
 */
public interface AttendMapper extends BaseMapper<Attend> {

    List<Attend> findNeedLeaveAttend(Integer courseId, Timestamp leaveTime, Timestamp backTime);

    List<Attend> findAttendByTime(Integer courseId, String time);

    List<Attend> findStudentAttend(Integer courseId, Timestamp joinTime);

    List<Attend> findStudentAttendByTime(Integer courseId, Timestamp joinTime, String time);
}
