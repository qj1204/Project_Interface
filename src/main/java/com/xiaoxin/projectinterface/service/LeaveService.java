package com.xiaoxin.projectinterface.service;

import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Leave;
import com.baomidou.mybatisplus.extension.service.IService;

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
public interface LeaveService extends IService<Leave> {

    boolean addLeave(Leave leave);

    List<Leave> findLeaveByMap(Map<String, Object> map);

    List<Leave> findAllLeave(Integer courseId);

    List<Leave> findAllLeaveWithStudent();

    List<Leave> findAllLeaveByStudentId(Integer courseId, Integer studentId);

    List<Leave> findLeaveByColumn(String column, String value);

    boolean updateLeave(Leave leave);

    boolean deleteLeave(Integer leaveId);
}
