package com.example.demo1_pbl4.service;
import com.example.demo1_pbl4.model.Donate;
import com.example.demo1_pbl4.model.dto.TotalDonationOfUser;

import java.util.List;

public interface DonateService {

     List<Donate> getAllDonates();
     public List<Donate> findAllDonatebyUser(Long user_id);


     boolean createDonate(Donate donate);

     List<TotalDonationOfUser> sumOfDonateUser();

     List<TotalDonationOfUser> sortByTotalDonate();

     public double SumDonateByUser(Long user_id);

     double sumAllDonate();

}

