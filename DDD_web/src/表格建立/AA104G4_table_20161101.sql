/*
 *  [好心提醒] 
 *  (1) 由於資料比較多 可以透過搜尋像是 ord (訂單) 到指定table去補充資料
 *  (2) level 2 3 4的外來鍵假資料 可以參考level數較前面 (level 1 2 3) 的
 *
 *
 * table建置順序 :
 * 
 *
 *  [Level 1] (NO Foreign Key) 
 * 員工 權限 一般會員 廠商會員 Banner方案 聊天室 附加服務 共住條件
 * 
 * [Level 2] (Foreign Key在 lv1)
 * 員工權限 聊天室內容 上架月租紀錄 附加服務細項 Banner 一般會員的共住條件
 * 房型
 *
 * [Level 3] (Foreign Key在 lv1~2)
 * 訂單 房型照片 願望項目
 *
 * [Level 4] (Foreign Key在 lv1~3)
 * 一般會員檢舉單 廠商會員檢舉單
 *
 */




/*
 *  [好心提醒] 
 *  (1) 由於資料比較多 可以透過搜尋像是 ord (訂單) 到指定table去補充資料
 *  (2) level 2 3 4的外來鍵假資料 可以參考level數較前面 (level 1 2 3) 的
 *
 *
 * table建置順序 :
 * 
 *
 *  [Level 1] (NO Foreign Key) 
 * 員工 權限 一般會員 廠商會員 Banner方案 聊天室 附加服務 共住條件
 * 
 * [Level 2] (Foreign Key在 lv1)
 * 員工權限 聊天室內容 上架月租紀錄 附加服務細項 Banner 一般會員的共住條件
 * 房型
 *
 * [Level 3] (Foreign Key在 lv1~2)
 * 訂單 房型照片 願望項目
 *
 * [Level 4] (Foreign Key在 lv1~3)
 * 一般會員檢舉單 廠商會員檢舉單
 *
 */








/* 整個table殺光光指令 */
DROP TABLE hotelRep;
DROP SEQUENCE hotelRep_seq;
DROP TABLE memRep;
DROP SEQUENCE memRep_seq;
DROP TABLE wish;
DROP TABLE roomphoto;
DROP SEQUENCE roomphoto_seq;
DROP TABLE ORD;
DROP SEQUENCE ord_seq;
DROP TABLE ROOM;
DROP SEQUENCE ROOM_seq;
DROP TABLE memLiveCond;
drop table Ad;
drop SEQUENCE Ad_seq;
DROP TABLE hotelServ;
DROP TABLE rent;
DROP SEQUENCE rent_seq;
DROP TABLE memchat;
DROP TABLE empAuth;
DROP TABLE LiveCond;
DROP SEQUENCE livecond_seq;
DROP TABLE serv;
DROP SEQUENCE serv_seq;
DROP TABLE chat;
DROP SEQUENCE chat_seq;
DROP TABLE adPlan;
DROP SEQUENCE adPlan_seq;
DROP TABLE hotel;
DROP SEQUENCE hotel_seq;
DROP TABLE mem;
DROP SEQUENCE mem_seq;
DROP TABLE AUTH;
DROP SEQUENCE auth_seq;
DROP TABLE EMP;
DROP SEQUENCE emp_seq;








/* [Level 1] 員工 權限 一般會員 廠商會員 Banner方案 聊天室 附加服務 共住條件 */




/* 員工 */
CREATE TABLE EMP (
 empId          VARCHAR2(5) NOT NULL,
 empName        VARCHAR2(20),
 empAccount     VARCHAR2(60),
 empPwd         VARCHAR2(20),
 empPhone       VARCHAR2(10),
 empHireDate	DATE,
 empFireDate    DATE,
 empStatus      VARCHAR2(1),
 empBirthDate   DATE,
 empProfile	    BLOB,
 empROCId	    VARCHAR2(10),
 empAddress 	VARCHAR2(40),
 CONSTRAINT EMP_EMPID_PK PRIMARY KEY (empId)
);
 
CREATE SEQUENCE emp_seq
INCREMENT BY 1
START WITH 10001
NOMAXVALUE
NOCYCLE
NOCACHE;




INSERT INTO EMP VALUES (emp_seq.NEXTVAL,'Alen','a12345@hotmail.com','123','0953589679',SYSDATE,'','0',TO_DATE('1993-02-27','YYYY-MM-DD'),'','A177712238','桃園市楊梅區民族路16號');
INSERT INTO EMP VALUES (emp_seq.NEXTVAL,'Jack','b12345@hotmail.com','123','0923558778',SYSDATE,'','0',TO_DATE('1991-02-27','YYYY-MM-DD'),'','A194531193','台北市信義區信義路10號');
INSERT INTO EMP VALUES (emp_seq.NEXTVAL,'Peter','c12345@hotmail.com','123','0912356895',SYSDATE,'','0',TO_DATE('1992-02-27','YYYY-MM-DD'),'','A187836423','桃園市中壢區中大路16號');
INSERT INTO EMP VALUES (emp_seq.NEXTVAL,'Sara','d12345@hotmail.com','123','0988087087',SYSDATE,'','0',TO_DATE('1995-02-27','YYYY-MM-DD'),'','A162371494','新竹市金山街100號');
INSERT INTO EMP VALUES (emp_seq.NEXTVAL,'Amy','e12345@hotmail.com','123','0972556556',SYSDATE,'','0',TO_DATE('1990-02-27','YYYY-MM-DD'),'','A164040858','桃園市楊梅區新農街116號');




commit;




