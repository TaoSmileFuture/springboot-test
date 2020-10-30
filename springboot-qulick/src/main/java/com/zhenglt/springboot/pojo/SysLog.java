package com.zhenglt.springboot.pojo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "t_sys_log")
public class SysLog implements Serializable{

	private static final long serialVersionUID = -6309732882044872298L;

	@Id
    @GenericGenerator(name = "uuid",strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;
	private String username;
	private String operation;
	private Long time;
	private String method;
	private String params;
	private String ip;
	private Date createTime;

}
