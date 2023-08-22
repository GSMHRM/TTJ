package com.gsmhrm.anything_back.domain.plan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.plan.entity.Plan;
import com.gsmhrm.anything_back.domain.plan.exception.NotFoundPlanException;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.request.CreatePlanRequest;
import com.gsmhrm.anything_back.domain.plan.repository.PlanRepository;
import com.gsmhrm.anything_back.domain.plan.service.CreatePlanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(JpaMetamodelMappingContext.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class PlanControllerTest {

    @Autowired private PlanRepository planRepository;
    @Autowired private MockMvc mockMvc;
    @MockBean private CreatePlanService createPlanService;

    @WithMockUser(username = "TesterA")
    @Test
    @DisplayName("Plan 생성 테스트")
    void createPlanTest() throws Exception {

        CreatePlanRequest request = new CreatePlanRequest(
              "제목",
              "내용",
              LocalDateTime.now(),
              LocalDateTime.now()
        );

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/plan")
                        .with(SecurityMockMvcRequestPostProcessors.user("TesterA"))
                        .accept(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(request))
                )
                .andExpect(status().isCreated())
                .andDo(print());



        Plan plan = planRepository.findById(1L)
                .orElseThrow(NotFoundPlanException::new);

        Member member = plan.getMember();
    
        Assertions.assertEquals(member.getName(), "TesterA");
    }
}
