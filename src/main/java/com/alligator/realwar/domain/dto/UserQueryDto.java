package com.alligator.realwar.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 数据查询实体
 */
@Data
public class UserQueryDto implements Serializable {

    private static final long serialVersionUID = 2611333137364089482L;

    @NotEmpty(message = "用户名不能为空")
    private String username;
}
