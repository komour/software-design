package ru.ifmo.komarov.sd3.controller;

import org.springframework.web.bind.annotation.*;
import ru.ifmo.komarov.sd3.model.Account;
import ru.ifmo.komarov.sd3.service.ManagerService;


@RestController
@RequestMapping("account")
public class ManagerController {
    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("create")
    public void addAccount(@RequestBody Account acc) {
        managerService.addAccount(acc);
    }

    @PostMapping("addVisits/{accId}")
    public void addVisits(@PathVariable Long accId, @RequestParam Integer count) {
        managerService.addVisits(accId, count);
    }

    @GetMapping("check/{accId}")
    public Boolean accountExists(@PathVariable Long accId) {
        return managerService.accountExists(accId);
    }

    @GetMapping("doneVisits/{accId}")
    public Long getAccountDoneVisits(@PathVariable Long accId) {
        return managerService.getVisitCount(accId);
    }

    @GetMapping("actualVisits/{accId}")
    public Long getAccountActualVisits(@PathVariable Long accId) {
        return managerService.getAccountActualVisits(accId);
    }

    public void deleteAll() {
        managerService.deleteAll();
    }
}

