# Use the official PostgreSQL image as a base
FROM postgres:latest

# Set environment variables
ENV POSTGRES_DB=Devsync
ENV POSTGRES_USER=myuser
ENV POSTGRES_PASSWORD=mypassword

# Copy the SQL script to initialize the database
COPY ./init.sql /docker-entrypoint-initdb.d/

# Expose the PostgreSQL port
EXPOSE 5432
