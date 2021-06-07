DROP TABLE IF EXISTS Cards;
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Clients;

CREATE TABLE Clients (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR,
  age INT
);

CREATE TABLE Accounts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  number VARCHAR,
  balance INT,
  currency VARCHAR,
  client_id INT NOT NULL,
  foreign key (client_id) references Clients(id) on delete cascade on update cascade
);

CREATE TABLE Cards (
  id INT AUTO_INCREMENT PRIMARY KEY,
  number VARCHAR,
  pin INT,
  cvc2 INT,
  account_id INT NOT NULL,
  foreign key (account_id) references Accounts(id) on delete cascade on update cascade
);

INSERT INTO Clients (name, age) VALUES
  ('Client #1', 31),
  ('Client #2', 32),
  ('Client #3', 33);

INSERT INTO Accounts (number, balance, currency, client_id) VALUES
  ('1111 1111 1111 1111', 100000, 'RUB', 1),
  ('2222 2222 2222 2222', 200000, 'USD', 2),
  ('3333 3333 3333 3333', 300000, 'EUR', 3);

INSERT INTO Cards (number, pin, cvc2, account_id) VALUES
  ('1111 1111 1111 1111', 1111, 111, 1),
  ('2222 2222 2222 2222', 2222, 222, 2),
  ('3333 3333 3333 3333', 3333, 333, 3);