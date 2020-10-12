package com.alligator.realwar.service.impl;

import com.alligator.realwar.dao.UserDao;
import com.alligator.realwar.domain.common.PageQuery;
import com.alligator.realwar.domain.common.PageResult;
import com.alligator.realwar.domain.dto.UserDto;
import com.alligator.realwar.domain.dto.UserQueryDto;
import com.alligator.realwar.domain.entity.UserDo;
import com.alligator.realwar.service.UserService;
import com.alligator.realwar.util.ValidatorUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int save(UserDto userDto) {

        UserDo userDo = new UserDo();

        //todo 浅拷贝，要求对象属性名必须一致,属性名相同才能拷贝
        BeanUtils.copyProperties(userDto,userDo);

        userDo.setSalt("qwer");
        userDo.setCreated(LocalDateTime.now());
        userDo.setModified(LocalDateTime.now());

        return userDao.insert(userDo);
    }

    @Override
    public int update(int id, UserDto userDto) {

        UserDo userDo = new UserDo();

        BeanUtils.copyProperties(userDto,userDo);

        userDo.setId(id);
        userDo.setModified(LocalDateTime.now());

        return userDao.updateById(userDo);
    }

    @Override
    public int delete(int id) {

        return userDao.deleteById(id);
    }

    @Override
    public PageResult<List<UserDto>> query(PageQuery<UserQueryDto> pageQuery) {

        //手动校验
        ValidatorUtils.validate(pageQuery);

        Page<?> page = PageHelper.startPage(pageQuery.getPageNo(),pageQuery.getPageSize());

        //根据userquerydto查询
        UserQueryDto query = pageQuery.getQuery();
        List<UserDo> userDos = userDao.query(query);

        //赋值
        List<UserDto> userDtos = Optional.ofNullable(userDos)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDo -> {
                    UserDto userDto = new UserDto();
                    BeanUtils.copyProperties(userDo, userDto);

                    return userDto;
                })
                .collect(Collectors.toList());

        PageResult<List<UserDto>> pages = new PageResult<>();

        pages.setPageNo(pageQuery.getPageNo());
        pages.setPageSize(pageQuery.getPageSize());
        pages.setData(userDtos);

        return pages;
    }
}
