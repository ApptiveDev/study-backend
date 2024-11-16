package apptive.study.controller;

import apptive.study.domain.Member;
import apptive.study.dto.ApiResponse;
import apptive.study.dto.request.MemberRequest;
import apptive.study.dto.response.MemberResponse;
import apptive.study.service.MemberService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/new")
    public ResponseEntity<ApiResponse<?>> create(@RequestBody @Valid MemberRequest memberRequest) {
        Member member = memberService.join(memberRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.successResponse(MemberResponse.from(member)));
    }

    @GetMapping( "/members")
    public ResponseEntity<ApiResponse<?>> list() {
        List<Member> members = memberService.findMembers();

        List<MemberResponse> memberResponses = members.stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.successResponse(memberResponses));
    }
}