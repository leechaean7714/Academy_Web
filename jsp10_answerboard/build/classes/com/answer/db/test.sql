DROP SEQUENCE BOARDNOSEQ;

DROP SEQUENCE GROUPNOSEQ;

DROP TABLE ANSWERBOARD;

CREATE SEQUENCE BOARDNOSEQ;   --글 번호
CREATE SEQUENCE GROUPNOSEQ;    --그룹 번호

--글번호 그룹번호 그룹순서 제목탭 제목 내용 작성자 작성일

CREATE TABLE ANSWERBOARD(
BOARDNO NUMBER PRIMARY KEY,
GROUPNO NUMBER NOT NULL,
GROUPSEQ NUMBER NOT NULL,
TITLETAB NUMBER NOT NULL,
TITLE VARCHAR2(500) NOT NULL,
CONTENT VARCHAR2(4000) NOT NULL, 
WRITER VARCHAR2(100) NOT NULL,
REGDATE DATE NOT NULL
);




--첫번째 글

INSERT INTO ANSWERBOARD VALUES(
BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL,1,0,'1번글 입니다.','1번글 내용입니다.','관리자',SYSDATE
);

--두번째 글 

INSERT INTO ANSWERBOARD VALUES(
BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL,1,0,'2번글 입니다.','2번글 내용입니다.','학생1',SYSDATE
);

--1번글입니다(글번호 1번)에 답변달기
--UPDATE:부모의 글과 같은 그룹번호, 부모의 그룹순서보다 ㅡ더 큰 그룹순서 
--       그룹순서를+1

UPDATE ANSWERBOARD
SET GROUPSEQ = GROUPSEQ+1
WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO =1)
AND GROUPSEQ   >(SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO =1)



INSERT INTO ANSWERBOARD VALUES(
BOARDNOSEQ.NEXTVAL,--부모의 그룹번호와 같은 그룹번호
(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO =1),
(SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO =1)+1,
(SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO =1)+1,
'RE:1번글 입니다.',
'1번글에 답변 달기',
'학생',
SYSDATE
);

--RE:1번글 입니다(R글번호 3번)에 답변달기
--UPDATE:부모와 같은 그룹번호, 부모의 그룹순서보다 더큰 그룹 순서인
--			글들을 찾아서 그룹순서+1

UPDATE ANSWERBOARD
SET GROUPSEQ = GROUPSEQ+1
WHERE GROUPNO=(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO =3)
AND GROUPSEQ> (SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO =3)


INSERT INTO ANSWERBOARD VALUES(
BOARDNOSEQ.NEXTVAL,
	(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO =3),
	(SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO =3)+1,
	(SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO =3)+1,
	'RE:RE:1번글 입니다.',
	'RE:1번글에 답변 달기',
	'강사',SYSDATE
);

--1번글의 답글을 달아보자

UPDATE ANSWERBOARD 
SET GROUPSEQ = GROUPSEQ+1
WHERE GROUPNO=(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO=4)
AND GROUPSEQ > (SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO =4)


INSERT INTO ANSWERBOARD VALUES(
BOARDNOSEQ.NEXTVAL,
	(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO =1),
	(SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO =1)+1,
	(SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO =1)+1,
	'RE:1번글 입니다.',
	'1번글에 답변 달기',
	'???',SYSDATE
);

--6번은 5번의 댓글

UPDATE ANSWERBOARD
SET GROUPSEQ = GROUPSEQ +1
WHERE GROUPNO=(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO=5)
AND GROUPSEQ > (SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO =5)


INSERT INTO ANSWERBOARD VALUES(
BOARDNOSEQ.NEXTVAL,
	(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO =5),
	(SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO =5)+1,
	(SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO =5)+1,
	'RE:5번글 입니다.',
	'1번글에 답변 달기',
	'???',SYSDATE
);

INSERT INTO ANSWERBOARD VALUES(
BOARDNOSEQ.NEXTVAL,
	(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO =6),
	(SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO =6)+1,
	(SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO =6)+1,
	'RE:5번글 입니다.',
	'1번글에 답변 달기',
	'???',SYSDATE
);

SELECT * FROM ANSWERBOARD ORDER BY GROUPNO DESC, GROUPSEQ;
DELETE FROM ANSWERBOARD WHERE BOARDNO = 3;

UPDATE ANSWERBOARD
SET GROUPSEQ = GROUPSEQ +1
WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO=1)
AND GROUPSEQ >(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO=1)

INSERT INTO ANSWERBOARD VALUES(
BOARDNOSEQ.NEXTVAL,
(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO=1),
(SELECT GEOUPSEQ FROM ANSWERBOARD WHERE BOARDNO=1)+1,
(SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO=1)+1,
'6','6','6',SYSDATE
);