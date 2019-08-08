CREATE TABLE IF NOT EXISTS AUTHOR
(
  ID NUMBER NOT NULL,
  FULL_NAME VARCHAR2(128) NOT NULL,
  IMAGE_NAME VARCHAR (64),

  PRIMARY KEY(ID)
);
CREATE INDEX IF NOT EXISTS AUTHOR_FULL_NAME_INDEX ON AUTHOR(FULL_NAME);

CREATE SEQUENCE IF NOT EXISTS quotes_author_id_seq start with 1 increment by 1 CACHE 100;

CREATE TABLE IF NOT EXISTS CATEGORY
(
  ID NUMBER NOT NULL,
  NAME VARCHAR2(128) NOT NULL,
  IMAGE_NAME VARCHAR (32),

  PRIMARY KEY(ID)
);
CREATE INDEX IF NOT EXISTS CATEGORY_NAME_INDEX ON CATEGORY(NAME);

CREATE SEQUENCE IF NOT EXISTS quotes_category_id_seq start with 1 increment by 1 CACHE 100;

CREATE TABLE IF NOT EXISTS QUOTE
(
  ID NUMBER NOT NULL,
  TEXT VARCHAR2(4096) NOT NULL,
  SOURCE VARCHAR2(256),
  SOURCE_TYPE VARCHAR2(16),
  YEAR VARCHAR2(32),
  AUTHOR_ID NUMBER NOT NULL,
  CATEGORY_ID NUMBER NOT NULL,

  PRIMARY KEY(ID),
  FOREIGN KEY(AUTHOR_ID) REFERENCES AUTHOR,
  FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORY
);

CREATE SEQUENCE IF NOT EXISTS quotes_quote_id_seq start with 1 increment by 1 CACHE 1000;