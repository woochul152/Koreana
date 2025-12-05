
-- CREATE DATABASE travel_reservation;
-- CREATE TABLE Employee (
--     SSN CHAR(9) PRIMARY KEY,      
--     firstName VARCHAR(50) NOT NULL,
--     lastName VARCHAR(50) NOT NULL,
--     address VARCHAR(100),
--     city VARCHAR(50),
--     state CHAR(2),
--     zipCode CHAR(5),               
--     email VARCHAR(100) UNIQUE,      
--     startDate DATE,              
--     hourlyRate DECIMAL(10,2),       
--     isManager TINYINT(1) 
-- );

-- Sample data Employee
-- INSERT INTO Employee (SSN, firstName, lastName, address, city, state, zipCode, email, startDate, hourlyRate, isManager)
-- VALUES
-- ('123456789', 'John', 'Smith', '12 Main St', 'Stony Brook', 'NY', '11790', 'john.smith@company.com', '2018-03-15', 25.50, 0),
-- ('987654321', 'Emily', 'Johnson', '45 Lake Rd', 'Stony Brook', 'NY', '11790', 'emily.johnson@company.com', '2016-07-01', 32.00, 1),
-- ('555443333', 'Michael', 'Lee', '210 Ocean Ave', 'Brooklyn', 'NY', '11201', 'michael.lee@company.com', '2020-01-10', 22.75, 0),
-- ('631413555', 'Sarah', 'Kim', '88 Liberty St', 'New York', 'NY', '10005', 'sarah.kim@company.com', '2017-11-05', 40.00, 1),
-- ('411228900', 'David', 'Park', '300 Northern Blvd', 'Great Neck', 'NY', '11021', 'david.park@company.com', '2019-05-20', 28.00, 0);




-- CREATE TABLE Flight (
--     AirlineID CHAR(2) NOT NULL,
--     FlightNo INT NOT NULL,
--     NumOfSeats INT NOT NULL,
--     DaysOperating CHAR(7) NOT NULL,
--     MinLengthOfStay INT NOT NULL,
--     MaxLengthOfStay INT NOT NULL,
--     NumReservations INT DEFAULT 0,
--     PRIMARY KEY (AirlineID, FlightNo)
-- );

-- INSERT INTO Flight VALUES
-- ('AA', 111, 150, '1111100', 1, 30, 0),
-- ('AA', 245, 180, '1010101', 2, 21, 0),
-- ('DL', 330, 200, '1111111', 1, 14, 0),
-- ('DL', 512, 160, '1101100', 0, 7, 0),
-- ('UA', 220, 140, '1010110', 1, 10, 0),
-- ('UA', 555, 210, '1110000', 3, 21, 0),
-- ('SW', 700, 130, '0110111', 0, 7, 0),
-- ('JB', 990, 175, '1011001', 2, 28, 0);




-- CREATE TABLE Customer (
--     AccountNo INT AUTO_INCREMENT PRIMARY KEY,
--     FirstName VARCHAR(50) NOT NULL,
--     LastName VARCHAR(50) NOT NULL,
--     Address VARCHAR(100),
--     City    VARCHAR(50),
--     State   CHAR(2),
--     ZipCode CHAR(5),
--     Email   VARCHAR(100) UNIQUE NOT NULL,
--     CreditCard VARCHAR(30),
--     Rating  INT DEFAULT 0
-- );

-- INSERT INTO Customer (FirstName, LastName, Address, City, State, ZipCode, Email, CreditCard, Rating)
-- VALUES
-- ('John', 'Wick', '123 Continental St', 'New York', 'NY', '10001', 'john.wick@example.com', '4111111111111111', 5),
-- ('Emily', 'Clark', '55 Maple Ave', 'Boston', 'MA', '02115', 'emily.clark@example.com', '5500000000000004', 3),
-- ('Michael', 'Kim', '789 Oak Road', 'Seattle', 'WA', '98101', 'michael.kim@example.com', '6011111111111117', 4),
-- ('Sophia', 'Lee', '10 River St', 'San Jose', 'CA', '95112', 'sophia.lee@example.com', '378282246310005', 2),
-- ('Daniel', 'Park', '77 Cherry Lane', 'Chicago', 'IL', '60616', 'daniel.park@example.com', '4012888888881881', 1),
-- ('Grace', 'Choi', '420 Pine St', 'Austin', 'TX', '73301', 'grace.choi@example.com', '4222222222222', 4),
-- ('David', 'Nguyen', '963 Walnut Dr', 'Houston', 'TX', '77001', 'david.nguyen@example.com', '5555555555554444', 5),
-- ('Isabella', 'Martinez', '30 Golden Rd', 'Miami', 'FL', '33101', 'isabella.martinez@example.com', '378734493671000', 3),
-- ('James', 'Johnson', '81 Harbor Blvd', 'San Diego', 'CA', '92101', 'james.johnson@example.com', '30569309025904', 2),
-- ('Hannah', 'Lim', '12 Sunrise Dr', 'New York', 'NY', '10002', 'hannah.lim@example.com', '6011000990139424', 5);




