FROM postgres
ENV POSTGRES_USER project0
ENV POSTGRES_PASSWORD password
ADD schema.sql /docker-entrypoint-initdb.d
EXPOSE 5432