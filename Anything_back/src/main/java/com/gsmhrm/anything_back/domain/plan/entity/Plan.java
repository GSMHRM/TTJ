package com.gsmhrm.anything_back.domain.plan.entity;

import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.request.EditPlanRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Boolean completed;

    private LocalDateTime createTime;

    private LocalDateTime editTime;
    public void editPlan(EditPlanRequest editPlanRequest, Boolean completed) {
        this.title = editPlanRequest.getTitle();
        this.content = editPlanRequest.getContent();
        this.completed = completed;
    }
}
