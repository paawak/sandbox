-- SEQUENCES

CREATE SEQUENCE SEQ_COUNTRY;
CREATE SEQUENCE SEQ_CITY;
CREATE SEQUENCE SEQ_STATE;
CREATE SEQUENCE SEQ_CLIENTACCOUNT;

-- TABLES

DROP TABLE clientaccount;
DROP TABLE city;
DROP TABLE state;
DROP TABLE country;

CREATE TABLE country (
  id SMALLINT NOT NULL DEFAULT NEXTVAL('SEQ_COUNTRY'),
  name VARCHAR(250) NOT NULL UNIQUE,
  shortname VARCHAR(15) UNIQUE,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  PRIMARY KEY  (id)
);


CREATE TABLE state (
  id INTEGER NOT NULL DEFAULT NEXTVAL('SEQ_STATE'),
  name VARCHAR(250) NOT NULL UNIQUE,
  shortname VARCHAR(15) NOT NULL UNIQUE,
  country SMALLINT NOT NULL REFERENCES country(id),
  PRIMARY KEY  (id)
);


CREATE TABLE city (
  id INTEGER NOT NULL DEFAULT NEXTVAL('SEQ_CITY'),
  name VARCHAR(250) NOT NULL,
  shortname VARCHAR(15),
  state INTEGER NOT NULL REFERENCES state(id),
  PRIMARY KEY  (id),
  UNIQUE (name, state)
);


CREATE TABLE clientaccount (
  accnum INTEGER NOT NULL DEFAULT NEXTVAL('SEQ_CLIENTACCOUNT'),
  name VARCHAR(250) NOT NULL UNIQUE,
  shortname VARCHAR(15),
  contactfirstname VARCHAR(15) NOT NULL,
  contactmiddlename VARCHAR(15),
  contactlastname VARCHAR(15) NOT NULL,
  telephone1 VARCHAR(15),
  telephone2 VARCHAR(15),
  mobile VARCHAR(15) NOT NULL,
  fax VARCHAR(15),
  email VARCHAR(25) NOT NULL,
  address1 VARCHAR(250) NOT NULL,
  address2 VARCHAR(250),
  city INTEGER NOT NULL REFERENCES city(id),
  pincode VARCHAR(100) NOT NULL,
  isorganization BOOLEAN NOT NULL DEFAULT FALSE,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  PRIMARY KEY  (accnum)
);



