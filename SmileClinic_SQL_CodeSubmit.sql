  drop database if exists SmileClinic;
create database SmileClinic ;
		use SmileClinic;
        

DROP TABLE IF EXISTS appointment ;

CREATE TABLE IF NOT EXISTS appointment (
  `Appointment_id` INT NOT NULL,
  `Appointment_Date` DATE NOT NULL,
  `Appointment_Time` TIME NOT NULL,
  PRIMARY KEY (`Appointment_id`));
  INSERT INTO `appointment` VALUES (1,'2023-01-10','09:00:00'),(2,'2023-01-10','10:30:00'),(3,'2023-01-11','11:00:00'),(4,'2023-01-11','14:00:00'),(5,'2023-01-12','09:30:00'),(6,'2023-01-12','15:00:00'),(7,'2023-01-13','10:00:00'),(8,'2023-01-13','16:00:00'),(9,'2023-01-14','09:15:00'),(10,'2023-01-14','15:45:00');
  select*from appointment;



DROP TABLE IF EXISTS dentist;

CREATE TABLE IF NOT EXISTS dentist (
  `Dentist_id` CHAR(9) NOT NULL,
  `Dentist_Name` VARCHAR(45) NOT NULL,
  `Dentist_Birthday` DATE NOT NULL,
  `Dentist_Email` VARCHAR(70) NOT NULL,
  `Dentist_phoneNum` VARCHAR(10) NOT NULL,
  `Dentist_Address` VARCHAR(45) NOT NULL,
  `Dentist_specialization` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`Dentist_id`));
  INSERT INTO `dentist` VALUES ('111111111','Dr. Saleh AlSheeby','1975-04-12','Saleh@gmail.com','0596000100','Ramallah','Orthodontics'),('222222222','Dr. Huda Karazon','1980-08-25','Hudah@gmail.com','02-2963493','Ramallah','orthodontics'),('333333333','Dr. Ahed Kadamani','1985-12-30','Ahedwhite@gmail.com','0569000100','Ramallah','Prosthetics and Cosmetics Dentistry'),('444444444','Dr. kifah shuaib','1978-03-05','kifah@gmail.com','0569000100','Ramallah','Dental implant'),('555555555','Dr. Ahamd shadied','1982-06-17','Ahamd@gmail.com','0569000100','Ramallah','Cosmetic Dentistry'),('666666666','Dr. Osama Bder','1990-01-22','Osama@gmail.com','02-2963493','Ramallah','Endodontics-Neurologist'),('777777777','Dr. Dima Hummus','1992-02-12','Dima@gmail.com','02-2963493','Ramallah','Whitening'),('888888888','Dr. Rawand Rifai','1999-03-03','Rawand@gmail.com','02-2963493','Ramallah','Whitening'),('999999999','Dr. Shorouq Abu Zeina','1988-05-19','Shorouq@gmail.com','02-2963493','Ramallah','Endodontics-Neurologist');
  select*from dentist;


DROP TABLE IF EXISTS equipments;

CREATE TABLE IF NOT EXISTS equipments (
  `Equipment_id` INT NOT NULL,
  `Equipment_Name` VARCHAR(100) NOT NULL,
  `Equipment_Description` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`Equipment_id`));

INSERT INTO `equipments` VALUES (1,'Dental Chair','Adjustable chair used for patient seating during dental procedures.'),(2,'X-ray Machine','Used for taking radiographs of patients’ teeth and jaw.'),(3,'Ultrasonic Cleaner','Device for cleaning dental instruments using high-frequency sound waves.'),(4,'Sterilizer','Machine used to sterilize dental instruments and supplies.'),(5,'Dental Drill','High-speed drill used for cavity preparation and shaping tooth structure.'),(6,'Suction Device','Used to remove saliva, blood, and other debris from the patient’s mouth during procedures.'),(7,'Dental Laser','Used for various soft tissue procedures and teeth whitening.'),(8,'Air Compressor','Supplies compressed air for various dental tools and equipment.'),(9,'Scaler','Hand-held tool used for removing tartar and plaque from teeth.'),(10,'Mouth Mirror','Small mirror attached to a metal handle, used to view hard-to-see areas of the mouth.');
select*from equipments;


