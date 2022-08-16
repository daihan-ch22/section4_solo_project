package api.v1.member.controller;

import api.v1.member.entity.Member;
import api.v1.member.mapper.MemberMapper;
import api.v1.member.service.MemberService;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
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
    public ResponseEntity getMember(@PathVariable("member-id") long memberId){
        System.out.println("memberId: " + memberId);
        Member response = memberService.findMember(memberId);

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response),
                HttpStatus.OK);


    }
    //전체 회원 조회
    @GetMapping
    public ResponseEntity getMembers() {
        System.out.println("get member!");
        memberService.createMember(new Member());

        List<Member> response = memberService.findMembers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //회원 삭제
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId) {
        System.out.println("member Deleted!");
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
