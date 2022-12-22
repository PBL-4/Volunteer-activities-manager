package com.example.demo1_pbl4.service.impl;


import com.example.demo1_pbl4.model.Donate;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.model.dto.TotalDonationOfUser;
import com.example.demo1_pbl4.repository.DonateRepository;
import com.example.demo1_pbl4.repository.UserRepository;
import com.example.demo1_pbl4.service.DonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

import java.util.*;

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
    public List<TotalDonationOfUser> sumOfDonateUser() {
        List<TotalDonationOfUser> totalDonationOfUserList = new ArrayList<>();
        for (Donate donate : donateRepository.sumOfDonateUser()) {
            User user = userRepository.findById(donate.getUser().getUserId()).get();
            TotalDonationOfUser totalDonationOfUser = new TotalDonationOfUser(user.getUserId(), user.getFirstName(), user.getLastName(), donateRepository.SumofDonate(user.getUserId()));
            totalDonationOfUser.setAddress(user.getAddress());
            totalDonationOfUserList.add(totalDonationOfUser);
        }
        return totalDonationOfUserList;
    }

    @Override
    public List<TotalDonationOfUser> sortByTotalDonate() {
        List<TotalDonationOfUser> listUser = sumOfDonateUser();
        System.out.println(listUser.get(2).getTotal());
        listUser.sort((o1, o2) ->
        {
            return Double.compare(o2.getTotal(), o1.getTotal());
        });
        System.out.println(listUser.get(0).getFirstName());
        System.out.println(listUser.get(1).getFirstName());
        System.out.println(listUser.get(2).getFirstName());
        return listUser;
    }

    @Override
    public double SumDonateByUser(Long user_id) {
        return donateRepository.SumofDonate(user_id);
    }

    @Override
    public double sumAllDonate() {
        double total = 0;
        for (Donate d : getAllDonates()) {
            total += d.getMoney();
        }
        return total;
    }
}

