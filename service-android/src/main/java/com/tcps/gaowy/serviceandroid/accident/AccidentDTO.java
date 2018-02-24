package com.tcps.gaowy.serviceandroid.accident;

import com.tcps.gaowy.basecore.dao.BaseDO;
import com.tcps.gaowy.serviceandroid.validator.IllegalLicense;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccidentDTO extends BaseDO {


    private String id;
    private String accidentDate;
    @NotBlank(message = "地址不能为空！")
    private String accidentAddress;
    @IllegalLicense(message = "无法识别的牌照！")
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


