CREATE TABLE User (
    userID VARCHAR(255) PRIMARY KEY NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO UserS (userID, firstName, lastName, email) VALUES
('U001', 'Alice', 'Smith', 'alice.smith@example.com'),
('U002', 'Bob', 'Johnson', 'bob.johnson@example.com'),
('U003', 'Charlie', 'Brown', 'charlie.brown@example.com'),
('U004', 'Diana', 'Prince', 'diana.prince@example.com'),
('U005', 'Evan', 'Williams', 'evan.williams@example.com');

select * from Users;
