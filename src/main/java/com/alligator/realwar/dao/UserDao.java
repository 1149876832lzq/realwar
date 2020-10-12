package com.alligator.realwar.dao;

import com.alligator.realwar.domain.dto.UserQueryDto;
import com.alligator.realwar.domain.entity.UserDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 新增
     * @param userDo
     * @return
     */
    int insert(UserDo userDo);

    /**
     * 更新
     * @param
     * @param userDo
     * @return
     */
    int updateById(UserDo userDo);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 用户查询
     * @param userQueryDto
     * @return
     */
    List<UserDo> query(UserQueryDto userQueryDto);

}
