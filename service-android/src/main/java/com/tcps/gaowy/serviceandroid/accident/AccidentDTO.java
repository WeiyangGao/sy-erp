package com.tcps.gaowy.serviceandroid.accident;

import com.tcps.gaowy.basecore.dao.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccidentDTO extends BaseDO {

    private String id;
    private Date accidentDate;
    private String accidentAddress;
    private String license;
    private String accidentNote;
    private Long accidentImgNo;
    private Date op_date;
    private String op_no;

    @Override
    public String tableName() {
        return "android_bus_accident";
    }

}


