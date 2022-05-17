package com.xiaoxin.projectinterface.mapper;

import com.xiaoxin.projectinterface.entity.Leave;
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
public interface LeaveMapper extends BaseMapper<Leave> {

    List<Leave> findAllLeave(Integer courseId);

    List<Leave> findAllLeaveWithStudent();

    List<Leave> findLeavedStudent(Integer courseId, Timestamp startTime, Timestamp endTime);
}
