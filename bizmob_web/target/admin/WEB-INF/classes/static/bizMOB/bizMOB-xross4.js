/**
 * bizMOB client xross interface
 *
 * @class	bizMOB xross 인터페이스
 * @version 4.0.0
 * @author	mobile C&C
 */
var bizMOB = new Object();

bizMOB.servicename = 'bizMOB';
bizMOB.FStorage = {}; // Native에서 직접 값을 저장하는 변수

/**
 * Config Set
 */
bizMOB.setConfig = function(sTarget, sClassName, oParam) {
    switch (sTarget) {
        case 'APP':
            window.bizMOBCore[sClassName].config = Object.assign({}, window.bizMOBCore[sClassName].config, oParam);
            break;

        case 'WEB':
            window.bizMOBWebCore[sClassName].config = Object.assign({}, window.bizMOBWebCore[sClassName].config, oParam);
            break;

        default:
            break;
    }
};

/**
 * Config Get
 */
bizMOB.getConfig = function(sTarget, sClassName) {
    switch (sTarget) {
        case 'APP':
            // App Target인 경우 App Core인 경우에만 반환
            return window.bizMOBCore[sClassName].config;

        case 'WEB':
            // Web Target인 경우 Web Core인 경우에만 반환
            return window.bizMOBWebCore[sClassName].config;

        default:
            return null;
    }
};

/**
 * Core 함수 호출 Gateway
 *
 * @param {String} sClassName 클래스 명칭
 * @param {String} sMethod 함수 명칭
 * @param {Array} aRequired 필수 키 값 목록
 * @param {Object} oParam 전달 파라미터
 */
bizMOB.gateway = function(sClassName, sMethod, aRequired, oParam) {
    var $bizMOBCore = window.bizMOBCore.readystatus ? window.bizMOBCore : window.bizMOBWebCore;
    var required = aRequired || [];
    var param = oParam || {};
    var isMock = param._bMock || (param._oParam && param._oParam._bMock) || false; // mock 데이터 호출 여부 (callPlugin 까지 고려)
    var isRelease = $bizMOBCore.App.config._bRelease || false; // Release 모드 여부

    // 필수 파라미터 check
    if ($bizMOBCore.Module.checkParam(param, required)) {
    // Service Call
        try {
            // Mock 데이터 호출 여부 (Release 모드가 아닌 경우에만 호출)
            if (isMock && !isRelease) {
                window.bizMOBWebCore.Http.requestMock(sClassName, sMethod, param);
            }
            // Logger 호출
            else if (sMethod === 'logger') {
                $bizMOBCore[sClassName][sMethod](param._sService, param._sAction, param._sLogType, param._sMessage);
            }
            // Core 함수 호출
            else {
                return $bizMOBCore[sClassName][sMethod](param);
            }
        }
        // 지원하지 않는 서비스인 경우
        catch (error) {
            var call = param._fCallback || param.callback || null;
            $bizMOBCore.Module.logger('bizMOB', 'gateway', 'W', 'This feature is not supported. - ' + sClassName + '.' + sMethod);

            // 이벤트 추가 관련은 callback 실행하지 않음
            if (sClassName !== 'EventManager') {
                call && call({ result: false, type: $bizMOBCore.name === 'bizMOBCore' ? 'app' : 'web' });
            }
        }
    }
    else {
        return false;
    }
};

/**
 * Native 이벤트 등록
 *
 * @param {String} sEvent Native 이벤트 명칭
 * @param {Function} sCallback Callback 함수 or 명칭
 */
bizMOB.setEvent = function(sEvent, fCallback) {
    if (bizMOB.Device.isApp()) {
        window.bizMOBCore.EventManager.set({ _sEvent: sEvent, _fCallback: fCallback });
    }
    else {
        window.bizMOBWebCore.EventManager.set({ _sEvent: sEvent, _fCallback: fCallback });
    }
};

/**
 * Native Event Clear
 *
 * @param {String} sEvent Clear할 Native 이벤트 명
 */
bizMOB.clearEvent = function(sEvent) {
    bizMOB.gateway('EventManager', 'clear', ['_sEvent'], { _sEvent: sEvent });
};

/**
 * 01.클래스 설명 : Web Application 로그 클래스
 * 02.제품구분 : bizMOB Xross
 * 03.기능(콤퍼넌트) 명 : Web Application개발시 로그 작성
 * 04.관련 API/화면/서비스 : bizMOBAppCore.Module.logger
 *
 * @author 김승현
 * @version 1.0
 */
bizMOB.Logger = new Object();

/**
 * info Level 로그 작성
 *
 * @param {String} sMessage 로그 메시지
 */
bizMOB.Logger.info = function(sMessage) {
    bizMOB.gateway('Module', 'logger', [], { _sService: 'Page', _sAction: 'info', _sLogType: 'I', _sMessage: sMessage });
};

/**
 * log Level 로그 작성
 *
 * @param {String} sMessage 로그 메시지
 */
bizMOB.Logger.log = function(sMessage) {
    bizMOB.gateway('Module', 'logger', [], { _sService: 'Page', _sAction: 'log', _sLogType: 'L', _sMessage: sMessage });
};

/**
 * warn Level 로그 작성
 *
 * @param {String} sMessage 로그 메시지
 */
bizMOB.Logger.warn = function(sMessage) {
    bizMOB.gateway('Module', 'logger', [], { _sService: 'Page', _sAction: 'warn', _sLogType: 'W', _sMessage: sMessage });
};

/**
 * debug Level 로그 작성
 *
 * @param {String} sMessage 로그 메시지
 */