/* 權限 */
CREATE TABLE AUTH (
 authId      VARCHAR2(3) NOT NULL,
 authName	 VARCHAR2(25),
 CONSTRAINT AUTH_authId_PK PRIMARY KEY (authId)
);
 
CREATE SEQUENCE auth_seq
INCREMENT BY 1
START WITH 101
NOMAXVALUE
NOCYCLE
NOCACHE;




INSERT INTO AUTH VALUES(auth_seq.NEXTVAL,'人事管理');
INSERT INTO AUTH VALUES(auth_seq.NEXTVAL,'行政業務管理');
INSERT INTO AUTH VALUES(auth_seq.NEXTVAL,'首頁管理');
INSERT INTO AUTH VALUES(auth_seq.NEXTVAL,'客服管理');




commit;




/* 一般會員 */
create table mem(
        memId varchar2(8) not null,
        memAccount varchar2(60),
        memPsw varchar2(60),
        memName varchar2(60),
        memGender varchar2(1),
        memTwId varchar2(10),
        memBirthDate Date,
        memPhone varchar2(60),
        memLiveBudget number(7),
        memIntro clob,
        memProfile blob,
        memBlackList varchar2(60),
        memCreditCardNo varchar2(16),
        memCreditCheckNo varchar2(3),
        memCreditDueDate varchar2(15),
 		CONSTRAINT mem_memId_PK PRIMARY KEY (memId)
 );




create sequence mem_seq
                increment by 1
                start with 10000001
                NOMAXVALUE
                nocache
                nocycle;




insert into mem(memId, memAccount, memPsw, memName, memGender, memTwId, memBirthDate, memPhone, memlivebudget, memintro, memprofile, memblacklist, memcreditcardno, memcreditcheckno, memcreditduedate) 
      values(mem_seq.NEXTVAL, 'MarvinPMcNerney@armyspy.com', 'oobi6aSee', '譚芝穎', 'f', 'G207842621', to_Date('1994-04-25', 'yyyy-mm-dd'), '0977885526', 1500, '我崇尚自然，希望靠這次旅行，能認識更多的人', null, '0', '5590143876967822', '641', '2021-10');
insert into mem(memId, memAccount, memPsw, memName, memGender, memTwId, memBirthDate, memPhone, memlivebudget, memintro, memprofile, memblacklist, memcreditcardno, memcreditcheckno, memcreditduedate) 
      values(mem_seq.NEXTVAL, 'PengYaPing@rhyta.com', 'aeReetoa4Th', '彭雅萍', 'f', 'E204746648', to_Date('1991-12-10', 'yyyy-mm-dd'), '0954122356', 1700, '天馬行空的幻想', null, '0', '4556548042942332', '836', '2018-12');
insert into mem(memId, memAccount, memPsw, memName, memGender, memTwId, memBirthDate, memPhone, memlivebudget, memintro, memprofile, memblacklist, memcreditcardno, memcreditcheckno, memcreditduedate) 
      values(mem_seq.NEXTVAL, 'LinWeiTing@jourrapide.com', 'wah8AviC', '林威廷', 'm', 'C106824310', to_Date('1973-04-22', 'yyyy-mm-dd'), '0952221456', 2000, '哈哈哈哈哈', null, '0', '4485055095528840', '724', '2021-05');
insert into mem(memId, memAccount, memPsw, memName, memGender, memTwId, memBirthDate, memPhone, memlivebudget, memintro, memprofile, memblacklist, memcreditcardno, memcreditcheckno, memcreditduedate) 
      values(mem_seq.NEXTVAL, 'HuChuLing@jourrapide.com', 'ieNei8Beet', '胡淑玲', 'f', 'F243467258', to_Date('1983-06-06', 'yyyy-mm-dd'), '0912556674', 1200, '1s,1h', null, '0', '5544368296299202', '696', '2020-11');
insert into mem(memId, memAccount, memPsw, memName, memGender, memTwId, memBirthDate, memPhone, memlivebudget, memintro, memprofile, memblacklist, memcreditcardno, memcreditcheckno, memcreditduedate) 
      values(mem_seq.NEXTVAL, 'BoGuanLin@rhyta.com', 'cuP0ahL2y', '白冠霖', 'm', 'A167463308', to_Date('1993-12-23', 'yyyy-mm-dd'), '0955123225', 1800, '全國最大的裸聊', null, '0', '4485789622527244', '795', '2018-11');
insert into mem(memId, memAccount, memPsw, memName, memGender, memTwId, memBirthDate, memPhone, memlivebudget, memintro, memprofile, memblacklist, memcreditcardno, memcreditcheckno, memcreditduedate) 
      values(mem_seq.NEXTVAL, 'sandrahaneyxyK@teleosaurs.xyz', 'rruXjwCR5YvKrNS', '黃仙珊', 'f', 'L167425787', to_Date('1996-10-02', 'yyyy-mm-dd'), '0921254297', 2100, '喜歡旅行、認識新朋友，夢想是有一天能走片世界各地', null, '0', '3337908044924274', '820', '2019-11');


