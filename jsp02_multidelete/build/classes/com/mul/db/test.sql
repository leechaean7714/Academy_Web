DROP SEQUENCE MDBOARDPRACSEQ;

DROP TABLE MDBOARDPRAC;

CREATE SEQUENCE MDBOARDPRACSEQ;

CREATE TABLE MDBOARDPRAC(
SEQ NUMBER PRIMARY KEY,
WRITER VARCHAR2(1000) NOT NULL,
TITLE VARCHAR2(2000) NOT NULL,
CONTENT VARCHAR2(4000) NOT NULL,
REGDATE DATE NOT NULL
);

INSERT INTO MDBOARDPRAC VALUES(MDBOARDPRACSEQ.NEXTVAL,'관리자','TEST 글','TEST 내용',SYSDATE);

SELECT * FROM MDBOARDPRAC;