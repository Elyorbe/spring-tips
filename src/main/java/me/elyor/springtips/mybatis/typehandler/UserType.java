package me.elyor.springtips.mybatis.typehandler;

import lombok.Getter;
import org.apache.ibatis.type.MappedTypes;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Getter
public enum UserType implements CodableEnum {
    ADMIN("관리자", 1),
    USER("일반사용자", 2);

    private final String label;
    private final int code;

    private static final Map<String, UserType> stringToEnum =
            Stream.of(values()).collect(toMap(Object::toString, e -> e));

    private static final Map<Integer, UserType> codeToEnum =
            Stream.of(values()).collect(toMap(e -> e.code, e -> e));

    UserType(String label, int code) {
        this.label = label;
        this.code = code;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    @MappedTypes(UserType.class)
    public static class TypeHandler extends CodableEnumTypeHandler<UserType> {
        public TypeHandler() {
            super(UserType.class);
        }
    }
}