INSERT INTO mem VALUES (mem_seq.NEXTVAL,'a12345@hotmail.com','123','Alen','m','H121335148',TO_DATE('1991-02-27','YYYY-MM-DD'),'0953545123','0','大家好','','0','4752145612541121','587','2018-05');
INSERT INTO mem VALUES (mem_seq.NEXTVAL,'b12345@hotmail.com','123','Jax','m','H121335148',TO_DATE('1993-03-27','YYYY-MM-DD'),'0953554523','0','大家好','','0','4752145612541121','587','2018-05');
INSERT INTO mem VALUES (mem_seq.NEXTVAL,'c12345@hotmail.com','123','Ben','m','H121335148',TO_DATE('1997-04-27','YYYY-MM-DD'),'0925745123','0','大家好','','0','4752145612541121','587','2018-05');
INSERT INTO mem VALUES (mem_seq.NEXTVAL,'d12345@hotmail.com','123','Scott','m','H121335148',TO_DATE('1993-05-27','YYYY-MM-DD'),'0912545123','0','大家好','','0','4752145612541121','587','2018-05');
INSERT INTO mem VALUES (mem_seq.NEXTVAL,'e12345@hotmail.com','123','iris','f','H221335148',TO_DATE('1998-06-27','YYYY-MM-DD'),'0953542143','0','大家好','','0','4752145612541121','587','2018-05');




commit;	




/* 廠商會員 */
CREATE TABLE hotel (
 hotelId            VARCHAR2(5) NOT NULL,
 hotelType	 	    VARCHAR2(20),
 hotelName	 	    VARCHAR2(60),
 hotelTaxId	 	    VARCHAR2(8),
 hotelRegisterPic   BLOB,
 hotelCity	 	    VARCHAR2(12),
 hotelCounty	    VARCHAR2(12),
 hotelRoad	 	    VARCHAR2(50),
 hotelOwner		    VARCHAR2(15),
 hotelAccount	    VARCHAR2(60),
 hotelPwd	 	    VARCHAR2(10),
 hotelPhone	 	    VARCHAR2(10),
 hotelLon	 	    NUMBER(9,6),
 hotelLat	 	    NUMBER(9,6),
 hotelIntro	 	    VARCHAR2(600),
 hotelCoverPic	    BLOB,
 hotelLink	 	    VARCHAR2(60),
 hotelStatus	    VARCHAR2(1),
 hotelBlackList	    VARCHAR2(1),
 hotelRatingTotal   NUMBER(10),
 hotelRatingResult  NUMBER(10),
 hotelCreditCardNo  VARCHAR2(16),
 hotelCreditCheckNo VARCHAR2(3),
 hotelCreditDueDate VARCHAR2(15),
 CONSTRAINT hotel_hotelId_pk PRIMARY KEY (hotelId)
);
 
CREATE SEQUENCE hotel_seq
INCREMENT BY 1
START WITH 10001
NOMAXVALUE
NOCYCLE
NOCACHE;




INSERT INTO hotel VALUES(hotel_seq.NEXTVAL,'民宿','小新茗宿旅館','56745644','','台北市','信義區','信義路385號','江添財','house1@gmail.com','123456789','0925990128','121.576957','25.028702','景色優美','','https://www.agoda.com/zh-tw/city/taipei-tw.html','0','0','0','0','4705381232568781','138','2020-03');
INSERT INTO hotel VALUES(hotel_seq.NEXTVAL,'汽車旅館','OA汽車旅館','51231655','','桃園市','中壢區','中北路340號','楊仙生','house2@gmail.com','123456789','0925990128','121.576957','25.028702','景色優美','','https://www.agoda.com/zh-tw/city/taipei-tw.html','0','0','0','0','4705381232568781','138','2023-05');








commit;








/* Banner方案 */
CREATE TABLE adPlan(
 adPlanId             VARCHAR2(8) NOT NULL,
 adPlanName           VARCHAR2(90),
 adPlanStartDate      DATE,
 adPlanEndDate        DATE,
 adPlanPrice          Number(6),
 adPlanRemainNo       Number(2),
 CONSTRAINT adPlan_adPlanId_PK PRIMARY KEY (adPlanId)
 );




CREATE SEQUENCE adPlan_seq
INCREMENT BY 1
START WITH 10000001
NOMAXVALUE;




INSERT INTO adPlan (adPlanId,adPlanName,adPlanStartDate,adPlanEndDate,adPlanPrice,adPlanRemainNo) VALUES (adPlan_seq.NEXTVAL,'11月方案',to_date('11 1,2016','MM DD,YYYY'), to_date('11 30,2016','MM DD,YYYY'),5000, 2);




commit;




/* 聊天室 */
CREATE TABLE chat(
 chatId   VARCHAR2(8) NOT NULL,
 chatName VARCHAR2(30),
 CONSTRAINT chat_chatId_pk PRIMARY KEY (chatId)
);




CREATE SEQUENCE chat_seq
INCREMENT BY 1
START WITH 10000001
NOMAXVALUE
NOCYCLE
NOCACHE;




INSERT INTO chat
VALUES(chat_seq.NEXTVAL, null);




commit;




/* 附加服務 */
create table  serv(
              servId VARCHAR2(3) not null,
              servName  VARCHAR2(60),
              constraint serv_primary_key primary key (servId)
              );




create sequence serv_seq
                increment by 1
                start with 101
                NOMAXVALUE
                nocache
                nocycle;








insert into serv(servId, servname) values(serv_seq.NEXTVAL, '早餐');
insert into serv(servId, servname) values(serv_seq.NEXTVAL, '禁菸飯店');
insert into serv(servId, servname) values(serv_seq.NEXTVAL, '停車場(車位有限)');
insert into serv(servId, servname) values(serv_seq.NEXTVAL, '室外游泳池');
insert into serv(servId, servname) values(serv_seq.NEXTVAL, '免費Wi-Fi');
insert into serv(servId, servname) values(serv_seq.NEXTVAL, '電梯');
insert into serv(servId, servname) values(serv_seq.NEXTVAL, '免費ADSL');
insert into serv(servId, servname) values(serv_seq.NEXTVAL, '洗衣設施');
insert into serv(servId, servname) values(serv_seq.NEXTVAL, '健身設施');
insert into serv(servId, servname) values(serv_seq.NEXTVAL, '旅遊資訊');
insert into serv(servId, servname) values(serv_seq.NEXTVAL, '行李寄放');
insert into serv(servId, servname) values(serv_seq.NEXTVAL, '公共區域提供咖啡/茶');
insert into serv(servId, servname) values(serv_seq.NEXTVAL, '大廳提供免費報紙');
insert into serv(servId, servname) values(serv_seq.NEXTVAL, '餐廳');
insert into serv(servId, servname) values(serv_seq.NEXTVAL, '櫃台保險箱');
commit;		








