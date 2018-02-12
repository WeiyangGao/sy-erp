package com.tcps.gaowy.serviceandroid.accident;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AccidentDAOTest {

    @Autowired
    private AccidentDAO accidentDAO;

    @Test
    public void batchUpdate() throws SQLException {
        List<AccidentDTO> accidentDTOList = initData();
        int[][] id = accidentDAO.batchUpdate(accidentDTOList);
        for (int i = 0; i < id.length; i++) {
            for (int j = 0; j < id[i].length; j++) {
                log.info(i + ":" + id[i][j] + "");
            }
        }
    }

    private List<AccidentDTO> initData() {
        List<AccidentDTO> accidentDTOList = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            AccidentDTO accidentDTO = new AccidentDTO();
            accidentDTO.setAccidentAddress("街道" + i);
            accidentDTO.setLicense("牌照" + i);
            accidentDTO.setAccidentNote("备注" + i);
            accidentDTOList.add(accidentDTO);
        }
        return accidentDTOList;
    }
}