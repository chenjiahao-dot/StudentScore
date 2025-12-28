package org.example.Demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.Demo.entity.User.User;
@Mapper
public interface UserManageMapper extends BaseMapper<User> {
}
