package com.zhenglt.springboot.repository;

import com.zhenglt.springboot.pojo.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UserRepository
 * @Description
 * @Author zhenglt
 * @Date 20/10/20 15:38
 **/
public interface UserRepository extends JpaRepository<UserBean,String>{
}