DROP TABLE IF EXISTS dentist_has;
CREATE TABLE IF NOT EXISTS dentist_has (
  `Dentist_id` CHAR(9) NOT NULL,
  `Dentist_Name` VARCHAR(45) NOT NULL,
  `Dentist_Birthday` DATE NOT NULL,
  `Dentist_Email` VARCHAR(70) NOT NULL,
  `Dentist_phoneNum` VARCHAR(10) NOT NULL,
  `Dentist_Address` VARCHAR(45) NOT NULL,
  `Dentist_specialization` VARCHAR(70) NOT NULL,
  `Equipment_id_has` INT NOT NULL,
  PRIMARY KEY (`Dentist_id`),
  INDEX `FK1_idx` (`Equipment_id_has` ASC),
  CONSTRAINT `FK1`
    FOREIGN KEY (`Equipment_id_has`)
    REFERENCES equipments(`Equipment_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO `dentist_has` VALUES ('111111111','Dr. Saleh Alsheeby','1975-04-12','Saleh@gmail.com','0596000100','Ramallah','Orthodontics',1),('222222222','Dr. Huda Karazon','1980-08-25','Hudah@gmail.com','02-2963493','Ramallah','orthodontics',10);
select*from dentist_has;


DROP TABLE IF EXISTS patient;

CREATE TABLE IF NOT EXISTS patient(
  `Patient_id` CHAR(9) NOT NULL,
  `Patient_name` VARCHAR(45) NOT NULL,
  `Patient_Gender` VARCHAR(10) NOT NULL,
  `Patient_PhoneNum` VARCHAR(10) NOT NULL,
  `Birthday` DATE NOT NULL,
  `Patient_Email` VARCHAR(70) NOT NULL,
  `Patient_LastVisit_Date` DATE NULL DEFAULT NULL,
  `Patient_Insurance` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Patient_id`));
INSERT INTO `patient` VALUES ('123456789','Maryam','Female','0595959595','2003-01-23','1200837@student.birzeit.edu',NULL,'Globement'),('213456789','Leena','Female','0500000000','2002-12-26','1200335@student.birzeit.edu','2023-02-25','NetHealth'),('312456789','Dalia','Female','0511122222','2002-05-05','1211610@student.birzeit.edu','2023-07-05',NULL),('412356789','Ahmad','Male','0533366555','2000-01-22','Ahmad.a@gmail.com','2023-09-09','Alalmeya'),('512346789','Omar','Male','0569696996','1990-12-12','Omar.m@gmail.com',NULL,'NetHealth'),('612345789','Khaled','Male','0595959595','1983-11-22','Khaled.t@gmail.com','2023-12-12','Globement'),('212121211','neew','Female','0500000000','2002-12-26','1200335@student.birzeit.edu','2023-02-25','NetHealth'),('712345689','Rania','Female','0599667675','1985-07-03','Rania@gmail.com',NULL,NULL);
select*from patient;


DROP TABLE IF EXISTS dentist_patient_appointment;

CREATE TABLE IF NOT EXISTS dentist_patient_appointment (
  
  
  `Appointment_id` INT NOT NULL,
  `Appointment_Date` DATE NOT NULL,
  `Appointment_Time` TIME NOT NULL,
  `Patient_id` CHAR(9) NOT NULL,
  `Patient_name` VARCHAR(45) NOT NULL,
  `Dentist_id` CHAR(9) NOT NULL,
  `Dentist_Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Dentist_id`, `Patient_id`, `Appointment_id`),
  INDEX `fk_dentist_has_patient_patient1_idx` (`Patient_id` ASC),
  INDEX `fk_dentist_has_patient_dentist1_idx` (`Dentist_id` ASC),
 
  
    FOREIGN KEY (`Dentist_id`) REFERENCES dentist (`Dentist_id`),
  CONSTRAINT `fk_dentist_has_patient_patient1`
   FOREIGN KEY (`Patient_id`) REFERENCES patient (`Patient_id`));
INSERT INTO `dentist_patient_appointment` VALUES (1,'2023-01-10','09:00:00','123456789','Maryam','111111111','Dr. Saleh Alsheeby'),(2,'2023-01-10','10:30:00','213456789','Leena','222222222','Dr. Huda Karazon'),(3,'2023-01-11','11:00:00','312456789','Dala','333333333','Dr. Ahed Kadamani'),(4,'2023-01-11','14:00:00','412356789','Ahmad','444444444','Dr. kifah shuaibi'),(5,'2023-01-12','09:30:00','512346789','Omar','555555555','Dr. Ahmad shaded'),(6,'2023-01-12','15:00:00','612345789','Khaled','666666666','Dr. Osama Bder'),(7,'2023-01-13','10:00:00','712345689','Rania','777777777','Dr. Dima Hummus'),(8,'2024-01-23','10:00:00','213456789','Leena','111111111','Dr. Saleh Alsheeby'),(9,'2024-01-23','10:15:00','412356789','Ahmad','222222222','Dr. Huda Karazon'),(10,'2024-01-12','09:00:00','512346789','Omar','777777777','Dr. Dima Hummus');
select*from dentist_patient_appointment;


DROP TABLE IF EXISTS dentist_treatment_patient ;

