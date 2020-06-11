--Derby does not support DROP TABLE IF EXISTS 
DROP TABLE DISPONIBILIDADEMPLEADO;
DROP TABLE VINCULACIONCONLAEMPRESA;
DROP TABLE ROLESENEMPRESA;
DROP TABLE TIPODEDISPONIBILIDAD;
DROP TABLE TIPODEVINCULACION;
DROP TABLE TIPODEROL;
DROP TABLE LINEAPEDIDO;
DROP TABLE PEDIDO;
DROP TABLE LINEACOMPRA;
DROP TABLE COMPRA;
DROP TABLE ESTADOPEDIDO;
DROP TABLE FACTURA;
DROP TABLE ESTADOFACTURA;
DROP TABLE PREFERENCIA;
DROP TABLE REFERENCIA;
DROP TABLE VINO;
DROP TABLE BODEGA;
DROP TABLE CATEGORIA;
DROP TABLE DENOMINACIONORIGEN;
DROP TABLE ABONADO;
DROP TABLE EMPLEADO;
DROP TABLE PERSONA;

-- Enum
create table TIPODEROL
(
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)
);

INSERT INTO TIPODEROL
VALUES  (1,'Almacen'),
        (2,'AtencionCliente'),
        (3,'Contabilidad');

-- Enum
create table TIPODEVINCULACION
(
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)

);

INSERT INTO TIPODEVINCULACION
VALUES  (1,'Contratado'),
        (2,'Despedido'),
        (3,'EnERTE');

-- Enum
create table TIPODEDISPONIBILIDAD
(
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)
);

INSERT INTO TIPODEDISPONIBILIDAD
VALUES  (1,'Vacaciones'),
        (2,'BajaTemporal'),
		(3, 'Trabajando');

-- Entity
CREATE TABLE PERSONA(
	Nif VARCHAR(9),
	Nombre VARCHAR(50) not null,
	Apellidos VARCHAR(50) not null,
	Direccion VARCHAR(50) not null,
	Telefono VARCHAR(50) not null,
	Email VARCHAR(50) not null,
	CuentaBancaria VARCHAR(30) not null,
	PRIMARY KEY(Nif)
);

-- Entity
create table EMPLEADO
(
	Nif VARCHAR(9),
	Password VARCHAR(20) not null,
	FechaInicioEnEmpresa DATE not null,
		PRIMARY KEY(Nif),
		FOREIGN KEY(Nif) REFERENCES PERSONA(Nif)
);

-- Association
create table ROLESENEMPRESA
(
	ComienzoEnRol DATE not null,
	Empleado VARCHAR(9) not null,
	Rol SMALLINT not null,
            FOREIGN KEY(Empleado) REFERENCES EMPLEADO(Nif),
            FOREIGN KEY(Rol) REFERENCES TIPODEROL(IdTipo)
);

-- Association
create table VINCULACIONCONLAEMPRESA
(
	inicio DATE not null,
	Empleado VARCHAR(9) not null,
	Vinculo SMALLINT not null,
		FOREIGN KEY(Empleado) REFERENCES EMPLEADO(Nif),
		FOREIGN KEY(Vinculo) REFERENCES TIPODEVINCULACION(IdTipo) 
);

-- Association
create table DISPONIBILIDADEMPLEADO
(
	Comienzo DATE not null,
	FinalPrevisto DATE,
	Empleado VARCHAR(9) not null,
	Disponibilidad SMALLINT not null,
		FOREIGN KEY(Empleado) REFERENCES EMPLEADO(Nif),
		FOREIGN KEY(Disponibilidad) REFERENCES TIPODEDISPONIBILIDAD(IdTipo)
);

-- Entity
CREATE TABLE ABONADO(
	NumeroAbonado INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	OpenIDref VARCHAR(50) unique,
	Nif VARCHAR(9) not null unique,
	PRIMARY KEY(NumeroAbonado),
	FOREIGN KEY(Nif) REFERENCES PERSONA(Nif)
);

-- Enum
CREATE TABLE ESTADOFACTURA(
	Id SMALLINT,
	Nombre VARCHAR(20) not null unique,
	PRIMARY KEY(id)
);

INSERT INTO ESTADOFACTURA
VALUES  (1,'emitida'),
 		(2,'vencida'),
 		(3,'pagada');

-- Entity
CREATE TABLE FACTURA(
	NumeroFactura INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	FechaEmision DATE,
	Importe REAL,
	Estado SMALLINT,
	FechaPago DATE,
	IdExtractoBancario VARCHAR(30),
	PRIMARY KEY(NumeroFactura),
	FOREIGN KEY(Estado) REFERENCES ESTADOFACTURA(Id)
);

