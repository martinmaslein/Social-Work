DROP DATABASE IF EXISTS sistema;

CREATE DATABASE IF NOT EXISTS sistema;

USE sistema;

CREATE TABLE IF NOT EXISTS Empleado(
	legajo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    apellido VARCHAR(128) NOT NULL, 
    nombre VARCHAR(128) NOT NULL, 
    direccion VARCHAR(128) NOT NULL, 
    telefono VARCHAR(128) NOT NULL, 
    nro_doc INT UNSIGNED NOT NULL,
    fecha_nac DATE NOT NULL,
    correo VARCHAR(128) NOT NULL, 
    password CHAR(32) NOT NULL,
    PRIMARY KEY(legajo)
)ENGINE=INNODB;


CREATE TABLE IF NOT EXISTS Cliente(
    usuario VARCHAR(128) NOT NULL,
	nro_cliente SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    password CHAR(32) NOT NULL,
    apellido VARCHAR(128) NOT NULL, 
    nombre VARCHAR(128) NOT NULL, 
    fecha_nac DATE NOT NULL,
    direccion VARCHAR(128) NOT NULL,
    telefono VARCHAR(128) NOT NULL,
    correo VARCHAR(128) NOT NULL, 
    nro_doc INT UNSIGNED NOT NULL,
    PRIMARY KEY(nro_cliente)
)ENGINE=INNODB;

/*falta cambiar*/
CREATE TABLE IF NOT EXISTS Administrador(
	legajo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    apellido VARCHAR(128) NOT NULL, 
    nombre VARCHAR(128) NOT NULL, 
    tipo_doc VARCHAR(20) NOT NULL, 
    direccion VARCHAR(128) NOT NULL, 
    telefono VARCHAR(128) NOT NULL, 
    nro_doc INT UNSIGNED NOT NULL,
    password VARCHAR(32) NOT NULL,
    PRIMARY KEY(legajo)
)ENGINE=INNODB;

#--------------------------------------------------------------------------------------
/*falta cambiar*/
DROP USER 'admin'@'localhost';
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON sistema.* TO 'admin'@'localhost' WITH GRANT OPTION;
    
DROP USER 'empleado'@'%';
CREATE USER 'empleado'@'%' IDENTIFIED BY 'empleado';
GRANT SELECT ON sistema.empleado TO 'empleado'@'%';
GRANT SELECT,UPDATE,INSERT ON sistema.Cliente TO 'empleado'@'%';  

DROP USER 'cliente'@'%';
CREATE USER 'cliente'@'localhost' IDENTIFIED BY 'cliente';
GRANT ALL PRIVILEGES ON sistema.* TO 'cliente'@'localhost' WITH GRANT OPTION;
GRANT CREATE USER ON *.* TO 'cliente'@'localhost';

SELECT legajo,password FROM Empleado;


#------------------------------CARGA DE DATOS-------------------------------------#

#--------------Cliente (nro_cliente, password,apellido, nombre, fecha_nac, tipo_doc, direccion, telefono, corero,nro_doc, )-------------#
INSERT INTO Cliente VALUES ("josecito",01,md5('asd'),"Lopez", "Jorge", "1980/03/05", "Sarmiento 245", "2915667893", "jorgelop33@gmail.com",34567892);
INSERT INTO Cliente VALUES ("jorgito",02,md5('KDWOE4'),"Perez", "Guillermo", "2000/10/10","Alem 3590", "2917856343", "guillermop45@gmail.com",42189488);
INSERT INTO Cliente VALUES ("quimeycita",03,md5('GHJKDW67'),"Arena", "Camila", "1990/07/23","Viamonte 201", "0114567892", "camiarena@gmail.com",20678945);
INSERT INTO Cliente VALUES ("tinchito",04,md5('78DEHI8'),"Torres", "Sofia", "1986/05/09", "Terrada 50", "2916735489", "sofitorres@gmail.com",45678936);
INSERT INTO Cliente VALUES ("didicito",05,md5('4567UNHJ'),"Rodriguez", "Matias", "1970/12/12","Gorriti 302", "0116578635", "matirod78@gmail.com",26783645);

#--------------Empleado (legajo, apellido, nombre, edad, direccion, telefono, nro_doc, fecha_nac, correo, password)-------------#
INSERT INTO Empleado VALUES ("Anita",121943,"Maslein", "Ana", "Salliquelo 2009","0112377489", 44567789,"1999/11/05" , "anamaslein@gmail.com",md5('Cl123'));
INSERT INTO Empleado VALUES ("Leonardito",108976,"Hughes", "Leonardo", "Colon 230", "0117822983", 42556333, "1945/09/09","leohughes@gmail.com",md5('456GHDJ'));
INSERT INTO Empleado VALUES ("Pedrito",89027,"Gonzalo", "Pedro", "DNI", "Roca 1500", "291674523", 10287364, "1989/03/02","gonzalopedro@yahoo.com", md5('AGJEU64'));
INSERT INTO Empleado VALUES ("Rochi",134579,"Villafaña", "Rocio", "DNI", "Tucuman 567", "296786543", 32897654, "2002/07/01","rovillafania@gmail.com", md5('F56892'));
INSERT INTO Empleado VALUES ("Selenita",126667,"Alvarez", "Selene", "DNI", "Viamonte 120", "298765432", 221987365, "2000/07/02","selenealv@gmail.com",md5('C66728'));