CREATE TABLE IF NOT EXISTS dentist_treatment_patient(
  `Patient_id` CHAR(9) NOT NULL,
  `Patient_name` VARCHAR(45) NOT NULL,
  `Dentist_id` CHAR(9) NOT NULL,
  `Dentist_Name` VARCHAR(45) NOT NULL,
  `Treatment_Name` VARCHAR(45) NOT NULL,
  `Treatment_Cost` DOUBLE NOT NULL,
  `The_Amount_Paid` DOUBLE NOT NULL,
  `Treatment_Description` VARCHAR(100) NULL DEFAULT NULL,
  
 
  PRIMARY KEY (`Patient_id`, `Dentist_id`),
  INDEX `FKP_idx` (`Patient_id` ASC),
  INDEX `fk_dentist_treatment_patient_dentist1_idx` (`Dentist_id` ASC),
  CONSTRAINT `fk_dentist_treatment_patient_dentist1`
    FOREIGN KEY (`Dentist_id`) REFERENCES dentist (`Dentist_id`),
  CONSTRAINT `FKFOR_D`
    FOREIGN KEY (`Patient_id`) REFERENCES patient(`Patient_id`)
    ON DELETE CASCADE);
INSERT INTO `dentist_treatment_patient` (
  `Patient_id`, 
  `Patient_name`, 
  `Dentist_id`, 
  `Dentist_Name`, 
  `Treatment_Name`, 
  `Treatment_Cost`, 
  `The_Amount_Paid`, 
  `Treatment_Description`
) VALUES 
('123456789', 'Maryam', '111111111', 'Dr. Saleh AlSheeby',  'Teeth Cleaning', 100.00, 80.00, 'Routine dental cleaning to remove plaque and tartar.'),
('213456789', 'Leena', '222222222', 'Dr. Huda Karazon',  'Cavity Filling', 150.00, 100.00, 'Filling a dental cavity with tooth-colored resin.'),
('312456789', 'Dalia', '333333333', 'Dr. Ahed Kadamani',  'Root Canal Therapy', 500.00, 500.00, 'Treatment of tooth pulp and root infections.'),
('412356789', 'Ahmad', '444444444', 'Dr. kifah shuaib',  'Dental Crown', 800.00, 500.00, 'A crown to cover and protect a damaged tooth.'),
('123456789', 'Maryam', '555555555', 'Dr. Ahamd shadied',  'Teeth Whitening', 250.00, 250.00, 'Professional bleaching to whiten teeth.'),
('612345789', 'Khaled', '666666666', 'Dr. Osama Bder',  'Dental Implants', 3000.00, 1000.00, 'Surgical implant to replace a missing tooth.'),
('712345689', 'Rania', '111111111', 'Dr. Saleh AlSheeby', 'Orthodontic Braces', 4000.00, 2000.00, 'Braces to correct teeth alignment.');
select*from dentist_treatment_patient;


DROP TABLE IF EXISTS handles;

CREATE TABLE IF NOT EXISTS handles (
  `Patient_id_H` CHAR(9) NOT NULL,
  `Dentist_id_H` CHAR(9) NOT NULL,
  PRIMARY KEY (`Patient_id_H`, `Dentist_id_H`),
  INDEX `FK2_idx` (`Dentist_id_H` ASC),
  CONSTRAINT `FK2`
    FOREIGN KEY (`Dentist_id_H`) REFERENCES dentist(`Dentist_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK3`
    FOREIGN KEY (`Patient_id_H`) REFERENCES patient (`Patient_id`) ON DELETE CASCADE
    ON UPDATE CASCADE);
INSERT INTO `handles` VALUES ('123456789','111111111'),('213456789','222222222'),('312456789','333333333'),('412356789','444444444'),('512346789','555555555'),('612345789','666666666'),('712345689','777777777');
select*from handles;


DROP TABLE IF EXISTS medical_state ;

CREATE TABLE IF NOT EXISTS medical_state (
  `Patient_id` CHAR(9) NOT NULL,
  `Patient_name` VARCHAR(45) NOT NULL,
  `M_medical_state` CHAR(100) NOT NULL,
  `Last_visited_date` DATE NOT NULL,
  
  PRIMARY KEY (`M_medical_state`,`Patient_id`),
  INDEX `FKMedicalState_idx` (`Patient_id` ASC),
  CONSTRAINT `FKMedicalState`
    FOREIGN KEY (`Patient_id`) REFERENCES patient (`Patient_id`)on delete cascade);

INSERT INTO `medical_state` (`Patient_id`, `Patient_name`, `M_medical_state`, `Last_visited_date`) 
VALUES 
('123456789', 'Maryam', 'Allergies', '2023-01-23'),
('123456789', 'Maryam', 'Cancer', '2023-01-23'),
('213456789', 'Leena', 'Cancer', '2023-02-25'),
('712345689', 'Rania', 'Pregnancy', '2023-12-12');
select*from medical_state;

