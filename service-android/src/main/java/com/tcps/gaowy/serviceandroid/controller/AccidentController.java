package com.tcps.gaowy.serviceandroid.controller;

import com.tcps.gaowy.basecore.page.Page;
import com.tcps.gaowy.basecore.page.PageInfo;
import com.tcps.gaowy.basecore.utils.Result;
import com.tcps.gaowy.basecore.utils.ResultUtil;
import com.tcps.gaowy.serviceandroid.accident.AccidentDTO;
import com.tcps.gaowy.serviceandroid.accident.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/accident")
public class AccidentController {

    @Autowired
    AccidentService accidentService;

    @GetMapping(value = "/getAccident")
    public List<AccidentDTO> listForPage(AccidentDTO accidentDTOPage, PageInfo pageInfo) {
        return accidentService.listForPage(accidentDTOPage,pageInfo);
    }

    @GetMapping(value = "/accidents")
    public List<AccidentDTO> listAll() {
        return accidentService.listAll();
    }

    @PostMapping(value = "/saveAccident")
    public Result<AccidentDTO> save(AccidentDTO accidentDTO) {
        accidentService.save(accidentDTO);
        return ResultUtil.success();
    }

    @PutMapping(value = "/updateAccident")
    public Result<AccidentDTO> update(AccidentDTO accidentDTO) {
        accidentService.update(accidentDTO);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "/deletAccident")
    public Result delete() {
        accidentService.delete("");
        return ResultUtil.success();
    }

}
