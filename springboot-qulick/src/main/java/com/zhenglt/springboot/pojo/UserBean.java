package com.zhenglt.springboot.pojo;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName UserBean
 * @Description
 * @Author zhenglt
 * @Date 20/10/20 11:27
 **/
@Entity
@Table(name = "t_user_info")
@Data
@ToString
public class UserBean implements Serializable{

    private static final long serialVersionUID = -5454768280157322570L;

    @Id
//    根据数据库自动生成
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    uuid
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid")
    @Column(length = 200)
    private String id;

    @Column(length = 400)
    private String name;

    @Column(length = 3)
    private Integer age;

    @Column(length = 400)
    private String userid;

    @Column(length = 400)
    private String password;

    @Column(length = 1)
    private String flag;
}
