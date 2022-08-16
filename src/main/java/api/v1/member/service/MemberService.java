package api.v1.member.service;


import api.v1.member.entity.Member;
import api.v1.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(Member member){
        member.setMemberId(1L);
        member.setPassword("12345");
        member.setSex("Male");
        member.setUserName("danc");
        member.setCompany_name("companyName");
        member.setCompany_location("companyLocation");
        member.setCompany_type("companyType");

        return member;
    }

    public Member updateMember(Member member){
        return null;
    }

    public Member findMember(long memberId){
        return null;
    }

    public List<Member> findMembers(){
        return null;
    }

    public void deleteMember(long memberId){

    }
}
