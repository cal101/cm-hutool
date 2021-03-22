package cn.hutool.db.dialect.impl;

import cn.hutool.db.Page;
import cn.hutool.db.dialect.DialectName;
import cn.hutool.db.sql.SqlBuilder;

/**
 * Oracle 方言
 * @author loolly
 *
 */
public class OracleDialect extends AnsiSqlDialect{
	private static final long serialVersionUID = 6122761762247483015L;

	@Override
	protected SqlBuilder wrapPageSql(SqlBuilder find, Page page) {
		final int[] startEnd = page.getStartEnd();
		return find
			.insertPreFragment("SELECT * FROM ( SELECT row_.*, rownum rownum_ from ( ")
			.append(" ) row_ where rownum <= ").append(startEnd[1])//
			.append(") table_alias")//
			.append(" where table_alias.rownum_ > ").append(startEnd[0]);//
	}
	
	@Override
	public String dialectName() {
		return DialectName.ORACLE.name();
	}
}
