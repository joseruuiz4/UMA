use P1_JoseRuiz;
GO

DELETE FROM HOSPITAL;
INSERT INTO HOSPITAL VALUES (13, 'Provincial', '0Donell 5O', '964-4264', 502);
INSERT INTO HOSPITAL VALUES (18, 'General', 'Atocha s/n', '595-3111', 987);
INSERT INTO HOSPITAL VALUES (22, 'La Paz', 'Castellana 1000', '923-5411', 412);
INSERT INTO HOSPITAL VALUES (45, 'San Carlos', 'Ciudad Universitaria', '597-1500', 845);
select * from HOSPITAL;

DELETE FROM SALA;
INSERT INTO SALA VALUES (13, 3, 'Cuidados Intensivos', 21);
INSERT INTO SALA VALUES (13, 6, 'Psiquiátrico', 67);
INSERT INTO SALA VALUES (18, 3, 'Cuidados Intensivos', 10);
INSERT INTO SALA VALUES (18, 4, 'Cardiología', 53);
INSERT INTO SALA VALUES (22, 1, 'Recuperación', 10);
INSERT INTO SALA VALUES (22, 2, 'Maternidad', 34);
INSERT INTO SALA VALUES (22, 6, 'Psiquiátrico', 118);
SELECT * FROM SALA;

DELETE FROM PLANTILLA;
INSERT INTO PLANTILLA VALUES (22, 6, 1009, 'Higueras D.', 'Enfermera', 'T', 20050.00);
INSERT INTO PLANTILLA VALUES (22, 2, 1234, 'Garcia J.', 'Enfermo', 'M', 30000.00);
INSERT INTO PLANTILLA VALUES (22, 2, 1280, 'Amigo R.', 'Interno', 'N', 22100.00);
INSERT INTO PLANTILLA VALUES (13, 6, 3106, 'Hernandez J.', 'Enfermero', 'T', 27550.00);
INSERT INTO PLANTILLA VALUES (13, 6, 3754, 'Diaz B.', 'Enfermera', 'T', 22620.00);
INSERT INTO PLANTILLA VALUES (22, 1, 6065, 'Rivera G.', 'Enfermera', 'N', 16260.00);
INSERT INTO PLANTILLA VALUES (18, 4, 6357, 'Karplus W.', 'Interno', 'T', 33790.00);
INSERT INTO PLANTILLA VALUES (22, 1, 7379, 'Carlos R.', 'Enfermera', 'T', 21190.00);
INSERT INTO PLANTILLA VALUES (22, 6, 8422, 'Bocina G.', 'Enfermero', 'M', 16380.00);
INSERT INTO PLANTILLA VALUES (22, 1, 8526, 'Frank H.', 'Enfermera', 'T', 25220.00);
INSERT INTO PLANTILLA VALUES (22, 2, 9901, 'Nuñez C.', 'Interno', 'M', 22100.00);

SELECT * FROM PLANTILLA;


