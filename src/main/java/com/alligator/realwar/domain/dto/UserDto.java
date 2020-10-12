package com.alligator.realwar.domain.dto;

import com.alligator.realwar.util.InsertValidationGroup;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * UserDto实体
 */
@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = -141672576171595394L;
    /**
     * 用户主信息
     */
    @NotBlank(message = "用户名不能为空",
            groups = {InsertValidationGroup.class} )
    private String username;

    @NotBlank(message = "密码不能为空",
            groups = {InsertValidationGroup.class} )
    @Length(min = 6,max = 18,message = "密码必须大于6位小于18位")
    private String password;

    @NotEmpty(message = "邮箱不能为空",groups = {InsertValidationGroup.class})
    @Email(message = "必须为有效邮箱！")
    private String email;

    @NotNull(message = "年龄不能为空",groups = {InsertValidationGroup.class})
    @Max(value =60 ,message = "年龄不能大于60岁")
    @Min(value = 18, message = "年龄不能小于18岁")
    private Integer age;

    @NotBlank(message = "手机号不能为空",groups = {InsertValidationGroup.class})
    private String phone;

}
