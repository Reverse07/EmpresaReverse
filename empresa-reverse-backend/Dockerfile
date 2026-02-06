# ============================
# Stage 1: Build con Maven
# ============================
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# ✅ ACTUALIZAR ALPINE A LA ÚLTIMA VERSIÓN (3.21+)
RUN apk update && \
    apk upgrade --no-cache --available && \
    apk add --no-cache \
        ca-certificates \
        tzdata

COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn
RUN chmod +x ./mvnw  # ← AGREGAR ESTA LÍNEA
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

# ============================
# Stage 2: Runtime con Alpine actualizado
# ============================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# ✅ ACTUALIZAR ALPINE Y FORZAR VERSIONES ESPECÍFICAS DE PAQUETES VULNERABLES
RUN apk update && \
    apk upgrade --no-cache --available && \
    apk add --no-cache \
        curl>=8.11.1-r0 \
        ca-certificates \
        tzdata && \
    # ✅ FORZAR ACTUALIZACIÓN DE PAQUETES CRÍTICOS
    apk add --no-cache --upgrade \
        libcrypto3>=3.5.5-r0 \
        libssl3>=3.5.5-r0 \
        libexpat>=2.7.4-r0 \
        libpng>=1.6.54-r0 \
        gnupg>=2.4.9-r0 && \
    # Limpiar cache
    rm -rf /var/cache/apk/* /tmp/*

# ✅ Crear usuario no-root
RUN addgroup -S spring && adduser -S spring -G spring

# ✅ Crear carpeta temporal con permisos correctos
RUN mkdir -p /app/tmp /app/logs && \
    chown -R spring:spring /app

# Copiar JAR
COPY --chown=spring:spring --from=builder /app/target/*.jar app.jar

USER spring
EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=5s --start-period=60s --retries=3 \
  CMD curl -fsS http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", \
    "-XX:MaxRAMPercentage=75.0", \
    "-XX:+UseG1GC", \
    "-Djava.io.tmpdir=/app/tmp", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-jar", \
    "app.jar"]