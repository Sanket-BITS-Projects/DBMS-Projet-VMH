DROP TABLE IF EXISTS SESSION, PRESCRIBED_LAB_TESTS, PRESCRIBED_MEDICINE, PRESCRIPTION, APPOINTMENT,
    DOCTOR_SPECIALITY, DOCTOR, ROLE, MEDICINE, LAB_TESTS, ILLNESS, SPECIALIZATION, PERSON CASCADE;

CREATE TABLE Role
(
    role_name VARCHAR(10) NOT NULL,
    role_id   INT AUTO_INCREMENT,
    PRIMARY KEY (role_id)
);

INSERT INTO Role(ROLE_NAME)
VALUES ('ADMIN');
INSERT INTO Role(ROLE_NAME)
VALUES ('DOCTOR');
INSERT INTO Role(ROLE_NAME)
VALUES ('USER');

CREATE TABLE Specialization
(
    sp_id      INT AUTO_INCREMENT,
    speciality VARCHAR(50) NOT NULL,
    PRIMARY KEY (sp_id)
);

INSERT INTO Specialization(SPECIALITY)
VALUES ('Physician');
INSERT INTO Specialization(SPECIALITY)
VALUES ('Pediatrician');
INSERT INTO Specialization(SPECIALITY)
VALUES ('Gynecologist');
INSERT INTO Specialization(SPECIALITY)
VALUES ('Surgeon');
INSERT INTO Specialization(SPECIALITY)
VALUES ('Psychiatrist');
INSERT INTO Specialization(SPECIALITY)
VALUES ('Cardiologist');
INSERT INTO Specialization(SPECIALITY)
VALUES ('Dermatologist');
INSERT INTO Specialization(SPECIALITY)
VALUES ('Endocrinologist');
INSERT INTO Specialization(SPECIALITY)
VALUES ('Gastroenterologist');
INSERT INTO Specialization(SPECIALITY)
VALUES ('Neurologist');
INSERT INTO Specialization(SPECIALITY)
VALUES ('Anesthesiologist');
INSERT INTO Specialization(SPECIALITY)
VALUES ('Orthopedist');
INSERT INTO Specialization(SPECIALITY)
VALUES ('Radiologist');

CREATE TABLE Medicine
(
    m_id   INT AUTO_INCREMENT,
    m_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (m_id)
);

CREATE TABLE Lab_Tests
(
    lt_id   INT AUTO_INCREMENT,
    lt_name VARCHAR(30) NOT NULL,
    PRIMARY KEY (lt_id)
);

INSERT INTO Lab_Tests(LT_NAME)
VALUES ('Complete Blood Count');
INSERT INTO Lab_Tests(LT_NAME)
VALUES ('Basic Metabolic Panel');
INSERT INTO Lab_Tests(LT_NAME)
VALUES ('Lipid Panel');
INSERT INTO Lab_Tests(LT_NAME)
VALUES ('Liver Panel');
INSERT INTO Lab_Tests(LT_NAME)
VALUES ('Urinalysis');
INSERT INTO Lab_Tests(LT_NAME)
VALUES ('MRI');

CREATE TABLE Illness
(
    i_id        INT AUTO_INCREMENT,
    title       VARCHAR(30)  NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (i_id)
);

CREATE TABLE Person
(
    p_name   VARCHAR(50)  NOT NULL,
    dob      DATE         NOT NULL,
    phone    VARCHAR(10)  NOT NULL,
    p_id     INT AUTO_INCREMENT,
    email    VARCHAR(320) NOT NULL,
    password VARCHAR(255) NOT NULL,
    balance  INT DEFAULT 2000,
    address  VARCHAR(255) NOT NULL,
    role_id  INT          NOT NULL,
    PRIMARY KEY (p_id),
    FOREIGN KEY (role_id) REFERENCES Role (role_id)
);

INSERT INTO PERSON(P_NAME, DOB, PHONE, EMAIL, PASSWORD, ADDRESS, ROLE_ID)
VALUES ('ADMIN', '1990-01-01', '9876543210', 'admin@email.com', 'password', 'Sample Address', 1);
INSERT INTO PERSON(P_NAME, DOB, PHONE, EMAIL, PASSWORD, ADDRESS, ROLE_ID)
VALUES ('Abc xyz', '1990-01-01', '9876543210', 'abcxyz@email.com', 'password', 'Sample Address', 3);
INSERT INTO PERSON(P_NAME, DOB, PHONE, EMAIL, PASSWORD, ADDRESS, ROLE_ID)
VALUES ('Xyz', '1990-01-01', '9876543210', 'xyz@email.com', 'password', 'Sample Address', 3);

