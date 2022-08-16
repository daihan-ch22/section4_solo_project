package api.v1.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String userName;
    private String password;
    private String sex;
    private String company_name;
    private String company_type;
    private String company_location;
}
