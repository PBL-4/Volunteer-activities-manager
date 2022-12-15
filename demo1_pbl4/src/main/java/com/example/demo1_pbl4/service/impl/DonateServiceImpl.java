package com.example.demo1_pbl4.service.impl;


import com.example.demo1_pbl4.model.Donate;
import com.example.demo1_pbl4.repository.DonateRepository;
import com.example.demo1_pbl4.service.DonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DonateServiceImpl")
public class DonateServiceImpl implements DonateService {

    @Autowired
    private DonateRepository donateRepository;
    @Override
    public List<Donate> getAllDonates() {
        return donateRepository.findAll();

    }
    @Override
    public List<Donate> findAllDonatebyUser(Long user_id)
    {
        return donateRepository.findAllDonatebyUser(user_id);
    }
    @Override
    public void updateDonate(Donate donate){
         donateRepository.save(donate);
    }

}

