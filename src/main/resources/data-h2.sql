DELETE FROM USER_;
DELETE FROM USER_STATUS;

INSERT INTO USER_STATUS (ID, STATUS, OWNER_ID) VALUES
  (1, 'stutus-uno', NULL ),
  (2, 'status-quo',  NULL );