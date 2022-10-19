package me.elyor.springtips.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Enum type handler for MyBatis.
 * Enum class must implement {@link CodableEnum} interface.
 * <p>
 * <p>
 *     Usage example:
 * </p>
 *
 * <pre class="code">
 * public enum Status implements CodableEnum {
 *     ACTIVE("활성", 99),
 *
 *     private final String label;
 *     private final int code;
 *
 *     Status(String label, int code) {
 *         this.label = label;
 *         this.code = code;
 *     }
 *
 *     &#064;Override
 *     public int getCode() {
 *         return this.code;
 *     }
 *
 *     &#064;MappedTypes(Status.class)
 *     public static class TypeHandler extends CodableEnumTypeHandler<Status> {
 *         public TypeHandler() {
 *             super(Status.class);
 *         }
 *     }
 * }
 * </pre>
 *
 */
public class CodableEnumTypeHandler <E extends Enum<E>> implements TypeHandler<CodableEnum> {
    private Class<E> type;

    public CodableEnumTypeHandler(Class<E> type) {
        this.type = type;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, CodableEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public CodableEnum getResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return fromCode(code);
    }

    @Override
    public CodableEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return fromCode(code);
    }

    @Override
    public CodableEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return fromCode(code);
    }

    private CodableEnum fromCode(int code) {
        CodableEnum[] enums = (CodableEnum[]) type.getEnumConstants();
        return Arrays.stream(enums)
                .filter(c -> c.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new TypeException("Can't make enum object " + type + "from " + code));
    }
}