bizMOB.Logger.debug = function(sMessage) {
    bizMOB.gateway('Module', 'logger', [], { _sService: 'Page', _sAction: 'debug', _sLogType: 'D', _sMessage: sMessage });
};

/**
 * error Level 로그 작성
 *
 * @param {String} sMessage 로그 메시지
 */
bizMOB.Logger.error = function(sMessage) {
    bizMOB.gateway('Module', 'logger', [], { _sService: 'Page', _sAction: 'error', _sLogType: 'E', _sMessage: sMessage });
};

/**
 * 01.클래스 설명 : Web Storage 클래스
 * 02.제품구분 : bizMOB Xross
 * 03.기능(콤퍼넌트) 명 : 휘발성 데이터 저장소
 * 04.관련 API/화면/서비스 : bizMOBAppCore.Module.checkparam,bizMOBAppCore.Storage.set, bizMOBAppCore.Storage.get, bizMOBAppCore.Storage.remove
 *
 * @author 김승현
 * @version 1.0
 */
bizMOB.Storage = new Object();

/**
 * Storage 데이터 저장
 *
 * @param {String} _sKey 저장할 값의 키
 * @param {Variable} _vValue 저장할 값
 */
bizMOB.Storage.set = function() {
    bizMOB.gateway('Storage', 'set', ['_sKey', '_vValue'], arguments[0]);
};

/**
 * Storage 복수 데이터 저장
 *
 * @param {Array} _aList 저장할 데이터들의 배열
 */
bizMOB.Storage.setList = function() {
    bizMOB.gateway('Storage', 'set', ['_aList'], arguments[0]);
};

/**
 * Storage 데이터 불러오기
 *
 * @param {String} _sKey 저장 값의 키
 */
bizMOB.Storage.get = function() {
    return bizMOB.gateway('Storage', 'get', ['_sKey'], arguments[0]);
};


/**
 * Storage 데이터 삭제
 *
 * @param {String} _sKey 저장 값의 키
 */
bizMOB.Storage.remove = function() {
    bizMOB.gateway('Storage', 'remove', ['_sKey'], arguments[0]);
};

/**
 * 01.클래스 설명 : Properties 저장 클래스
 * 02.제품구분 : bizMOB Xross
 * 03.기능(콤퍼넌트) 명 : 영구 데이터 저장소
 * 04.관련 API/화면/서비스 : bizMOBAppCore.Module.checkparam, bizMOBAppCore.Properties.set, bizMOBAppCore.Properties.get,bizMOBAppCore.Properties.remove
 *
 * @author 김승현
 * @version 1.0
 */
bizMOB.Properties = new Object();

/**
 * Properties 데이터 저장
 *
 * @param {String} _sKey 저장할 값의 키
 * @param {Variable} _vValue 저장할 값
 */
bizMOB.Properties.set = function() {
    bizMOB.gateway('Properties', 'set', ['_sKey', '_vValue'], arguments[0]);
};

/**
 * Properties 복수 데이터 저장.
 *
 * @param {Array} _aList 저장할 데이터들의 배열
 */
bizMOB.Properties.setList = function() {
    bizMOB.gateway('Properties', 'set', ['_aList'], arguments[0]);
};

/**
 * Properties 데이터 불러오기
 *
 * @param {String} _sKey 저장 값의 키
 */
bizMOB.Properties.get = function() {
    return bizMOB.gateway('Properties', 'get', ['_sKey'], arguments[0]);
};

/**
 * Properties 데이터 삭제
 *
 * @param {String} _sKey 저장 값의 키
 */
bizMOB.Properties.remove = function() {
    return bizMOB.gateway('Properties', 'remove', ['_sKey'], arguments[0]);
};


/**
 * 01.클래스 설명 : Network 통신 클래스
 * 02.제품구분 : bizMOB Xross
 * 03.기능(콤퍼넌트) 명 : bizMOB Server와 통신
 * 04.관련 API/화면/서비스 : bizMOBAppCore.Module.checkparam, bizMOBAppCore.Network.requestTr,bizMOBAppCore.Network.requestLogin
 *
 * @author 김승현
 * @version 1.0
 */
bizMOB.Network = new Object();

/**
 * Network 통신 시 로케일 변경
 *
 * @param {String} _sLocaleCd 언어코드
 */
bizMOB.Network.changeLocale = function() {
    bizMOB.gateway('Network', 'changeLocale', ['_sLocaleCd'], arguments[0]);
};

/**
 * bizMOB Server 전문 통신
 *
 * @param {String} _sTrcode bizMOB Server 인증 전문코드
 * @param {String} _oHeader bizMOB Server 인증 전문 Header 객체
 * @param {String} _oBody bizMOB Server 인증 전문 Body 객체
 * @param {Boolean} _bProgressEnable (default:true) 서버에 통신 요청시 progress 표시 여부( true 또는 false )
 * @param {Number} _nTimeout (default: 60) 서버에 통신 요청시 timeout 시간 (sec)
 * @param {Function} _fCallback	서버와 통신 후 실행될 callback 함수
 */

bizMOB.Network.requestTr = function() {
    bizMOB.gateway('Network', 'requestTr', ['_sTrcode'], arguments[0]);
};

/**
 * bizMOB Server 로그인(인증)전문 통신
 *
 * @param {String} _sUserId 인증 받을 사용자 아이디
 * @param {String} _sPassword 인증 받을 사용자 패스워드
 * @param {String} _sTrcode 레거시 로그인 인증 전문코드
 * @param {String} _oHeader 레거시 로그인 인증 전문 Header 객체
 * @param {String} _oBody 레거시 로그인 인증 전문 Body 객체
 * @param {Boolean} _bProgressEnable (default:true) 서버에 통신 요청시 progress 표시 여부( true 또는 false )
 * @param {Number} _nTimeout (default: 60) 서버에 통신 요청시 timeout 시간 (sec)
 * @param {Function} _fCallback	서버와 통신 후 실행될 callback 함수
 */
