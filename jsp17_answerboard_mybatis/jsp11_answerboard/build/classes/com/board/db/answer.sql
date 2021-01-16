
DROP SEQUENCE BOARDNOSEQ_D;
DROP SEQUENCE GROUPNOSEQ_D;
DROP TABLE ANSWERBOARD_D;

CREATE SEQUENCE BOARDNOSEQ_D;			-- 글 번호
CREATE SEQUENCE GROUPNOSEQ_D;			-- 그룹 번호

--글번호 그룹번호 그룹순서 제목탭 제목 내용 작성자 작성일
CREATE TABLE ANSWERBOARD_D(
	BOARDNO NUMBER PRIMARY KEY,
	GROUPNO NUMBER NOT NULL,
	GROUPSEQ NUMBER NOT NULL,
	TITLETAB NUMBER NOT NULL,
	TITLE VARCHAR2(500) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WRITER VARCHAR2(100) NOT NULL,
	REGDATE DATE NOT NULL,
	HITNO NUMBER NOT NULL,
	FLAGDEL VARCHAR2(100) NOT NULL,
	CONSTRAINT AB_FD_CHK CHECK (FLAGDEL IN('Y', 'N'))
);

SELECT * FROM ANSWERBOARD_D ORDER BY GROUPNO DESC, GROUPSEQ;

-- 첫번째 글 작성
INSERT INTO ANSWERBOARD_D
VALUES(BOARDNOSEQ_D.NEXTVAL, GROUPNOSEQ_D.NEXTVAL,1,0,
'1번글 입니다.','1번글 내용입니다.','관리자',SYSDATE,0, 'N');

--두번째 글 작성
INSERT INTO ANSWERBOARD_D
VALUES(BOARDNOSEQ_D.NEXTVAL, GROUPNOSEQ_D.NEXTVAL, 1, 0,
'2번글 입니다.', '2번글 내용입니다.', '학생1', SYSDATE,0, 'N');

--<1>
--1번글입니다(글번호 1번) 에 답변 달기
--UPDATE : 부모의 글과 같은 그룹번호, 부모의 그룹순서보다 더 큰 그룹순서
--         그룹순서를 + 1
UPDATE ANSWERBOARD_D
SET GROUPSEQ = GROUPSEQ + 1
WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD_D WHERE BOARDNO = 1)
AND GROUPSEQ > (SELECT GROUPSEQ FROM ANSWERBOARD_D WHERE BOARDNO = 1);


--INSERT boardno3.
INSERT INTO ANSWERBOARD_D
VALUES(
	BOARDNOSEQ_D.NEXTVAL,
	(SELECT GROUPNO FROM ANSWERBOARD_D WHERE BOARDNO = 1),
	(SELECT GROUPSEQ FROM ANSWERBOARD_D WHERE BOARDNO = 1) + 1,
	(SELECT TITLETAB FROM ANSWERBOARD_D WHERE BOARDNO = 1) + 1,
	'RE:1번글 입니다',
	'1번글에 답변달기',
	'학생',
	SYSDATE,0, 'N'
);

--<2>
-- RE:1번글입니다(글번호 : 3)에 답변달기
-- UPDATE : 부모와 같은 그룹번호, 부모의 그룹순서보다 더 큰 그룹순서인 
--          글들을 찾아서 그룹순서 + 1
UPDATE ANSWERBOARD_D
SET GROUPSEQ = GROUPSEQ+1
WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD_D WHERE BOARDNO = 3)
AND GROUPSEQ > (SELECT GROUPSEQ FROM ANSWERBOARD_D WHERE BOARDNO = 3);

--INSERT boardno.4
INSERT INTO ANSWERBOARD_D
VALUES(
	BOARDNOSEQ_D.NEXTVAL,
	(SELECT GROUPNO FROM ANSWERBOARD_D WHERE BOARDNO = 3),
	(SELECT GROUPSEQ FROM ANSWERBOARD_D WHERE BOARDNO = 3) + 1,
	(SELECT TITLETAB FROM ANSWERBOARD_D WHERE BOARDNO = 3) + 1,
	'RE:RE:1번글 입니다',
	'RE:1번글에 답변 달아보자',
	'강사',
	SYSDATE,0, 'N'
);

--<3>
-- 1번글입니다(글번호 1번) 에 답변달기
-- UPDATE
UPDATE ANSWERBOARD_D
SET GROUPSEQ = GROUPSEQ+1
WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD_D WHERE BOARDNO = 1)
AND GROUPSEQ > (SELECT GROUPSEQ FROM ANSWERBOARD_D WHERE BOARDNO = 1);

--INSERT
INSERT INTO ANSWERBOARD_D
VALUES(
	BOARDNOSEQ.NEXTVAL,
	(SELECT GROUPNO FROM ANSWERBOARD_D WHERE BOARDNO = 1),
	(SELECT GROUPSEQ FROM ANSWERBOARD_D WHERE BOARDNO = 1) + 1,
	(SELECT TITLETAB FROM ANSWERBOARD_D WHERE BOARDNO = 1) + 1,
	'RE:1번글 입니다',
	'1번글에 또다시 답변 달아보자',
	'조커',
	SYSDATE,0, 'N'
);


UPDATE ANSWERBOARD_D
SET HITNO = HITNO + 1
WHERE BOARDNO =1;

















