/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Folio
 * Created: 09-Oct-2020
 */
USE mms;


-- Create Table For The administrator Account
CREATE TABLE adminaccount (
    adminId VARCHAR(100) PRIMARY KEY,
    adminFirstName VARCHAR(20) NOT NULL,
    adminLastName VARCHAR(20) ,
    adminPass VARCHAR(100) NOT NULL
);


-- Create Table To Check if The User is
-- Registered In This portal or Not
-- Create Table For The Users Login Information
-- Used To Authenicate The Users To The Application
CREATE TABLE userlogin (
    userId VARCHAR(50) PRIMARY KEY NOT NULL UNIQUE,
    userFirstName VARCHAR(50) NOT NULL,
    userLastName VARCHAR(50) NOT NULL,
    userEmailid VARCHAR(50) NOT NULL UNIQUE,
    userPass VARCHAR(100) NOT NULL,
    userContactNo VARCHAR(11) NOT NULL,
    userGender VARCHAR(10) NOT NULL
);


-- Create Table To Store Registered Trains Information
CREATE TABLE traininfo (
    trainNum VARCHAR(50) PRIMARY KEY UNIQUE,
    trainName VARCHAR(50) NOT NULL,
    firstStation VARCHAR(30) NOT NULL,
    lastStation VARCHAR(30) NOT NULL,
    departureTime VARCHAR(10) NOT NULL,
    arrivalTime VARCHAR(10) NOT NULL,
    --seatsFirstClass INT DEFAULT 0 NOT NULL,
    --seatsSecondClass INT DEFAULT 0 NOT NULL,
    --seatsSleeperClass INT DEFAULT 0,
    feeFirstClass INT DEFAULT 0 NOT NULL,
    feeSecondClass INT DEFAULT 0 NOT NULL,
    feeSleeperClass INT DEFAULT 0,
    days VARCHAR(7) NOT NULL,
    cancel INT DEFAULT 0
);

-- Create firstClass Table To Store Information of available seats
-- of First Class
CREATE TABLE firstClass (
    trainNum VARCHAR(50) PRIMARY KEY,
    totalseats INT DEFAULT 0 NOT NULL,
    uppers INT DEFAULT 0 NOT NULL,
    lowers INT DEFAULT 0 NOT NULL,
    --middles INT DEFAULT 0 NOT NULL,
    sideuppers INT DEFAULT 0 NOT NULL,
    sidelowers INT DEFAULT 0 NOT NULL,
    FOREIGN KEY(trainNum) REFERENCES traininfo(trainNum)
);

-- Create secondClass Table To Store Information of available seats
-- of Second Class
CREATE TABLE secondClass (
    trainNum VARCHAR(50) PRIMARY KEY,
    totalseats INT DEFAULT 0 NOT NULL,
    uppers INT DEFAULT 0 NOT NULL,
    lowers INT DEFAULT 0 NOT NULL,
    --middles INT DEFAULT 0 NOT NULL,
    sideuppers INT DEFAULT 0 NOT NULL,
    sidelowers INT DEFAULT 0 NOT NULL,
    FOREIGN KEY(trainNum) REFERENCES traininfo(trainNum)
);

-- Create sleeperClass Table To Store Information of available seats
-- of Sleeper Class
CREATE TABLE sleeperClass (
    trainNum VARCHAR(50) PRIMARY KEY,
    totalseats INT DEFAULT 0 NOT NULL,
    uppers INT DEFAULT 0 NOT NULL,
    lowers INT DEFAULT 0 NOT NULL,
    middles INT DEFAULT 0 NOT NULL,
    sideuppers INT DEFAULT 0 NOT NULL,
    sidelowers INT DEFAULT 0 NOT NULL,
    FOREIGN KEY(trainNum) REFERENCES traininfo(trainNum)
);


-- Create Table For The Passengers 
-- Who Are Registered For The Travel
CREATE TABLE passengerdetail (
    --ID INT AUTO_INCREMENT NOT NULL UNIQUE,
    trainNum VARCHAR(10) NOT NULL,
    userId VARCHAR(50) NOT NULL,
    passclass VARCHAR(20) NOT NULL,
    passseatNo INT DEFAULT 0 NOT NULL,
    passengerTicketId VARCHAR(70) NOT NULL PRIMARY KEY,
    passengerFirstName VARCHAR(50) NOT NULL,
    passengerLastName VARCHAR(50),
    passengerAge INT NOT NULL,
    passengergender VARCHAR(10) NOT NULL,
    travdate DATE NOT NULL,
    FOREIGN KEY(userId) REFERENCES userlogin(userId),
    FOREIGN KEY(trainNum) REFERENCES traininfo(trainNum)
);