/* 共住條件 */
CREATE TABLE liveCond(
  liveCondId VARCHAR2(3) NOT NULL,
  liveCondName VARCHAR2(90),
  CONSTRAINT livecond_livecondid_pk PRIMARY KEY (livecondid)
);




CREATE SEQUENCE livecond_seq
INCREMENT BY 1
START WITH 101
NOMAXVALUE
NOCYCLE
NOCACHE;




INSERT INTO livecond (liveCondId, liveCondName)
VALUES(livecond_seq.NEXTVAL, '被男的找');
INSERT INTO livecond (liveCondId, liveCondName)
VALUES(livecond_seq.NEXTVAL, '被女的找');




commit;	




/* [Level 2] 員工權限 聊天室內容 上架月租紀錄 附加服務細項 Banner 一般會員的共住條件 房型 */




/* 員工權限 */
CREATE TABLE empAuth (
 empAuthEmpId            VARCHAR2(5) NOT NULL,
 empAuthAuthId	 	   VARCHAR2(3) NOT NULL,
 
 CONSTRAINT empAuth_empAuthEmpId_FK FOREIGN KEY (empAuthEmpId) REFERENCES EMP (empId),
 CONSTRAINT empAuth_empAuthAuthId_FK FOREIGN KEY (empAuthAuthId) REFERENCES AUTH (authId),
 CONSTRAINT empAuth_empAuthAuthId_PK PRIMARY KEY (empAuthEmpId,empAuthAuthId));
 
 
 INSERT INTO empAuth VALUES(10001,101);
 INSERT INTO empAuth VALUES(10001,102);
 INSERT INTO empAuth VALUES(10001,103);
 INSERT INTO empAuth VALUES(10001,104);
 INSERT INTO empAuth VALUES(10002,104);
 INSERT INTO empAuth VALUES(10003,102);
 INSERT INTO empAuth VALUES(10003,103);
 INSERT INTO empAuth VALUES(10004,101);
 INSERT INTO empAuth VALUES(10004,102);
 
 
 commit;








/* 聊天室內容 */
CREATE TABLE memChat(
  memChatChatId VARCHAR2(8) NOT NULL,
  memChatMemId VARCHAR2(8) NOT NULL,
  memChatDate TIMESTAMP,
  memChatContent VARCHAR2(300),
  memChatPic BLOB,
  
  CONSTRAINT memChat_memChatMemId_fk FOREIGN KEY (memChatMemId) REFERENCES mem(memId),
  CONSTRAINT memChat_memChatChatId_fk FOREIGN KEY (memChatChatId) REFERENCES chat(chatId),
  CONSTRAINT memChat_memChatMemChatDate_pk PRIMARY KEY (memChatMemId, memChatChatId, memChatDate)
);




INSERT INTO memchat
VALUES((select chatId 
        from (select rownum rnum, chatId
              from chat
              where rownum <= 1)
        where rnum >= 1),
       (select memid 
        from (select rownum rnum, memid
              from mem
              where rownum <= 1)
        where rnum >= 1),
        TO_TIMESTAMP ('2016-10-01 14:00:00.123', 'YYYY-MM-DD HH24:MI:SS.FF'), 
        '你好，我也在找房間，要一起嗎',
        null
       );




--第二個會員說話
INSERT INTO memchat
VALUES((select chatId 
        from (select rownum rnum, chatId
              from chat
              where rownum <= 1)
        where rnum >= 1),
       (select memid 
        from (select rownum rnum, memid
              from mem
              where rownum <= 2)
        where rnum >= 2),
        TO_TIMESTAMP ('2016-10-01 14:01:00.456', 'YYYY-MM-DD HH24:MI:SS.FF'),
        '好啊，你也是自己出來自助旅行嗎?',
        null
       );




--第一個會員說話
INSERT INTO memchat
VALUES((select chatId 
        from (select rownum rnum, chatId
              from chat
              where rownum <= 1)
        where rnum >= 1),
        (select memid 
        from (select rownum rnum, memid
              from mem
              where rownum <= 1)
        where rnum >= 1),
         TO_TIMESTAMP ('2016-10-01 14:02:00.456', 'YYYY-MM-DD HH24:MI:SS.FF'),
        '哈我不是，我是被公司臨時叫到這出差',
        null
       );
       
--第二個會員說話
INSERT INTO memchat
VALUES((select chatId 
        from (select rownum rnum, chatId
              from chat
              where rownum <= 1)
        where rnum >= 1),
        (select memid 
        from (select rownum rnum, memid
              from mem
              where rownum <= 2)
        where rnum >= 2),
         TO_TIMESTAMP ('2016-10-01 14:03:00.123', 'YYYY-MM-DD HH24:MI:SS.FF'),
        '好辛苦qq',
        null
       );       




