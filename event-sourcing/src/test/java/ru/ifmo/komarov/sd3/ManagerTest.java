package ru.ifmo.komarov.sd3;

import org.junit.jupiter.api.AfterEach;
import ru.ifmo.komarov.sd3.controller.ManagerController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ifmo.komarov.sd3.model.Account;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ManagerTest {
    @Autowired
    private ManagerController managerController;

    @AfterEach
    public void cleanUp() {
        managerController.deleteAll();
    }

    @Test
    public void addAccountTest() {
        managerController.addAccount(new Account(1L));
        assertTrue(managerController.accountExists(1L));
    }

    @Test
    public void addVisitsTest() {
        managerController.addAccount(new Account(1L));
        managerController.addVisits(1L, 10);
        assertEquals(10L, managerController.getAccountActualVisits(1L).longValue());
        assertEquals(0L, managerController.getAccountDoneVisits(1L).longValue());
    }
}
