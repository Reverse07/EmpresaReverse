# 🏢 EmpresaReverse

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-17+-red.svg)](https://angular.io/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Swarm-2496ED.svg)](https://www.docker.com/)
[![CI/CD](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-2088FF.svg)](https://github.com/features/actions)

**Sistema de gestión empresarial fullstack** para soluciones tecnológicas con arquitectura multicapa, APIs REST seguras con JWT y despliegue automatizado.

🔗 **Repositorio:** [github.com/Reverse07/EmpresaReverse](https://github.com/Reverse07/EmpresaReverse)

---

## 📋 Tabla de Contenidos

- [Características Principales](#-características-principales)
- [Arquitectura del Sistema](#-arquitectura-del-sistema)
- [Stack Tecnológico](#️-stack-tecnológico)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación y Configuración](#-instalación-y-configuración)
- [Ejecución del Proyecto](#️-ejecución-del-proyecto)
- [Despliegue con Docker](#-despliegue-con-docker)
- [CI/CD Pipeline](#-cicd-pipeline)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [API Endpoints](#-api-endpoints)
- [Troubleshooting](#-troubleshooting)
- [Contribución](#-contribución)
- [Licencia](#-licencia)

---

## ✨ Características Principales

### Backend (Spring Boot)
- 🔐 **Autenticación y Autorización** con Spring Security y JWT
- 🏗️ **Arquitectura Multicapa** (Controller → Service → Repository)
- 📊 **APIs REST** con documentación automática
- 🗄️ **Persistencia** con Spring Data JPA e Hibernate
- ✅ **Validación** de datos con Bean Validation
- 🔄 **Manejo de excepciones** centralizado
- 📈 **Actuator** para monitoreo y métricas

### Frontend (Angular)
- 🎨 **Interfaz moderna** y responsive
- 🔒 **Guards** para protección de rutas
- 🌐 **Servicios HTTP** con interceptores
- 📱 **Componentes reutilizables**
- 🎯 **Routing** modular
- 🔔 **Sistema de notificaciones** en tiempo real

### Funcionalidades de Negocio
- 👥 **Gestión de Usuarios** (Admin, Cliente, Asesor)
- 🛠️ **Catálogo de Servicios** técnicos
- 📦 **Gestión de Pedidos** con estados y seguimiento
- 💰 **Facturación** electrónica con cálculo de impuestos
- 🎫 **Sistema de Tickets** para soporte técnico
- 📢 **Notificaciones** automáticas
- 📊 **Dashboard** con métricas y reportes

---

## 🏗️ Arquitectura del Sistema

```
┌─────────────────────────────────────────────────────────────┐
│                      FRONTEND (Angular)                      │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐      │
│  │ Components   │  │  Services    │  │   Guards     │      │
│  └──────────────┘  └──────────────┘  └──────────────┘      │
└────────────────────────┬────────────────────────────────────┘
                         │ HTTP/REST
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                   BACKEND (Spring Boot)                      │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              Controllers (REST API)                   │  │
│  └────────────────────┬─────────────────────────────────┘  │
│                       │                                      │
│  ┌────────────────────▼─────────────────────────────────┐  │
│  │                Services (Business Logic)              │  │
│  └────────────────────┬─────────────────────────────────┘  │
│                       │                                      │
│  ┌────────────────────▼─────────────────────────────────┐  │
│  │          Repositories (Data Access - JPA)             │  │
│  └────────────────────┬─────────────────────────────────┘  │
└───────────────────────┼──────────────────────────────────────┘
                        │ JDBC
                        ▼
┌─────────────────────────────────────────────────────────────┐
│                   DATABASE (PostgreSQL)                      │
│  Usuarios • Servicios • Pedidos • Facturas • Tickets        │
└─────────────────────────────────────────────────────────────┘
```

---

## 🛠️ Stack Tecnológico

### Backend
| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 21 | Lenguaje de programación |
| Spring Boot | 3.5.5 | Framework backend |
| Spring Security | 6.x | Autenticación y autorización |
| Spring Data JPA | 3.x | Capa de persistencia |
| Hibernate | 6.x | ORM |
| PostgreSQL | 15 | Base de datos relacional |
| Maven | 3.9+ | Gestión de dependencias |
| JWT | - | Tokens de autenticación |
| Lombok | - | Reducción de código boilerplate |

### Frontend
| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Angular | 17+ | Framework frontend |
| TypeScript | 5.x | Lenguaje tipado |
| RxJS | 7.x | Programación reactiva |
| Angular Material | - | Componentes UI |
| NgBootstrap | - | Bootstrap para Angular |

### DevOps
| Tecnología | Propósito |
|------------|-----------|
| Docker | Containerización |
| Docker Swarm | Orquestación |
| GitHub Actions | CI/CD |
| Nginx | Reverse proxy |
| Trivy | Análisis de seguridad |

---

## 📋 Requisitos Previos

Asegúrate de tener instalado:

### Para Desarrollo Local

```bash
# Verificar versiones
git --version           # Git
java -version          # Java JDK 21
mvn -v                 # Maven 3.9+
node -v                # Node.js 20.x LTS
npm -v                 # npm 10.x
```

### Instalación de Herramientas

#### Java JDK 21
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-21-jdk

# macOS (con Homebrew)
brew install openjdk@21

# Windows
# Descargar desde: https://adoptium.net/
```

#### Node.js y npm
```bash
# Ubuntu/Debian
curl -fsSL https://deb.nodesource.com/setup_20.x | sudo -E bash -
sudo apt install -y nodejs

# macOS
brew install node@20

# Windows
# Descargar desde: https://nodejs.org/
```

#### Angular CLI (opcional)
```bash
npm install -g @angular/cli
```

#### PostgreSQL
```bash
# Ubuntu/Debian
sudo apt install postgresql postgresql-contrib

# macOS
brew install postgresql@15

# Windows
# Descargar desde: https://www.postgresql.org/download/windows/
```

---

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone https://github.com/Reverse07/EmpresaReverse.git
cd EmpresaReverse
```

### 2. Configurar Base de Datos

#### Crear la base de datos:
```sql
-- Conectar a PostgreSQL
psql -U postgres

-- Crear base de datos
CREATE DATABASE empresa_db;

-- Crear usuario (opcional)
CREATE USER empresa_user WITH PASSWORD 'empresa123';
GRANT ALL PRIVILEGES ON DATABASE empresa_db TO empresa_user;
```

#### Configurar credenciales:

Editar `src/main/resources/application.properties`:

```properties
# Configuración de Base de Datos
spring.datasource.url=jdbc:postgresql://localhost:5432/empresa_db
spring.datasource.username=postgres
spring.datasource.password=postgres

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Servidor
server.port=8080

# JWT (cambiar en producción)
jwt.secret=miClaveSecretaSuperSegura123456789
jwt.expiration=86400000
```

### 3. Instalar Dependencias del Frontend

```bash
cd empresa-reverse-frontend
npm install
cd ..
```

---

## ▶️ Ejecución del Proyecto

### Opción 1: Desarrollo Local

#### Backend (Terminal 1)

```bash
# Desde la raíz del proyecto
./mvnw spring-boot:run

# O en Windows
mvnw.cmd spring-boot:run
```

✅ Backend disponible en: `http://localhost:8080`

#### Frontend (Terminal 2)

```bash
cd empresa-reverse-frontend
ng serve --open

# O sin Angular CLI global
npx ng serve --open
```

✅ Frontend disponible en: `http://localhost:4200`

### Opción 2: Compilar y Ejecutar JAR

```bash
# Compilar
./mvnw clean package -DskipTests

# Ejecutar
java -jar target/empresa-reverse-*.jar
```

### Opción 3: Modo Producción (Frontend)

```bash
cd empresa-reverse-frontend
npm run build -- --configuration production

# Los archivos compilados estarán en dist/
```

---

## 🐳 Despliegue con Docker

### Prerrequisitos
- Docker instalado
- Docker Compose instalado (opcional)

### Despliegue con Docker Swarm

#### 1. Inicializar Swarm
```bash
docker swarm init
```

#### 2. Construir Imágenes

```bash
# Backend
cd empresa-reverse-backend
docker build -t diegoarroyo7/empresa-backend:latest .

# Frontend
cd ../empresa-reverse-frontend
docker build -t diegoarroyo7/empresa-frontend:latest .
```

#### 3. Desplegar Stack

```bash
# Desde la raíz del proyecto
docker stack deploy -c docker-compose.yml empresa-stack
```

#### 4. Verificar Despliegue

```bash
# Ver servicios
docker service ls

# Ver logs
docker service logs empresa-stack_backend
docker service logs empresa-stack_frontend

# Ver estado
docker stack ps empresa-stack
```

### Acceso a la Aplicación

- **Frontend:** http://localhost:80
- **Backend API:** http://localhost:8080
- **Health Check:** http://localhost:8080/actuator/health

### Comandos Útiles Docker

```bash
# Escalar servicios
docker service scale empresa-stack_backend=3

# Actualizar servicio
docker service update --image diegoarroyo7/empresa-backend:latest empresa-stack_backend

# Rollback
docker service rollback empresa-stack_backend

# Eliminar stack
docker stack rm empresa-stack
```

---

## 🔄 CI/CD Pipeline

El proyecto incluye pipelines automatizados con GitHub Actions.

### Workflows Configurados

#### 1. Backend CI/CD
- ✅ Build con Maven
- ✅ Construcción de imagen Docker
- ✅ Push a Docker Hub
- ✅ Security scan con Trivy
- ✅ Reporte SARIF a GitHub Security

#### 2. Frontend CI/CD
- ✅ Tests y Linting
- ✅ Build de producción
- ✅ Construcción de imagen Docker
- ✅ Push a Docker Hub
- ✅ Security scan con Trivy

### Configuración de Secrets

En GitHub → Settings → Secrets and variables → Actions:

| Secret | Descripción |
|--------|-------------|
| `DOCKER_USERNAME` | Usuario de Docker Hub |
| `DOCKER_PASSWORD` | Password/Token de Docker Hub |

### Triggers

- **Push a `main`:** Build + Push + Security Scan
- **Push a `develop`:** Build + Push
- **Pull Request:** Build + Tests

### Manual Deploy

Después de que el pipeline termine:

```bash
# Conectar al servidor
ssh user@your-server

# Pull de imágenes actualizadas
docker pull diegoarroyo7/empresa-backend:latest
docker pull diegoarroyo7/empresa-frontend:latest

# Actualizar stack
docker stack deploy -c docker-compose.yml empresa-stack
```

---

## 📁 Estructura del Proyecto

```
EmpresaReverse/
├── .github/
│   └── workflows/
│       ├── backend-cicd.yml          # Pipeline backend
│       └── frontend-cicd.yml         # Pipeline frontend
│
├── empresa-reverse-backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/empresa/reverse/
│   │   │   │   ├── config/           # Configuraciones (Security, CORS)
│   │   │   │   ├── controller/       # REST Controllers
│   │   │   │   ├── dto/              # Data Transfer Objects
│   │   │   │   ├── entity/           # Entidades JPA
│   │   │   │   ├── repository/       # Repositorios Spring Data
│   │   │   │   ├── service/          # Lógica de negocio
│   │   │   │   ├── security/         # JWT, Auth
│   │   │   │   └── exception/        # Manejo de errores
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/                     # Tests unitarios
│   ├── Dockerfile
│   ├── pom.xml
│   └── mvnw
│
├── empresa-reverse-frontend/
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/           # Componentes UI
│   │   │   ├── services/             # Servicios HTTP
│   │   │   ├── guards/               # Route Guards
│   │   │   ├── models/               # Interfaces/Modelos
│   │   │   ├── interceptors/         # HTTP Interceptors
│   │   │   └── app.routes.ts         # Configuración de rutas
│   │   ├── environments/             # Variables de entorno
│   │   └── assets/                   # Recursos estáticos
│   ├── nginx/
│   │   └── default.conf              # Configuración Nginx
│   ├── Dockerfile
│   ├── angular.json
│   └── package.json
│
├── docker-compose.yml                # Orquestación Docker Swarm
├── deploy-swarm.sh                   # Script de deploy
├── backup-db.sh                      # Script de backup
└── README.md
```

---

## 📡 API Endpoints

### Autenticación

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/auth/login` | Login de usuario |
| POST | `/api/auth/register` | Registro de usuario |

### Usuarios

| Método | Endpoint | Descripción | Rol |
|--------|----------|-------------|-----|
| GET | `/api/usuarios` | Listar usuarios | ADMIN |
| GET | `/api/usuarios/{id}` | Obtener usuario | ADMIN |
| POST | `/api/usuarios` | Crear usuario | ADMIN |
| PUT | `/api/usuarios/{id}` | Actualizar usuario | ADMIN |
| DELETE | `/api/usuarios/{id}` | Eliminar usuario | ADMIN |

### Servicios

| Método | Endpoint | Descripción | Rol |
|--------|----------|-------------|-----|
| GET | `/api/servicios` | Listar servicios | ALL |
| GET | `/api/servicios/{id}` | Obtener servicio | ALL |
| POST | `/api/servicios` | Crear servicio | ADMIN |
| PUT | `/api/servicios/{id}` | Actualizar servicio | ADMIN |
| DELETE | `/api/servicios/{id}` | Eliminar servicio | ADMIN |

### Pedidos

| Método | Endpoint | Descripción | Rol |
|--------|----------|-------------|-----|
| GET | `/api/pedidos` | Listar pedidos | ADMIN, ASESOR |
| GET | `/api/pedidos/{id}` | Obtener pedido | ALL |
| POST | `/api/pedidos` | Crear pedido | CLIENTE |
| PUT | `/api/pedidos/{id}` | Actualizar pedido | ADMIN, ASESOR |
| DELETE | `/api/pedidos/{id}` | Cancelar pedido | ADMIN |

### Facturas

| Método | Endpoint | Descripción | Rol |
|--------|----------|-------------|-----|
| GET | `/api/facturas` | Listar facturas | ADMIN |
| GET | `/api/facturas/{id}` | Obtener factura | ALL |
| POST | `/api/facturas` | Crear factura | ADMIN |
| PUT | `/api/facturas/{id}/pagar` | Registrar pago | ADMIN |

### Tickets

| Método | Endpoint | Descripción | Rol |
|--------|----------|-------------|-----|
| GET | `/api/tickets` | Listar tickets | ALL |
| GET | `/api/tickets/{id}` | Obtener ticket | ALL |
| POST | `/api/tickets` | Crear ticket | CLIENTE |
| PUT | `/api/tickets/{id}/resolver` | Resolver ticket | ASESOR |

### Notificaciones

| Método | Endpoint | Descripción | Rol |
|--------|----------|-------------|-----|
| GET | `/api/notificaciones` | Listar notificaciones | ALL |
| PUT | `/api/notificaciones/{id}/leer` | Marcar como leída | ALL |

### Actuator (Monitoreo)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/actuator/health` | Estado de salud |
| GET | `/actuator/info` | Información de la app |
| GET | `/actuator/metrics` | Métricas |

---

## 🐛 Troubleshooting

### Error: No se puede conectar a la base de datos

**Síntomas:**
```
Unable to acquire JDBC Connection
```

**Solución:**
1. Verificar que PostgreSQL esté corriendo:
   ```bash
   sudo systemctl status postgresql
   ```
2. Verificar credenciales en `application.properties`
3. Verificar que la base de datos existe:
   ```bash
   psql -U postgres -l
   ```

---

### Error: Puerto en uso

**Síntomas:**
```
Port 8080 is already in use
```

**Solución:**
```bash
# Ver qué está usando el puerto
sudo lsof -i :8080

# Cambiar puerto en application.properties
server.port=8081
```

---

### Error: mvnw Permission denied

**Síntomas:**
```
bash: ./mvnw: Permission denied
```

**Solución:**
```bash
chmod +x mvnw
```

---

### Error: Angular CLI no encontrado

**Síntomas:**
```
ng: command not found
```

**Solución:**
```bash
# Opción 1: Instalar globalmente
npm install -g @angular/cli

# Opción 2: Usar npx
npx ng serve
```

---

### Error: CORS en el navegador

**Síntomas:**
```
Access to XMLHttpRequest has been blocked by CORS policy
```

**Solución:**
Verificar configuración CORS en `CorsConfig.java`:
```java
@Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*");
}
```

---

### Frontend no puede conectarse al Backend

**Síntomas:**
```
Http failure response for http://localhost:8080/api/...
```

**Solución:**
1. Verificar que el backend esté corriendo: `curl http://localhost:8080/actuator/health`
2. Verificar URL en `environment.ts`:
   ```typescript
   export const environment = {
     production: false,
     apiUrl: 'http://localhost:8080/api'
   };
   ```

---

### Error: Docker service no inicia

**Síntomas:**
```
service converged
```

**Solución:**
```bash
# Ver logs detallados
docker service logs empresa-stack_backend --tail 100

# Ver tareas fallidas
docker stack ps empresa-stack --no-trunc

# Reiniciar servicio
docker service update --force empresa-stack_backend
```

---

## 🤝 Contribución

### Cómo Contribuir

1. **Fork** el repositorio
2. **Crea** una rama para tu feature:
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. **Commit** tus cambios:
   ```bash
   git commit -m "feat: agregar nueva funcionalidad"
   ```
4. **Push** a la rama:
   ```bash
   git push origin feature/nueva-funcionalidad
   ```
5. **Abre** un Pull Request

### Convenciones de Commit

Usamos [Conventional Commits](https://www.conventionalcommits.org/):

- `feat:` Nueva funcionalidad
- `fix:` Corrección de bug
- `docs:` Cambios en documentación
- `style:` Formato, sin cambios de código
- `refactor:` Refactorización de código
- `test:` Agregar o corregir tests
- `chore:` Tareas de mantenimiento

### Código de Conducta

Por favor, lee nuestro [Código de Conducta](CODE_OF_CONDUCT.md) antes de contribuir.

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

---

## 👥 Autores

- **Diego Arroyo** - *Desarrollo Inicial* - [@Reverse07](https://github.com/Reverse07)

---

## 🙏 Agradecimientos

- Spring Boot Community
- Angular Team
- Todos los contribuidores del proyecto

---

## 📞 Contacto y Soporte

- 📧 Email: soporte@empresareverse.com
- 🐛 Issues: [GitHub Issues](https://github.com/Reverse07/EmpresaReverse/issues)
- 💬 Discussions: [GitHub Discussions](https://github.com/Reverse07/EmpresaReverse/discussions)

---

<div align="center">

**⭐ Si este proyecto te ha sido útil, considera darle una estrella ⭐**

Made with ❤️ by [Reverse07](https://github.com/Reverse07)

</div>
