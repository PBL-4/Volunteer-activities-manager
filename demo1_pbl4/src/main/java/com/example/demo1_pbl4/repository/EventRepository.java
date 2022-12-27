package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = "SELECT * from Events e where location=?1 and event_name LIKE %?2%", nativeQuery = true)
    public List<Event> findEventByLocationAndKeyword(String location, String keyword);

    @Query(value = "SELECT * from Events e where location LIKE %?1%", nativeQuery = true)
    public List<Event> findEventByLocation(String location);

    @Query(value = "SELECT * from Events e where hostname LIKE %?1%", nativeQuery = true)
    public List<Event> findEventByHostname(String hostname);

    @Query(value = "SELECT * from Events  where event_name LIKE %?1%", nativeQuery = true)
    public List<Event> findEventByEventName(String eventName);

    // Lấy sự kiện dựa vào host của event: BachLT

    @Query(value="SELECT * FROM Events LEFT JOIN user_event on events.event_id=user_event.event_event_id" +
            " WHERE user_user_id=?1  and role_of_event=?2 and is_approval= 1",nativeQuery=true)
    public Page<Event> findHostOfEvent(Long userId, String role, Pageable pageable);
    @Query(value = "SELECT COUNT(*) AS so_su_kien FROM events WHERE MONTH(begin_time) = ?1 AND YEAR(begin_time)= ?2",nativeQuery = true)
    public int numberEventsInMonth(int month, int year);
    
    /* */
    @Query(value = "SELECT * FROM Events LEFT JOIN user_event on events.event_id=user_event.event_event_id" +
            " WHERE user_user_id=?1 and role_of_event=?2 and is_approval= 1", nativeQuery = true)
    public Page<Event> findEventOfHost(Long userId, String role, Pageable pageable);

    @Query(value="SELECT COUNT(*) from user_event where event_event_id=?1 and is_approval=1",nativeQuery=true)
    public int countCurrentMember(Long eventId);

    // Đếm số lượng thành viên cần chấp thuận
    @Query(value="SELECT COUNT(*) from user_event where event_event_id=?1 and is_approval=0",nativeQuery=true)
    public int countWaitingApproval(Long eventId);

    // Đếm số lượng sự kiện trong năm
    @Query(value= "SELECT * FROM events where YEAR(begin_time) =?1",nativeQuery = true)
    public List<Event> donationEventinYear(int year);
    // Đếm số lượng tình nguyện viên trong tháng
    @Query(value= "SELECT count(current_member) FROM events where MONTH(begin_time) =?1 AND YEAR(begin_time) =?2",nativeQuery = true)
    public int countVolunteerInMonthAndYear(int month,int year);

    //Lọc sự kiện theo sự giảm dần của tg
    @Query(value="SELECT * FROM Events ORDER BY begin_time DESC",nativeQuery=true)
    public List<Event> findEventOrderByBeginTime();

    @Query(value="SELECT * FROM Events where begin_time > CURDATE() ORDER BY begin_time DESC ",nativeQuery=true)
    public List<Event> findUpcomingEvent();
}