bizMOB.Network.requestLogin = function() {
    bizMOB.gateway('Network', 'requestLogin', ['_sUserId', '_sPassword'], arguments[0]);
};

/**
 * API 서버 통신
 *
 * @param {String} _sUrl 서버 URL
 * @param {String} _sMethod 통신 방식 (get, post)
 * @param {String} _oHeader Http Header
 * @param {String} _oBody Http Body
 * @param {Boolean} _bProgressEnable (default:true) 서버에 통신 요청시 progress 표시 여부( true 또는 false )
 * @param {Number} _nTimeout (default: 60) 서버에 통신 요청시 timeout 시간 (sec)
 * @param {Function} _fCallback	서버와 통신 후 실행될 callback 함수
 */

bizMOB.Network.requestHttp = function() {
    bizMOB.gateway('Network', 'requestHttp', ['_sUrl', '_sMethod'], arguments[0]);
};

/**
 * 01.클래스 설명 : System 기능 클래스
 * 02.제품구분 : bizMOB Xross
 * 03.기능(콤퍼넌트) 명 : OS 기반 기본 기능
 * 04.관련 API/화면/서비스 : bizMOBAppCore.Module.checkparam,bizMOBAppCore.System.callBrowser, bizMOBAppCore.System.callCamera, bizMOBAppCore.System.callGallery, bizMOBAppCore.System.callMap, bizMOBAppCore.System.callSMS, bizMOBAppCore.System.callTEL, bizMOBAppCore.System.getGPS
 *
 * @author 김승현
 * @version 1.0
 */
bizMOB.System = new Object();

/**
 * 전화걸기
 *
 * @param {String} _sNumber	전화번호
 * @param {Function} _fCallback	실행후 결과를 처리할 callback 함수
 */
bizMOB.System.callTEL = function() {
    bizMOB.gateway('System', 'callTEL', ['_sNumber'], arguments[0]);
};

/**
 * 문자보내기
 *
 * @param {Array} _aNumber	메세지를 보낼 전화번호 배열
 * @param {String} _sMessage	보낼 메세지
 * @param {Function} _fCallback	실행후 결과를 처리할 callback 함수
 */
bizMOB.System.callSMS = function() {
    bizMOB.gateway('System', 'callSMS', ['_aNumber'], arguments[0]);
};

/**
 * 단말기 설치된 브라우져 열기
 *
 * @param {String} _sURL	메세지를 보낼 전화번호 배열
 */
bizMOB.System.callBrowser = function() {
    bizMOB.gateway('System', 'callBrowser', ['_sURL'], arguments[0]);
};

/**
 * 단말기 디바이스의 갤러리(사진앨범) 보기
 *
 * @param {String} _sType	String	(Default : all) 갤러리에서 불러올 미디어 타입( all, image, video )가 있습니다.
 * @param {Function} _fCallback	갤러리에서 선택한 미디어를 결과를 전달 받아서 처리할 callback 함수.
 */
bizMOB.System.callGallery = function() {
    bizMOB.gateway('System', 'callGallery', ['_fCallback'], arguments[0]);
};

/**
 * 단말기 카메라 촬영
 *
 * @param {Function} _fCallback		갤러리에서 선택한 미디어를 전달 받아서 처리하는 callback 함수
 * @param {String} _sFileName		찍은 이미지를 저장할 이름
 * @param {String} _sDirectory	찍은 이미지를 저장할 경로
 * @param {Boolean} _bAutoVerticalHorizontal	(Default : true) 찍은 이미지를 화면에 맞게 자동으로 회전시켜 저장할지를 설정 값
 */
bizMOB.System.callCamera = function() {
    bizMOB.gateway('System', 'callCamera', ['_fCallback'], arguments[0]);
};


/**
 * 단말기 지도 실행
 *
 * @param {String} _sLocation	위치 정보(주소, 위경도값)
 */
bizMOB.System.callMap = function() {
    bizMOB.gateway('System', 'callMap', ['_sLocation'], arguments[0]);
};

/**
 * OS별 지도 실행
 *
 * @param {String} _sLocation	위치 정보(주소, 위경도값)
 */
bizMOB.System.getGPS = function() {
    bizMOB.gateway('System', 'getGPS', ['_fCallback'], arguments[0]);
};

/**
 * 01.클래스 설명 : bizMOB Window 클래스
 * 02.제품구분 : bizMOB Xross
 * 03.기능(콤퍼넌트) 명 :  bizMOB Client에서 생성하는 Window 객체
 * 04.관련 API/화면/서비스 : bizMOBAppCore.Module.checkparam,bizMOBAppCore.Window.alert,bizMOBAppCore.Window.back,bizMOBAppCore.Window.close,bizMOBAppCore.Window.confirm,bizMOBAppCore.Window.createElement,bizMOBAppCore.Window.createElement.setProperty,bizMOBAppCore.Window.createSideBar,bizMOBAppCore.Window.createSideBar.setProperty,bizMOBAppCore.Window.createTitleBar,bizMOBAppCore.Window.createTitleBar.setProperty,bizMOBAppCore.Window.createToolBar,bizMOBAppCore.Window.createToolBar.setProperty,bizMOBAppCore.Window.draw,bizMOBAppCore.Window.go,bizMOBAppCore.Window.open,bizMOBAppCore.Window.openCodeReader,bizMOBAppCore.Window.openImageViewer,bizMOBAppCore.Window.openSignPad,bizMOBAppCore.Window.postMessage,bizMOBAppCore.Window.replace,bizMOBAppCore.Window.toast
 *
 * @author 김승현
 * @version 1.0
 */
