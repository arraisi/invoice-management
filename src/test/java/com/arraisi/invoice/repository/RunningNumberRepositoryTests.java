package com.arraisi.invoice.repository;

import com.arraisi.invoice.service.RunningNumberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
public class RunningNumberRepositoryTests {
    @Autowired
    private RunningNumberService runningNumberService;

    @Test
    public void testGetNumber() {
        Long result = runningNumberService.getNumber("Test");
        Assertions.assertNotNull(result);
        System.out.println("Result : " + result);
    }

    @Test
    public void testGetNumberMultithreaded() throws InterruptedException {
        int threadCount = 2;
        final int iterasi = 3;

        ConcurrentHashMap<Long, List<Long>> hasilMap = new ConcurrentHashMap<>();

        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < iterasi; j++) {
                        List<Long> lastNumber = hasilMap.get(this.getId());
                        if (lastNumber == null) {
                            lastNumber = new ArrayList<>();
                        }
                        Long result = runningNumberService.getNumber("Test");
                        System.out.println("Thread [" + this.getId() + "] Result : " + result);

                        lastNumber.add(result);
                        hasilMap.put(this.getId(), lastNumber);
                    }
                }
            };
            t.start();
        }

        Thread.sleep(10 * 1000);
        Enumeration<Long> keys = hasilMap.keys();
        while (keys.hasMoreElements()) {
            Long key = keys.nextElement();
            System.out.println("====== Thread " + key + "======");
            System.out.println(hasilMap.get(key));
        }
    }
}
