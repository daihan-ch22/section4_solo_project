package api.v1.member.mapper;

import api.v1.member.dto.MemberResponseDto;
import api.v1.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberResponseDto memberToMemberResponseDto(Member member)
    {
        return new MemberResponseDto(member.getMemberId(),
                member.getUserName(), member.getPassword(), member.getSex()
                , member.getCompany_name(), member.getCompany_type(),
                member.getCompany_location());
    }

}
