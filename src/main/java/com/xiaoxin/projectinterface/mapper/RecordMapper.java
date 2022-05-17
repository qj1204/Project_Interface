package com.xiaoxin.projectinterface.mapper;

import com.xiaoxin.projectinterface.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 14290
 * @since 2022-01-26
 */
public interface RecordMapper extends BaseMapper<Record> {

    List<Record> findAllRecord(Integer attendId);

    List<Record> findRecordByTime(String time);

    List<Record> findAllRecordWithAttend();
}
