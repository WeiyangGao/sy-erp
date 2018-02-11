package com.tcps.gaowy.serviceandroid.controller;

import com.tcps.gaowy.basecore.page.Page;
import com.tcps.gaowy.basecore.utils.Result;
import com.tcps.gaowy.basecore.utils.ResultUtil;
import com.tcps.gaowy.serviceandroid.accident.AccidentBean;
import com.tcps.gaowy.serviceandroid.accident.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accident")
public class AccidentController {

    @Autowired
    AccidentService accidentService;

    @PostMapping
    public Result<AccidentBean> save(AccidentBean accidentDTO) {
        accidentService.save(accidentDTO);
        return ResultUtil.success();
    }

    @GetMapping
    public Page<AccidentBean> listForPage(Page<AccidentBean> accidentDTOPage) {
        return accidentService.listForPage(accidentDTOPage);
    }

    @PutMapping
    public Result<AccidentBean> update(AccidentBean accidentDTO) {
        accidentService.update(accidentDTO);
        return ResultUtil.success();
    }

}
