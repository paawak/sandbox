CREATE TABLE country (
  id SMALLINT NOT NULL,
  name VARCHAR(250) NOT NULL,
  shortname VARCHAR(15),
  PRIMARY KEY  (id)
);


CREATE TABLE state (
  id INTEGER NOT NULL,
  name VARCHAR(250) NOT NULL,
  shortname VARCHAR(15),
  country SMALLINT NOT NULL REFERENCES country(id),
  PRIMARY KEY  (id)
);


CREATE TABLE city (
  id INTEGER NOT NULL,
  name VARCHAR(250) NOT NULL,
  shortname VARCHAR(15),
  state INTEGER NOT NULL REFERENCES state(id),
  PRIMARY KEY  (id)
);


CREATE TABLE clientaccount (
  accnum INTEGER NOT NULL,
  name VARCHAR(250) NOT NULL,
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
  status BOOLEAN NOT NULL DEFAULT TRUE,
  PRIMARY KEY  (accnum)
);



