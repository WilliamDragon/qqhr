package com.qqhr.dao.mapper;



import com.qqhr.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    List<User> login(@Param("userName") String userName, @Param("password")String password);

    //@Select("select * from user")
    List<User> findAll();

    List<User> findAllByCondition(User user);
    @Delete("delete from user where id = #{id}")
    int deleteById(@Param("id")String id);

    int save(User user);

    int findUserById(String id);
    //@Select("select * from user where login_name = #{username}")
    User findByUserName(String username);

    List<User> findListUser(Map param);
}