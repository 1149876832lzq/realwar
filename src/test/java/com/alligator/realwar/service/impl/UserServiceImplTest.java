package com.alligator.realwar.service.impl;

import com.alligator.realwar.domain.common.PageQuery;
import com.alligator.realwar.domain.common.PageResult;
import com.alligator.realwar.domain.dto.UserDto;
import com.alligator.realwar.domain.dto.UserQueryDto;
import com.alligator.realwar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveTest(){

        UserDto userDto = new UserDto();
        userDto.setUsername("张三");
        userDto.setPassword("123");
        userDto.setAge(23);
        userDto.setEmail("135@qq.com");
        userDto.setPhone("17555555555");

        int save = userService.save(userDto);

        log.info("{}",save);

    }

    @Test
    public void selectTest(){
        UserQueryDto userQueryDto = new UserQueryDto();
        userQueryDto.setUsername("张三");

        PageQuery<UserQueryDto> dtos = new PageQuery<>();
        dtos.setPageNo(1);
        dtos.setPageSize(5);
        dtos.setQuery(userQueryDto);

        PageResult<List<UserDto>> query = userService.query(dtos);

        System.out.println(query.getData());
    }

    @Test
    public void deleteTest() {

        int delete = userService.delete(2);

//        System.out.println(delete);

        log.info("[{}]",delete);
    }
}

