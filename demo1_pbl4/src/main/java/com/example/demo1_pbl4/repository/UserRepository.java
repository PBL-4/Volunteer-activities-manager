package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.model.dto.MemberInRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    @Query(value = "SELECT * FROM User u WHERE u.username = ?1",nativeQuery = true)
    public User findUserByUserName(String username);
    @Query(value = "SELECT * FROM User u WHERE u.email = ?1",nativeQuery = true)
    public User findUserByEmail(String email);
    @Query(value = "SELECT * FROM users WHERE username=?1", nativeQuery = true)
    User findUserByUsername(String username);

    // LTBach
//    @Query(value = "SELECT (user_id,first_name,last_name,role_of_event,point1_of_member,point2_of_member,point3_of_member) FROM users INNER JOIN user_event on users.user_id=user_event.user_user_id WHERE event_event_id=?1 and role_of_event=?2 and is_approval=1 ", nativeQuery = true)
//    List<MemberInRating> findMemberInEvent(Long eventId, String role);

    //HVNghĩa: Search bên phía admin
    @Query(value = "SELECT * FROM users  WHERE CONCAT(first_name,' ',last_name) LIKE %?1%",nativeQuery = true)
    public List<User> search(String keyword);

}
