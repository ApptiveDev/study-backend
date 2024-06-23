# 앱티브 1학기 MVP 미션

### 구현 목표

- [x]  유저 등록 API  (파라미터 :이름, 이메일)
- [x]  유저 조회 API(by email) (유저의 이름 반환)
- [x]  유저 삭제 API(by email) (유저 삭제)
- [x]  유저 수정 API(by email) (유저 이름 변경)
- [x]  게시글 작성 API (파라미터: user email, 게시글 title, 게시글 content)
- [x]  게시글 전체 조회 API (모든 게시글) (게시글의 제목, post id를 반환)
- [x]  게시글 상세조회 API (파라미터: post id) , (게시글의 제목, 내용을 반환)
- [x]  특정 유저의 게시글 조회 API (파라미터: user email) (유저의 이메일을 통해 특정 유저가 작성한 게시글을 모두 조회한다 게시글의 제목, post id를 반환)

### 상세 조건

1. 유저는 이름, 이메일을 가진다.
2. 유저의 이메일은 고유해야한다. (유저 등록 전 확인)
3. 유저는 게시글을 여러개 작성할 수 있다. ( 유저 : 게시글 = 1 : N 매핑)
4. 게시글은 제목, 내용, 등록한 유저를 가진다.

### 추가 목표

1. 유저를 삭제하면 유저가 삭제한 게시글을 함께 삭제하기. (**orphanRemoval 옵션)**

### N + 1 문제 해결
- [Fetch join](https://secretive-clipper-af5.notion.site/N-1-fetch-join-999a97b429b140a794ffa4a42bc118ae)