bizMOB.Window = new Object();

/**
 * SignPad(서명) Window 띄우기
 *
 * @param {String} _sTargetPath		사인패드에서 서명한 이미지를 저장할 File Path.
 * @param {Function} _fCallback		사인패드 처리 결과값을 받을 callback 함수.
 */
bizMOB.Window.openSignPad = function() {
    bizMOB.gateway('Window', 'openSignPad', ['_sTargetPath'], arguments[0]);
};

/**
 * ImageViewer  띄우기
 *
 * @param {String} _sImagePath	이미지 뷰어로 열 이미지 File Path.
 * @param {Function} _fCallback	 이미지 뷰어 Close시 결과값을 받을 callback함수.
 */
bizMOB.Window.openImageViewer = function() {
    bizMOB.gateway('Window', 'set', [], arguments[0]);
};

/**
 * CodeReader( BarCode, QRCode )  띄우기
 *
 * @param {Function} _fCallback	 Code 판독 결과값을 받을 callback함수.
 */
bizMOB.Window.openCodeReader = function() {
    bizMOB.gateway('Window', 'openCodeReader', [], arguments[0]);
};

/**
 * FileExplorer 띄우기
 *
 * @param {Function} _fCallback	탐색기에서 선택한 파일 정보 결과값을 받을 callback함수.
 */
bizMOB.Window.openFileExplorer = function() {
    bizMOB.gateway('Window', 'openFileExplorer', ['_fCallback'], arguments[0]);
};

/**
 * 01.클래스 설명 : App 컨트롤 관련 클래스
 * 02.제품구분 : bizMOB Xross
 * 03.기능(콤퍼넌트) 명 : App 컨트롤 관련 기능
 * 04.관련 API/화면/서비스 : bizMOBAppCore.Module.checkparam,bizMOBAppCore.App.exit, bizMOBAppCore.App.getTimeout, bizMOBAppCore.App.setTimeout
 *
 * @author 김승현
 * @version 1.0
 */
bizMOB.App = new Object();

/**
 * App 종료
 *
 * @param {String} _sType	(Default : kill )어플리케이션 종료 유형( logout 또는 kill )
 */
bizMOB.App.exit = function() {
    bizMOB.gateway('App', 'exit', [], arguments[0]);
};

/**
 * App 자동 종료 시간 설정
 *
 * @param {Number} _nSeconds	( default : 7200 )어플리케이션의 세션 만료 시간(초단위) 설정 값.
 */
bizMOB.App.setTimeout = function() {
    bizMOB.gateway('App', 'requestTimeout', ['_nSeconds'], arguments[0]);
};

/**
 * App 자동 종료 설정 시간 조회
 *
 * @param {Function} _fCallback	세션 만료 시간을 받아서 처리할 Callback 함수.
 */
bizMOB.App.getTimeout = function() {
    bizMOB.gateway('App', 'getTimeout', ['_fCallback'], arguments[0]);
};

/**
 * App 스플래시가 수동 조작인 경우 종료 기능
 *
 * @param {Function} _fCallback	세션 만료 시간을 받아서 처리할 Callback 함수.
 */
bizMOB.App.hideSplash = function() {
    bizMOB.gateway('App', 'hideSplash', [], arguments[0]);
};

/**
 * App 자동 종료 설정 시간 조회
 *
 * @param {String} sId 커스텀으로 추가된 플러그인 Call ID.
 * @param {Object} oParam 플러그인에서 사용될 Parameters.
 */
bizMOB.App.callPlugIn = function(sId, oParam)	{
    bizMOB.gateway('ExtendsManager', 'executer', ['_sID'], { _sID: sId, _oParam: oParam });
};

/**
 * 01.클래스 설명 : 전화번호부 클래스
 * 02.제품구분 : bizMOB Xross
 * 03.기능(콤퍼넌트) 명 : 전화번호부 관련 클래스
 * 04.관련 API/화면/서비스 : bizMOBAppCore.Module.checkparam,bizMOBAppCore.App.exit, bizMOBAppCore.App.getTimeout, bizMOBAppCore.App.setTimeout
 *
 * @author 김승현
 * @version 1.0
 */
bizMOB.Contacts = new Object();

/**
 * 전화번호부 검색
 *
 * @param {String} _sSearchType	(Default : "", 전체조회) 주소록 검색 대상 필드(name 또는 phone)
 * @param {String} _sSearchText	(Default : "") 주소록 검색어
 * @param {Function} _fCallback	주소록 검색 결과를 받아 처리할 callback함수
 */
bizMOB.Contacts.get = function() {
    bizMOB.gateway('Contacts', 'get', [], arguments[0]);
};

/**
 * 01.클래스 설명 : File 클래스
 * 02.제품구분 : bizMOB Xross
 * 03.기능(콤퍼넌트) 명 : File 컨트롤 클래스.
 * 04.관련 API/화면/서비스 : bizMOBAppCore.Module.checkparam,bizMOBAppCore.File.copy,bizMOBAppCore.File.directory,bizMOBAppCore.File.download,bizMOBAppCore.File.exist,bizMOBAppCore.File.getInfo,bizMOBAppCore.File.move,bizMOBAppCore.File.open,bizMOBAppCore.File.remove,bizMOBAppCore.File.resizeImage,bizMOBAppCore.File.rotateImage,bizMOBAppCore.File.unzip,bizMOBAppCore.File.upload,bizMOBAppCore.File.zip
 *
 * @author 김승현
 * @version 1.0
 */
