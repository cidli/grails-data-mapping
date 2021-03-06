package org.grails.orm.hibernate

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types

import org.hibernate.engine.spi.SessionImplementor

class TestEnumUserType extends AbstractUserType {

	private static final int[] SQL_TYPES = [Types.INTEGER]

	int[] sqlTypes() { SQL_TYPES }
	Class returnedClass() { TestEnum }

	Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws SQLException {
		int number = rs.getInt(names[0])
		rs.wasNull() ? null : TestEnum.byValue(number / 100)
	}

	void nullSafeSet(PreparedStatement ps, value, int index, SessionImplementor session) throws SQLException {
		if (value == null) {
			ps.setNull index, Types.INTEGER
		}
		else {
			ps.setInt index, ((TestEnum)value).value * 100
		}
	}
}
