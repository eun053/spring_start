package eun.study.spring_start.controller;

import eun.study.spring_start.domain.Member;
import eun.study.spring_start.domain.MemberForm;
import eun.study.spring_start.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// @Controller annotation이 있으면,
// spring이 뜰때, 객체를 생성해서 spring이 관리를 하는데 있는데.
// => spring container에서 spring bean이 관리된다고 표현한다.
@Controller
public class MemberController {
    private final MemberService memberService;

    // 생성자에 @Autowired가 있으면 spring이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/new")
    public String createForm() {
        return "member/createForm";
    }

    @PostMapping("/member/new")
    public String createForm(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/member")
    public String memberList(Model model) {
        List<Member> memberList = memberService.findMembers();
        model.addAttribute("members", memberList);

        return "member/list";
    }
}