bizMOB.File = new Object();

/**
 * 파일 열기
 *
 * @param {String} _sSourcePath 열어볼 파일 경로. 기본 설치App으로 연결.
 * @param {Function} _fCallback 파일을 열고 난 후 호출될 callback함수.
 */
bizMOB.File.open = function() {
    bizMOB.gateway('File', 'open', ['_sSourcePath'], arguments[0]);
};


/**
 * 파일 압축
 *
 * @param {String} _sSourcePath 소스 File Path.
 * @param {String} _sTargetPath 결과 File Path.
 * @param {Function} _fCallback 압축 후 호출될 callback함수.
 */
bizMOB.File.zip= function() {
    bizMOB.gateway('File', 'zip', ['_sSourcePath', '_sTargetPath'], arguments[0]);
};

/**
 * 파일 압축해제
 *
 * @param {String} _sSourcePath 소스 File Path.
 * @param {String} _sDirectory 압축 해제할 Directory Path.
 * @param {Function} _fCallback 압축 후 호출될 callback함수.
 */
bizMOB.File.unzip= function() {
    bizMOB.gateway('File', 'unzip', ['_sSourcePath', '_sDirectory'], arguments[0]);
};

/**
 * 파일 이동
 *
 * @param {String} _sSourcePath 소스 File Path.
 * @param {String} _sTargetPath 이동될 File Path.
 * @param {Function} _fCallback 이동 후 호출될 callback함수.
 */
bizMOB.File.move = function() {
    bizMOB.gateway('File', 'move', ['_sSourcePath', '_sTargetPath'], arguments[0]);
};

/**
 * 파일 복사
 *
 * @param {String} _sSourcePath 소스 File Path.
 * @param {String} _sTargetPath 복사될 File Path.
 * @param {Function} _fCallback 복사 후 호출될 callback함수.
 */
bizMOB.File.copy = function() {
    bizMOB.gateway('File', 'copy', ['_sSourcePath', '_sTargetPath'], arguments[0]);
};

/**
 * 파일 삭제
 *
 * @param {Array} _aSourcePath 삭제할 File Path 목록.
 * @param {Function} _fCallback 삭제 후 호출될 callback함수.
 */
bizMOB.File.remove = function() {
    bizMOB.gateway('File', 'remove', ['_aSourcePath'], arguments[0]);
};

/**
 * 디렉토리 정보 읽기
 *
 * @param {Array} _aSourcePath 삭제할 File Path 목록.
 * @param {Function} _fCallback 삭제 후 호출될 callback함수.
 */
bizMOB.File.directory = function() {
    bizMOB.gateway('File', 'directory', ['_sDirectory'], arguments[0]);
};


/**
 * 파일 존재 여부 확인
 *
 * @param {String} _sSourcePath 확인할 File Path 목록.
 * @param {Function} _fCallback 확인 후 호출될 callback함수.
 */
bizMOB.File.exist = function() {
    bizMOB.gateway('File', 'exist', ['_sSourcePath'], arguments[0]);
};

/**
 * 파일 다운로드
 *
 * @param {Array} _aFileList 다운로드할 URL 주소 목록.
 * @param {String} _sMode 파일 다운로드 모드. (background 또는 foreground ).
 * @param {String} _sProgressBar 다운로드할 때 프로그래스바 설정 값.( off , each, full )
 * @param {Function} _fCallback 결과를 받을 callback 함수.
 */
bizMOB.File.download = function() {
    bizMOB.gateway('File', 'download', ['_aFileList'], arguments[0]);
};

/**
 * 파일 업로드
 *
 * @param {Array} _aFileList 업로드할 File Path 목록.
 * @param {Function} _fCallback	 결과를 받을 callback 함수.
 */
bizMOB.File.upload = function() {
    bizMOB.gateway('File', 'upload', ['_aFileList'], arguments[0]);
};

/**
 * 파일 정보 가져오기
 *
 * @param {Array} _aFileList 정보를 가져올 File Path 목록.
 * @param {Function} _fCallback	 결과를 받을 callback 함수.
 */
bizMOB.File.getInfo = function() {
    bizMOB.gateway('File', 'getInfo', ['_aFileList'], arguments[0]);
};

/**
 * 이미지 파일 리사이즈
 *
 * @param {Array} _aFileList 이미지 파일 목록.
 * @param {Boolean} _bIsCopy (Default : false) 원본 파일 유지 여부. (true 또는 false)
 * @param {String} _sTargetDirectory _bIsCopy가 true일 경우 복사본이 저장될 디렉토리 경로.
 * @param {Number} _nWidth 파일의 가로 크기를 설정.
 * @param {Number} _nHeight  파일의 세로 크기를 설정.
 * @param {Number} _nCompressRate	Number X (Default : 1.0) 파일의 압축률 값( 0.0부터 1.0까지 값 지정가능 )
 * @param {Number} _nFileSize 리사이즈 된 파일 용량의 최대값.( byte단위 )
 * @param {Function} _fCallback 결과를 받아 처리할 callback 함수.
 */
bizMOB.File.resizeImage = function() {
    bizMOB.gateway('File', 'resizeImage', ['_aFileList'], arguments[0]);
};

/**
 * 이미지 파일 회전
 *
 * @param {String} _sSourcePath 이미지 File Path.
 * @param {String} _sTargetPath 회전된 이미지가 저장될 Path.
 * @param {Number} _nOrientation 회전 시킬 각도(EXIF_Orientation)값.(1, 2, 3, 4, 5, 6, 7, 8 )
 * @param {Function} _fCallback 결과를 받아 처리할 callback 함수.
 */