-- Enum
CREATE TABLE CATEGORIA(
	Id SMALLINT,
	Nombre VARCHAR(20) not null unique,
	PRIMARY KEY(Id)
);

INSERT INTO CATEGORIA
VALUES  (1,'joven'), 
		(2,'crianza'), 
		(3,'reserva'), 
		(4,'gran reserva');

-- Entity
CREATE TABLE DENOMINACIONORIGEN(
	Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	Nombre VARCHAR(50) not null unique,
	PRIMARY KEY(Id)
);

-- Entity
CREATE TABLE PREFERENCIA(
	IdDenominacion INTEGER,
	Categoria SMALLINT,
	NumeroAbonado INTEGER,
	FOREIGN KEY(IdDenominacion) REFERENCES DENOMINACIONORIGEN(Id),
	FOREIGN KEY(Categoria) REFERENCES CATEGORIA(Id),
	FOREIGN KEY(NumeroAbonado) REFERENCES ABONADO(NumeroAbonado)
);

-- Entity
CREATE TABLE BODEGA(
	Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	Nombre VARCHAR(50) not null unique,
	CIF VARCHAR(9) not null unique,
	Direccion VARCHAR(50) not null,
	PRIMARY KEY(Id)
);

-- Entity
CREATE TABLE VINO(
	Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	NombreComercial VARCHAR(50) not null unique,
	Ano SMALLINT,
	Comentario VARCHAR(200),
	IdDenominacion INTEGER,
	Categoria SMALLINT,
	IdBodega INTEGER,
	PRIMARY KEY(Id),
	FOREIGN KEY(IdDenominacion) REFERENCES DENOMINACIONORIGEN(Id),
	FOREIGN KEY(Categoria) REFERENCES CATEGORIA(Id),
	FOREIGN KEY(IdBodega) REFERENCES BODEGA(Id)
);

-- Entity
CREATE TABLE REFERENCIA(
	Codigo INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	EsPorCajas CHAR(1),
	ContenidoEnCL SMALLINT,
	Precio REAL,
	Disponible CHAR(1),
	VinoId INTEGER,
	PRIMARY KEY(Codigo),
	FOREIGN KEY(VinoId) REFERENCES VINO(Id)
);

-- Entity
CREATE TABLE COMPRA(
	IdCompra INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	FechaInicioCompra DATE,
	RecibidaCompleta CHAR(1),
	FechaCompraCompletada DATE,
	Importe REAL,
	Pagada CHAR(1),
	FechaPago DATE,
	IdProveedor INTEGER not null,
	PRIMARY KEY(IdCompra),
	FOREIGN KEY (IdProveedor) REFERENCES BODEGA(Id)
);

-- Entity
CREATE TABLE LINEACOMPRA(
	Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	Unidades SMALLINT,
	Recibida CHAR(1),
	FechaRecepcion DATE,
	IdCompra INTEGER not null,
	CodigoReferencia INTEGER not null,
	PRIMARY KEY(Id),
	FOREIGN KEY(IdCompra) REFERENCES COMPRA(IdCompra),
	FOREIGN KEY(CodigoReferencia) REFERENCES REFERENCIA(Codigo)
);

-- Enum
CREATE TABLE ESTADOPEDIDO(
	Id SMALLINT,
	Nombre VARCHAR(20)not null unique,
	PRIMARY KEY(Id)
);

INSERT INTO ESTADOPEDIDO
VALUES (1,'pendiente'),
		(2,'tramitado'),
		(3,'completado'),
		(4,'servido'),
		(5,'facturado'),
		(6,'pagado');

-- Entity
CREATE TABLE PEDIDO(
	Numero INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	Estado SMALLINT,
	FechaRealizacion DATE,
	NotaEntrega VARCHAR(200),
	Importe REAL,
	FechaRecepcion DATE,
	FechaEntrega DATE,
	NumeroFactura INTEGER,
	NumeroAbonado INTEGER,
	PRIMARY KEY(Numero),
	FOREIGN KEY(Estado) REFERENCES ESTADOPEDIDO(Id),
	FOREIGN KEY(NumeroFactura) REFERENCES FACTURA(NumeroFactura),
	FOREIGN KEY(NumeroAbonado) REFERENCES ABONADO(NumeroAbonado)
);

-- Entity
CREATE TABLE LINEAPEDIDO(
	Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	Unidades INTEGER,
	Completada CHAR(1),
	CodigoReferencia INTEGER,
	NumeroPedido INTEGER,
	IdLineaCompra INTEGER,
	PRIMARY KEY(Id),
	FOREIGN KEY(CodigoReferencia) REFERENCES REFERENCIA(Codigo),
	FOREIGN KEY(NumeroPedido) REFERENCES PEDIDO(Numero),
	FOREIGN KEY(IdLineaCompra) REFERENCES LINEACOMPRA(Id)
);


