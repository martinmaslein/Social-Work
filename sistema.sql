DROP DATABASE IF EXISTS sistema;

CREATE DATABASE IF NOT EXISTS sistema;

USE sistema;

CREATE TABLE IF NOT EXISTS Administrador(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username CHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    PRIMARY KEY(id)
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Empleado(
	legajo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username CHAR(32) NOT NULL,
    password CHAR(32) NOT NULL,
    apellido VARCHAR(128) NOT NULL, 
    nombre VARCHAR(128) NOT NULL, 
    direccion VARCHAR(128) NOT NULL, 
    telefono VARCHAR(128) NOT NULL, 
    fecha_nac VARCHAR(128) NOT NULL,
    correo VARCHAR(128) NOT NULL, 
    nro_doc INT UNSIGNED NOT NULL,
    PRIMARY KEY(legajo)
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Plan(
	nro_plan INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nombre CHAR(32),
    reintegro DECIMAL(5,2),
    CONSTRAINT pk_Plan PRIMARY KEY(nro_plan)
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Cliente(
	nro_cliente INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username CHAR(32) NOT NULL,
    password CHAR(32) NOT NULL,
    apellido VARCHAR(128) NOT NULL, 
    nombre VARCHAR(128) NOT NULL, 
    fecha_nac VARCHAR(128) NOT NULL,
    direccion VARCHAR(128) NOT NULL,
    telefono VARCHAR(128) NOT NULL,
    correo VARCHAR(128) NOT NULL, 
    nro_doc INT UNSIGNED NOT NULL,
    nro_plan INT UNSIGNED NOT NULL,
    CONSTRAINT pk_Cliente PRIMARY KEY(nro_cliente),

    CONSTRAINT FK_Cliente_Plan
    FOREIGN KEY (nro_plan) REFERENCES Plan(nro_plan)
    ON DELETE RESTRICT ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Familiar(
    nro_familiar INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nro_cliente INT  UNSIGNED,
    apellido VARCHAR(128) NOT NULL, 
    nombre VARCHAR(128) NOT NULL, 
    fecha_nac VARCHAR(128) NOT NULL,
    direccion VARCHAR(128) NOT NULL,
    telefono VARCHAR(128) NOT NULL,
    CONSTRAINT pk_Familiar PRIMARY KEY(nro_familiar),

    CONSTRAINT FK_Familiar_Cliente
    FOREIGN KEY (nro_cliente) REFERENCES Cliente(nro_cliente)
        ON DELETE RESTRICT ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Servicio(
    nro_servicio INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nombre CHAR(32),
    CONSTRAINT pk_Servicio PRIMARY KEY(nro_servicio)
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Servicio_plan(
    id_servicio_plan INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nro_servicio INT UNSIGNED,
    nro_plan INT UNSIGNED,
    CONSTRAINT pk_Servicio_plan PRIMARY KEY(id_servicio_plan),
    FOREIGN KEY (nro_servicio) REFERENCES Servicio(nro_servicio)
        ON DELETE RESTRICT ON UPDATE CASCADE,
     FOREIGN KEY (nro_plan) REFERENCES Plan(nro_plan)
        ON DELETE RESTRICT ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Solicitud(
    id_solicitud INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nro_cliente INT UNSIGNED,
    nro_plan INT UNSIGNED,
    CONSTRAINT pk_Solicitud PRIMARY KEY(id_solicitud),
    FOREIGN KEY (nro_cliente) REFERENCES Cliente(nro_cliente)
        ON DELETE CASCADE ON UPDATE CASCADE,
     FOREIGN KEY (nro_plan) REFERENCES Plan(nro_plan)
        ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=INNODB;

#--------------------------------------------------------------------------------------
DROP USER 'admin'@'localhost';
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON sistema.* TO 'admin'@'localhost' WITH GRANT OPTION;
    
DROP USER 'empleado'@'%';
CREATE USER 'empleado'@'%' IDENTIFIED BY 'empleado';
GRANT SELECT ON sistema.empleado TO 'empleado'@'%';
GRANT SELECT,UPDATE,INSERT ON sistema.empleado TO 'empleado'@'%'; 
GRANT SELECT,UPDATE,INSERT ON sistema.cliente TO 'empleado'@'%';  

DROP USER 'cliente'@'%';
CREATE USER 'cliente'@'%' IDENTIFIED BY 'cliente';
GRANT SELECT ON sistema.cliente TO 'cliente'@'%';
GRANT SELECT,UPDATE,INSERT ON sistema.cliente TO 'cliente'@'%';  

#------------------------------CARGA DE DATOS-------------------------------------#

#--------------Empleado (legajo, username, password ,apellido, nombre, direccion, telefono, fecha_nac, correo,nro_doc)-------------#
INSERT INTO Empleado VALUES (1,'usuario1',md5('contraseña1'),"Lopez","Jorge","Sarmiento 245","2915667893","1980/03/05","jorgelop33@gmail.com",34567892);
INSERT INTO Empleado VALUES (2,'usuario2',md5('contraseña2'),"Perez","Guillermo","Alem 3590","2917856343","2000/10/10","guillermop45@gmail.com",42189488);
INSERT INTO Empleado VALUES (3,'usuario3',md5('contraseña3'),"Arena","Camila","Viamonte 201","0114567892","1990/07/23","camiarena@gmail.com",20678945);
INSERT INTO Empleado VALUES (4,'usuario4',md5('contraseña4'),"Torres","Sofia","Terrada 50","2916735489","1986/05/09","sofitorres@gmail.com",45678936);
INSERT INTO Empleado VALUES (5,'usuario5',md5('contraseña5'),"Rodriguez","Matias","Gorriti 302","0116578635","1970/12/12","matirod78@gmail.com",26783645);

#--Plan (nro_plan,nombre,reintegro)----#
INSERT INTO Plan VALUES (1,"A",70.05);
INSERT INTO Plan VALUES (2,"B",20.95);

#--------------Cliente (nro_cliente, username, password, apellido, nombre, fecha_nac, direccion, telefono, correo, nro_doc, nro_plan )-------------#
INSERT INTO Cliente VALUES (1,'cliente1',md5('contraseña1'),"Lopez","Jorge","1980/03/05","Sarmiento 245","2915667893","jorgelop33@gmail.com",34567892,1);
INSERT INTO Cliente VALUES (2,'cliente2',md5('contraseña2'),"Perez","Guillermo","2000/10/10","Alem 3590","2917856343","guillermop45@gmail.com",42189488,2);
INSERT INTO Cliente VALUES (3,'cliente3',md5('contraseña3'),"Arena","Camila","1990/07/23","Viamonte 201","0114567892","camiarena@gmail.com",20678945,2);
INSERT INTO Cliente VALUES (4,'cliente4',md5('contraseña4'),"Torres","Sofia","1986/05/09","Terrada 50","2916735489","sofitorres@gmail.com",45678936,2);
INSERT INTO Cliente VALUES (5,'cliente5',md5('contraseña5'),"Rodriguez","Matias","1970/12/12","Gorriti 302","0116578635","matirod78@gmail.com",26783645,1);

#------ Familiar (nro_familiar,nro_cliente,apellido, nombre, fecha_nac, direccion, telefono) --------------------#
INSERT INTO Familiar VALUES (1,1,"Lopez","Joan","1990/08/11","Gorriti 501",2916663748);
INSERT INTO Familiar VALUES (2,1,"Lopez","Valentina","1970/05/12","San Juan 200",2916663748);
INSERT INTO Familiar VALUES (3,2,"Perez","Teresa","2000/04/09","Alem 3800",2916663748);
INSERT INTO Familiar VALUES (4,3,"Arena","Manuel","2001/01/05","Zapiola 50",2916663748);
INSERT INTO Familiar VALUES (5,4,"Torres","Nicolas","2002/12/04","Sarmiento 101",2916663748);

#-----Servicio (nro_servicio,nombre)
INSERT INTO Servicio VALUES (1,"Dentista");
INSERT INTO Servicio VALUES (2,"Oftalmologo");
INSERT INTO Servicio VALUES (3,"Medico Clinico");

#----servicio_plan (id_servicio_plan, nro_servicio, nro_plan)
INSERT INTO Servicio_plan VALUES (1,1,1);
INSERT INTO Servicio_plan VALUES (2,2,1);
INSERT INTO Servicio_plan VALUES (3,3,2);

#---Administrador (id,username,password)
INSERT INTO Administrador VALUES (1,"admin",md5('admin'));