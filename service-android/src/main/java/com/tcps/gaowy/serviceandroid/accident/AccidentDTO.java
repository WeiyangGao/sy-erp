package com.tcps.gaowy.serviceandroid.accident;

import com.tcps.gaowy.basecore.dao.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccidentDTO extends BaseDO {

    private String serialNo;
    private Date accidentDate;
    private Date accidentTime;
    private String accidentPlace;
    private String license;
    private String deptNo;
    private String groupNo;
    private String lineNo;

    @Override
    public String tableName() {
        return "TAB_SEC URITY_ACCI_REG";
    }

}


