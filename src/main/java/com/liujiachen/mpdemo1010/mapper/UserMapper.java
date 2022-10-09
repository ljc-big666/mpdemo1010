package com.liujiachen.mpdemo1010.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujiachen.mpdemo1010.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: UserMapper
 * @Description:
 * @date: 2022/9/28 21:03
 */

@Repository
public interface UserMapper extends BaseMapper<User> {
}
