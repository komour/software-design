package ru.ifmo.komarov.sd3.service;

import org.springframework.stereotype.Component;
import ru.ifmo.komarov.sd3.model.Account;
import ru.ifmo.komarov.sd3.model.Action;
import ru.ifmo.komarov.sd3.repository.ActionRepository;

@Component
public class ManagerService extends BaseService {
    protected ManagerService(ActionRepository actionRepository) {
        super(actionRepository);
    }

    public void addAccount(Account acc) {
        actionRepository.save(new Action(acc.getId(), Action.Type.Register));
    }

    public void addVisits(Long accId, Integer count) {
        actionRepository.save(new Action(accId, Action.Type.AddVisits, count.toString()));
    }

    public Boolean accountExists(Long accId) {
        return actionRepository.existsActionByAccountIdAndType(accId, Action.Type.Register);
    }

    public void deleteAll() {
        actionRepository.deleteAll();
    }
}
