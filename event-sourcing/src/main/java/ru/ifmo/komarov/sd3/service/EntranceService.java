package ru.ifmo.komarov.sd3.service;

import org.springframework.stereotype.Component;
import ru.ifmo.komarov.sd3.model.Action;
import ru.ifmo.komarov.sd3.repository.ActionRepository;

@Component
public class EntranceService extends BaseService {
    private final ReportService reportService;

    protected EntranceService(ActionRepository actionRepository, ReportService reportService) {
        super(actionRepository);
        this.reportService = reportService;
    }

    public Boolean enter(Long accId) {
        if (notExists(accId) || isVisitNow(accId) || getAccountActualVisits(accId) == 0L) {
            return false;
        }
        actionRepository.save(new Action(accId, Action.Type.Enter));
        reportService.updateActions();
        return true;
    }

    public Boolean leave(Long accId) {
        if (notExists(accId)) {
            return false;
        }
        if (isVisitNow(accId)) {
            actionRepository.save(new Action(accId, Action.Type.Leave));
            reportService.updateActions();
            return true;
        }
        return false;
    }

}
