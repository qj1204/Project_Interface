package com.xiaoxin.projectinterface.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Record;
import com.xiaoxin.projectinterface.entity.Statistics;

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
public interface RecordService extends IService<Record> {

    List<Record> findRecordByMap(Map<String, Object> map);

    boolean addRecord(Record record);

    boolean updateRecord(Record record);

    ResultVO<String> deleteRecord(Integer attendId, Integer studentId);

    List<Record> findRecordByColumn(String column, Object value);

    List<Record> findAllRecord(Integer attendId);

    List<Record> findRecordByTime(String time);

    List<Statistics> findAllStudentRecord(Integer courseId);

    List<Record> findAllRecordWithAttend();
}
