DROP SEQUENCE MVCBOARDSEQ;

DROP TABLE MVCBOARD;
CREATE SEQUENCE MVCBOARDSEQ;

CREATE TABLE MVCBOARD(
SEQ NUMBER PRIMARY KEY,
WRITER VARCHAR2(1000) NOT NULL,
TITLE VARCHAR2(2000) NOT NULL,
CONTENT VARCHAR2(4000) NOT NULL,
REGDATE DATE NOT NULL
);

INSERT INTO MVCBOARD
VALUES(MVCBOARDSEQ.NEXTVAL,'관리자','TEST 제목','TEST내용124',SYSDATE);

SELECT SEQ, WRITER,TITLE,CONTENT,REGDATE
FROM MVCBOARD;