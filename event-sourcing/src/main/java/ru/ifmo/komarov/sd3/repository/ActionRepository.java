package ru.ifmo.komarov.sd3.repository;

import org.springframework.stereotype.Repository;
import ru.ifmo.komarov.sd3.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
    Boolean existsActionByAccountIdAndType(Long accId, Action.Type type);
    List<Action> findAllByAccountIdAndType(Long accId, Action.Type type);
    List<Action> findAllByIdAfter(Long id);
}
