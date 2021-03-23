package ru.ifmo.komarov.sd3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.ifmo.komarov.sd3.controller.ManagerController;
import ru.ifmo.komarov.sd3.controller.EntranceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ifmo.komarov.sd3.model.Account;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class EntranceTest {
    @Autowired
    ManagerController managerController;
    @Autowired
    EntranceController entranceController;

    @BeforeEach
    public void createTwoUsers() {
        managerController.addAccount(new Account(1L));
        managerController.addAccount(new Account(2L));
    }

    @AfterEach
    public void cleanUp() {
        managerController.deleteAll();
    }

    @Test
    public void leaveTwoTimesInARowTest() {
        managerController.addVisits(1L, 1);
        assertTrue(entranceController.enter(1L));
        assertTrue(entranceController.leave(1L));
        assertFalse(entranceController.leave(1L));
    }

    @Test
    public void enterTwoTimesInARowTest() {
        managerController.addVisits(1L, 1);
        assertTrue(entranceController.enter(1L));
        assertFalse(entranceController.enter(1L));
    }

    @Test
    public void enterWithoutVisitsTest() {
        assertFalse(entranceController.enter(1L));
    }

    @Test
    public void randomTest() {
        managerController.addVisits(1L, 10);
        managerController.addVisits(2L, 1);

        assertTrue(entranceController.enter(1L));
        assertFalse(entranceController.enter(1L));

        assertFalse(entranceController.leave(2L));

        assertTrue(entranceController.leave(1L));

        assertTrue(entranceController.enter(2L));
        assertTrue(entranceController.leave(2L));
        assertFalse(entranceController.enter(2L));
    }
}
