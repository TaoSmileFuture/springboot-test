package com.zhenglt.springboot.repository;

import com.zhenglt.springboot.pojo.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName SysLogRepository
 * @Description
 * @Author zhenglt
 * @Date 2020/10/30 16:28
 **/
public interface SysLogRepository extends JpaRepository<SysLog,String>{
}
