package ru.ifmo.komarov.sd3.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.komarov.sd3.service.EntranceService;

@RestController
@RequestMapping("entrance")
public class EntranceController {
    private final EntranceService entranceService;

    public EntranceController(EntranceService entranceService) {
        this.entranceService = entranceService;
    }

    @PostMapping("enter/{accId}")
    public Boolean enter(@PathVariable Long accId) {
        return entranceService.enter(accId);
    }

    @PostMapping("leave/{accId}")
    public Boolean leave(@PathVariable Long accId) {
        return entranceService.leave(accId);
    }

}
