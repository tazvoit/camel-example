DROP TABLE IF EXISTS TBL_USUARIOS;
  
CREATE TABLE TBL_USUARIOS (
  id  SERIAL PRIMARY KEY NOT NULL,
  nombre VARCHAR(250) NOT NULL,
  puesto VARCHAR(250),
  fechanacimento DATE NOT NULL
);