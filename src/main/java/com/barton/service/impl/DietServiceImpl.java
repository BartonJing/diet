package com.barton.service.impl;

import com.barton.service.DietService;
import org.springframework.stereotype.Service;

@Service
public class DietServiceImpl implements DietService {
    @Override
    public void test() {
        System.out.print("**************************************");
    }
}
