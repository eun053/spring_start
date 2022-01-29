package eun.study.spring_start.service;

import eun.study.spring_start.domain.Member;
import eun.study.spring_start.repository.MemberRepository;
import eun.study.spring_start.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.fail;


class MemberServiceTest {
    //MemberService memberService = new MemberService();
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        // DI (Dependency Injection)
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("member1");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(saveId).isEqualTo(findMember.getId());
    }


    @Test
    void 중복회원가입체크() {
        // given
        Member member = new Member();
        member.setName("member1");

        Member member2 = new Member();
        member2.setName("member1");

        // when
        memberService.join(member);
        //memberService.join(member2);   // 오류발생

        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
        }

        // then
    }

    @Test
    void 전체회원조회() {
    }

    @Test
    void ID로회원조회() {
    }
}