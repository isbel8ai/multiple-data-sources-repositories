package org.isbel8ai.training.datasources.repository.procedure;

import org.isbel8ai.training.datasources.model.procedure.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
}
