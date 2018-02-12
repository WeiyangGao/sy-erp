package com.tcps.gaowy.serviceandroid.accident;


import com.tcps.gaowy.basecore.jdbc.TcpsBaseDAO;
import com.tcps.gaowy.basecore.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Slf4j
public class AccidentDAO extends TcpsBaseDAO<AccidentDTO> {

    public AccidentDTO saveAccident(AccidentDTO accidentDTO) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(accidentDTO);
        Number id = saveByBeanReturnKey(accidentDTO, parameterSource);
        accidentDTO.setSerialNo(id.toString());
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
}
