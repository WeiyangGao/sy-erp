package com.tcps.gaowy.serviceandroid.accident;


import com.tcps.gaowy.basecore.jdbc.TcpsBaseDAO;
import com.tcps.gaowy.basecore.page.Page;
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

    public AccidentDTO saveAccident(AccidentDTO accidentDTO) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(accidentDTO);
        //如果想获得刚才存入的数据的ID.
        Object id = saveByBeanReturnKey(accidentDTO, parameterSource);
        accidentDTO.setId(id.toString());
        return accidentDTO;

    }

    public Page<AccidentDTO> listForPage(final Page<AccidentDTO> accidentDTOPage) {
        AccidentDTO accidentDTO = accidentDTOPage.getBean();
        String sql = "select * from TAB_SECURITY_ACCI_REG where 1=1 ";
        log.info(sql);
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(accidentDTO);
        return queryForPage(accidentDTOPage, sql, parameterSource);

    }

    public List<AccidentDTO> getDeptInfoByLicense(AccidentDTO accidentDTO) {
        String sql = "SELECT * FROM TAB_BUS_INFO WHERE LICENSE=:license";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("license", accidentDTO.getLicense());
        return namedParameterJdbcTemplate.query(sql, parameterSource, new BeanPropertyRowMapper<>(AccidentDTO.class));
    }

    public Integer haveLicense(String license) {
        String sql = "SELECT count(1) FROM tab_bus_info WHERE license = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{license}, Integer.class);
    }

    public Integer update(AccidentDTO accidentDTO) {
        String sql = "UPDATE TAB_SECURITY_ACCI_REG t " +
                "SET t.accident_date = :accidentDate," +
                "t.accident_time = :accidentTime," +
                "t.license = :license," +
                "t.accident_place =:accidentPlace";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(accidentDTO);
        return namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public Integer delete(String id) {
        String sql = "DELETE FROM TAB_SECURITY_ACCI_REG WHERE id = ?";
        return jdbcTemplate.update(sql, new Object[]{id}, Integer.class);
    }

    /**
     * id可以先存临时表里，再次调用时从临时表里取出。
     * @param actors
     * @return
     * @throws SQLException
     */
    public int[][] batchUpdate(final Collection<AccidentDTO> actors) throws SQLException {
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
}
