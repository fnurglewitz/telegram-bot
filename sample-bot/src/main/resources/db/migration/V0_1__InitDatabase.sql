CREATE TABLE IF NOT EXISTS PUBLIC.USER (
  USER_ID BIGINT,
  FIRST_NAME VARCHAR(100),
  LAST_NAME VARCHAR(100),
  USERNAME VARCHAR(100),
  CONSTRAINT PK_USER PRIMARY KEY (USER_ID)
);

CREATE TABLE IF NOT EXISTS PUBLIC.QUESTION_DATA (
	QUESTION_ID VARCHAR(100),
	CATEGORY VARCHAR(100),
  DIFFICULTY VARCHAR(100),
  TYPE VARCHAR(100),
  QUESTION TEXT,
	ANSWER TEXT,
  WINNING_USER_ID BIGINT,
	CONSTRAINT PK_QUESTION_DATA PRIMARY KEY ( QUESTION_ID )
);
