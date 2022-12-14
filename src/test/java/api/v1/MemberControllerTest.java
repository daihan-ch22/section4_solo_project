package api.v1;

import api.v1.member.controller.MemberController;
import api.v1.member.entity.Member;
import api.v1.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberController memberController;

    @MockBean
    private MemberService memberService;

    private List<Member> members;

    @BeforeEach
    public void preSetup() {

        members = Arrays.asList(
                new Member(1L,
                        "?????????",
                        "s4goodbye!",
                        "M",
                        "????????????????????????",
                        "005",
                        "001"),

                new Member(2L,
                        "?????????",
                        "12345",
                        "F",
                        "???????????????",
                        "003",
                        "005"),

                new Member(3L,
                        "?????????",
                        "1232245",
                        "N/A",
                        "PetStock",
                        "001",
                        "001")
        );
    }


    @Test
    public void testGetAll() throws Exception {

        //Pagination
        String page = "1";
        String size = "10";

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);

        Page<Member> memberPaging =
                new PageImpl<>(List.of(members.get(0),members.get(1),members.get(2)),
                        PageRequest.of(0,10,
                Sort.by("memberId").descending()),2);

        when(memberController.getMembers()).thenReturn(members);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/v1/member")
                .params(queryParams)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andDo(document(
                        "get-all-members",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                List.of(
                                        parameterWithName("page").description("Page ??????"),
                                        parameterWithName("size").description("Page Size")
                                )
                        ),
                        relaxedResponseFields(
                                List.of(
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("????????? ??????").optional(),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("????????? ??????").optional(),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("????????? ?????????").optional(),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("?????? ??? ???").optional(),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("?????? ????????? ???").optional(),

                                        fieldWithPath("[]").type(JsonFieldType.ARRAY).description("?????? ?????????"),
                                        fieldWithPath("[].memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("[].userName").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("[].password").type(JsonFieldType.STRING).description("????????????"),
                                        fieldWithPath("[].sex").type(JsonFieldType.STRING).description("????????? ??????"),
                                        fieldWithPath("[].company_name").type(JsonFieldType.STRING).description("????????????"),
                                        fieldWithPath("[].company_type").type(JsonFieldType.STRING).description("????????????"),
                                        fieldWithPath("[].company_location").type(JsonFieldType.STRING).description("????????????")
                                )
                        )


                )).andReturn();
    }



}