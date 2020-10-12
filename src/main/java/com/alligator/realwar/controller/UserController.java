package com.alligator.realwar.controller;

import com.alligator.realwar.domain.common.PageQuery;
import com.alligator.realwar.domain.common.PageResult;
import com.alligator.realwar.domain.common.ResponseResult;
import com.alligator.realwar.domain.dto.UserDto;
import com.alligator.realwar.domain.dto.UserQueryDto;
import com.alligator.realwar.domain.vo.UserVo;
import com.alligator.realwar.exception.ErrorCodeEnum;
import com.alligator.realwar.service.UserService;
import com.alligator.realwar.util.InsertValidationGroup;
import com.alligator.realwar.util.UpdateValidationGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/users")
//开启基础类型的验证
@Validated
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Post /api/users UserDto
     * 新建用户
     */
    @CacheEvict(cacheNames = "users-cache", allEntries = true)
    @PostMapping
    public ResponseResult save(@Validated(InsertValidationGroup.class)
                                   @RequestBody UserDto userDto) {

        int save = userService.save(userDto);

        if(save == 1) {
            return ResponseResult.success("新增成功");
        }else {
            return ResponseResult.failure(ErrorCodeEnum.INSERT_FAILURE);
        }
    }

    /**
     * PUT /api/users/{id} UserDto userDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseResult update(@NotNull @PathVariable("id") Integer id,
                                 @Validated(UpdateValidationGroup.class) @RequestBody UserDto userDto) {

        int update = userService.update(id, userDto);

        if (update == 1) {
            return ResponseResult.success(ErrorCodeEnum.SUCCESS);
        }else {
            return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 删除用户信息
     * delete /api/users/{id}
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult delete(@NotNull @PathVariable("id") Integer id) {

        int delete = userService.delete(id);

        if (delete == 1) {
            return ResponseResult.success("删除成功");
        }else {
            return ResponseResult.failure(ErrorCodeEnum.DELETE_FAILURE);
        }

    }

    /**
     * 查询用户信息
     * 如果不设置cachename，默认使用几个参数进行hash之后的hash值作为key，数据作为缓存
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Cacheable(cacheNames = "users-cache")
    @GetMapping
    public ResponseResult<PageResult> query(@NotNull Integer pageNo,
                                            @NotNull Integer pageSize,
                                            @Validated UserQueryDto query) {

        //构造查询条件
        PageQuery<UserQueryDto> userQueryDtoPageQuery = new PageQuery<>();
        userQueryDtoPageQuery.setPageNo(pageNo);
        userQueryDtoPageQuery.setPageSize(pageSize);
        userQueryDtoPageQuery.setQuery(query);

        //查询
        PageResult<List<UserDto>> pageResult = userService.query(userQueryDtoPageQuery);

        //实体转换
        List<UserVo> userVos = Optional.ofNullable(pageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDto -> {
                    UserVo userVo = new UserVo();

                    BeanUtils.copyProperties(userDto, userVo);
                    //对特殊字段做处理，密码
                    userVo.setPassword("******");
                    userVo.setPhone(userDto.getPhone()
                            .replace("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));

                    return userVo;
                })
                .collect(Collectors.toList());

        //封装返回结果
        PageResult<List<UserVo>> result = new PageResult<>();
        BeanUtils.copyProperties(pageResult,result);
        result.setData(userVos);

        return ResponseResult.success(result);
    }

}
