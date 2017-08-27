DELETE FROM MATCH_MESSAGE_LIST;
DELETE FROM MESSAGE;
DELETE FROM MATCH_USERS;
DELETE FROM MATCH;
DELETE FROM USER_STATUS;
DELETE FROM USER_;
DELETE FROM LIKE_STATUS;
DELETE FROM DISLIKE_STATUS;

INSERT INTO LIKE_STATUS (ID, GIVER, TAKER) VALUES
  (1, 2, 1),
  (3, 2, 3),
  (7, 3, 4),
  (9, 2, 5);

INSERT INTO USER_(ID, EMAIL, USER_PASS,LIKE_ID, DISLIKE_ID ) VALUES
  -- 1st pass: status-uno
  (1, 'cele@yahoo.com', '12f7da18e62c05b3a58d16ddc09642991171c151ceb76af21af9523fd4fe756454e1747f37f2e6ace5a154433da1facc9b1481f7482639d8980ef872549efd99' , null, null),
  -- 2nd pass: status-duo
  (2, 'bla@gmx.de' , 'b0dc3c57c9ca3c94ae7c39e160dd57bea9e10e03a7fdcf399795f7575c3a3dd1e5566d77f5d24a9b3a6f6e7fdc93ad4ec8aad72528fa0e6306b3717a7a906d3d', null, null),
  -- 3d pass: status-drei
  (3, 'boring@gmx.de' , '6b321a908efb731cd1a3e341d2f54e63043247907ab2b6633d83a3bbba5f22c7b01e83e111e19de9720fa6f150cb9e781e94006b942bae57b95ea06cc8492f1a', null, null),
  -- 4th pass: status-vier
  (4, 'musk@gmx.de' , '0e3e97b9678ad9a38ecf4c6f8456a668924b6b86ff50866c74404eb6b17568345847a3ee8fe29310785a70c3be70715f1a4521bc5e1419ff79d4eebba85be944', null, null),
  -- 5th pass: status-funf
  (5, 'cr7@gmx.de' , '141f8bc4146dfd8399c286f025b499452e56f72589d9aa8011448396445613d1f1e0e951d19c2643f5a8e12279485e4390be41e82791e65b94571a622f4bc98e', null, null);

INSERT INTO USER_STATUS (ID, STATUS, OWNER_ID) VALUES
  (1, 'If I won the award for laziness, I would send somebody to pick it up for me.', 1 ),
  (2, 'Maybe if we tell people the brain is an app, they''ll start using it.',  2 ),
  (3, 'When you wake up at 6 in the morning, you close your eyes for 5 minutes and it''s already 6:45. When you''re at work and it''s 2:30, you close your eyes for 5 minutes and it''s 2:31.',  3 ),
  (4, 'My room is like the Bermuda triangle, stuff goes in and is never seen again. - http://coolfunnyquotes.com ',  4 ),
  (5, 'Life always offers you a second chance. It''s called tomorrow. ',  5 );

