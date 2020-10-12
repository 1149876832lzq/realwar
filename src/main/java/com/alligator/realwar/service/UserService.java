package com.alligator.realwar.service;

import com.alligator.realwar.domain.common.PageQuery;
import com.alligator.realwar.domain.common.PageResult;
import com.alligator.realwar.domain.dto.UserDto;
import com.alligator.realwar.domain.dto.UserQueryDto;

import java.util.List;

public interface UserService {

    /**
     * 新增
     * @param userDto
     * @return
     */
    int save(UserDto userDto);

    /**
     * 更新
     * @param id
     * @param userDto
     * @return
     */
    int update(int id,UserDto userDto);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 用户查询
     * @param pageQuery
     * @return
     */
    PageResult<List<UserDto>> query(PageQuery<UserQueryDto> pageQuery);
}
