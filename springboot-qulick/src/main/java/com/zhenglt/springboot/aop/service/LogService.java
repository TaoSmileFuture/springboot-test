package com.zhenglt.springboot.aop.service;

import com.zhenglt.springboot.pojo.SysLog;
import com.zhenglt.springboot.repository.SysLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName LogService
 * @Description
 * @Author zhenglt
 * @Date 2020/10/30 16:22
 **/
@Service
public class LogService {

    @Autowired
    private SysLogRepository logRepository;

    @Transactional(rollbackFor = Exception.class)
    public int saveLog(SysLog sysLog){
        logRepository.save(sysLog);
//        String str = null;
//        System.out.println(str.length());
        return 1;
    }
}
