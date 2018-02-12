package com.tcps.gaowy.serviceandroid.accident;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AccidentServiceTest {

    @Autowired
    private AccidentService accidentService;

    @Test
    public void save() {
        AccidentDTO accidentDTO = new AccidentDTO();
        accidentDTO.setAccidentAddress("南京南街。");
        accidentDTO.setAccidentDate(new Date());
        accidentDTO.setLicense("鄂F2869学");
        accidentDTO.setOp_no("1");
        accidentDTO.setOp_date(new Date());
        accidentDTO.setAccidentNote("备注1");
        accidentDTO.setAccidentImgNo(111L);
        accidentService.save(accidentDTO);
        log.info("id = {}", accidentDTO.getId());
    }

    @Test
    public void listForPage() {

    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {

    }
}