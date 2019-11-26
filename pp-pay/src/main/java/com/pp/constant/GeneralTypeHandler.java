package com.pp.constant;

import common.status.BaseStatusEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneralTypeHandler<T extends BaseStatusEnum> extends BaseTypeHandler<BaseStatusEnum> {
    private Class<T> type;
    private T[] enums;

    public GeneralTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }

        this.type = type;
        this.enums = this.type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BaseStatusEnum parameter, JdbcType jdbcType)
            throws SQLException {
        if (jdbcType == null) {
            // 当状态值等于全部的时为空
            ps.setObject(i,
                    parameter.getValue() == null || parameter.getValue().equals(-1) ? null : parameter.getValue());
        } else {
            ps.setObject(i,
                    parameter.getValue() == null || parameter.getValue().equals(-1) ? null : parameter.getValue(),
                    jdbcType.TYPE_CODE);
        }
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object code = rs.getObject(columnName);

        if (rs.wasNull()) {
            return null;
        }

        return getEnmByCode(code);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object code = rs.getObject(columnIndex);
        if (rs.wasNull()) {
            return null;
        }

        return getEnmByCode(code);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object code = cs.getObject(columnIndex);
        if (cs.wasNull()) {
            return null;
        }

        return getEnmByCode(code);
    }

    private T getEnmByCode(Object code) {
        if (code == null) {
            throw new NullPointerException("the result code is null " + code);
        }

        if (code instanceof Integer) {
            for (T e : enums) {
                if (e.getValue() == code) {
                    return e;
                }
            }
            throw new IllegalArgumentException(
                    "Unknown enumeration type , please check the enumeration code :  " + code);
        }

        if (code instanceof String) {
            for (T e : enums) {
                if (code.equals(e.getValue())) {
                    return e;
                }
            }

            throw new IllegalArgumentException(
                    "Unknown enumeration type , please check the enumeration code :  " + code);
        }

        throw new IllegalArgumentException("Unknown enumeration type , please check the enumeration code :  " + code);
    }
}