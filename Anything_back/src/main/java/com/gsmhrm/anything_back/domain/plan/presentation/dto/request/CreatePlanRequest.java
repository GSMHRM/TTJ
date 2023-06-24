package com.gsmhrm.anything_back.domain.plan.presentation.dto.request;

import com.gsmhrm.anything_back.domain.plan.entity.enums.TypeTrans;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlanRequest {

    private String title;

    private TypeTrans typeTrans;

    private String country;

    private Date start_date;

    private Date end_date;
}
