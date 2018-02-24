package com.tcps.gaowy.serviceandroid.controller;

import com.tcps.gaowy.basecore.page.PageInfo;
import com.tcps.gaowy.basecore.utils.Result;
import com.tcps.gaowy.basecore.utils.ResultUtil;
import com.tcps.gaowy.serviceandroid.accident.AccidentDTO;
import com.tcps.gaowy.serviceandroid.accident.AccidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/accident")
public class AccidentController {

    @Autowired
    AccidentService accidentService;

    @GetMapping(value = "/list")
    public List<AccidentDTO> listForPage(AccidentDTO accidentDTOPage, PageInfo pageInfo) {
        //Long date = new Date().getTime();
        return accidentService.listForPage(accidentDTOPage, pageInfo);
    }

    @GetMapping(value = "/{id}")
    public AccidentDTO findByid(@PathVariable String id) {
        return accidentService.findById(id);
    }

    @PostMapping
    public Result<AccidentDTO> save(@Valid @RequestBody AccidentDTO accidentDTO, BindingResult errors) {
        if (errors.hasErrors()) {
//            errors.getAllErrors().stream().forEach(error -> {
//                FieldError fieldError = (FieldError) error;
//                String message = fieldError.getField() + ":" + error.getDefaultMessage();
//                log.info(String.valueOf(message));
//            });
            return ResultUtil.error(0, errors.getAllErrors().toString());
        }
        accidentService.save(accidentDTO);
        return ResultUtil.success();
    }

    @PutMapping
    public Result<AccidentDTO> update(AccidentDTO accidentDTO) {
        accidentService.update(accidentDTO);
        return ResultUtil.success();
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        accidentService.delete(id);
        return ResultUtil.success();
    }

}
