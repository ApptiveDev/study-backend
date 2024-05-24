# study-backend

## N+1 문제 해결 미션 

### 조건 
1. 유저 3명 등록
2. 각 유저당 post 3개씩 등록


```
@GetMapping("/n-plus-one")
    public ResponseEntity<List<PostDto.Response>> getAllPost() {
        return ResponseEntity.ok(postService.NplusOne());
    }
```

위 API 호출 시 N+1 문제가 발생하는 상황을 해결하기
