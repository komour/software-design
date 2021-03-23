package ru.ifmo.komarov.sd3.service;

import org.springframework.stereotype.Component;
import ru.ifmo.komarov.sd3.model.Action;
import ru.ifmo.komarov.sd3.repository.ActionRepository;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ReportService extends BaseService {
    private final Set<Action> actions;
    private Long curActionId;
    private final static String REPORT_BORDER = "<==================================================>\n";
    private final static long dayMillis = Duration.ofDays(1).toMillis();

    protected ReportService(ActionRepository actionRepository) {
        super(actionRepository);
        actions = new TreeSet<>();
        curActionId = 0L;
    }

    public void updateActions() {
        List<Action> newActions = actionRepository.findAllByIdAfter(curActionId);
        if (newActions != null) {
            curActionId += newActions.size();
            actions.addAll(newActions.stream()
                    .filter(action -> action.getType() == Action.Type.Enter || action.getType() == Action.Type.Leave)
                    .collect(Collectors.toSet()));
        }
    }

    public String perDayReport() {
        StringBuilder reportBuilder = new StringBuilder(REPORT_BORDER);
        reportBuilder.append("Per day report:\n");
        for (Map.Entry<Long, List<Action>> entry : groupByDays().entrySet()) {
            reportBuilder.append("day [").append(entry.getKey()).append("]\n");
            for (Action action : entry.getValue()) {
                reportBuilder.append(action.toString()).append("\n");
            }
        }
        reportBuilder.append(REPORT_BORDER);
        return reportBuilder.toString();
    }

    public String avgReport() {
        return REPORT_BORDER + "Avg statistics\n" +
                "Avg visits per day: " + 1.0 * actions.stream()
                .filter(action -> action.getType() == Action.Type.Leave)
                .toArray().length / (groupByDays().isEmpty() ? 1 : groupByDays().size()) +
                "\n" +
                "Avg visit duration: " + getAvgDurationOfVisit() + " ms\n" +
                REPORT_BORDER;
    }

    private Map<Long, List<Action>> groupByDays() {
        Map<Long, List<Action>> groups = new TreeMap<>();
        for (Action action : actions) {
            long key = action.getTime() / dayMillis;
            List<Action> curGroup = groups.containsKey(key) ? groups.get(key) : new ArrayList<>();
            curGroup.add(action);
            groups.put(key, curGroup);
        }
        return groups;
    }

    private Double getAvgDurationOfVisit() {
        long cnt = 0;
        long sum = 0;
        long left = 0;
        for (Action action : actions) {
            if (action.getType() == Action.Type.Enter) {
                left = action.getTime();
            } else if (action.getType() == Action.Type.Leave) {
                sum += action.getTime() - left;
                ++cnt;
            }
        }
        return 1.0 * sum / (cnt == 0 ? 1 : cnt);
    }

}