--第一個會員說話
INSERT INTO memchat
VALUES((select chatId 
        from (select rownum rnum, chatId
              from chat
              where rownum <= 1)
        where rnum >= 1),
        (select memid 
        from (select rownum rnum, memid
              from mem
              where rownum <= 1)
        where rnum >= 1),
         TO_TIMESTAMP ('2016-10-01 14:04:00.456', 'YYYY-MM-DD HH24:MI:SS.FF'),
        '還好啦。不過還是滿羨慕你的，可以到處走來走去',
        null
       );       




--第二個會員說話
INSERT INTO memchat
VALUES((select chatId 
        from (select rownum rnum, chatId
              from chat
              where rownum <= 1)
        where rnum >= 1),
        (select memid 
        from (select rownum rnum, memid
              from mem
              where rownum <= 2)
        where rnum >= 2),
         TO_TIMESTAMP ('2016-10-01 14:05:00.123', 'YYYY-MM-DD HH24:MI:SS.FF'),
        '哈我也沒那麼幸運，這個假日可是我的特休',
        null
       );




--第一個會員說話
INSERT INTO memchat
VALUES((select chatId 
        from (select rownum rnum, chatId
              from chat
              where rownum <= 1)
        where rnum >= 1),
        (select memid 
        from (select rownum rnum, memid
              from mem
              where rownum <= 1)
        where rnum >= 1),
         TO_TIMESTAMP ('2016-10-01 14:06:00.789', 'YYYY-MM-DD HH24:MI:SS.FF'),
        '原來也是同道中人XD。我看到這間還不錯，雙人房899$，你看看。
         http://tw.yahoo.com/',
        null
       ); 




--第二個會員說話
INSERT INTO memchat
VALUES((select chatId 
        from (select rownum rnum, chatId
              from chat
              where rownum <= 1)
        where rnum >= 1),
        (select memid 
        from (select rownum rnum, memid
              from mem
              where rownum <= 2)
        where rnum >= 2),
         TO_TIMESTAMP ('2016-10-01 14:07:00.456', 'YYYY-MM-DD HH24:MI:SS.FF'),
        '真的還不錯，我先加到我的願望清單。那你現在在哪裡?',
        null
       );       




--第一個會員說話
INSERT INTO memchat
VALUES((select chatId 
        from (select rownum rnum, chatId
              from chat
              where rownum <= 1)
        where rnum >= 1),
        (select memid 
        from (select rownum rnum, memid
              from mem
              where rownum <= 1)
        where rnum >= 1),
         TO_TIMESTAMP ('2016-10-01 14:08:00.123', 'YYYY-MM-DD HH24:MI:SS.FF'),
        '這個APP可以直接幫忙定位，你直接點"你在哪"按鈕就能看到了。',
        null
       ); 




--第二個會員說話
INSERT INTO memchat
VALUES((select chatId 
        from (select rownum rnum, chatId
              from chat
              where rownum <= 1)
        where rnum >= 1),
        (select memid 
        from (select rownum rnum, memid
              from mem
              where rownum <= 2)
        where rnum >= 2),
         TO_TIMESTAMP ('2016-10-01 14:09:00.456', 'YYYY-MM-DD HH24:MI:SS.FF'),
        '找到了，好像都離旅館不遠! 那我們等等直接到那間旅館會面吧。我叫小愛，穿紅色披肩和白色清衫',
        null
       );




--第一個會員說話
INSERT INTO memchat
VALUES((select chatId 
        from (select rownum rnum, chatId
              from chat
              where rownum <= 1)
        where rnum >= 1),
        (select memid 
        from (select rownum rnum, memid
              from mem
              where rownum <= 1)
        where rnum >= 1),
         TO_TIMESTAMP ('2016-10-01 14:10:00.123', 'YYYY-MM-DD HH24:MI:SS.FF'),
        '恩恩好。我叫大城，正統西裝男，那待回見了小愛。',
        null
       );




commit;




/* 上架月租紀錄 */
create table rent(
 rentId VARCHAR2(10 char) constraint rent_rentId_pk primary key,
 rentHotelId VARCHAR2(5 char) not null constraint rent_rentHotelId_fk references hotel(hotelid),
 rentPayDate Date not null,
 rentStartDate Date not null,
 rentEndDate Date not null,
 rentPrice Number not null
);




CREATE SEQUENCE rent_seq
INCREMENT BY 1
START WITH 1000000001
NOMAXVALUE
NOCYCLE
NOCACHE;




commit;




/* 附加服務細項 */
create table  hotelServ(
  hotelServServId varchar(3) not null,
  hotelServHotelId varchar(5) not null,
  constraint hotelServ_hotelServId_fk foreign key (hotelServServId) references serv(servId),
  constraint hotelServ_hotelServHotelId_fk foreign key (hotelServHotelId) references hotel(hotelId),
  constraint hotelServ_primaryKey_pk primary key (hotelServServId,hotelServHotelId)
);




commit;




/* Banner */
CREATE TABLE Ad (
 adId               VARCHAR2(8) NOT NULL,
 adAdPlanId         VARCHAR2(8) NOT NULL,
 adHotelId          VARCHAR2(5) NOT NULL,
 adStatus           VARCHAR2(1) ,
 adPayDate          DATE,
 adPic              BLOB,
 adPicContent      VARCHAR2(150),
 adHit              NUMBER(4) ,
 
 CONSTRAINT Ad_adId_PK PRIMARY KEY (adId));




CREATE SEQUENCE Ad_seq
INCREMENT BY 1
START WITH 10000001
NOMAXVALUE;




INSERT INTO Ad (adId,adAdPlanId,adHotelId,adStatus,adPayDate,adPicContent,adHit)VALUES (Ad_seq.NEXTVAL,adPlan_seq.NEXTVAL,10001,0,sysdate,'特惠方案',50);




commit;




