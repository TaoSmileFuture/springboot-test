package com.zhenglt.springboot.service; 

import com.zhenglt.springboot.MySpringBootApplication;
import com.zhenglt.springboot.pojo.UserBean;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/** 
* UserService Tester. 
* 
* @author <Authors name> 
* @since <pre>十月 20, 2020</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MySpringBootApplication.class})
public class UserServiceTest { 

    @Autowired
    private UserService userService;
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

    /**
    *
    * Method: getUserList()
    *
    */
    @Test
    public void testGetUserList() throws Exception {
    //TODO: Test goes here...
        List<UserBean> userList = userService.getUserList();
        System.out.println(userList);
    }

    @Test
    public void testGetUserListByJpa() throws Exception {
        List<UserBean> userList = userService.getUserListByJpa();
        System.out.println(userList);
    }

    @Test
    public void testGetUserListByRedis() throws Exception {
        System.out.println(userService.getUserListByRedis());
    }
}
