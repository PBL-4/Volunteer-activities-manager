package com.example.demo1_pbl4.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEventId implements Serializable {

    @Column(name="user_id")
    private Long userId;
    @Column(name="event_id")
    private Long eventId;

}
