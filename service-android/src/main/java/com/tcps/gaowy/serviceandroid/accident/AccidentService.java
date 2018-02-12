package com.tcps.gaowy.serviceandroid.accident;

import com.tcps.gaowy.basecore.page.Page;

public interface AccidentService {

    void save(AccidentDTO accidentDTO);

    Page<AccidentDTO> listForPage(Page<AccidentDTO> accidentDTO);

    void update(AccidentDTO accidentDTO);

    void delete(String id);
}