/* 一般會員的共住條件 */
CREATE TABLE memLiveCond(
  memLiveCondLiveCondId VARCHAR2(3),
  memLiveCondMemId VARCHAR2(8),
  
  CONSTRAINT memLiveCond_memLCLCId_fk FOREIGN KEY (memLiveCondLiveCondId) REFERENCES LiveCond(LiveCondId),
  CONSTRAINT memLiveCond_memLCLCMemId_fk FOREIGN KEY (memLiveCondMemId) REFERENCES Mem(MemId)
);




-- 會員1000000001只想被女的找
INSERT INTO memLiveCond (memLiveCondLiveCondId, memLiveCondMemId)
VALUES (102, 10000001);
-- 會員1000000002只想被男的找
INSERT INTO memLiveCond (memLiveCondLiveCondId, memLiveCondMemId)
VALUES (101, 10000002);
-- 會員1000000003都可以，所以沒有設定
 
commit;








/* 房型 */
create table room(
 roomId VARCHAR2(7 char) constraint room_roomId_pk primary key,
 roomHotelId VARCHAR2(5 char) not null constraint room_roomHotelId_fk references hotel(hotelid),
 roomName VARCHAR2(30 char),
 roomTotalNo NUMBER(3),
 roomPrice NUMBER(7),
 roomForSell VARCHAR2(1) constraint room_roomForSell_ck check(roomForSell ='0' or roomForSell='1'),
 roomForSellAuto VARCHAR2(1) constraint room_roomForSellAuto_ck check(roomForSellAuto ='0' or roomForSellAuto='1'),
 roomRemainNo NUMBER(3), constraint room_roomRemainNo_ck check(roomRemainNo < roomTotalNo),
 roomDiscountStartDate NUMBER,
 roomDiscountEndDate NUMBER,
 roomDisccountPercent NUMBER(3,2),
 roomDiscountHr NUMBER(1),
 roomOnePrice VARCHAR2(1) constraint room_roomOnePrice_ck check(roomOnePrice ='0' or roomOnePrice='1'),
 roomFun VARCHAR2(50 char),
 roomMeal VARCHAR2(50 char),
 roomSleep VARCHAR2(50 char),
 roomFacility VARCHAR2(50 char),
 roomSweetFacility VARCHAR2(50 char),
 roomCapacity NUMBER(2),
 roomOneBed NUMBER(2),
 roomTwoBed NUMBER(2)
);








CREATE SEQUENCE room_seq
INCREMENT BY 1
START WITH 1000001
NOMAXVALUE
NOCYCLE
NOCACHE;








insert into room(
roomId,roomHotelId,roomName,roomTotalNo,roomPrice,roomForSell,
roomForSellAuto,roomRemainNo,roomDiscountStartDate,roomDiscountEndDate,
roomDisccountPercent,roomDiscountHr,roomOnePrice,roomFun,roomMeal,
roomSleep,roomFacility,roomSweetFacility,roomCapacity,roomOneBed,roomTwoBed
)values(
room_seq.NEXTVAL,'10001','標準雙床間',15,3000,'0','0',12,64800000,79200000,0.48,1,'0',
'免費無線上網、48 吋LCD 液晶電視和付費電視頻道',
'冰箱、義大利濃縮咖啡機、電熱水壺和免費瓶裝水',
'遮光窗簾',
'保險箱、書桌和熨斗及熨衣板 (可要求)，可要求提供 免費搖籃/嬰兒床',
'空調和每日客房清潔服務',4,2,1
);




insert into room(
roomId,roomHotelId,roomName,roomTotalNo,roomPrice,roomForSell,
roomForSellAuto,roomRemainNo,roomDiscountStartDate,roomDiscountEndDate,
roomDisccountPercent,roomDiscountHr,roomOnePrice,roomFun,roomMeal,
roomSleep,roomFacility,roomSweetFacility,roomCapacity,roomOneBed,roomTwoBed
)values(
room_seq.NEXTVAL,'10002','標準雙床間',15,3000,'0','0',12,64800000,79200000,0.48,1,'0',
'免費無線上網、48 吋LCD 液晶電視和付費電視頻道',
'冰箱、義大利濃縮咖啡機、電熱水壺和免費瓶裝水',
'遮光窗簾',
'保險箱、書桌和熨斗及熨衣板 (可要求)，可要求提供 免費搖籃/嬰兒床',
'空調和每日客房清潔服務',4,2,1
);








insert into room(
roomId,roomHotelId,roomName,roomTotalNo,roomPrice,roomForSell,
roomForSellAuto,roomRemainNo,roomDiscountStartDate,roomDiscountEndDate,
roomDisccountPercent,roomDiscountHr,roomOnePrice,roomFun,roomMeal,
roomSleep,roomFacility,roomSweetFacility,roomCapacity,roomOneBed,roomTwoBed
)values(
room_seq.NEXTVAL,'10002','雅逸居一大床',8,12000,'0','0',3,64800000,79200000,0.03,1,'0',
'免費無線上網、48 吋LCD 液晶電視和付費電視頻道',
'冰箱、迷你吧、24 小時客房送餐服務和免費瓶裝水',
'高級寢具、多種枕頭選擇、遮光窗簾和開床服務',
'保險箱、免費報紙和書桌，可要求提供折疊床/加床和免費搖籃/嬰兒床',
'空調和每日客房清潔服務',4,2,1
);




