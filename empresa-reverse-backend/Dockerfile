# ============================
# Stage 1: Build con Maven
# ============================
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

# ============================
# Stage 2: Runtime seguro
# ============================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Crear usuario no-root
RUN addgroup -S spring && adduser -S spring -G spring

# Instalar curl para healthcheck
RUN apk add --no-cache curl

# Crear carpeta temporal para Tomcat y asignar permisos
RUN mkdir /app/tmp && chown spring:spring /app/tmp

# Copiar JAR con permisos correctos
COPY --chown=spring:spring --from=builder /app/target/*.jar app.jar

USER spring
EXPOSE 8080

# Healthcheck
HEALTHCHECK --interval=30s --timeout=5s --start-period=60s --retries=3 \
  CMD curl -fsS http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-XX:+UseG1GC", "-jar", "app.jar"]

