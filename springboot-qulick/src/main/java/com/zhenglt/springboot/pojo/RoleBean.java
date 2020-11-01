package com.zhenglt.springboot.pojo;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName RoleBean
 * @Description
 * @Author zhenglt
 * @Date 2020/11/01 15:05
 **/
@Entity
@Table(name = "t_role")
@Data
@ToString
public class RoleBean implements Serializable{


    private static final long serialVersionUID = -4269816613766268636L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid")
    private String id;

    private String name;

    private String memo;
}
