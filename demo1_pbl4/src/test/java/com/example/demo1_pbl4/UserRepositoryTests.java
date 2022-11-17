package com.example.demo1_pbl4;

import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repos;

    @Test
    public void testAdd()
    {
        User user=new User();
        user.setUserId(1L);// 1: int -> 1L : Long
        user.setFirstName("Le Trong");
        user.setLastName("Bach");
        user.setPhoneNum("09091209192");
        user.setEmail("letrongbach02@gmail.com");

    }

}
