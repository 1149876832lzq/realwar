package com.alligator.realwar.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * UserVo实体
 */
@Data
public class UserVo implements Serializable{

    private static final long serialVersionUID = -1270488269055613609L;

    /**
     * 用户主信息
     */
    private String username;

    private String password;

    private String email;

    private Integer age;

    private String phone;
}
