package com.arraisi.invoice.service;

import com.arraisi.invoice.entity.RunningNumber;
import com.arraisi.invoice.repository.RunningNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RunningNumberService {
    @Autowired
    RunningNumberRepository runningNumberRepository;

    public Long getNumber(String prefix) {
        RunningNumber rn = runningNumberRepository.findByPrefix(prefix);
        if (rn == null) {
            rn = new RunningNumber();
            rn.setPrefix(prefix);
        }

        rn.setLastNumber(rn.getLastNumber() + 1);
        runningNumberRepository.save(rn);
        return rn.getLastNumber();
    }
}
