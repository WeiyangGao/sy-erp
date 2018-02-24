package com.tcps.gaowy.serviceandroid.accident;

import com.tcps.gaowy.basecore.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//每个方法一个事物
@Transactional
public class AccidentServiceImpl implements AccidentService {

    @Autowired
    AccidentDAO accidentDAO;

    @Override
    public AccidentDTO findById(String id) {
        return accidentDAO.findById(id);
    }

    @Override
    public List<AccidentDTO> listForPage(AccidentDTO accidentDTO, PageInfo pageInfo) {
        return accidentDAO.listForPage(accidentDTO, pageInfo);
    }

    @Override
    public void save(AccidentDTO accidentDTO) {
        accidentDAO.saveAccident(accidentDTO);
    }

    @Override
    public void update(AccidentDTO accidentDTO) {
        accidentDAO.update(accidentDTO);
    }

    @Override
    public void delete(String id) {
        accidentDAO.delete(id);
    }

}
