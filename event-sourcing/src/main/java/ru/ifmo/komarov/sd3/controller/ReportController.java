package ru.ifmo.komarov.sd3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.komarov.sd3.service.ReportService;

@RestController
@RequestMapping("report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("perDays")
    public String perDayReport() {
        return reportService.perDayReport();
    }

    @GetMapping("avg")
    public String avgReport() {
        return reportService.avgReport();
    }
}
