package org.isbel8ai.training.datasources.controller.dto;

import lombok.Builder;
import org.isbel8ai.training.datasources.model.account.Account;
import org.isbel8ai.training.datasources.model.procedure.Procedure;

import java.util.List;

@Builder
public record DataResponse(List<Account> accounts, List<Procedure> procedures) {
}
