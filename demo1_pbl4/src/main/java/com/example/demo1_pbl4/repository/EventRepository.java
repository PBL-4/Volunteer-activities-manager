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

    @Query(value = "SELECT * from Events e where location LIKE %?1%", nativeQuery = true)
    public Page<Event> findEventByLocation(String location,Pageable pageable);

    @Query(value = "SELECT * from Events e where hostname LIKE %?1%", nativeQuery = true)
    public List<Event> findEventByHostname(String hostname);

    @Query(value = "SELECT * from Events e where hostname LIKE %?1%", nativeQuery = true)
    public Page<Event> findEventByHostname(String hostname,Pageable pageable);


    @Query(value = "SELECT * from Events  where event_name LIKE %?1%", nativeQuery = true)
    public List<Event> findEventByEventName(String eventName);


    @Query(value = "SELECT * from Events  where event_name LIKE %?1%", nativeQuery = true)
    public Page<Event> findEventByEventName(String eventName,Pageable pageable);

    // Chức nănq sort:
    //Lọc sự kiện theo tên sự kiện:
    @Query(value="SELECT * FROM Events ORDER BY event_name ASC",nativeQuery=true)
    public Page<Event> findEventOrderByEventName(Pageable pageable);

    //Lọc sự kiện theo sự kiện mới nhất:
    @Query(value="SELECT * FROM Events ORDER BY begin_time DESC",nativeQuery=true)
    public Page<Event> findEventOrderByBeginTime(Pageable pageable);

    //Lọc sự kiện theo sự kiện mới nhất:
    @Query(value="SELECT * FROM Events ORDER BY rating DESC",nativeQuery=true)
    public Page<Event> findEventOrderByPopular(Pageable pageable);

    @Query(value="SELECT * FROM Events where begin_time > CURDATE() ORDER BY begin_time DESC ",nativeQuery=true)
    public Page<Event> findUpcomingEvent(Pageable pageable);

    //Vừa sort vừa search
    @Query(value="SELECT * FROM Events where location LIKE %?1% ORDER BY event_name ASC",nativeQuery=true)
    public Page<Event> findEventByLocationOrderByEventName(String location,Pageable pageable);

    @Query(value="SELECT * FROM Events where location LIKE %?1% ORDER BY begin_time DESC",nativeQuery=true)
    public Page<Event> findEventByLocationOrderByBeginTime(String location,Pageable pageable);

    @Query(value="SELECT * FROM Events where location LIKE %?1% ORDER BY rating DESC",nativeQuery=true)
    public Page<Event> findEventByLocationOrderByPopular(String location,Pageable pageable);

    @Query(value="SELECT * FROM Events where hostname LIKE %?1% ORDER BY event_name ASC",nativeQuery=true)
    public Page<Event> findEventByHostnameOrderByEventName(String hostname,Pageable pageable);

    @Query(value="SELECT * FROM Events where hostname LIKE %?1% ORDER BY begin_time DESC",nativeQuery=true)
    public Page<Event> findEventByHostnameOrderByBeginTime(String hostname,Pageable pageable);

    @Query(value="SELECT * FROM Events where hostname LIKE %?1% ORDER BY rating DESC",nativeQuery=true)
    public Page<Event> findEventByHostnameOrderByPopular(String hostname,Pageable pageable);

    @Query(value="SELECT * FROM Events where event_name LIKE %?1% ORDER BY event_name ASC",nativeQuery=true)
    public Page<Event> findEventByEventNameOrderByEventName(String eventName,Pageable pageable);

    @Query(value="SELECT * FROM Events where event_name LIKE %?1% ORDER BY begin_time DESC",nativeQuery=true)
    public Page<Event> findEventByEventNameOrderByBeginTime(String eventName,Pageable pageable);

    @Query(value="SELECT * FROM Events where event_name LIKE %?1% ORDER BY rating DESC",nativeQuery=true)
    public Page<Event> findEventByEventNameOrderByPopular(String eventName,Pageable pageable);



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


}
