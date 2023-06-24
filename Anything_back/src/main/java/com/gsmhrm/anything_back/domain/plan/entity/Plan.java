package com.gsmhrm.anything_back.domain.plan.entity;

import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.plan.entity.enums.TypeTrans;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Plan {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id", nullable = false)
    private Long plan_id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeTrans trans;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Date start_date;

    @Column(nullable = false)
    private Date end_date;
}
