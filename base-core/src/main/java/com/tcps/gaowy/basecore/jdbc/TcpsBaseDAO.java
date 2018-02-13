package com.tcps.gaowy.basecore.jdbc;

import com.tcps.gaowy.basecore.dao.BaseDO;
import com.tcps.gaowy.basecore.page.Page;
import com.tcps.gaowy.basecore.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 处理分页,返回Page分页类，组装结果在service中进行。
 * service中getList 和 Page
 * gaoweiyang 20180110
 */
public abstract class TcpsBaseDAO<T extends BaseDO> {

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /**
     * 翻页查询。
     *
     * @param t
     * @param pageInfo
     * @param sql
     */
    @SuppressWarnings("unchecked")
    protected List<T> queryForPage(final T t, final PageInfo pageInfo, String sql, final SqlParameterSource sqlParameterSource) {
        Class tClass = t.getClass();
        final String tableName = t.tableName();
        int totalCount = jdbcTemplate.queryForObject("SELECT count(1) FROM " + tableName, Integer.class);
        pageInfo.setTotalCount(totalCount);
        sql = sql + " LIMIT " + pageInfo.getPageSize() + " OFFSET " + pageInfo.getPageStartIndex();
        if (!StringUtils.isEmpty(pageInfo.getOrderBy())) {
            sql = sql + pageInfo.getOrderBy();
        }
        return this.namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper(tClass));
    }

    /**
     * 根据Map绑定列名与值。
     *
     * @param t
     * @param params
     * @return
     */
    @SuppressWarnings("JavaDoc")
    protected Number saveByMapReturnKey(T t, Map<String, Object> params) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource());
        simpleJdbcInsert.withTableName(t.tableName()).usingGeneratedKeyColumns("id");
        return simpleJdbcInsert.executeAndReturnKey(params);
    }

    /**
     * 根据Bean 的属性插入。
     *
     * @param t
     * @param parameterSource
     * @return 返回Id, 类型调用者转。
     */
    protected Object saveByBeanReturnKey(T t, SqlParameterSource parameterSource) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource());
        simpleJdbcInsert.withTableName(t.tableName()).usingGeneratedKeyColumns("id");
        KeyHolder keyHolder = simpleJdbcInsert.executeAndReturnKeyHolder(parameterSource);
        Object keyObj = keyHolder.getKeys().get("ID");
        return keyObj;
    }


}
