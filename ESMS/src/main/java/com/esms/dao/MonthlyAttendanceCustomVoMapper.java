package com.esms.dao;

import com.esms.vo.MonthlyAttendanceCustomVo;

import java.util.List;
import java.util.Map;

/**
 * @program: ssm
 * @Author：邬俊标
 * @Description：
 * @Date：10:47 2018/8/9
 * @Version: 1.0
 */
public interface  MonthlyAttendanceCustomVoMapper {
    public List<MonthlyAttendanceCustomVo>
    selectMonthlyAttendanceCustomVoMapperByeAccountAnddIdAndTime(Map<String,Object> map);

    MonthlyAttendanceCustomVo selectVoByPrimaryKey(int id);
}
