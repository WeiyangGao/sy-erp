package com.tcps.gaowy.serviceandroid.accident;

import com.tcps.gaowy.basecore.page.Page;

public interface AccidentService {

    void save(AccidentBean accidentDTO);

    Page<AccidentBean> listForPage(Page<AccidentBean> accidentDTO);

    void update(AccidentBean accidentDTO);

    void delete(String id);
}
