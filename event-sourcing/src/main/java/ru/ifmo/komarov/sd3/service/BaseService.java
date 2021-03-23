package ru.ifmo.komarov.sd3.service;

import ru.ifmo.komarov.sd3.model.Action;
import ru.ifmo.komarov.sd3.repository.ActionRepository;

import java.util.List;

public class BaseService {
    protected final ActionRepository actionRepository;

    public boolean notExists(Long accId) {
        return !actionRepository.existsActionByAccountIdAndType(accId, Action.Type.Register);
    }

    protected BaseService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public Long getVisitCount(Long accId) {
        List<Action> doneVisits = actionRepository.findAllByAccountIdAndType(accId, Action.Type.Leave);
        return doneVisits == null ? 0L : doneVisits.size();
    }

    public Long getAccountActualVisits(Long accId) {
        long allVisits = 0;
        List<Action> addActions = actionRepository.findAllByAccountIdAndType(accId, Action.Type.AddVisits);
        if (addActions != null) {
            for (Action action : addActions) {
                allVisits += Long.parseLong(action.getData(), 10);
            }
        }
        return allVisits - getVisitCount(accId);
    }

    public Boolean isVisitNow(Long accId) {
        List<Action> enterVisits = actionRepository.findAllByAccountIdAndType(accId, Action.Type.Enter);
        return enterVisits != null && enterVisits.size() == getVisitCount(accId) + 1;
    }
}
