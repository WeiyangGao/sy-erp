package com.tcps.gaowy.serviceandroid.accident;

import com.tcps.gaowy.basecore.appexception.ApplicationException;
import com.tcps.gaowy.basecore.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccidentServiceImpl implements AccidentService {

    @Autowired
    AccidentDAO accidentDO;

    @Override
    public void save(AccidentDTO accidentDTO) {
        int licenseCount = accidentDO.haveLicense(accidentDTO.getLicense());
        if (licenseCount < 1) {
            throw new ApplicationException(0, "不存在的车辆牌照！");
        }
        accidentDO.saveAccident(accidentDTO);
    }

    @Override
    public Page<AccidentDTO> listForPage(Page<AccidentDTO> accidentDTO) {
        return accidentDO.listForPage(accidentDTO);
    }

    @Override
    public void update(AccidentDTO accidentDTO) {
        int licenseCount = accidentDO.haveLicense(accidentDTO.getLicense());
        if (licenseCount < 1) {
            throw new ApplicationException(0, "不存在的车辆牌照！");
        }
        accidentDO.update(accidentDTO);
    }

    @Override
    public void delete(String id) {

    }

}
