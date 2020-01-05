package com.esms.dao;

import com.esms.vo.EmployeeCustomVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: ssm
 * @Author：邬俊标
 * @Description：
 * @Date：10:22 2018/8/9
 * @Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class EmployeeCustomVoTest {
    @Autowired
    private EmployeeCustomVoMapper employeeCustomVoMapper = null;
    @Test
    public void test(){
        EmployeeCustomVo employeeCustomVo = employeeCustomVoMapper.selectEmployeeById(1);
        System.out.println(employeeCustomVo);
    }
}