insert into room(
roomId,roomHotelId,roomName,roomTotalNo,roomPrice,roomForSell,
roomForSellAuto,roomRemainNo,roomDiscountStartDate,roomDiscountEndDate,
roomDisccountPercent,roomDiscountHr,roomOnePrice,roomFun,roomMeal,
roomSleep,roomFacility,roomSweetFacility,roomCapacity,roomOneBed,roomTwoBed
)values(
room_seq.NEXTVAL,'10002','雲天露臺客房',10,8670,'0','0',8,64800000,79200000,0.05,1,'0',
'免費無線和有線上網、42 吋平面電視',
'冰箱、迷你吧、24 小時客房送餐服務和免費瓶裝水',
'高級寢具、多種枕頭選擇、遮光窗簾和開床服務',
'沙發床、保險箱和免費報紙，可要求提供折疊床/加床和免費搖籃/嬰兒床',
'空調和每日客房清潔服務,非吸煙客房',4,0,2
);








insert into room(
roomId,roomHotelId,roomName,roomTotalNo,roomPrice,roomForSell,
roomForSellAuto,roomRemainNo,roomDiscountStartDate,roomDiscountEndDate,
roomDisccountPercent,roomDiscountHr,roomOnePrice,roomFun,roomMeal,
roomSleep,roomFacility,roomSweetFacility,roomCapacity,roomOneBed,roomTwoBed
)values(
room_seq.NEXTVAL,'10002','尊榮客房 (台北101景觀)',20,10900,'0','0',13,64800000,79200000,0.10,1,'0',
'免費無線和有線上網、40 吋電視',
'24 小時客房送餐服務、迷你吧和免費瓶裝水',
'低過敏寢具、遮光窗簾和開床服務',
'保險箱、免費報紙和書桌，可要求提供 免費搖籃/嬰兒床',
'空調和每日客房清潔服務,非吸煙客房',6,2,2
);








commit;








/* [Level 3] 訂單 房型照片 願望項目 */




/* 訂單 */
CREATE TABLE ORD (
ORDID              VARCHAR2(10) NOT NULL,
ORDROOMID          VARCHAR2(7) NOT NULL,
ORDMEMID           VARCHAR2(8) NOT NULL,
ORDHOTELID         VARCHAR2(5) NOT NULL,
ORDPRICE           VARCHAR2(7),
ORDLIVEDATE        DATE,
ORDDATE            DATE,
ORDSTATUS          VARCHAR2(1),
ORDRATINGCONTENT   VARCHAR2(150),
ORDRATINGSTARNO    NUMBER(2),
ORDQRPIC           BLOB,
ORDMSGNO           VARCHAR2(4),  
CONSTRAINT ORD_ORDID_PK PRIMARY KEY (ORDID),
CONSTRAINT ORD_ORDROOMID_FK FOREIGN KEY (ORDROOMID) REFERENCES ROOM (ROOMID),
CONSTRAINT ORD_ORDMEMID_FK FOREIGN KEY (ORDMEMID) REFERENCES MEM (MEMID),
CONSTRAINT ORD_ORDHOTELID_FK FOREIGN KEY (ORDHOTELID) REFERENCES HOTEL (HOTELID)
);








CREATE SEQUENCE ord_seq
INCREMENT BY 1
START WITH 1001
NOMAXVALUE
NOCYCLE
NOCACHE;








INSERT INTO ORD
VALUES (
CONCAT(TO_CHAR(SYSDATE,'YYYYMM'),ord_seq.NEXTVAL),
'1000001',
'10000001',
'10001',
1000000,
SYSDATE,
SYSDATE+1,
0,
'啊不就好棒棒123',
10,
null,
'ABCD'
); 


INSERT INTO ORD
VALUES (
CONCAT(TO_CHAR(SYSDATE,'YYYYMM'),ord_seq.NEXTVAL),
'1000004',
'10000001',
'10002',
1000000,
SYSDATE,
SYSDATE+1,
0,
'啊不就好棒棒789',
10,
null,
'ABCD'
); 




INSERT INTO ORD
VALUES (
CONCAT(TO_CHAR(SYSDATE,'YYYYMM'),ord_seq.NEXTVAL),
'1000003',
'10000001',
'10002',
1000000,
SYSDATE,
SYSDATE+1,
0,
'啊不就好棒棒456',
10,
null,
'ABCD'
); 


INSERT INTO ORD
VALUES (
CONCAT(TO_CHAR(SYSDATE,'YYYYMM'),ord_seq.NEXTVAL),
'1000002',
'10000002',
'10002',
100,
SYSDATE,
SYSDATE+1,
0,
'台中人愛吃辣123',
0,
null,
'EFGH'
); 




commit;




/* 房型照片 */
create table roomphoto(
 roomPhotoId VARCHAR2(12 char) constraint roomphoto_roomphotoId_pk primary key,
 roomPhotoRoomId VARCHAR2(7 char) not null constraint roomphoto_roomPhotoRoomId_fk references room(roomid),
 roomPhotoPic blob
);




CREATE SEQUENCE roomphoto_seq
INCREMENT BY 1
START WITH 10001
NOMAXVALUE
NOCYCLE
NOCACHE;




commit;




/* 願望項目 */
create table wish(
  wishMemId varchar(8) not null,
  wishRoomId varchar(7) not null,
  constraint wish_wishMemId_fk foreign key (wishMemId) references mem(memId),
  constraint wish_wishRoomId_fk foreign key (wishRoomId) references room(roomId),
  constraint wish_primaryKey_pk primary key (wishMemId,wishRoomId));




 INSERT INTO wish VALUES(10000001,1000001);
 INSERT INTO wish VALUES(10000001,1000002);
 INSERT INTO wish VALUES(10000001,1000003);
 INSERT INTO wish VALUES(10000002,1000002);
 INSERT INTO wish VALUES(10000003,1000002);








commit;




