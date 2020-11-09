create table person
(
    p_id  int primary key auto_increment,
    name  varchar(50),
    email varchar(50),
    phone int(12),
    dob   date
);

create table role
(
    role_id   int primary key auto_increment,
    role_name varchar(20)
);

create table person_type
(
    p_id   int references person (p_id),
    p_type int references role (role_id),
    unique (p_id, p_type)
);

create table authentication
(
    p_id        int references person (p_id),
    username    varchar(20),
    password    varchar(50),
    session_id  varchar(32),
    session_end datetime,
    primary key (p_id)
);

create table specialization
(
    sp_id   int primary key auto_increment,
    sp_name varchar(50),
    keywords varchar(100)
);

create table doctor
(
    p_id          int references person (p_id),
    service_start date,
    suspended     int(1),
    primary key (p_id)
);

create table doctor_speciality
(
    p_id       int references doctor (p_id),
    speciality int references specialization (sp_id),
    unique (p_id, speciality)
);

create table illness
(
    i_id        int primary key auto_increment,
    p_id        int references person (p_id),
    title       varchar(40),
    description varchar(255),
    timestamp   datetime default systimestamp
);

create table appointment
(
    a_id          int primary key auto_increment,
    p_id          int references person (p_id),
    d_id          int references doctor (p_id),
    i_id          int references illness (i_id),
    a_time        datetime,
    duration      int(2),
    d_accept      int(1),
    share_history int(1),
    timestamp     datetime default systimestamp
);

create table prescription
(
    pr_id           int primary key auto_increment,
    a_id            int references appointment (a_id),
    description     varchar(255),
    course_duration int(3), //duration this prescription would be in effective state in days
    timestamp       datetime default systimestamp
);

create table feedback
(
    f_id        int primary key auto_increment,
    a_id        int references appointment (a_id),
    rating      int(1),
    description varchar(255),
    timestamp   datetime default systimestamp
);

create table doctor_leave
(
    dl_id    int primary key auto_increment,
    p_id     int references doctor (p_id),
    off_date date,
    unique (p_id, off_date)
);

create table doctor_commission
(
    dc_id int primary key auto_increment,
    p_id int references doctor(p_id),
    fees int(5) //Fees per unit of time (unit of time has to be defined based on situation)
);

create table people_wallet
(
    p_id int references person(p_id),
    balance int(5),
    primary key(p_id)
);

insert into role
values (null, 'USER');
insert into role
values (null, 'ADMIN');
insert into role
values (null, 'DOCTOR');

insert into specialization (sp_id,sp_name,keywords)
values (null,'Physician', 'general,glu,cold,fever,family,normal' );
insert into specialization (sp_id,sp_name,keywords)
values ( null, 'Pediatrician', 'young,children,child,baby,cough,cold,stomach,flu' );
insert into specialization (sp_id,sp_name,keywords)
values ( null, 'Gynecologist', 'women,lady,preventive,care,pregnancy,delivery,reproduction,reproductive,hormone,fertility' );
insert into specialization (sp_id,sp_name,keywords)
values ( null, 'Surgeon', 'surgery,operation' );
insert into specialization (sp_id,sp_name,keywords)
values ( null, 'Psychiatrist', 'mental,psycho,emotion,behavior,addiction,depress,depression' );
insert into specialization (sp_id,sp_name,keywords)
values ( null, 'Cardiologist', 'heart,blood,bp,blood,pressure,blood pressure,vessels' );
insert into specialization (sp_id,sp_name,keywords)
values ( null, 'Dermatologist', 'laser,modern,botox' );
insert into specialization (sp_id,sp_name,keywords)
values ( null, 'Endocrinologist', 'gland,glands,hormone,diabetes,sugar,thyroid' );
insert into specialization (sp_id,sp_name,keywords)
values ( null, 'Gastroenterologist', 'gastric,digest,digestion,food,acid,acidity');
insert into specialization (sp_id,sp_name,keywords)
values ( null, 'Neurologist', 'brain,spine,nerve,nerves,migraine,headache,dizziness' );
insert into specialization (sp_id,sp_name,keywords)
values ( null, 'Anesthesiologist', 'pain,surgery,anesthesia' );
insert into specialization (sp_id,sp_name,keywords)
values ( null,  'Orthopedist', 'joints,muscles,bones,orthopedist');
insert into specialization (sp_id,sp_name,keywords)
values ( null, 'Radiologist', 'mri,ct,ctscan,scan,xray,x-ray,ct-scan,pet' );

insert into person
values ( null, 'admin', 'admin@email.com', 9876543210, parsedatetime('01-01-2020','dd-MM-yyyy'));
insert into person_type
values ( select p_id from person where name = 'admin', select role_id from role where role_name = 'ADMIN');
insert into authentication
values ( select p_id from person where name = 'admin', 'admin', 'admin', null, now());