-- CREATE TABLE FlightReservation (
--     ResrNo INT AUTO_INCREMENT PRIMARY KEY,
--     ResrDate DATE NOT NULL,
--     TotalFare DOUBLE NOT NULL,
--     BookingFee DOUBLE NOT NULL,
--     Revenue DOUBLE DEFAULT 0,
--     RepSSN CHAR(9),
--     PassengerID INT,
--     AccountNo INT,
--     FOREIGN KEY (AccountNo) 
--         REFERENCES Customer(AccountNo)
--         ON DELETE CASCADE
--         ON UPDATE CASCADE,
--     FOREIGN KEY (PassengerID) 
--         REFERENCES Customer(AccountNo)
--         ON DELETE CASCADE
--         ON UPDATE CASCADE,
--     FOREIGN KEY (RepSSN)
--         REFERENCES Employee(SSN)
--         ON DELETE SET NULL
--         ON UPDATE CASCADE
-- );

-- INSERT INTO FlightReservation
-- (ResrDate, TotalFare, BookingFee, Revenue, RepSSN, PassengerID, AccountNo)
-- VALUES
-- ('2024-11-01', 350.00, 20.00, 330.00, '631413555', 1, 1),
-- ('2024-11-02', 420.00, 25.00, 395.00, '631413555', 2, 2),
-- ('2024-11-03', 150.00, 10.00, 140.00, '631413555', 3, 3),
-- ('2024-11-04', 580.00, 30.00, 550.00, '631413555', 4, 4),
-- ('2024-11-05', 260.00, 15.00, 245.00, '631413555', 5, 5);


-- CREATE TABLE Itinerary (
--     ResrNo INT NOT NULL,
--     AirlineID CHAR(2) NOT NULL,
--     FlightNo INT NOT NULL,
--     Departure VARCHAR(20),
--     Arrival VARCHAR(20),
--     DepTime DATETIME,
--     ArrTime DATETIME,
--     ActualArrTime DATETIME,
--     ActualDepTime DATETIME,
--     FOREIGN KEY (ResrNo)
--         REFERENCES FlightReservation(ResrNo)
--         ON DELETE CASCADE
--         ON UPDATE CASCADE,
--     FOREIGN KEY (AirlineID, FlightNo)
--         REFERENCES Flight(AirlineID, FlightNo)
--         ON DELETE CASCADE
--         ON UPDATE CASCADE
-- );

-- INSERT INTO Itinerary 
-- (ResrNo, AirlineID, FlightNo, Departure, Arrival,
--  DepTime, ArrTime, ActualDepTime, ActualArrTime)
-- VALUES
-- (1, 'AA', 111, 'JFK', 'LAX',
--  '2024-11-10 06:00:00', 
--  '2024-11-10 09:00:00',
--  '2024-11-10 06:02:00',      
--  '2024-11-10 08:58:00'),     

-- (2, 'AA', 245, 'BOS', 'MIA',
--  '2024-11-11 07:30:00',
--  '2024-11-11 11:00:00',
--  '2024-11-11 07:35:00',      
--  '2024-11-11 11:25:00'),     

-- (3, 'DL', 330, 'SEA', 'SFO',
--  '2024-11-12 13:00:00',
--  '2024-11-12 15:00:00',
--  '2024-11-12 13:00:00',      
--  '2024-11-12 15:00:00'),     

-- (4, 'UA', 555, 'ORD', 'JFK',
--  '2024-11-13 09:00:00',
--  '2024-11-13 12:00:00',
--  '2024-11-13 09:10:00',     
--  '2024-11-13 12:40:00'),    

-- (5, 'SW', 700, 'SFO', 'LAS',
--  '2024-11-14 10:00:00',
--  '2024-11-14 11:15:00',
--  '2024-11-14 10:00:00',  
--  '2024-11-14 11:12:00');


-- SELECT * FROM Employee;
-- SELECT * FROM Flight;
-- SELECT * FROM Customer;
-- SELECT * FROM FlightReservation; 
-- SELECT * FROM Itinerary;





