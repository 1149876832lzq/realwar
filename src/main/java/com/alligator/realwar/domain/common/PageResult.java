package com.alligator.realwar.domain.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页返回实体
 */
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1110721221167756440L;

    //当前页号
    private Integer pageNo;
    //每页行数
    private Integer pageSize;
    //总记录数
    private Integer total;
    //总页数
    private Integer pageNum;
    //动态内容
    private T data;
}
