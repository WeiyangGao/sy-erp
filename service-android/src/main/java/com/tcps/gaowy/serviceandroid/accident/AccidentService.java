package com.tcps.gaowy.serviceandroid.accident;

import com.tcps.gaowy.basecore.page.PageInfo;

import java.util.List;

public interface AccidentService {

    AccidentDTO findById(String id);

    List<AccidentDTO> listForPage(AccidentDTO accidentDTO, PageInfo pageInfo);

    void save(AccidentDTO accidentDTO);

    void update(AccidentDTO accidentDTO);

    void delete(String id);

}
