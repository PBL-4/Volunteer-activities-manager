package com.example.demo1_pbl4;

import com.example.demo1_pbl4.model.Role;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repos;

    @Autowired
    private TestEntityManager tester;

//    @Test
//    public void testAdd() {
//        User user = new User();
//        //  user.setUserId(14L);// 1: int -> 1L : Long
//        user.setFirstName("Le Trong");
//        user.setLastName("Bach1");
//        user.setPhoneNum("09091209192");
//        user.setEmail("bach2002@gmail.com");
//        user.setUsername("bachlee123");
//        user.setPassword("123456");
//        user.setAddress("Da Nang");
//        Role role = new Role();
//        user.setRole(role);
//        User saveUser = repos.save(user);
//        User userCheck = tester.find(User.class, saveUser.getUserId());
//        assertThat(userCheck.getEmail().equals(saveUser.getEmail()));
//    }

    @Test
    public void TestFindUsername() {
        String username = "bachlee";
        User user = repos.findUserByUsername(username);
        assertThat(user).isNotNull();
    }
}
