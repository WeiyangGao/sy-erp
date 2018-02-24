package com.tcps.gaowy.basecore.jdbc;

import com.tcps.gaowy.basecore.dao.BaseDO;
import com.tcps.gaowy.basecore.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 分页查询基类
 * gaoweiyang 20180110
 */
public abstract class TcpsBaseDAO<T extends BaseDO> {

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    //执行SQL
    @SuppressWarnings("unchecked")
    private List<T> excuteSQL(final T t, final PageInfo pageInfo, String sql, final SqlParameterSource sqlParameterSource) {
        Class tClass = t.getClass();
        return this.namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper(tClass));
    }

    //组织翻页信息
    private PageInfo countPageInfo(final T t, final PageInfo pageInfo) {
        final String tableName = t.tableName();
        int totalCount = jdbcTemplate.queryForObject("SELECT count(1) FROM " + tableName, Integer.class);
        pageInfo.setTotalCount(totalCount);
        return pageInfo;
    }

    //拼接Oralcle翻页，排序SQL
    protected List<T> queryByPageForOracle(final T t, final PageInfo pageInfo, String sql, final SqlParameterSource sqlParameterSource) {
        countPageInfo(t, pageInfo);
        int pageStartIndex = pageInfo.getPageStartIndex();
        int pageEndIndex = pageInfo.getPageEndIndex();
        String finalSQL = "SELECT * FROM (SELECT tt.*, ROWNUM AS rowno FROM ";
        finalSQL += "(" + sql + ") tt ";
        finalSQL += " WHERE ROWNUM <= " + pageEndIndex + ") table_alias";
        finalSQL += " WHERE table_alias.rowno >= " + pageStartIndex;
        return excuteSQL(t, pageInfo, finalSQL, sqlParameterSource);
    }

    //拼接Postgre翻页，排序SQL
    protected List<T> queryByPageForPostgre(final T t, final PageInfo pageInfo, String sql, final SqlParameterSource sqlParameterSource) {
        countPageInfo(t, pageInfo);
        sql = sql + " LIMIT " + pageInfo.getPageSize() + " OFFSET " + pageInfo.getPageStartIndex();
        if (!StringUtils.isEmpty(pageInfo.getOrderBy())) {
            sql = sql + " " + pageInfo.getOrderBy();
        }
        return excuteSQL(t, pageInfo, sql, sqlParameterSource);
    }

    //根据Map保存字段返回数据类型ID。（不常用）
    protected Number saveByMapReturnKey(T t, Map<String, Object> params) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource());
        simpleJdbcInsert.withTableName(t.tableName()).usingGeneratedKeyColumns("id");
        return simpleJdbcInsert.executeAndReturnKey(params);
    }

    //根据DTO保存字段返回任意类型ID。
    protected Object saveByBeanReturnKey(T t, SqlParameterSource parameterSource) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource());
        simpleJdbcInsert.withTableName(t.tableName()).usingGeneratedKeyColumns("id");
        KeyHolder keyHolder = simpleJdbcInsert.executeAndReturnKeyHolder(parameterSource);
        Object keyObj = keyHolder.getKeys().get("ID");
        return keyObj;
    }
}
