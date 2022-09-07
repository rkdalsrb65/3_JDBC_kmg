/*

- Data
관찰 결과로 나타난 정량적 혹은 정성적인 실제 값

- 정보(Information)
데이터를 기반으로 의미를 부여한 것

- DB(DataBase)
한 조직에 필요한 정보를 여러 응용 시스템에서 공용적으로 사용할 수 있도록 논리적으로
연관된 데이터를 모으고 중복되는 데이터를 최소화하여 구조적으로 통합/저장 해놓은 것

- DBMS(DataBase Management System)
데이터베이스에서 데이터 추출,조작,정의,제어 등을 할 수 있게 해주는 데이터베이스 전용 관리 프로그램

- SQL(Structured Query Language)
관계형 데이터베이스에서 데이터를 조회하거나 조작하기 위해 사용하는 표준 검색 언어
원하는 데이터를 찾는 방법이나 절차를 기술하는 것이 아닌 조건을 기술하여 작성

- SELECT
데이터를 조회한 결과를 Result Set이라고 하는데 SELECT구문에 의해 조회된 행들의 집합을 의미.

- SELECT 작성법
SELECT 컬럼 명 FROM 테이블 명 WHERE 조건식;

- SELECT 해석 순서
 5 : SELECT 컬럼명 AS 별칭, 계산식, 함수식
 1 : FROM 참조할 테이블명
 2 : WHERE 컬럼명 | 함수식 비교연산자 비교값
 3 : GROUP BY 그룹을 묶을 컬럼명
 4 : HAVING 그룹함수식 비교연산자 비교값
 6 : ORDER BY 컬럼명 | 별칭 | 컬럼순번 정렬방식 [NULLS FIRST | LAST];

- JOIN
하나 이상의 테이블에서 데이터를 조회하기 위해 사용하고 수행 결과는 하나의 Result Set으로 나옴

- JOIN 종류별 작성법
내부 조인 : INNER JOIN, JOIN USING / ON
자연 조인 : NATURAL JOIN
교차 조인 : CROSS JOIN

- DML
데이터 조작 언어로 테이블에 값을 삽입, 수정, 삭제하는 구문

- INSERT : 테이블에 새로운 행을 추가하는 구문
작성법 : INSERT INTO 테이블명 VALUES;

- UPDATE : 테이블에 기록된 컬럼의 값을 수정하는 구문으로
작성법 : UPDATE 테이블명 SET 컬럼명 = 바꿀값 WHERE 컬럼명 비교연산자 비교값;

- DELETE : 테이블의 행을 삭제하는 구문
작성법 : DELTE FROM 테이블명 WHERE 조건설정 (조건 미설정시 모든 행 삭제)

- TCL
트랜잭션을 제어하는 언어
COMMIT(트랜잭션 종료 후 저장), ROLLBACK(트랜잭션 취소), SAVEPOINT(임시저장)

- 트랜잭션(Transaction)
데이터베이스의 논리적 연산 단위로 데이터 변경 사항을 묶어 하나의 트랜잭션에 담아 처리함

- COMMIT
메모리 버퍼(트랜잭션)에 임시 저장된 데이터 변경 사항을 DB에 반영

- ROLLBACK
메모리 버퍼(트랜잭션)에 임시 저장된 데이터 변경 사항을 삭제하고
마지막 COMMIT 상태로 돌아감. (DB에 변경 내용 반영 X)

- SAVEPOINT
메모리 버퍼(트랜잭션)에 저장 지점을 정의하여
ROLLBACK 수행 시 전체 작업을 삭제하는 것이 아닌
저장 지점까지만 일부 ROLLBACK

- JDBC
Java에서 DB에 연결(접근)할 수 있게 해주는 Java Programming API

- OJDBC 라이브러리
Oracle에서 Java 애플리케이션과 연결할 때 사용할 JDBC를 상속 받아 구현한 클래스 모음

- JDBC 객체
1) Connection
DB 연결 정보를 담은 객체

2) DriverManager
메모리에 로드된 JDBC 드라이버를 이용해서 Connection 객체를 만드는 역할

3) Statement
Connection을 통해 SQL 문을 DB에 전달하여 실행하고
생성된 결과(ResultSet, 성공한 행의 개수)를 반환하는 데 사용되는 객체

4) PreparedStatement
Statement의 자식으로 향상된 기능을 제공
? 기호 (placeholder, 위치홀더)를 이용해서 SQL에 작성되어지는 리터럴을 동적으로 제어함

5) ResultSet
​SELECT 질의 성공 시 반환되는데 조회 결과 집합을 나타내는 객체

- excuteQuery(), executeUpdate() 차이점
​excuteQuery() : SELECT 수행 후 ResultSet 반환
executeUpdate() : DML 수행 후 결과 행 개수 반환

- Statement / PreparedStatement
 SELECT / DML(INSERT, UPDATE, DELETE)에 따른
 DAO 메서드 코드 작성방법
-SELECT 수행 -> ResultSet 반환
Statement : executeQuery(sql);
PreparedStatement : executeQuery();
- DML (INSERT DELETE UPDATE) -> 반영된 행의 개수(int형)
Statement : executeUpdate(sql);
PreparedStatement : executeUpdate();

- Java의 char자료형 , DB의 CHAR 자료형의 차이점
​Java의 char 자료형 : 문자 1개를 정의하는 문자형 변수
DB의 CHAR 자료형 : 문자열 데이터로 변경하는 형변환 함수

- DAO 메서드에서 반환형이 List, 객체, int로 다른 이유
​List : SELECT 구문을 수행 후 ResultSet를 반환해야할 때 사용한다.
객체 : 메서드로 데이터를 전달하여 값을 반환받아할 때 사용한다.
int : DML 구문을 수행 후 테이블의 반영된 행의 개수(int형)를 반환해야할 때 사용한다.

- Java 객체 생성과 필드 초기화
기본 생성자로 객체 생성 후 setter를 이용해서 필드를 초기화한다.
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 * */
 