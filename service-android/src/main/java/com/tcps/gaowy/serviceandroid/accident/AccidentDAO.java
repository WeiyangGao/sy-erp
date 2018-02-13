package com.tcps.gaowy.serviceandroid.accident;


import com.tcps.gaowy.basecore.jdbc.TcpsBaseDAO;
import com.tcps.gaowy.basecore.page.Page;
import com.tcps.gaowy.basecore.page.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@Slf4j
public class AccidentDAO extends TcpsBaseDAO<AccidentDTO> {

    protected AccidentDTO saveAccident(AccidentDTO accidentDTO) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(accidentDTO);
        //如果想获得刚才存入的数据的ID.
        Object id = saveByBeanReturnKey(accidentDTO, parameterSource);
        accidentDTO.setId(id.toString());
        return accidentDTO;

    }

    protected List<AccidentDTO> listForPage(final AccidentDTO accidentDTO, PageInfo pageInfo) {
        pageInfo.setOrderBy("order by id asc");
        String sql = "select * from TAB_SECURITY_ACCI_REG where 1=1 ";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(accidentDTO);
        return queryForPage(accidentDTO,pageInfo, sql, parameterSource);

    }

    protected List<AccidentDTO> listAll() {
        String sql = " SELECT * FROM ANDROID_BUS_ACCIDENT";
        //SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("status", accidentDTO.getStatus());
        return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AccidentDTO.class));
    }

    protected List<AccidentDTO> getDeptInfoByLicense(AccidentDTO accidentDTO) {
        String sql = "SELECT * FROM TAB_BUS_INFO WHERE LICENSE=:license";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("license", accidentDTO.getLicense());
        return namedParameterJdbcTemplate.query(sql, parameterSource, new BeanPropertyRowMapper<>(AccidentDTO.class));
    }

    protected Integer haveLicense(String license) {
        String sql = "SELECT count(1) FROM tab_bus_info WHERE license = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{license}, Integer.class);
    }

    protected Integer update(AccidentDTO accidentDTO) {
        String sql = "UPDATE TAB_SECURITY_ACCI_REG t " +
                "SET t.accident_date = :accidentDate," +
                "t.accident_time = :accidentTime," +
                "t.license = :license," +
                "t.accident_place =:accidentPlace";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(accidentDTO);
        return namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    protected Integer delete(String id) {
        String sql = "DELETE FROM TAB_SECURITY_ACCI_REG WHERE id = ?";
        return jdbcTemplate.update(sql, new Object[]{id}, Integer.class);
    }

    /**
     * 批量导入，可控制每次导入数据量。
     * id可以先存临时表里，再次调用时从临时表里取出。
     *
     * @param actors
     * @return
     * @throws SQLException
     */
    protected int[][] batchUpdate(final Collection<AccidentDTO> actors) throws SQLException {
        String sql = "INSERT INTO ANDROID_BUS_ACCIDENT (ACCIDENT_ADDRESS,ACCIDENT_NOTE,LICENSE) VALUES (?,?,?)";
        //数组第一个，批量执行的次数，第二个，影响行数。
        int[][] updateCounts = jdbcTemplate.batchUpdate(
                sql,
                actors,
                100,
                new ParameterizedPreparedStatementSetter<AccidentDTO>() {
                    public void setValues(PreparedStatement ps, AccidentDTO accidentDTO) throws SQLException {
                        ps.setString(1, accidentDTO.getId());
                        ps.setString(2, accidentDTO.getAccidentNote());
                        ps.setString(3, accidentDTO.getLicense());
                    }
                });
        return updateCounts;
    }

    protected int[] batchSave(List<AccidentDTO> deptDTOList) {
        String sql = "INSERT INTO ANDROID_BUS_ACCIDENT(ACCIDENT_NOTE,ACCIDENT_ADDRESS,ACCIDENT_DATE) " +
                "VALUES (:accidentNote,:accidentAddress,:accidentDate)";
        return namedParameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(deptDTOList.toArray()));
    }

    protected int[] batchUpateStatus(List<AccidentDTO> deptDTOList) {
        String sql = "UPDATE ANDROID_BUS_ACCIDENT SET ACCIDENT_NOTE=:accidentNote WHERE id = :id";
        return namedParameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(deptDTOList.toArray()));
    }
}
