package me.elyor.springtips.mybatis.typehandler.user;

import me.elyor.springtips.mybatis.typehandler.Status;
import me.elyor.springtips.mybatis.typehandler.UserType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(name, status, type) VALUES(#{name}, #{status}, #{type})")
    void saveOne(User user);

    @Select("SELECT * FROM user WHERE name = #{name}")
    Optional<User> findByName(String name);

    @Select("SELECT status FROM user WHERE name =#{name}")
    Optional<Status> findStatusByName(String name);

    @Select("SELECT status FROM user WHERE name = #{name}")
    Optional<Integer> findStatusCodeByName(String name);

    @Select("SELECT type FROM user WHERE name =#{name}")
    Optional<UserType> findTypeByName(String name);

    @Select("SELECT type FROM user WHERE name = #{name}")
    Optional<Integer> findTypeCodeByName(String name);

}
