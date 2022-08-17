package api.v1;

import api.v1.member.controller.MemberController;
import api.v1.member.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberController memberController;

    @Test
    public void testGetAll() throws Exception {
        List<Member> members = Arrays.asList(
                new Member(1L,
                        "kim",
                        "12345",
                        "male",
                        "codecompany",
                        "financial",
                        "Seoul"),

                new Member(2L,
                        "kim2",
                        "12345",
                        "male",
                        "codecompany",
                        "financial",
                        "Seoul"),

                new Member(3L,
                        "kim1",
                        "12345",
                        "male",
                        "codecompany",
                        "financial",
                        "Seoul")
        );

        when(memberController.getMembers()).thenReturn(members);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/v1/member");
        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andDo(document(
                        "get-all-members", preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }
}