bizMOB.File.rotateImage = function() {
    bizMOB.gateway('File', 'rotateImage', ['_sSourcePath', '_sTargetPath', '_nOrientation'], arguments[0]);
};

/**
 * 01.클래스 설명 : Push 기능 클래스
 * 02.제품구분 : bizMOB Xross
 * 03.기능(콤퍼넌트) 명 : bizPush Server Open API연동 본 기능
 * 04.관련 API/화면/서비스 : bizMOBAppCore.Module.checkparam,bizMOBAppCore.Push.getAlarm,bizMOBAppCore.Push.getMessageList,bizMOBAppCore.Push.getPushKey,bizMOBAppCore.Push.getUnreadCount,bizMOBAppCore.Push.readMessage,bizMOBAppCore.Push.registerToServer,bizMOBAppCore.Push.sendMessage,bizMOBAppCore.Push.setAlarm,bizMOBAppCore.Push.setBadgeCount
 *
 * @author 김승현
 * @version 1.0
 */
bizMOB.Push = new Object();

/**
 * 푸시 기본 저장 정보 초기화
 *
 * @param
{ *}
 * @return
 */
bizMOB.Push.reset = function()	{
    bizMOB.gateway('PushManager', 'reset', [], arguments[0]);
};

/**
 * 푸시키 정보 조회
 *
 * @param {Function} _fCallback	결과를 받아 처리할 callback 함수.
 * @param {Boolean} _bProgressEnable		(default:true) 푸시 서버와 통신 중일때 화면에 progress 를 표시할지에 대한 여부( true 또는 false )
 */
bizMOB.Push.getPushKey = function()	{
    bizMOB.gateway('PushManager', 'getPushKey', ['_fCallback'], arguments[0]);
};

/**
 * 푸시서버에 사용자 정보 등록
 *
 * @param {String} _sServerType		푸시키를 등록할 서버 타입.( bizpush 또는 push )
 * @param {String_sUserId}			푸시키를 등록할 사용자 아이디.
 * @param {String} _sAppName		푸시키를 등록할 앱 이름.
 * @param {Function} _fCallback		결과를 받아 처리할 callback 함수.
 * @param {Boolean} _bProgressEnable		(default:true) 푸시 서버와 통신 중일때 화면에 progress 를 표시할지에 대한 여부( true 또는 false )
 */
bizMOB.Push.registerToServer = function()	{
    bizMOB.gateway('PushManager', 'registerToServer', ['_sServerType', '_sUserId', '_sAppName'], arguments[0]);
};

/**
 * 푸시 알람 수신여부 설정
 *
 * @param {String} _sUserId		푸시 알림 설정을 등록할 사용자 이이디.
 * @param {Boolean}  _bEnabled		(Default : true) 알람 수신 여부 설정 ( true 또는 false )
 * @param {Boolean} _bProgressEnable		(Default:true) 푸시 알람 설정 요청시 화면에 progress 표시 여부( true 또는 false )
 * @param {Function} _fCallback	결과를 받아 처리할 callback 함수.
 */
bizMOB.Push.setAlarm = function()	{
    bizMOB.gateway('PushManager', 'setAlarm', ['_sUserId'], arguments[0]);
};

/**
 * 푸시 알람 수신여부 조회
 *
 * @param {String} _sUserId		푸시 알림 설정을 조회할 사용자 이이디.
 * @param {Boolean} _bProgressEnable		(Default:true) 푸시 알람 설정 요청시 화면에 progress 표시 여부( true 또는 false )
 * @param {Function} _fCallback	결과를 받아 처리할 callback 함수.
 */
bizMOB.Push.getAlarm = function()	{
    bizMOB.gateway('PushManager', 'getAlarm', ['_sUserId'], arguments[0]);
};

/**
 * 푸시 수신 목록 조회
 *
 * @param {String} _sAppName	푸시 서버에 등록된 앱 이름.
 * @param {String} _sUserId		푸시 메세지를 조회할 사용자 이이디.
 * @param {Number} _nPageIndex	푸시 메세지를 가져올 페이징 값.
 * @param {Number} _nItemCount	푸시 메세지를 가져올 페이징 처리 갯수
 * @param {Boolean} _bProgressEnable		(default:true) 푸시 서버와 통신 중일때 화면에 progress 를 표시할지에 대한 여부( true 또는 false )
 * @param {Function} _fCallback	결과를 받아 처리할 callback 함수.
 */
bizMOB.Push.getMessageList = function()	{
    bizMOB.gateway('PushManager', 'getMessageList', ['_sAppName', '_nPageIndex', '_nItemCount', '_sUserId', '_fCallback'], arguments[0]);
};

/**
 * 푸시 메세지 읽기
 *
 * @param {String} _sTrxDay		푸시 메세지를 읽은 날짜.(yyyymmdd)
 * @param {String} _sTrxId		푸시 메세지 아이디.
 * @param {String} _sUserId	사용자 아이디.
 * @param {Boolean} _bProgressEnable		(Default:true) 푸시 알람 설정 요청시 화면에 progress 표시 여부( true 또는 false )
 * @param {Function} _fCallback	결과를 받아 처리할 callback 함수.
 */
bizMOB.Push.readMessage = function()	{
    bizMOB.gateway('PushManager', 'readMessage', ['_sTrxDay', '_sTrxId', '_sUserId'], arguments[0]);
};

