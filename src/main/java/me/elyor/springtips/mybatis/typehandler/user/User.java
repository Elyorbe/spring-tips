package me.elyor.springtips.mybatis.typehandler.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.elyor.springtips.mybatis.typehandler.Status;
import me.elyor.springtips.mybatis.typehandler.UserType;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private long id;
    private String name;
    private Status status;
    private UserType type;

}
