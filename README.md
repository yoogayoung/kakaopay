
# 카카오페이 서버 개발 직무 과제
: 멤버십 서비스 구현

1. 서비스 설명
● 카카오페이에는 멤버십 서비스가 있습니다. 해당 서비스를 만들어봅시다.
● 멤버십은 사용자 별로 하나의 멤버십 바코드를 발급하고 있습니다.
● 상점에서 포인트 적립 또는 사용을 할 수 있습니다.
● 상점은 상점명과 하나의 업종 정보를 가지고 있습니다.
● 같은 업종의 가맹점들은 적립된 포인트를 같이 쓸 수 있습니다.
● 업종정보는 다음과 같이 3가지가 존재합니다. (A: 식품, B : 화장품, C : 식당)
● 하나의 상점은 하나의 업종 정보만 가지고 있고 변경 될수 없습니다.
● 사용자 탈퇴는 없으며, 발급된 바코드는 계속 유효합니다.
● 발급된 멤버십바코드는 가족이나 친구끼리 공유가 가능합니다.

2. 요구사항
● 아래 API들을 구현 합니다.
1. 통합 바코드 발급 API
2. 포인트 적립 API
3. 포인트 사용 API
4. 기간별 내역 조회 API
● 작성하신 어플리케이션이 다수의 서버에 다수의 인스턴스로 동작하더라도 기능에 문제가 없도록
설계되어야 합니다.
● 멤버십바코드는 공유가 가능해서 동일 바코드에 의한 사용, 적립 요청이 동시간에 들어올 경우도
고려해야 합니다.
● 각 기능 및 제약사항에 대한 단위테스트를 반드시 작성합니다.

3. API 설명
1) 통합 바코드 발급 API
● 요청값은 사용자 id가 포함됩니다.
● 사용자 id는 9자리 숫자, 멤버십 바코드는 10자리 숫자형 스트링을 사용합니다.
● 발급된 멤버십 바코드는 다른 사람과 중복될수 없습니다.
● 다음번 발급될 멤버십 바코드가 예측 가능해서도 안됩니다.
● 이미 발급된 id의 발급 요청이 올 경우 기존 멤버십 바코드를 반환합니다.
2) 포인트 적립 API
● 요청값은 상점 id, (1)번 API로 발급한 바코드, 적립금이 포함됩니다.
● 포인트 적립은 상점의 업종별로 통합하여 적립됩니다.
● 등록되지 않은 상점 id 경우 등록되지 않은 상점 오류를 돌려줍니다.
● 등록되지 않은 멤버십 바코드의 경우 등록되지 않은 멤버십 바코드 오류를 돌려줍니다.
3) 포인트 사용 API
● 요청값은 상점 id, (1)번 API로 발급한 바코드, 사용금액이 포함됩니다.
● 포인트 사용은 요청한 상점의 업종별 통합 적립금에서 사용됩니다.(B업종 정립금이 있더라도 요청
상점이 A업종이라고 사용할수 없습니다.)
● 포인트 사용요청 시 적립 금액을 초과하는 사용의 경우 포인트 부족으로 사용할수 없다는 오류를
돌려줍니다.
● 등록되지 않은 상점 id 경우 등록되지 않은 상점 오류를 돌려줍니다.
● 등록되지 않은 멤버십 바코드의 경우 등록되지 않은 멤버십 바코드 오류를 돌려줍니다.
4) 내역 조회 API
● 요청값은 시작기간, 종료기간, 멤버십 바코드가 포함되어야 합니다.
● 응답값에는 사용시기, 구분(적립, 사용), 상점명, 업종정보등이 포함되어야 합니다.
● 등록되지 않은 바코드의 경우 등록되지 않은 멤버십 바코드 오류를 돌려줍니다.
Ex)
{"history":[{"approved_at":"2022-04-12
10:10:10","type":"use","category":"A","partner_name":"F마트"},{"approved_at":"2022-04-12
20:10:10","type":"earn","category":"A","partner_name":"Z마트"}]}

기술 제약사항
● 개발 언어는 Java, Kotlin 중 익숙한 개발 언어를 선택하여 과제를 진행해주시면 됩니다.
● 설계 내용과 설계의 이유, 핵심 문제해결 전략 및 분석한 내용을 작성하여 "readme.md" 파일에
첨부 해주세요.
● 데이터베이스 사용에는 제약이 없습니다.
● API 의 HTTP Method들 (GET | POST | PUT | DEL) 은 자유롭게 선택하세요.
● 에러응답, 에러코드는 자유롭게 정의해주세요.
