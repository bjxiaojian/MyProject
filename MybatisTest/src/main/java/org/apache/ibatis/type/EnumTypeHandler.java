package org.apache.ibatis.type;


import com.xxx.util.EnumUtil;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumTypeHandler extends BaseTypeHandler<EnumUtil.ValuedEnum> {
	private Method valueOfMethod;
	private Class<? extends EnumUtil.ValuedEnum> type;

	public EnumTypeHandler(Class<? extends EnumUtil.ValuedEnum> type) {
		if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
		this.type = type;
		try {
//			valueOfMethod = EnumUtil.class.getMethod("getEnum", new Class[] { Class.class, int.class });
			valueOfMethod = EnumUtil.class.getMethod("getEnum", new Class[] { Class.class, String.class });
		} catch (Exception e) {
			throw new TypeException("Failed to obtain valueOf method", e);
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, EnumUtil.ValuedEnum parameter, JdbcType jdbcType) throws SQLException {
//		ps.setByte(i, (byte) parameter.value());
		ps.setString(i, parameter.extValue());
	}

	@Override
	public EnumUtil.ValuedEnum getResult(ResultSet rs, String columnName) throws SQLException {

		try {

//			byte value = rs.getByte(columnName);
			String value = rs.getString(columnName);
			if (rs.wasNull()) {
				return null;
			} else {
				return (EnumUtil.ValuedEnum)valueOfMethod.invoke(null, type, value);
			}
		} catch (Exception e) {
			throw new TypeException("Exception while invoking valueOf method", e);
		}
	}

	public EnumUtil.ValuedEnum getResult(ResultSet rs, int columnIndex) throws SQLException {

		try {

//			byte value = rs.getByte(columnIndex);
			String value = rs.getString(columnIndex);
			if (rs.wasNull()) {
				return null;
			} else {
				return (EnumUtil.ValuedEnum)valueOfMethod.invoke(null, type, value);
			}
		} catch (Exception e) {
			throw new TypeException("Exception while invoking valueOf method", e);
		}
	}

	public EnumUtil.ValuedEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {

		try {

//			byte value = cs.getByte(columnIndex);
			String value = cs.getString(columnIndex);
			if (cs.wasNull()) {
				return null;
			} else {
				return (EnumUtil.ValuedEnum)valueOfMethod.invoke(null, type, value);
			}
		} catch (Exception e) {
			throw new TypeException("Exception while invoking valueOf method", e);
		}
	}

	@Override
	public EnumUtil.ValuedEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return null;
	}

	@Override
	public EnumUtil.ValuedEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public EnumUtil.ValuedEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return null;
	}
}
