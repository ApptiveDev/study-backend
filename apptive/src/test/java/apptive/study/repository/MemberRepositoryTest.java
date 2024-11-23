package apptive.study.repository;

import apptive.study.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    private Member member1;
    private Member member2;

    @BeforeEach
    void setUp(){
        member1 = new Member("김민지", 21);
        member2 = new Member("팜하니", 21);

        memberRepository.save(member1);
        memberRepository.save(member2);
    }

    @Test
    @DisplayName("회원 저장 테스트")
    void 회원_저장_테스트(){
        // given
        Member member = new Member("강수민", 24);

        // when
        Member savedMember = memberRepository.save(member);

        // then
        assertThat(savedMember.getName()).isEqualTo("강수민");
        assertThat(savedMember.getAge()).isEqualTo(24);
    }

    @Test
    @DisplayName("이름으로 회원 조회 테스트")
    void 이름으로_회원조회_테스트() {
        // when
        Member foundMember = memberRepository.findByName(member1.getName()).get();

        // then
        assertThat(foundMember.getName()).isEqualTo(member1.getName());
        assertThat(foundMember.getAge()).isEqualTo(member1.getAge());
    }

    @Test
    @DisplayName("전체 조회 테스트")
    void 전체_조회_테스트() {
        // when
        List<Member> members = memberRepository.findAll();

        // then
        assertThat(members.size()).isEqualTo(2);
    }
}