/**
 * 읽지 않은 푸시 메세지 카운트 조회
 *
 * @param {String} _sAppName	푸시 서버에 등록된 앱 이름.
 * @param {String} _sUserId		푸시 메세지를 조회할 사용자 이이디.
 * @param {Boolean} _bProgressEnable		(Default:true) 푸시 알람 설정 요청시 화면에 progress 표시 여부( true 또는 false )
 * @param {Function} _fCallback	결과를 받아 처리할 callback 함수.
 */
bizMOB.Push.getUnreadCount = function()	{
    bizMOB.gateway('PushManager', 'getUnreadMessageCount', ['_sAppName', '_sUserId', '_fCallback'], arguments[0]);
};

/**
 * 앱 아이콘에 숫자 표시하기
 *
 * @param {Number} _nBadgeCount		뱃지에 표시할 값 .( 양수 : 표시할 갯수 ,  0 : 뱃지카운트 초기화 )
 * @param {Function} _fCallback	결과를 받아 처리할 callback 함수.
 */
bizMOB.Push.setBadgeCount = function()	{
    bizMOB.gateway('PushManager', 'setBadgeCount', ['_nBadgeCount'], arguments[0]);
};

/**
 * 푸시 메세지 발송
 *
 * @param {String} _sAppName	푸시 메세지 보낼 앱 이름.
 * @param {Array} _aUsers		푸시 메세지 받을 사용자 목록.
 * @param {String} _sFromUser	푸시 메세지를 보낼 사용자 아이디.
 * @param {String} _sSubject		푸시 메세지 제목.
 * @param {String} _sContent		푸시 메세지 내용.
 * @param {String} _sTrxType		(Default : INSTANT) 푸시 메세지 전송 방식.( INSTANT 또는 SCHEDULE )
 * @param {String} _sScheduleDate	푸시 메세지 전송 날짜.
 * @param {Array} _aGroups	푸시 메세지를 받을 그룹 목록
 * @param {Boolean} _bToAll	(Default : false) 해당 앱을 사용하는 전체 사용자에게 푸시 메세지를 발송할지 여부.
 * @param {String} _sCategory	(Default : def) 푸시 메세지 카테고리.
 * @param {Object} _oPayLoad	푸시 기폰 용량 초과시 전달할 메세지.
 * @param {Boolean} _bProgressEnable		(Default:true) 푸시 알람 설정 요청시 화면에 progress 표시 여부( true 또는 false )
 * @param {Function} _fCallback	결과를 받아 처리할 callback 함수.
 */
bizMOB.Push.sendMessage = function()	{
    bizMOB.gateway('PushManager', 'sendMessage', ['_sAppName', '_aUsers', '_sFromUser', '_sSubject', '_sContent'], arguments[0]);
};

/**
 * 대용량 푸시 메세지 읽기
 * APNS와 GCM은 메시지 용량에 제한이 있으므로 appkey만 가지고 메시지 읽는 기능
 *
 * @param {String} _sMessageId	푸시 메세지 아이디.
 * @param {String} _sUserId	사용자 아이디.
 * @param {Function} _fCallback	결과를 받아 처리할 callback 함수.
 */
bizMOB.Push.readReceiptMessage = function()	{
    bizMOB.gateway('PushManager', 'readReceiptMessage', ['_sUserId', '_sMessageId', '_fCallback'], arguments[0]);
};

/**
 * 01.클래스 설명 : Device 기능 클래스 .
 * 02.제품구분 : bizMOB Xross
 * 03.기능(콤퍼넌트) 명 : bizMOB Client 단말기 정보 기능
 * 04.관련 API/화면/서비스 : bizMOBAppCore.Module.checkparam,bizMOBAppCore.Push.getAlarm,bizMOBAppCore.Push.getMessageList,bizMOBAppCore.Push.getPushKey,bizMOBAppCore.Push.getUnreadCount,bizMOBAppCore.Push.readMessage,bizMOBAppCore.Push.registerToServer,bizMOBAppCore.Push.sendMessage,bizMOBAppCore.Push.setAlarm,bizMOBAppCore.Push.setBadgeCount
 *
 * @author 김승현
 * @version 1.0
 */
bizMOB.Device = new Object();
bizMOB.Device.Info = {}; // Native에서 직접 값을 저장하는 변수

/**
 * 단말기 정보조회
 *
 * @param {String} _sKey	디바이스 정보 키 값.
 */
bizMOB.Device.getInfo = function() {
    return bizMOB.gateway('DeviceManager', 'getInfo', [], arguments[0]);
};

/**
 * App 판단 여부
 */
bizMOB.Device.isApp = function() {
    return bizMOB.gateway('DeviceManager', 'isApp', [], arguments[0]);
};

/**
 * Web 판단 여부
 */
bizMOB.Device.isWeb = function() {
    return bizMOB.gateway('DeviceManager', 'isWeb', [], arguments[0]);
};

/**
 * Mobile 여부
 */
bizMOB.Device.isMobile = function() {
    return bizMOB.gateway('DeviceManager', 'isMobile', [], arguments[0]);
};

/**
 * PC 여부
 */
bizMOB.Device.isPC = function() {
    return bizMOB.gateway('DeviceManager', 'isPC', [], arguments[0]);
};

/**
 * Android 여부
 */
bizMOB.Device.isAndroid = function() {
    return bizMOB.gateway('DeviceManager', 'isAndroid', [], arguments[0]);
};

/**
 * IOS 여부
 */
bizMOB.Device.isIOS = function() {
    return bizMOB.gateway('DeviceManager', 'isIOS', [], arguments[0]);
};

/**
 * Tablet 여부
 */
