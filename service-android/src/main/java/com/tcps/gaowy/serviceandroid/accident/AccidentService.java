package com.tcps.gaowy.serviceandroid.accident;

import com.tcps.gaowy.basecore.page.Page;

public interface AccidentService {

    Page<AccidentDTO> listForPage(Page<AccidentDTO> accidentDTO);

    void save(AccidentDTO accidentDTO);

    void update(AccidentDTO accidentDTO);

    void delete(String id);
}
