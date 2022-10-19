package me.elyor.springtips.mybatis.typehandler.user;

import me.elyor.springtips.mybatis.typehandler.Status;
import me.elyor.springtips.mybatis.typehandler.UserType;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Sql("classpath:db/user.sql")
@MybatisTest
class UserMapperTests {

    @Autowired
    UserMapper mapper;

    @Test
    void whenSaveOneExpectNoError() {
        User user = buildUser("user", Status.INACTIVE, UserType.USER);
        assertDoesNotThrow(() -> mapper.saveOne(user));
    }

    @Test
    void whenFindByNameThenExpectUser() {
        String name = "elyor";
        User expected = buildUser(name, Status.ACTIVE, UserType.ADMIN);
        assertDoesNotThrow(() -> mapper.saveOne(expected));

        Optional<User> returned = mapper.findByName(name);
        assertTrue(returned.isPresent());
        assertEquals(expected.getName(), returned.get().getName());
    }

    @Test
    void whenSaveAndReadExpectCorrectStatusConversion() {
        String name = "admin";
        User user = buildUser(name, Status.ACTIVE, UserType.ADMIN);
        assertDoesNotThrow(() -> mapper.saveOne(user));

        //check if saved as integer(code)
        Optional<Integer> returnedCode = mapper.findStatusCodeByName(name);
        assertTrue(returnedCode.isPresent());
        assertEquals(returnedCode.get(), Status.ACTIVE.getCode());

        //check if read as enum type
        Optional<Status> returnedStatus = mapper.findStatusByName(name);
        assertTrue(returnedStatus.isPresent());
        assertEquals(returnedStatus.get(), Status.ACTIVE);
    }

    @Test
    void whenSaveAndReadExpectCorrectUserTypeConversion() {
        String name = "user";
        User user = buildUser(name, Status.INACTIVE, UserType.USER);
        assertDoesNotThrow(() -> mapper.saveOne(user));

        //check if saved as integer(code)
        Optional<Integer> returnedTypeCode = mapper.findTypeCodeByName(name);
        assertTrue(returnedTypeCode.isPresent());
        assertEquals(returnedTypeCode.get(), UserType.USER.getCode());

        //check if read as enum type
        Optional<UserType> returnedType = mapper.findTypeByName(name);
        assertTrue(returnedType.isPresent());
        assertEquals(returnedType.get(), UserType.USER);
    }

    private User buildUser(String name, Status status, UserType type) {
        return User.builder()
                .name(name)
                .status(status)
                .type(type).build();
    }

}
