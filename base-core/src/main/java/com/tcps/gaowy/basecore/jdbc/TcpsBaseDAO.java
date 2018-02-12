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

    //protected SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource());

    /**
     * 觉得这种翻页满足不了需求，可以不使用这个方法。自己调用NamedParameterJdbcTemplate,JdbcTemplate完成SQL拼写查询。
     *
     * @param page
     * @param sql
     */
    protected Page<T> queryForPage(Page<T> page, String sql, final SqlParameterSource sqlParameterSource) {
        PageInfo pageInfo = page.getPageInfo();
        T t = page.getBean();
        Class tClass = t.getClass();
        final String tableName = t.tableName();
        int totalCount = jdbcTemplate.queryForObject("SELECT count(1) FROM " + tableName, Integer.class);
        pageInfo.setTotalCount(totalCount);
        sql = sql + " LIMIT " + pageInfo.getPageSize() + " OFFSET " + pageInfo.getPageStartIndex();
        List<T> busTypeList =
                this.namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper<>(tClass));
        page.setResultList(busTypeList);
        System.out.println(jdbcTemplate.getDataSource());
        return page;
    }

    protected Number saveByMapReturnKey(T t, Map<String, Object> params) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource());
        simpleJdbcInsert.withTableName(t.tableName()).usingGeneratedKeyColumns("id");
        return simpleJdbcInsert.executeAndReturnKey(params);
    }

    /**
     * 根据Bean 的属性插入。
     * @param t
     * @param parameterSource
     * @return 返回Id,类型调用者转。
     */
    protected Object saveByBeanReturnKey(T t, SqlParameterSource parameterSource) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource());
        simpleJdbcInsert.withTableName(t.tableName()).usingGeneratedKeyColumns("id");
        KeyHolder keyHolder = simpleJdbcInsert.executeAndReturnKeyHolder(parameterSource);
        Object keyObj = keyHolder.getKeys().get("ID");
        return keyObj;
    }


}
