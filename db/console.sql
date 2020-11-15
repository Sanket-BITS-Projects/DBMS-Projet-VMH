CREATE TABLE Role
(
    role_name VARCHAR(10) NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE Specialization
(
    sp_id INT NOT NULL,
    speciality VARCHAR(50) NOT NULL,
    PRIMARY KEY (sp_id)
);

CREATE TABLE Medicine
(
    m_id INT NOT NULL,
    m_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (m_id)
);

CREATE TABLE Lab_Tests
(
    lt_id INT NOT NULL,
    lt_name VARCHAR(30) NOT NULL,
    PRIMARY KEY (lt_id)
);

CREATE TABLE Illness
(
    i_id INT NOT NULL,
    title VARCHAR(30) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (i_id)
);

CREATE TABLE Person
(
    p_name VARCHAR(50) NOT NULL,
    dob DATE NOT NULL,
    phone VARCHAR(10) NOT NULL,
    p_id INT NOT NULL,
    email VARCHAR(320) NOT NULL,
    password VARCHAR(255) NOT NULL,
    balance INT NOT NULL,
    address VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (p_id),
    FOREIGN KEY (role_id) REFERENCES Role(role_id)
);

CREATE TABLE Session
(
    session_id VARCHAR(255) NOT NULL,
    timestamp DATE NOT NULL,
    p_id INT NOT NULL,
    PRIMARY KEY (p_id),
    FOREIGN KEY (p_id) REFERENCES Person(p_id),
    UNIQUE (session_id)
);

CREATE TABLE Doctor
(
    fees INT NOT NULL,
    d_id INT NOT NULL,
    PRIMARY KEY (d_id),
    FOREIGN KEY (d_id) REFERENCES Person(p_id)
);

CREATE TABLE Appointment
(
    a_id INT NOT NULL,
    a_date_time DATE NOT NULL,
    duration INT NOT NULL,
    timestamp DATE NOT NULL,
    doctor_accept CHAR(1) NOT NULL,
    patient_id INT NOT NULL,
    i_id INT NOT NULL,
    doctor_id INT NOT NULL,
    PRIMARY KEY (a_id),
    FOREIGN KEY (patient_id) REFERENCES Person(p_id),
    FOREIGN KEY (i_id) REFERENCES Illness(i_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctor(d_id)
);

CREATE TABLE Prescription
(
    description VARCHAR(255) NOT NULL,
    course_duration INT NOT NULL,
    timestamp DATE NOT NULL,
    presc_id INT NOT NULL,
    a_id INT NOT NULL,
    PRIMARY KEY (presc_id),
    FOREIGN KEY (a_id) REFERENCES Appointment(a_id),
    UNIQUE (a_id)
);

CREATE TABLE Doctor_Speciality
(
    sp_id INT NOT NULL,
    d_id INT NOT NULL,
    PRIMARY KEY (sp_id, d_id),
    FOREIGN KEY (sp_id) REFERENCES Specialization(sp_id),
    FOREIGN KEY (d_id) REFERENCES Doctor(d_id)
);

CREATE TABLE Prescribed_Medicine
(
    usage VARCHAR(255) NOT NULL,
    m_id INT NOT NULL,
    presc_id INT NOT NULL,
    PRIMARY KEY (m_id, presc_id),
    FOREIGN KEY (m_id) REFERENCES Medicine(m_id),
    FOREIGN KEY (presc_id) REFERENCES Prescription(presc_id)
);

CREATE TABLE Prescribed_Lab_Tests
(
    lt_id INT NOT NULL,
    presc_id INT NOT NULL,
    PRIMARY KEY (lt_id, presc_id),
    FOREIGN KEY (lt_id) REFERENCES Lab_Tests(lt_id),
    FOREIGN KEY (presc_id) REFERENCES Prescription(presc_id)
);