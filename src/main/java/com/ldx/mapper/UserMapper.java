package com.ldx.mapper;

import com.ldx.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Set;

/**
 * @author ldx
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2022-09-01 17:20:51
 * @Entity com.ldx.pojo.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT r_id from user_role where u_id=#{userId}")
    List<Integer> getRolesId(int userId);

    @Select({"<script> SELECT role from roles where id in <foreach collection='roleIds' item='id' open='(' separator=',' close=')'> #{id} </foreach> </script>"})
    Set<String> getRolesByIds(@Param("roleIds") List<Integer> roleIds);

    @Insert("INSERT INTO user_role(r_id, u_id) values(#{roleId},#{userId})")
    Boolean setRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}




