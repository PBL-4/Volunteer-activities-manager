package com.example.demo1_pbl4.service;
import com.example.demo1_pbl4.model.Donate;

import java.util.List;

public interface DonateService {

     List<Donate> getAllDonates();
     public List<Donate> findAllDonatebyUser(Long user_id);

     void updateDonate(Donate donate);
}

