package me.elyor.springtips.mybatis.typehandler;

import lombok.Getter;
import org.apache.ibatis.type.MappedTypes;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Getter
public enum Status implements CodableEnum {
    ACTIVE("활성", 99),
    INACTIVE("비활성", 1),
    DELETED("삭제", 2);

    private final String label;
    private final int code;

    private static final Map<String, Status> stringToEnum =
            Stream.of(values()).collect(toMap(Object::toString, e -> e));
    private static final Map<Integer, Status> codeToEnum =
            Stream.of(values()).collect(toMap(e -> e.code, e -> e));

    Status(String label, int code) {
        this.label = label;
        this.code = code;
    }

    public static Optional<Status> fromString(String status) {
        return Optional.ofNullable(stringToEnum.get(status.toLowerCase()));
    }

    public static Optional<Status> fromCode(int code) {
        return Optional.ofNullable(codeToEnum.get(code));
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    @MappedTypes(Status.class)
    public static class TypeHandler extends CodableEnumTypeHandler<Status> {
        public TypeHandler() {
            super(Status.class);
        }
    }
}