/* [Level 4] 一般會員檢舉單 廠商會員檢舉單 */




/* 一般會員檢舉單 */
CREATE TABLE memrep( 
  memrepId VARCHAR2(10) NOT NULL,
  memrepOrdId VARCHAR2(10) NOT NULL,
  memrepMemId VARCHAR2(8) NOT NULL,
  memrepHotelId VARCHAR2(5) NOT NULL,
  memrepEmpId VARCHAR2(8), --只有審核之後才會填入資料，因此可以是Null
  memrepContent VARCHAR2(150),
  memrepStatus VARCHAR2(1),
  memrepDate DATE,
  memrepReviewDate DATE,
  
  CONSTRAINT memrep_memrepId_pk PRIMARY KEY (memrepId),
  CONSTRAINT memrep_memrepOrdId_fk FOREIGN KEY (memrepOrdId) REFERENCES ord(ordId),
  CONSTRAINT memrep_memrepMemId_fk FOREIGN KEY (memrepMemId) REFERENCES mem(memId),  
  CONSTRAINT memrep_memrepHotelId_fk FOREIGN KEY (memrepHotelId) REFERENCES hotel(hotelId),  
  CONSTRAINT memrep_memrepEmpId_fk FOREIGN KEY (memrepEmpId) REFERENCES emp(empId)  
);




CREATE SEQUENCE memrep_seq
INCREMENT BY 1
START WITH 1000000001
NOMAXVALUE
NOCYCLE
NOCACHE;




-- 此檢舉單根據第一個訂單提出(根據訂單，附上memId以及hotelId)，且尚未處理
INSERT INTO memrep (memrepId, memrepOrdId, memrepMemId, memrepHotelId, memrepEmpId, memrepContent, memrepStatus,memrepDate,memrepReviewDate  )
VALUES ( memrep_seq.NEXTVAL,
        (select ordid 
          from (select rownum rnum, ordid
                from ord
                where rownum <= 1)
          where rnum >= 1), 
        (select ordmemid
          from (select rownum rnum, ordid, ordmemid
                from ord
                where rownum <= 1)
          where rnum >= 1),
        (select ordhotelid
          from (select rownum rnum, ordid, ordhotelid
                from ord
                where rownum <= 1)
          where rnum >= 1),
          null,
          '此旅館的服務態度很差',
          0, --0.未審核 1.已審核未通過 2.已審核已通過
          sysdate, -- 檢舉單提出日
          null
          );
-- 有空再補強: 增加check - 欄位: memrepEmpId, memrepStatus, memrepReviewDate 有連動關係




-- 此檢舉單根據第三個訂單提出(根據訂單，附上memId以及hotelId)，且已由第一位員工處理，在隔天
INSERT INTO memrep (memrepId, memrepOrdId, memrepMemId, memrepHotelId, memrepEmpId, memrepContent, memrepStatus,memrepDate,memrepReviewDate  )
VALUES ( memrep_seq.NEXTVAL,
        (select ordid 
          from (select rownum rnum, ordid
                from ord
                where rownum <= 2)
          where rnum >= 2), 
        (select ordmemid
          from (select rownum rnum, ordid, ordmemid
                from ord
                where rownum <= 2)
          where rnum >= 2),
        (select ordhotelid
          from (select rownum rnum, ordid, ordhotelid
                from ord
                where rownum <= 2)
          where rnum >= 2),
          (select empid
          from (select rownum rnum, empid
                from emp
                where rownum <= 1)
          where rnum >= 1), -- 第一位員工
          '我到了旅館，他們卻要跟我說兩倍的價格，太可惡。',
          2, --0.未審核 1.已審核未通過 2.已審核已通過
          sysdate-1, --檢舉單提出日
          sysdate --檢舉單審核日
          );
-- 有空再補強: 當審核狀態更改為2，要連動將一般會員的'黑名單狀態'作相對應更動        




commit;




/* 廠商會員檢舉單 */
CREATE TABLE HOTELREP(
hotelRepId       VARCHAR2(9) NOT NULL,
hotelRepHotelId  VARCHAR2(5) NOT NULL,
hotelRepMemId    VARCHAR2(8) NOT NULL, 
hotelRepOrdId    VARCHAR2(10) NOT NULL, 
hotelRepEmpId    VARCHAR2(5), 
hotelRepContent  VARCHAR2(150), 
hotelRepStatus   VARCHAR2(1), 
hotelRepDate     DATE,
hotelRepReviewDate DATE,




CONSTRAINT HOTELREP_HOTELREPID_PK PRIMARY KEY (hotelRepId),
CONSTRAINT HOTELREP_hotelRepHotelId_FK FOREIGN KEY (hotelRepHotelId) REFERENCES HOTEL (HOTELID),
CONSTRAINT HOTELREP_hotelRepMemId_FK FOREIGN KEY (hotelRepMemId) REFERENCES MEM (MEMID),
CONSTRAINT HOTELREP_hotelRepOrdId_FK FOREIGN KEY (hotelRepOrdId) REFERENCES ORD (ORDID),
CONSTRAINT HOTELREP_hotelRepEmpId_FK FOREIGN KEY (hotelRepEmpId) REFERENCES EMP (EMPID)
);








CREATE SEQUENCE hotelRep_seq
INCREMENT BY 1
START WITH 100000001
NOMAXVALUE
NOCYCLE
NOCACHE;








INSERT INTO HOTELREP
VALUES (
hotelRep_seq.NEXTVAL,
'10001',
'10000001',
to_char(sysdate,'yyyymm')||'1001',
'10001',
'客人不給錢',
'0',
sysdate,
null
); 





commit;