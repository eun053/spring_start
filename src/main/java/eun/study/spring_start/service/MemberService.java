package eun.study.spring_start.service;

import eun.study.spring_start.domain.Member;
import eun.study.spring_start.repository.MemberRepository;
import eun.study.spring_start.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// ctrl + Shift + T => create new test => 테스트 코드 자동 생성
//@Service
public class MemberService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 외부에서 주입받도록 변경
    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    };

    // 회원가입
    @Transactional
    public Long join(Member member) {
        // 이름 중복 체크
        /*memberRepository.findByName(member.getName())
                .ifPresent(m -> {     // ifPresent => null이 아니면, 값이 있으면..
                    throw new IllegalStateException("이미 존재하는 이름입니다.");
                });
         */
        // 중복체크 method로 뽑는다.
        // alt + enter => 'extract method'
        validateName(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateName(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {     // ifPresent => null이 아니면, 값이 있으면..
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 특정 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);

    }
}
