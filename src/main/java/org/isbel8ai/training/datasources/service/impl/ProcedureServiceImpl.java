package org.isbel8ai.training.datasources.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.isbel8ai.training.datasources.model.procedure.Procedure;
import org.isbel8ai.training.datasources.repository.procedure.ProcedureRepository;
import org.isbel8ai.training.datasources.service.ProcedureService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;

    @PostConstruct
    public void init() {
        Procedure procedure = Procedure.builder().build();
        procedureRepository.save(procedure);
    }

    public List<Procedure> getProcedures() {
        return procedureRepository.findAll();
    }
}