FROM mysql:8.0

ENV MYSQL_DATABASE=mi_db
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=password

COPY BaseDatos.sql /docker-entrypoint-initdb.d/
