# 사용자 정보
CREATE TABLE `TB_USER` (
	`IDX`         bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '유저IDX',
	`EMAIL`       varchar(50)  NOT NULL                            COMMENT '이메일',
	`PASSWORD`    varchar(500) NOT NULL                            COMMENT '비밀번호',
	`NAME`        varchar(20)  NOT NULL                            COMMENT '유저이름',
	`ROLE_IDX`    int          NOT NULL DEFAULT 1                  COMMENT '권한',
	`STATUS`      bit(1)       NOT NULL DEFAULT 0                  COMMENT '탈퇴유무(탈퇴1 , 비탈퇴0)',
	`REG_DATE`    datetime     NOT NULL DEFAULT now()              COMMENT '등록일',
	`UDT_DATE`    datetime     NULL                                COMMENT '수정일'
);

ALTER TABLE TB_USER COMMENT='사용자 정보';
ALTER TABLE TB_USER ADD UNIQUE UK_NAME (EMAIL);


# 사용자 권한
CREATE TABLE `TB_ROLE` (
    `IDX`       int         NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '권한IDX',
    `ROLE_NAME` varchar(30) NOT NULL                            COMMENT '권한이름',
    `COMMENT`   varchar(50) NULL                                COMMENT '권한설명'
);

ALTER TABLE TB_ROLE COMMENT='사용자 권한';

# 권한 2개
INSERT INTO TB_ROLE (ROLE_NAME, COMMENT) VALUES ('ROLE_USER','유저');
INSERT INTO TB_ROLE (ROLE_NAME, COMMENT) VALUES ('ROLE_ADMIN','관리자');


# 시험문제 메인 카테고리
CREATE TABLE `TB_EXAM_MAIN_CATEGORY` (
	`IDX`                 bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '메인카테고리IDX',
	`MAIN_CATEGORY_NAME`  varchar(30)  NOT NULL                            COMMENT '메인카테고리명',
	`USE_STATUS`          bit(1)       NOT NULL DEFAULT 1                  COMMENT '사용유무',
	`REG_DATE`            datetime     NOT NULL DEFAULT now()              COMMENT '등록일',
	`REG_IDX`             bigint       NOT NULL                            COMMENT '등록자',
	`UDT_DATE`            datetime     NULL                                COMMENT '수정일',
	`UDT_IDX`             bigint       NULL                                COMMENT '수정자'
);

ALTER TABLE TB_EXAM_MAIN_CATEGORY COMMENT='시험문제 메인 카테고리';


# 시험문제 서브 카테고리
CREATE TABLE `TB_EXAM_SUB_CATEGORY` (
    `IDX`                bigint      NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '서브카테고리IDX',
    `MAIN_CATEGORY_IDX`  bigint      NOT NULL                            COMMENT '메인카테고리IDX',
    `SUB_CATEGORY_NAME`  varchar(30) NOT NULL                            COMMENT '서브카테고리명',
    `OPTIONS_CNT`        int         NOT NULL                            COMMENT '선지 개수',
    `EXAM_CUT_OFF_SCORE` float       NOT NULL                            COMMENT '시험합격점수',
    `USE_STATUS`         bit(1)      NOT NULL DEFAULT 1                  COMMENT '사용유무',
    `REG_DATE`           datetime    NOT NULL DEFAULT now()              COMMENT '등록일',
    `REG_IDX`            bigint      NOT NULL                            COMMENT '등록자',
    `UDT_DATE`           datetime    NULL                                COMMENT '수정일',
    `UDT_IDX`            bigint      NULL                                COMMENT '수정자'
);

ALTER TABLE TB_EXAM_SUB_CATEGORY COMMENT='시험문제 서브 카테고리';


# 시험
CREATE TABLE `TB_EXAM` (
	`IDX`                bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '시험IDX',
	`MAIN_CATEGORY_IDX`  bigint       NOT NULL                            COMMENT '메인카테고리IDX',
	`SUB_CATEGORY_IDX`   bigint       NOT NULL                            COMMENT '서브카테고리IDX',
	`EXAM_NAME`          varchar(128) NOT NULL                            COMMENT '시험명',
	`CONFIRM_STATUS`     bit(1)       NOT NULL DEFAULT 0                  COMMENT '확정유무(1=확정,0,확정대기)',
	`USE_STATUS`         bit(1)       NOT NULL DEFAULT 1                  COMMENT '사용유무',
	`REG_DATE`           datetime     NOT NULL DEFAULT now()              COMMENT '등록일',
	`REG_IDX`            bigint       NOT NULL                            COMMENT '등록자',
	`UDT_DATE`           datetime     NULL                                COMMENT '수정일',
	`UDT_IDX`            bigint       NULL                                COMMENT '수정자'
);

ALTER TABLE TB_EXAM COMMENT='시험';


# 시험문항
CREATE TABLE `TB_EXAM_QUESTION` (
	`IDX`             bigint        NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '문항IDX',
	`EXAM_IDX`        bigint        NOT NULL                            COMMENT '시험IDX',
	`SUBJECT_IDX`     bigint        NOT NULL                            COMMENT '과목IDX',
	`MULTIMEDIA_IDX`  bigint        NULL                                COMMENT '멀티미디어SEQ',
	`QUESTION_NUMBER` int           NOT NULL                            COMMENT '문항번호',
	`QUESTION_NAME`   varchar(2048) NOT NULL                            COMMENT '문항명',
	`ANSWER_NUMBER`   int           NOT NULL                            COMMENT '정답번호'
);

ALTER TABLE TB_EXAM_QUESTION COMMENT='시험문항';

