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
 * @ClassName PermissionBean
 * @Description
 * @Author zhenglt
 * @Date 2020/11/01 15:07
 **/
@Entity
@Table(name = "t_permission")
@Data
@ToString
public class PermissionBean implements Serializable {
    private static final long serialVersionUID = 8539336848795524215L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid")
    private String id;

    private String name;

    private String url;
}
