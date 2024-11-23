package apptive.study.service;

import apptive.study.domain.Member;
import apptive.study.dto.request.MemberRequest;
import apptive.study.exception.member.MemberNameDuplicateException;
import apptive.study.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static apptive.study.exception.ErrorCode.MEMBER_NAME_DUPLICATE_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 정상 저장 테스트")
    void 회원_정상_저장_테스트() {
        // given
        MemberRequest memberRequest = new MemberRequest("강수민", 25);
        Member savedMember = new Member("강수민", 24);

        given(memberRepository.findByName(memberRequest.name())).willReturn(Optional.empty());
        given(memberRepository.save(any(Member.class))).willReturn(savedMember);

        // when
        Member member = memberService.join(memberRequest);

        // then
        assertThat(member.getName()).isEqualTo("강수민");
        assertThat(member.getAge()).isEqualTo(24);
    }

    @Test
    void 중복된_회원_저장_테스트() {
        // given
        MemberRequest memberRequest = new MemberRequest("강수민", 24);
        Member savedMember = new Member("강수민", 24);

        given(memberRepository.findByName(memberRequest.name())).willReturn(Optional.of(savedMember));

        // when & then
        assertThatThrownBy(() -> memberService.join(memberRequest))
                .isInstanceOf(MemberNameDuplicateException.class)
                .hasMessage(MEMBER_NAME_DUPLICATE_ERROR.getMessage());
    }

    @Test
    @DisplayName("전체 회원 조회 테스트")
    void 전체_회원_조회_테스트() {
        // given
        Member member1 = new Member("강수민", 24);
        Member member2 = new Member("김민지", 21);
        List<Member> savedMembers = List.of(member1, member2);

        given(memberRepository.findAll()).willReturn(savedMembers);

        // when
        List<Member> members = memberService.findMembers();

        // then
        assertThat(members.size()).isEqualTo(2);
        assertThat(members.get(1).getName()).isEqualTo("김민지");
    }
}