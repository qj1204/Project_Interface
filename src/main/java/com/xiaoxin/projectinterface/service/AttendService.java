package com.xiaoxin.projectinterface.service;

import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Attend;
import com.baomidou.mybatisplus.extension.service.IService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 14290
 * @since 2022-01-26
 */
public interface AttendService extends IService<Attend> {

    ResultVO<String> addAttend(Attend attend);

    boolean updateAttend(Attend attend);

    List<Attend> findAttendByColumn(String column, Object value);

    List<Attend> findAttendByMap(Map<String, Object> map);

    List<Attend> findAttendByTime(Integer courseId, String time);

    List<Attend> findAttendByCourseId(Integer courseId);

    List<Attend> findStudentAttend(Integer courseId, Timestamp joinTime);

    List<Attend> findStudentAttendByTime(Integer courseId, Timestamp joinTime, String time);

    boolean deleteAttend(Integer attendId);
}