INSERT INTO PERSONA VALUES (
	'123123','Pepe','Gonzalez','Calle Pepe','6786785', 
	'pepe@pepe.com','1234567890'
);

INSERT INTO EMPLEADO VALUES (
	'123123','password','2011-11-11'
);

INSERT INTO ROLESENEMPRESA VALUES (
	'2011-11-12','123123',3
);

INSERT INTO VINCULACIONCONLAEMPRESA VALUES(
	'2011-11-11','123123',1

);

INSERT INTO DISPONIBILIDADEMPLEADO VALUES (
	'2011-11-11','2011-11-13','123123',3
);

INSERT INTO PERSONA VALUES (
	'456456', 'Sara', 'Rodriguez', 'Calle Bla Bla', '1111111', 
	'holasoysara@gmail.com', '123456789078876'
);

INSERT INTO EMPLEADO VALUES (
	'456456', 'pass', '2003-11-11'
);

INSERT INTO ROLESENEMPRESA VALUES (
	'2003-11-11', '456456', 2
);

INSERT INTO VINCULACIONCONLAEMPRESA VALUES(
	'2003-11-11', '456456', 1

);

INSERT INTO DISPONIBILIDADEMPLEADO VALUES(
	'2003-11-11', '2023-11-11', '456456', 3
);

INSERT INTO PERSONA VALUES (
	'987987', 'Jose', 'Vinos', 'Calle Proveer', '1111111', 
	'ok@gmail.com', '123489078876'
);

INSERT INTO PERSONA VALUES (
	'121212', 'Maite', 'Garcia', 'Calle Proveer', '1111111', 
	'maite@gmail.com', '123489078876'
);

INSERT INTO EMPLEADO VALUES (
	'987987', 'perro', '2009-09-02'
);

INSERT INTO ROLESENEMPRESA VALUES (
	'2009-09-02', '987987', 1
);

INSERT INTO VINCULACIONCONLAEMPRESA VALUES(
	'2009-09-02', '987987', 1

);

INSERT INTO DISPONIBILIDADEMPLEADO VALUES(
	'2009-09-02', '2029-09-02', '987987', 3
);


INSERT INTO FACTURA(FechaEmision, Importe, Estado, FechaPago, IdExtractoBancario) VALUES (
	'2020-05-05', 13.0, 2, NULL, '879878'
);

INSERT INTO FACTURA(FechaEmision, Importe, Estado, FechaPago, IdExtractoBancario) VALUES (
	'2020-04-29', 130.0, 2, NULL, '879878'
);

INSERT INTO FACTURA(FechaEmision, Importe, Estado, FechaPago, IdExtractoBancario) VALUES (
	'2020-06-06', 13.0, 1, NULL, '879878'
);

INSERT INTO ABONADO(OpenIDref, Nif) VALUES(
        '2', '121212'
); 

INSERT INTO ABONADO(OpenIDref, Nif) VALUES(
        '1', '123123'
); 

INSERT INTO PEDIDO(Estado, FechaRealizacion, NotaEntrega, Importe, FechaRecepcion, FechaEntrega, NumeroFactura, NumeroAbonado) VALUES (
        5, '2020-04-05', 'Nota de entrega del pedido 1', 5.0, '2020-05-09', '2020-05-10', 1, 1
);

INSERT INTO PEDIDO(Estado, FechaRealizacion, NotaEntrega, Importe, FechaRecepcion, FechaEntrega, NumeroFactura, NumeroAbonado) VALUES (
        5, '2020-04-05', 'Nota de entrega del pedido 2', 7.0, '2020-04-09', '2020-04-10', 1, 1
);

INSERT INTO PEDIDO(Estado, FechaRealizacion, NotaEntrega, Importe, FechaRecepcion, FechaEntrega, NumeroFactura, NumeroAbonado) VALUES (
        5, '2020-04-05', 'Nota de entrega del pedido 3', 5.0, '2020-04-09', '2020-04-10', 1, 1
);

INSERT INTO PEDIDO(Estado, FechaRealizacion, NotaEntrega, Importe, FechaRecepcion, FechaEntrega, NumeroFactura, NumeroAbonado) VALUES (
        5, '2020-03-29', 'Observacion del pedido 1', 105.0, '2020-04-02', '2020-04-10', 2, 2
);

INSERT INTO PEDIDO(Estado, FechaRealizacion, NotaEntrega, Importe, FechaRecepcion, FechaEntrega, NumeroFactura, NumeroAbonado) VALUES (
        5, '2020-03-29', 'Observacion del pedido 2', 70.0, '2020-04-03', '2020-04-10', 2, 2
);

