<제목>bizMOB Front Framework</제목>

<개요>bizMOB Front Framework인 xross는 Front화면 개발시 모바일 네이티브 앱과 연동되는 기능을 제공하기 위한 JS api가 정의된 xross.js파일과 실제 APP PlugIn과 연동하여 데이터를 처리하는 Core.js로 구성되어 있습니다. 또한 웹사이트 어플리케이션 구현을 위한 core-web.js라이브러리도 제공합니다. 네이티브 앱에서 제공하는 동일한 api를 호출하여 기능을 구현하고 모바일 앱과 Front어플리케이션 간의 네이티브 브리지 역할을 합니다.이를 통해 두 OS(iOS, Android )에서 Front 애플리케이션 개발시 동일한 비즈니스로직으로 구현이 가능 합니다.</개요>

<특징>

- Cross-Platform 지원 : iOS, Android 앱과 웹 브라우저에서 동일한 API 사용
- 통합 개발 환경 : 앱/웹 환경 자동 감지 및 적절한 Core 모듈 라우팅
- Rich Native API : 파일 시스템, 데이터베이스, 푸시, 디바이스 정보 등 모바일 네이티브 기능 제공
- Mock 시스템 : 개발 단계에서 서버API 또는 네이티브 API 없이도 테스트 가능한 Mock 데이터 지원
- 성능 최적화 : FStorage 메모리 캐시, 비동기 콜백 시스템 등 성능 최적화 기능
- JWT 토큰 인증 : 자동 토큰 갱신 및 만료 처리가 포함된 안전한 서버 통신
- 다국어 지원 : i18n Vue Plugin(https://vue-i18n.intlify.dev/) 기반 다국어 처리 및 로케일 관리
- 개발자 친화적 : TypeScript 지원, 상세한 JSDoc, 완전한 예제 코드 제공
  </특징>

<architecture> 
    <실행흐름>
        <실행1>TypeScript API Wrapper Classes</실행1>
        <실행2>bizMOB-xross4.js
            <조건 구동환경="모바일">
                 <실행3>bizMOB-core.js(Mobile App PlugIn APIs 호출 후 결과 return)</실행3>
            </조건>
            <조건 구동환경="웹">
                <실행3>bizMOB-core.js(Web Browser APIs 호출 후 결과 return)</실행3>
            </조건>
        </실행2>
    </실행흐름>
    <파일구조>
        <파일명>bizMOB-xross4.js
            <기능>App PlugIn들을 연동할 JS api 라이브러리</기능>
            <기능>Front Source에서 사용할 app api 제공</기능>
            <기능>App과 Web환경을 구분하여 bizMOB Core Library를 호출하여 네이티브 브리지 역할 수행</기능>
        </파일명>
        <파일명>bizMOB-core.js
            <기능>Native App PlugIn들과 콜백 방식을 통해 직접적인 통신을 하기 위한 라이브러리</기능>
            <기능>모바일 환경에서만 동작</기능>
            <기능>xross api를 통하여 호출된 기능중 Native App PlugIn들과 파라미터 설정,요청 및 response데이터 처리 등을 관리</기능>
        </파일명>
        <파일명>bizMOB-core-web.js
            <기능>웹 환경용 Core 모듈</기능>
            <기능>웹 브라우저에서만 동작</기능>
            <기능>xross api를 통하여 호출된 기능중 브라우저에서만 가능한 기능 처리</기능>
        </파일명>
    </파일구조>

 </architecture>
