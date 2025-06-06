CREATE TABLE Baggage (
    claimId VARCHAR(255) PRIMARY KEY NOT NULL,
    location VARCHAR(255),
    status VARCHAR(255) NOT NULL,
    userID VARCHAR(255) NOT NULL,
    FOREIGN KEY (userID) REFERENCES user(userID)
);

INSERT INTO Baggage (claimId, location, status, userID) VALUES
('C1001', 'Terminal A', 'Pending', 'U001'),
('C1002', 'Terminal B', 'Claimed', 'U002'),
('C1003', 'Terminal A', 'Lost', 'U003'),
('C1004', 'Terminal C', 'Pending', 'U004'),
('C1005', 'Terminal B', 'Found', 'U005');

select * from Baggage;
