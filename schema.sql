CREATE TABLE users (name VARCHAR(50), email VARCHAR(50), password VARCHAR(50),  role VARCHAR(50), auth boolean, locked boolean);
INSERT INTO  users (name, email, password, role, auth, locked)
VALUES ('admin', 'qwerty@gmail.com', '123', 'admin', false, true);

CREATE TABLE foods (id SERIAL PRIMARY KEY, name VARCHAR(100), comment VARCHAR(250), price int, date DATE, email VARCHAR(50));
INSERT INTO foods(id, name, comment, price, date, email)
VALUES (10, 'cheese', 'product', 100, '2022-12-22', 'qwerty@gmail.com');