# Cimr: Users microservice

## Prerequisites

```bash
docker run -d --name users -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=users -p 5432:5432 postgres:13
```