bizMOB.Device.isTablet = function() {
    return bizMOB.gateway('DeviceManager', 'isTablet', [], arguments[0]);
};

/**
 * Phone 여부
 */
bizMOB.Device.isPhone = function() {
    return bizMOB.gateway('DeviceManager', 'isPhone', [], arguments[0]);
};

/**
 * 01.클래스 설명 : Database 기능 클래스 .
 * 02.제품구분 : bizMOB Xross
 * 03.기능(콤퍼넌트) 명 : 컨테이너 SQLite DB 사용 기능
 * 04.관련 API/화면/서비스 : bizMOBAppCore.Module.checkparam,bizMOBAppCore.Database.beginTransaction,bizMOBAppCore.Database.closeDatabase,bizMOBAppCore.Database.commitTransaction,bizMOBAppCore.Database.executeBatchSql,bizMOBAppCore.Database.executeSelect,bizMOBAppCore.Database.executeSql,bizMOBAppCore.Database.openDatabase
 *
 * @author 김승현
 * @version 1.0
 */
bizMOB.Database = new Object();

/**
 * DataBase Open
 *
 * @param {String} _sDbName		오픈할 데이터베이스 명.
 * @param {Function} _fCallback		결과를 받아 처리할 callback 함수.
 */
bizMOB.Database.openDatabase = function() {
    bizMOB.gateway('Database', 'openDatabase', ['_sDbName', '_fCallback'], arguments[0]);
};

/**
 * DataBase Close
 *
 * @param {Function} _fCallback		결과를 받아 처리할 callback 함수.
 */
bizMOB.Database.closeDatabase = function() {
    bizMOB.gateway('Database', 'closeDatabase', ['_fCallback'], arguments[0]);
};

/**
 * DataBase Transaction 시작
 *
 * @param {Function} _fCallback		결과를 받아 처리할 callback 함수.
 */
bizMOB.Database.beginTransaction = function() {
    bizMOB.gateway('Database', 'beginTransaction', ['_fCallback'], arguments[0]);
};

/**
 * DataBase Transaction Commit
 *
 * @param {Function} _fCallback		결과를 받아 처리할 callback 함수.
 */
bizMOB.Database.commitTransaction = function() {
    bizMOB.gateway('Database', 'commitTransaction', ['_fCallback'], arguments[0]);
};

/**
 * DataBase Transaction Rollback
 *
 * @param {Function} _fCallback		결과를 받아 처리할 callback 함수.
 */
bizMOB.Database.rollbackTransaction = function() {
    bizMOB.gateway('Database', 'rollbackTransaction', ['_fCallback'], arguments[0]);
};

/**
 * SQL쿼리문을 실행
 *
 * @param {String} _sQuery		실행할 SQL SELECT 쿼리문.
 * @param {Array} _aBindingValues		쿼리문의 각 변수 위치에 대입해줄 값의 배열.
 * @param {Function} _fCallback		SQL쿼리문 실행 요청 후 호출되는 callback 함수.
 */
bizMOB.Database.executeSql = function() {
    bizMOB.gateway('Database', 'executeSql', ['_sQuery', '_fCallback'], arguments[0]);
};

/**
 * SELECT SQL쿼리문을 실행
 *
 * @param {String} _sQuery		실행할 SQL SELECT 쿼리문.
 * @param {Array} _aBindingValues		쿼리문의 각 변수 위치에 대입해줄 값의 배열.
 * @param {Function} _fCallback		SQL쿼리문 실행 요청 후 호출되는 callback 함수.
 */
bizMOB.Database.executeSelect = function() {
    bizMOB.gateway('Database', 'executeSelect', ['_sQuery', '_fCallback'], arguments[0]);
};

/**
 * SQL쿼리문을 일괄 실행
 *
 * @param {String} _sQuery		실행할 SQL SELECT 쿼리문.
 * @param {Array} _aBindingValues		쿼리문의 각 변수 위치에 대입해줄 값의 배열.
 * @param {Function} _fCallback		SQL쿼리문 실행 요청 후 호출되는 callback 함수.
 */
bizMOB.Database.executeBatchSql = function() {
    bizMOB.gateway('Database', 'executeBatchSql', ['_sQuery', '_aBindingValues', '_fCallback'], arguments[0]);
};

/**
 * 01.클래스 설명 : Web 관련 클래스
 * 02.제품구분 : bizMOB Xross
 * 03.기능(콤퍼넌트) 명 : bizMOB Web Client 관련 기능
 *
 * @author 최명훈
 * @version 1.0
 */
bizMOB.Http = new Object();

/**
 * Http request 호출
 */
bizMOB.Http.request = function() {
    bizMOB.gateway('Http', 'request', [], arguments[0]);
};


/**
 * 01.클래스 설명 : Web 관련 클래스
 * 02.제품구분 : bizMOB Xross
 * 03.기능(콤퍼넌트) 명 : bizMOB Web Client 관련 기능
 *
 * @author 최명훈
 * @version 1.0
 */
bizMOB.Localization = new Object();

/**
  * Http request 호출
  */
bizMOB.Localization.setLocale = function() {
    bizMOB.gateway('Localization', 'setLocale', ['_sLocaleCd', '_fCallback'], arguments[0]);
};

/**
  * Http request 호출
  */
bizMOB.Localization.getLocale = function() {
    bizMOB.gateway('Localization', 'getLocale', ['_fCallback'], arguments[0]);
};

// Script ready
(function() {
    bizMOB.gateway('Module', 'logger', [], { _sService: 'bizMOB', _sAction: 'ready', _sLogType: 'I', _sMessage: 'bizMOB script is ready.' });
})();