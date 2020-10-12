package com.alligator.realwar.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * UserDo实体
 */
@Data
public class UserDo implements Serializable{

    /**
     * 序列化id，一定要加上
     */
    private static final long serialVersionUID = -551320466222355125L;
    /**
     * 用户主信息
     */
    private String username;

    private String password;

    private String email;

    private Integer age;

    private String phone;

    //盐值
    private String salt;

    /**
     * 数据库主信息
     */
    private Integer id;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    //修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified;
    //逻辑删除字段： 0：正常 1：逻辑删除也就是软删除
    private Integer status;
}
