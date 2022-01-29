package eun.study.spring_start;

import eun.study.spring_start.repository.JPAMemberRepository;
import eun.study.spring_start.repository.MemberRepository;
import eun.study.spring_start.repository.MemoryMemberRepository;
import eun.study.spring_start.repository.JdbcTemplateMemberRepository;
import eun.study.spring_start.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

   //private DataSource dataSource;
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JPAMemberRepository(em);
    }
}
