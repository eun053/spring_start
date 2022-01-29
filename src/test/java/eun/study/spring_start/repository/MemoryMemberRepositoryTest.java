package eun.study.spring_start.repository;

import eun.study.spring_start.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach    // test 끝날때마다 호출되는 callback method
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void saveTest() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        //Assertions.assertEquals(member, result);
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save((member1));

        Member member2 = new Member();   // shift + f6 : rename
        member2.setName("spring2");
        repository.save((member2));

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save((member1));

        Member member2 = new Member();   // shift + f6 : rename
        member2.setName("spring2");
        repository.save((member2));

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);


    }
}
