--------------------------------------------------------
--  FUNCTION for Table PRODUCT BLOB
--------------------------------------------------------
/* 檔案儲存的目錄路徑 

請在C槽下建立目錄DB_photos1
並把圖片放入此目錄

*/ 
CREATE OR REPLACE  DIRECTORY MEDIA_DIR AS 'C:/DB_photos1/'; 

/* 擷取檔案的FUNCTION */ 
CREATE OR REPLACE FUNCTION load_blob( myFileName VARCHAR) RETURN BLOB as result BLOB;  
  myBFILE      BFILE;
  myBLOB       BLOB;
BEGIN
    myBFILE := BFILENAME('MEDIA_DIR',myFileName);
    dbms_lob.createtemporary(myBLOB, TRUE);
    dbms_lob.fileopen(myBFILE,dbms_lob.file_readonly);
    dbms_lob.loadfromfile(myBLOB,myBFILE,dbms_lob.getlength(myBFILE) );
    dbms_lob.fileclose(myBFILE);
    RETURN myBLOB;
END load_blob;

/* 注意: 上下兩段要分開來個跑一次 */
/* *************************************************************************************** */

--load_blob('xxx.jpg')

-- roomphoto
INSERT INTO roomphoto  VALUES (10005||roomphoto_seq.NEXTVAL,1000005, load_blob('room102001.jpg'));
																				/* 圖片名稱*/							
commit;


--mem
UPDATE mem 
SET memProfile = (load_blob('mem10000001.jpg'))
WHERE memId = '10000001';
UPDATE mem 
SET memProfile = (load_blob('mem10000002.jpg'))
WHERE memId = '10000002';
UPDATE mem 
SET memProfile = (load_blob('mem10000003.jpg'))
WHERE memId = '10000003';
UPDATE mem 
SET memProfile = (load_blob('mem10000004.jpg'))
WHERE memId = '10000004';
UPDATE mem 
SET memProfile = (load_blob('mem10000005.jpg'))
WHERE memId = '10000005';
UPDATE mem 
SET memProfile = (load_blob('mem10000006.jpg'))
WHERE memId = '10000006';
UPDATE mem 
SET memProfile = (load_blob('mem10000007.jpg'))
WHERE memId = '10000007';
UPDATE mem 
SET memProfile = (load_blob('mem10000008.jpg'))
WHERE memId = '10000008';
UPDATE mem 
SET memProfile = (load_blob('mem10000009.jpg'))
WHERE memId = '10000009';
UPDATE mem 
SET memProfile = (load_blob('mem10000010.jpg'))
WHERE memId = '10000010';
UPDATE mem 
SET memProfile = (load_blob('mem10000011.jpg'))
WHERE memId = '10000011';
commit;