# 시험과목
CREATE TABLE `TB_EXAM_SUBJECT` (
    `IDX`                   bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '과목IDX',
    `SUB_CATEGORY_IDX`      bigint       NOT NULL                            COMMENT '서브카테고리IDX',
    `SUBJECT_NAME`          varchar(100) NOT NULL                            COMMENT '과목명',
    `SUBJECT_CUT_OFF_SCORE` float        NOT NULL                            COMMENT '과목합격기준',
    `SUBJECT_QUESTION_CNT`  int          NOT NULL                            COMMENT '과목 별 문항 개수',
    `USE_STATUS`            bit(1)       NOT NULL DEFAULT 1                  COMMENT '사용유무',
    `REG_DATE`              datetime     NOT NULL DEFAULT now()              COMMENT '등록일',
    `REG_IDX`               bigint       NOT NULL                            COMMENT '등록자',
    `UDT_DATE`              datetime     NULL                                COMMENT '수정일',
    `UDT_IDX`               bigint       NULL                                COMMENT '수정자'
);
ALTER TABLE TB_EXAM_SUBJECT COMMENT='시험과목';


# 시험문항선지
CREATE TABLE `TB_EXAM_OPTIONS` (
	`IDX`             bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '선지IDX',
	`QUESTION_IDX`    bigint       NOT NULL                            COMMENT '문항IDX',
	`OPTIONS_NUMBER`  int          NOT NULL                            COMMENT '선지번호',
	`OPTIONS_CONTENT` varchar(500) NOT NULL                            COMMENT '선지내용'
);

ALTER TABLE TB_EXAM_OPTIONS COMMENT='시험문항선지';


# 시험 멀티미디어
CREATE TABLE `TB_EXAM_MULTIMEDIA` (
	`IDX`                bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '멀티미디어IDX',
	`FILE_ID`            varchar(20)  NOT NULL                            COMMENT '파일ID',
	`ORIGINAL_FILE_NAME` varchar(100) NOT NULL                            COMMENT '파일원본이름',
	`FILE_EXTENSION`     varchar(20)  NOT NULL                            COMMENT '파일 확장자',
	`FILE_SIZE`          bigint       NOT NULL                            COMMENT '파일 용량',
	`USE_STATUS`         bit(1)       NOT NULL DEFAULT 1                  COMMENT '사용유무',
	`REG_DATE`           datetime     NOT NULL DEFAULT now()              COMMENT '등록일',
	`REG_IDX`            bigint       NOT NULL                            COMMENT '등록자'
);

ALTER TABLE TB_EXAM_MULTIMEDIA COMMENT='시험 멀티미디어';

# 시험 전체 결과
CREATE TABLE `TB_EXAM_ALL_RESULT` (
    `IDX`               bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '결과IDX',
    `MAIN_CATEGORY_IDX` bigint       NOT NULL                            COMMENT '메인카테고리IDX',
    `SUB_CATEGORY_IDX`  bigint       NOT NULL                            COMMENT '서브카테고리IDX',
    `SUBJECT_CNT`       int          NOT NULL                            COMMENT '과목 개수',
    `AVERAGE_SCORE`     float        NOT NULL                            COMMENT '평균 점수',
    `PASS_STATUS`       bit(1)       NOT NULL                            COMMENT '합격 유무',
    `LOGIN_STATUS`      bit(1)       NOT NULL                            COMMENT '로그인 유무',
    `UN_LOGIN_REG_ID`   varchar(30)  NULL                                COMMENT '비로그인시 등록 아이디',
    `EXAM_RESULT_TOKEN` varchar(100) NOT NULL                            COMMENT '시험 토큰',
    `LOGIN_REG_IDX`     bigint       NULL                                COMMENT '로그인시 등록자',
    `REG_DATE`          datetime     NOT NULL DEFAULT now()              COMMENT '등록일'
);

ALTER TABLE TB_EXAM_ALL_RESULT COMMENT='시험 전체 결과';

# 시험 문항 결과
CREATE TABLE `TB_EXAM_QUESTION_RESULT` (
    `IDX`             bigint NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '문항결과IDX',
    `EXAM_RESULT_IDX` bigint NOT NULL                            COMMENT '결과IDX',
    `QUESTION_IDX`    bigint NOT NULL                            COMMENT '문항IDX',
    `SUBJECT_IDX`     bigint NOT NULL                            COMMENT '과목IDX',
    `QUESTION_NUMBER` int    NOT NULL                            COMMENT '문항번호',
    `INPUT_ANSWER`    int    NOT NULL                            COMMENT '입력한 정답',
    `ANSWER_NUMBER`   int    NOT NULL                            COMMENT '정답번호',
    `ANSWER_STATUS`   bit(1) NOT NULL                            COMMENT '정답 유무'
);

ALTER TABLE TB_EXAM_QUESTION_RESULT COMMENT='시험 문항 결과';

# 시험 문항 결과
CREATE TABLE `TB_EXAM_SUBJECT_RESULT` (
    `IDX`             bigint NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '과목결과IDX',
    `EXAM_RESULT_IDX` bigint NOT NULL                            COMMENT '결과IDX',
    `SUBJECT_IDX`     bigint NOT NULL                            COMMENT '과목IDX',
    `SUBJECT_SCORE`   float  NOT NULL                            COMMENT '과목 평균 점수',
    `PASS_STATUS`     bit(1) NOT NULL                            COMMENT '합격 유무'
);

ALTER TABLE TB_EXAM_SUBJECT_RESULT COMMENT='시험 과목별 결과';