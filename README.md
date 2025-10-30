# EmpresaReverse

**Repositorio:** https://github.com/Reverse07/EmpresaReverse

Aplicación fullstack para una empresa de soluciones tecnológicas, con backend en Spring Boot (Java, Maven) bajo arquitectura multicapa y APIs REST seguras con JWT. Integra base de datos PostgreSQL y frontend en Angular para la gestión de usuarios, servicios, pedidos, facturas y notificaciones.

---

## 📌 Resumen
El repositorio contiene dos partes:
- Backend (Spring Boot) en la raíz (`pom.xml`, `src/`, `mvnw`, etc.)
- Frontend Angular en la carpeta `empresa-reverse-frontend`

---

## 🛠 Requisitos previos
Antes de ejecutar el proyecto debo tener instalado:

- **Git** → `git --version`
- **Java JDK 11 o 17** → `java -version`
- **Maven** (opcional si no uso el wrapper) → `mvn -v`
- **Node.js** (versión LTS recomendada) → `node -v`
- **npm** → `npm -v`
- **Angular CLI** (opcional si quiero usar `ng serve`):
  ```bash
  npm install -g @angular/cli
  ```
- **PostgreSQL** instalado y corriendo en el puerto 5432

---

## 📥 Clonar el repositorio
```bash
git clone https://github.com/Reverse07/EmpresaReverse.git
cd EmpresaReverse
```

---

## 🧩 Configurar la base de datos
Debo crear una base de datos en PostgreSQL, por ejemplo:

```sql
CREATE DATABASE empresa_db;
```

Luego, en `src/main/resources/application.properties`, reviso o configuro las credenciales:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/empresa_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
```

---

## ▶️ Ejecutar el backend (Spring Boot)
Desde la carpeta raíz del proyecto:

### Linux / macOS
```bash
./mvnw spring-boot:run
```

### Windows (PowerShell / CMD)
```powershell
mvnw.cmd spring-boot:run
```

También puedo empaquetar el proyecto y ejecutar el JAR:
```bash
./mvnw clean package -DskipTests
java -jar target/*.jar
```

El backend quedará disponible en `http://localhost:8080`

---

## ▶️ Ejecutar el frontend (Angular)
1. Entro a la carpeta del frontend:
```bash
cd empresa-reverse-frontend
```
2. Instalo las dependencias:
```bash
npm install
```
3. Inicio el servidor de desarrollo:
```bash
ng serve --open
# o si no tengo Angular CLI globalmente:
npx ng serve --open
```

El frontend quedará disponible en `http://localhost:4200`

En `src/environments/environment.ts` debo verificar que la URL de la API apunte a `http://localhost:8080`

---

## 🔁 Flujo de ejecución local
1. Levanto PostgreSQL y creo la base de datos.
2. Ejecuto el backend con `./mvnw spring-boot:run`
3. Instalo dependencias y ejecuto el frontend con `ng serve`
4. Accedo a `http://localhost:4200/`

---

## 🐛 Problemas comunes
- **Error al conectar a la base de datos**: verificar usuario, contraseña y URL en `application.properties`.
- **Puerto en uso**: cambiar `server.port` para el backend o usar `ng serve --port 4300` para el frontend.
- **Permisos en Linux/macOS para mvnw**: ejecutar `chmod +x mvnw`.
- **Angular CLI no instalado**: usar `npx ng serve` o instalarlo globalmente.