CREATE TABLE Session
(
    session_id VARCHAR(255) NOT NULL,
    timestamp  DATE         NOT NULL,
    p_id       INT          NOT NULL,
    PRIMARY KEY (p_id),
    FOREIGN KEY (p_id) REFERENCES Person (p_id),
    UNIQUE (session_id)
);

CREATE TABLE Doctor
(
    fees INT NOT NULL,
    d_id INT NOT NULL,
    PRIMARY KEY (d_id),
    FOREIGN KEY (d_id) REFERENCES Person (p_id)
);

INSERT INTO PERSON(P_NAME, DOB, PHONE, EMAIL, PASSWORD, ADDRESS, ROLE_ID)
VALUES ('Dr. Ramesh', '1990-05-11', '9876543210', 'dr.ramesh@email.com', 'password', 'Dummy Address', 2);
INSERT INTO DOCTOR(FEES, D_ID)
VALUES (300, SELECT P_ID FROM PERSON WHERE email = 'dr.ramesh@email.com');
INSERT INTO DOCTOR_SPECIALITY(SP_ID, D_ID)
VALUES (SELECT SP_ID FROM SPECIALIZATION WHERE speciality = 'Physician',
        SELECT P_ID FROM PERSON WHERE email = 'dr.ramesh@email.com');

INSERT INTO PERSON(P_NAME, DOB, PHONE, EMAIL, PASSWORD, ADDRESS, ROLE_ID)
VALUES ('Dr. Abhishek', '1999-05-16', '9876543210', 'dr.abhishek@email.com', 'password', 'Dummy Address', 2);
INSERT INTO DOCTOR(FEES, D_ID)
VALUES (500, SELECT P_ID FROM PERSON WHERE email = 'dr.abhishek@email.com');
INSERT INTO DOCTOR_SPECIALITY(SP_ID, D_ID)
VALUES (SELECT SP_ID FROM SPECIALIZATION WHERE speciality = 'Physician',
        SELECT P_ID FROM PERSON WHERE email = 'dr.abhishek@email.com');

CREATE TABLE Appointment
(
    a_id          INT AUTO_INCREMENT,
    a_date_time   DATE NOT NULL,
    timestamp     DATE NOT NULL,
    doctor_accept CHAR(1) DEFAULT '0',
    patient_id    INT  NOT NULL,
    i_id          INT  NOT NULL,
    doctor_id     INT  NOT NULL,
    PRIMARY KEY (a_id),
    FOREIGN KEY (patient_id) REFERENCES Person (p_id),
    FOREIGN KEY (i_id) REFERENCES Illness (i_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctor (d_id)
);

CREATE TABLE Prescription
(
    description     VARCHAR(255) NOT NULL,
    course_duration INT          NOT NULL,
    timestamp       DATE         NOT NULL,
    presc_id        INT AUTO_INCREMENT,
    a_id            INT          NOT NULL,
    PRIMARY KEY (presc_id),
    FOREIGN KEY (a_id) REFERENCES Appointment (a_id),
    UNIQUE (a_id)
);

CREATE TABLE Doctor_Speciality
(
    sp_id INT NOT NULL,
    d_id  INT NOT NULL,
    PRIMARY KEY (sp_id, d_id),
    FOREIGN KEY (sp_id) REFERENCES Specialization (sp_id),
    FOREIGN KEY (d_id) REFERENCES Doctor (d_id)
);

CREATE TABLE Prescribed_Medicine
(
    usage    VARCHAR(255) NOT NULL,
    m_id     INT          NOT NULL,
    presc_id INT          NOT NULL,
    PRIMARY KEY (m_id, presc_id),
    FOREIGN KEY (m_id) REFERENCES Medicine (m_id),
    FOREIGN KEY (presc_id) REFERENCES Prescription (presc_id)
);

CREATE TABLE Prescribed_Lab_Tests
(
    lt_id    INT NOT NULL,
    presc_id INT NOT NULL,
    PRIMARY KEY (lt_id, presc_id),
    FOREIGN KEY (lt_id) REFERENCES Lab_Tests (lt_id),
    FOREIGN KEY (presc_id) REFERENCES Prescription (presc_id)
);