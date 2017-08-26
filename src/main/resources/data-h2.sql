DELETE FROM USER_STATUS;
DELETE FROM USER_;
DELETE FROM LIKE_STATUS;

INSERT INTO LIKE_STATUS (ID, GIVER, TAKER) VALUES
  (1, 2, 1);

INSERT INTO USER_(ID, EMAIL, USER_PASS,LIKE_ID, DISLIKE_ID ) VALUES
  (1, 'scele', 'stutus-uno' , null, null),
  (2, 'georn' , 'geroni12', null, null);

INSERT INTO USER_STATUS (ID, STATUS, OWNER_ID) VALUES
  (1, 'stutus-uno', 1 ),
  (2, 'status-quo',  2 );

