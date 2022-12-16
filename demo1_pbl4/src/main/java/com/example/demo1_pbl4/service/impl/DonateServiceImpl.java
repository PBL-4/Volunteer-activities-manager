package com.example.demo1_pbl4.service.impl;


import com.example.demo1_pbl4.model.Donate;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.model.dto.TotalDonationOfUser;
import com.example.demo1_pbl4.repository.DonateRepository;
import com.example.demo1_pbl4.repository.UserRepository;
import com.example.demo1_pbl4.service.DonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("DonateServiceImpl")
public class DonateServiceImpl implements DonateService {

    @Autowired
    private DonateRepository donateRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Donate> getAllDonates() {
        return donateRepository.findAll();

    }

    @Override
    public List<Donate> findAllDonatebyUser(Long user_id) {
        return donateRepository.findAllDonatebyUser(user_id);
    }

    @Override
    public void updateDonate(Donate donate) {
        donateRepository.save(donate);
    }

    @Override
    public List<TotalDonationOfUser> SumofDonatUser() {
        List<TotalDonationOfUser> totalDonationOfUserList = new ArrayList<>();
        for (Donate donate : donateRepository.SumofDonatUser()) {
            User user = userRepository.findById(donate.getUser().getUserId()).get();
            TotalDonationOfUser totalDonationOfUser = new TotalDonationOfUser(user.getUserId(), user.getFirstName(), user.getLastName(), donateRepository.SumofDonate(user.getUserId()));
            totalDonationOfUserList.add(totalDonationOfUser);
        }
        return totalDonationOfUserList;
    }

    public double SumofDonate(Long user_id) {
        return donateRepository.SumofDonate(user_id);
    }
}

