package com.gsmhrm.anything_back.domain.plan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.repository.MemberRepository;
import com.gsmhrm.anything_back.domain.plan.entity.Plan;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.request.CreatePlanRequest;
import com.gsmhrm.anything_back.domain.plan.repository.PlanRepository;
import com.gsmhrm.anything_back.domain.plan.service.CreatePlanService;
import com.gsmhrm.anything_back.global.util.UserUtil;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired private MemberRepository memberRepository;
    @Autowired private UserUtil util;

    @BeforeEach
    @DisplayName("테스트전 데이터 생성")
    void createImplData() {
        Member member = Member.builder()
                .email("zadzed1100@gmail.com")
                .name("테스트이름")
                .password("1234")
                    .build();

        memberRepository.save(member);

        //WHEN
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                member.getEmail(),
                member.getPassword()
        );

        SecurityContextHolder.getContext().setAuthentication(token);

        Plan plan = Plan.builder()
                        .id(1L)
                                .member(member)
                                        .title("제목1번")
                                                .content("제목1에대한 내용")
                                                        .completed(false)
                                                                .start_Time(LocalDateTime.now())
                                                                        .end_Time(LocalDateTime.now())
                                                                                .createTime(LocalDateTime.now())
                                                                                        .editTime(LocalDateTime.now())
                                                                                                .build();

        planRepository.save(plan);

        System.out.println("List<Plan>   = " + planRepository.findAll());
    }

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


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonRequest = objectMapper.writeValueAsString(request);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/plan")
                        .with(SecurityMockMvcRequestPostProcessors.user("TesterA"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                )
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @WithMockUser(username = "테스트이름")
    @Test
    @DisplayName("Plan List 가져오기 테스트")
    void getPlanListTest() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/plan/list")
                        .with(SecurityMockMvcRequestPostProcessors.user("테스트이름"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @WithMockUser(username = "TesterA")
    @Test
    @DisplayName("Plan Delete 테스트")
    void deletePlanTest() throws Exception {

        Member member = Member.builder()
                .email("zadzed1100@gmail.com")
                .name("TesterA")
                .password("1234")
                .build();

        memberRepository.save(member);

        Plan plan = Plan.builder()
                .id(2L)
                .member(member)
                .title("제목1번")
                .content("제목1에대한 내용")
                .completed(false)
                .start_Time(LocalDateTime.now())
                .end_Time(LocalDateTime.now())
                .createTime(LocalDateTime.now())
                .editTime(LocalDateTime.now())
                .build();

        planRepository.save(plan);

        System.out.println("planRepository = " + planRepository.findAll());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/plan/delete/{id}", 2L)
                        .with(SecurityMockMvcRequestPostProcessors.user("TesterA"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
