package api.v1.member.controller;

import api.v1.member.entity.Member;
import api.v1.member.mapper.MemberMapper;
import api.v1.member.repository.MemberRepository;
import api.v1.member.service.MemberService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/member")
public class MemberController {


//    private final MemberService memberService;
//    private final MemberMapper mapper;

    private MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //조건에 맞는 특정 회원 조회(조건: 지역, 업종)
//    @GetMapping("/{company_location}")
//    public ResponseEntity getMemberLocation() {
//        System.out.println("company-location!!");
//        List<Member> response = memberService.
//        return new ResponseEntity<>()
//    }





    // 특정 회원 조회 (MemberID)
    @GetMapping("/v1/{member-id}")
    public Member getMember(@PathVariable(value = "member-id") long memberId){
        System.out.println("memberId: " + memberId);
        return memberRepository.findById(memberId).get();
    }

    //전체 회원 조회
    @GetMapping
    public List<Member> getMembers() {
        System.out.println("get member!");
        return memberRepository.findAll();
    }

    @PostMapping
    public Member createMember(@RequestBody Member member){
        return memberRepository.save(member);
    }

    @PutMapping
    public Member updateMember(@RequestBody Member member) throws Exception {
        return memberRepository.findById(member.getMemberId())
                .map(
                        db -> {memberRepository.save(member);
                            return member;
                        }).orElseThrow(Exception::new);
    }

    //회원 삭제
    @DeleteMapping("/{member-id}")
    public Member deleteMember(@PathVariable("member-id") long memberId)
    throws Exception{
        System.out.println("member Deleted!");

        return memberRepository.findById(memberId)
                .map(
                        member -> {memberRepository.save(member);
                            return member;
                        }).orElseThrow(Exception::new);
    }


}
