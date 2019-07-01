CREATE SCHEMA `tareas_proyectos` ;

USE mysql;

CREATE USER 'tareas_user'@'localhost' IDENTIFIED BY 'tar123';
GRANT ALL PRIVILEGES ON tareas_proyectos.* TO 'tareas_user'@'localhost';
FLUSH PRIVILEGES;