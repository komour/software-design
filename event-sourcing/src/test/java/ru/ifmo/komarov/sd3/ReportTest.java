package ru.ifmo.komarov.sd3;

import ru.ifmo.komarov.sd3.controller.ManagerController;
import ru.ifmo.komarov.sd3.controller.ReportController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ReportTest {
    @Autowired
    ManagerController admin;
    @Autowired
    ReportController controller;

    @Test
    public void testAvg() {
        assertNotNull(controller.perDayReport());
        assertNotNull(controller.avgReport());
    }
}
