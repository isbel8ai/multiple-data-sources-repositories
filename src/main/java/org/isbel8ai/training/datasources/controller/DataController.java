package org.isbel8ai.training.datasources.controller;

import lombok.RequiredArgsConstructor;
import org.isbel8ai.training.datasources.controller.dto.DataResponse;
import org.isbel8ai.training.datasources.service.AccountService;
import org.isbel8ai.training.datasources.service.ProcedureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("data")
@RequiredArgsConstructor
public class DataController {

    private final AccountService accountService;
    private final ProcedureService procedureService;

    @GetMapping
    public DataResponse getData() {
        return DataResponse.builder()
                .accounts(accountService.getAccounts())
                .procedures(procedureService.getProcedures())
                .build();
    }
}
