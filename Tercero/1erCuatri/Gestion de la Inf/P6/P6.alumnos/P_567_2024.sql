USE [master]
GO
IF EXISTS (SELECT 1 FROM sys.databases WHERE [name] = 'P_567_2024')
BEGIN
	DROP DATABASE P_567_2024
END;

GO

/****** Object:  Database [P_567_2024]    Script Date: 30/10/2024 10:06:05 ******/
CREATE DATABASE [P_567_2024]
GO
USE [P_567_2024]
GO
/****** Object:  Table [dbo].[Producto]    Script Date: 30/10/2024 10:06:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Producto](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[NOMBRE] [varchar](250) NOT NULL,
	[PROVEEDOR] [varchar](50) NOT NULL,
	[PRECIO] [real] NOT NULL,
 CONSTRAINT [PK_Producto] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Proveedor]    Script Date: 30/10/2024 10:06:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Proveedor](
	[CIF] [varchar](50) NOT NULL,
	[NOMBRE] [varchar](50) NOT NULL,
	[E_MAIL] [varchar](50) NULL,
	[TELEFONO] [varchar](50) NULL,
 CONSTRAINT [PK_Proveedor] PRIMARY KEY CLUSTERED 
(
	[CIF] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Producto]  WITH CHECK ADD  CONSTRAINT [FK_Producto_Proveedor] FOREIGN KEY([PROVEEDOR])
REFERENCES [dbo].[Proveedor] ([CIF])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Producto] CHECK CONSTRAINT [FK_Producto_Proveedor]
GO

INSERT INTO Proveedor VALUES('111111111A','Cofares (Grupo) ','111111111A@mail.com','111111111');
INSERT INTO Proveedor VALUES('222222222B','SegurCaixa Adeslas ','222222222B@mail.com','222222222');
INSERT INTO Proveedor VALUES('333333333C','Bidafarma ','333333333C@mail.com','333333333');
INSERT INTO Proveedor VALUES('444444444D','IDCQ Hospitales y Sanidad (Quironsalud) ','444444444D@mail.com','444444444');
INSERT INTO Proveedor VALUES('555555555E','Werfen (Grupo) ','555555555E@mail.com','555555555');
INSERT INTO Proveedor VALUES('666666666F','Lilly ','666666666F@mail.com','666666666');
INSERT INTO Proveedor VALUES('777777777G','Novartis (Grupo) ','777777777G@mail.com','777777777');
INSERT INTO Proveedor VALUES('888888888H','Sanitas ','888888888H@mail.com','888888888');
INSERT INTO Proveedor VALUES('999999999I','Hefame (Grupo) ','999999999I@mail.com','999999999');
INSERT INTO Proveedor VALUES('000000000J','Asisa ','000000000J@mail.com','000000000');
INSERT INTO Proveedor VALUES('111111111K','Alliance Healthcare (Grupo) ','111111111K@mail.com','111111111');
INSERT INTO Proveedor VALUES('222222222L','Pfizer (Grupo) ','222222222L@mail.com','222222222');
INSERT INTO Proveedor VALUES('333333333M','Unnefar ','333333333M@mail.com','333333333');
INSERT INTO Proveedor VALUES('444444444N','Janssen ','444444444N@mail.com','444444444');
INSERT INTO Proveedor VALUES('555555555O','Almirall (Grupo) ','555555555O@mail.com','555555555');
INSERT INTO Proveedor VALUES('666666666P','Sanofi ','666666666P@mail.com','666666666');
INSERT INTO Proveedor VALUES('777777777Q','MSD España ','777777777Q@mail.com','777777777');
INSERT INTO Proveedor VALUES('888888888R','Roche Farma ','888888888R@mail.com','888888888');
INSERT INTO Proveedor VALUES('999999999S','Fedefarma ','999999999S@mail.com','999999999');
INSERT INTO Proveedor VALUES('000000000T','Ferrer (Grupo) ','000000000T@mail.com','000000000');
INSERT INTO Proveedor VALUES('111111111U','Mapfre España ','111111111U@mail.com','111111111');
INSERT INTO Proveedor VALUES('222222222V','Boehringer Ingelheim (Grupo) ','222222222V@mail.com','222222222');
INSERT INTO Proveedor VALUES('333333333W','DKV Seguros ','333333333W@mail.com','333333333');
INSERT INTO Proveedor VALUES('444444444X','Bayer Hispania ','444444444X@mail.com','444444444');
INSERT INTO Proveedor VALUES('555555555Y','Goodgrower (Grupo) ','555555555Y@mail.com','555555555');
INSERT INTO Proveedor VALUES('666666666Z','Medtronic Ibérica ','666666666Z@mail.com','666666666');
INSERT INTO Proveedor VALUES('777777777A','Primero Salud (Grupo) ','777777777A@mail.com','777777777');
INSERT INTO Proveedor VALUES('888888888B','Infarco (Grupo) ','888888888B@mail.com','888888888');
INSERT INTO Proveedor VALUES('999999999C','GSK ','999999999C@mail.com','999999999');
INSERT INTO Proveedor VALUES('000000000D','Esteve (Grupo) ','000000000D@mail.com','000000000');
INSERT INTO Proveedor VALUES('111111111E','HM Hospitales (Grupo) ','111111111E@mail.com','111111111');
INSERT INTO Proveedor VALUES('222222222F','Roche Diagnostics ','222222222F@mail.com','222222222');
INSERT INTO Proveedor VALUES('333333333G','Rovi (Grupo) ','333333333G@mail.com','333333333');
INSERT INTO Proveedor VALUES('444444444H','Abbott Laboratories ','444444444H@mail.com','444444444');
INSERT INTO Proveedor VALUES('555555555I','Cruz Roja Española ','555555555I@mail.com','555555555');
INSERT INTO Proveedor VALUES('666666666J','Teva Pharma ','666666666J@mail.com','666666666');
INSERT INTO Proveedor VALUES('777777777K','Gilead Sciences ','777777777K@mail.com','777777777');
INSERT INTO Proveedor VALUES('888888888L','Faes Farma (Grupo) ','888888888L@mail.com','888888888');
INSERT INTO Proveedor VALUES('999999999M','Quirón Prevención ','999999999M@mail.com','999999999');
INSERT INTO Proveedor VALUES('000000000N','Merck ','000000000N@mail.com','000000000');
INSERT INTO Proveedor VALUES('111111111O','AstraZeneca Farmacéutica Spain ','111111111O@mail.com','111111111');
INSERT INTO Proveedor VALUES('222222222P','Bioibérica ','222222222P@mail.com','222222222');
INSERT INTO Proveedor VALUES('333333333Q','Becton Dickinson ','333333333Q@mail.com','333333333');
INSERT INTO Proveedor VALUES('444444444R','SEM ','444444444R@mail.com','444444444');
INSERT INTO Proveedor VALUES('555555555S','Astellas Pharma ','555555555S@mail.com','555555555');
INSERT INTO Proveedor VALUES('666666666T','Bristol Myers Squibb ','666666666T@mail.com','666666666');
INSERT INTO Proveedor VALUES('777777777U','Hipra (Grupo) ','777777777U@mail.com','777777777');
INSERT INTO Proveedor VALUES('888888888V','Johnson & Johnson ','888888888V@mail.com','888888888');
INSERT INTO Proveedor VALUES('999999999W','Takeda (Grupo','999999999W@mail.com','999999999');
GO

INSERT INTO Producto VALUES('Aspirador Portátil V7 Plus B','333333333C',546.48);
INSERT INTO Producto VALUES('Manta Termica Rescate Plata-Oro','111111111E',2.00);
INSERT INTO Producto VALUES('Desfibrilador CU-SP2-Semiautomatico','333333333G',1390.00);
INSERT INTO Producto VALUES('Maleta OXIVAC I Con Aspirador','222222222V',394.68);
INSERT INTO Producto VALUES('Maletín Emergencias Respiratorias Rojo','444444444R',176.50);
INSERT INTO Producto VALUES('Mochila OXIVAC RESCUE','666666666P',592.02);
INSERT INTO Producto VALUES('Aspirador Portátil V7 Ac','333333333Q',404.80);
INSERT INTO Producto VALUES('Mascarilla Plástico Para Reanimación CPR','111111111U',2.14);
INSERT INTO Producto VALUES('Monitor Paciente','777777777A',1295.00);
INSERT INTO Producto VALUES('Desfibrilador IPad CU-SP1-Semicautomatico','333333333C',1071.43);
INSERT INTO Producto VALUES('Maleta OXIVAC II','555555555E',419.89);
INSERT INTO Producto VALUES('Aspirador Portátil ASPIT 13,3l/Min','444444444N',786.51);
INSERT INTO Producto VALUES('Mascarilla Plástico Para Reanimación CPR','111111111A',2.14);
INSERT INTO Producto VALUES('Monitor Paciente Vital Sings','111111111A',950.00);
INSERT INTO Producto VALUES('Resucitador Revivator Plus Adultos + Reservorio','777777777K',96.14);
INSERT INTO Producto VALUES('Resucitador Revivator Plus Adultos Set -En Estuche Rigido','888888888B',116.00);
INSERT INTO Producto VALUES('Maleta OXIVAC I Sin Aspirador','666666666T',294.49);
INSERT INTO Producto VALUES('Inmovilizador De Cabeza Dama De Elche','111111111O',58.57);
INSERT INTO Producto VALUES('Aspirador Portátil V7 Mx','444444444R',708.40  );
INSERT INTO Producto VALUES('Monitor De Signos Vitales Inalámbrico Paciente Checkme Pro','888888888V',1250.50);
INSERT INTO Producto VALUES('Mascarilla Plástico Para Reanimación CPR','555555555I',1.50);
INSERT INTO Producto VALUES('Ampulario Isotérmico Mediano, Poliéster Rojo','333333333Q',23.66);
INSERT INTO Producto VALUES('Resucitador Revivator Res-Q Adultos Set - En Estuche Rigido','111111111E',61.73);
INSERT INTO Producto VALUES('Resucitador Revivator Res-Q Niños + Reservorio','888888888H',52.62);
INSERT INTO Producto VALUES('Maleta Emergencia Con O2 Y Aspirador','000000000N',629.20);
INSERT INTO Producto VALUES('Bolsa Ligera Emergencias','222222222B',39.69);
INSERT INTO Producto VALUES('Resucitador Revivator Res-Q Adultos Set - En Estuche Rigido','555555555I',61.73);
INSERT INTO Producto VALUES('Resucitador Revivator Res-Q Niños + Reservorio','444444444R',52.62);
INSERT INTO Producto VALUES('Maleta Emergencia Con O2 Y Aspirador','111111111A',629.20);
INSERT INTO Producto VALUES('Torniquete SWAT-T Estirar-Envolver-Plegar','222222222B',26.64);
INSERT INTO Producto VALUES('Bolsa Ligera Emergencias, Loneta','777777777U',39.69);
INSERT INTO Producto VALUES('Resucitador Revivator Res-Q Adultos','111111111A',65.00);
INSERT INTO Producto VALUES('Maleta Emergencia Con O2','444444444D',549.12);
INSERT INTO Producto VALUES('Torniquete SWAT-T Estirar-Envolver-Plegar','000000000T',26.64);
INSERT INTO Producto VALUES('Bolsa Ligera Emergencias, Loneta','999999999S',39.69);
INSERT INTO Producto VALUES('Resucitador Revivator Plus Niños + Reservorio','666666666J',96.14);
INSERT INTO Producto VALUES('Maleta Emergencia Con O2,Sin Resucitador','444444444R',411.84);
INSERT INTO Producto VALUES('Collarin Adulto STIFNECK SELECT Ajustable 4 En 1','888888888R',17.50);
INSERT INTO Producto VALUES('Botella Oxigeno Puro','888888888R',15.40);
INSERT INTO Producto VALUES('Mochila Rescate Emergencias, Loneta','666666666F',58.24);
INSERT INTO Producto VALUES('Resucitador Revivator Res-Q Adultos + Reservorio','111111111U',52.62);
INSERT INTO Producto VALUES('Bolsa Emergencias Gran Capacidad,','666666666P',63.17);
INSERT INTO Producto VALUES('Camilla Cuchara','888888888R',162.86);
INSERT INTO Producto VALUES('Tubos De Guedel (Juego De 4 Unidades)','333333333C',5.57);
INSERT INTO Producto VALUES('Bolsa Ligera Emergencias, Loneta','333333333G',39.69);
INSERT INTO Producto VALUES('Mochila Rescate Emergencias Roja','111111111A',84.36);
INSERT INTO Producto VALUES('Resucitador Revivator Res-Q Niños','666666666Z',47.56);
INSERT INTO Producto VALUES('Collarin Pediatrico STIFNECK SELECT Ajustable 3 En 1','000000000N',17.50);
INSERT INTO Producto VALUES('Bolsa Emergencias Gran Capacidad,','333333333Q',63.17);
INSERT INTO Producto VALUES('Collarin Pediatrico STIFNECK SELECT Ajustable 3 En 1','222222222B',17.50);
INSERT INTO Producto VALUES('Resucitador Revivator Plus Bebés - En Estuche Rigido','444444444X',111.32);
INSERT INTO Producto VALUES('Venda Hemostatica Azul 6cm X 45m Cederroth','666666666J',9.90);
INSERT INTO Producto VALUES('Mochila Rescate Emergencias','999999999I',58.24);
INSERT INTO Producto VALUES('Resucitador De Silicona Para ADULTO','999999999M',74.36);
INSERT INTO Producto VALUES('Venda Hemostatica Negra 6cm X 45m Cederroth','333333333Q',9.90);
INSERT INTO Producto VALUES('Equipo Reanimacion Silicona Adulto','999999999I',114.40);
INSERT INTO Producto VALUES('Desfibrilador CU-SP2 Semiautomático Y Manual','999999999W',2648.57);
INSERT INTO Producto VALUES('Dispensador Cuidado Heridas AZUL Primeros Auxilios Cederroth','555555555E',80.00);
INSERT INTO Producto VALUES('Botiquin Primeros Auxilios Rojo Incluye Material Sanitario','222222222F',10.71);
INSERT INTO Producto VALUES('Ampulario Isotérmico Gran Capacidad','222222222P',29.50);
INSERT INTO Producto VALUES('LifeVac Dispositivo Anti-Atragantamiento','111111111K',90.08);
INSERT INTO Producto VALUES('Laringoscopio 4 Palas McIntosh','999999999C',201.89);
INSERT INTO Producto VALUES('Laringoscopio 3 Palas Macintosh','000000000T',95.03);

GO