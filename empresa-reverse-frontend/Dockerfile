# ============================
# Stage 1: Build con Node
# ============================
FROM node:20-alpine AS build

# ✅ ACTUALIZAR ALPINE A LA ÚLTIMA VERSIÓN
RUN apk update && \
    apk upgrade --no-cache --available && \
    apk add --no-cache \
        ca-certificates \
        tzdata && \
    rm -rf /var/cache/apk/*

WORKDIR /app

# ✅ Instalar TODAS las dependencias (incluyendo devDependencies para Angular CLI)
COPY package*.json ./
RUN npm ci && \
    npm cache clean --force

# Compilar aplicación
COPY . .
RUN npm run build -- --configuration production

# ============================
# Stage 2: Nginx con Alpine actualizado
# ============================
FROM nginx:1.27-alpine

# ✅ ACTUALIZAR ALPINE Y FORZAR VERSIONES ESPECÍFICAS
RUN apk update && \
    apk upgrade --no-cache --available && \
    apk add --no-cache \
        ca-certificates \
        tzdata && \
    # ✅ FORZAR ACTUALIZACIÓN DE PAQUETES CRÍTICOS
    apk add --no-cache --upgrade \
        libcrypto3>=3.5.5-r0 \
        libssl3>=3.5.5-r0 \
        libexpat>=2.7.4-r0 \
        libpng>=1.6.54-r0 && \
    rm -rf /var/cache/apk/* /tmp/*

# Copiar archivos compilados
COPY --from=build /app/dist/empresa-reverse-frontend/browser /usr/share/nginx/html

# Copiar configuración
COPY nginx.conf /etc/nginx/conf.d/default.conf

# ✅ Crear directorios de logs
RUN mkdir -p /var/log/nginx && \
    touch /var/log/nginx/access.log /var/log/nginx/error.log && \
    chown -R nginx:nginx /var/log/nginx /usr/share/nginx/html /var/cache/nginx

EXPOSE 80 443

CMD ["nginx", "-g", "daemon off;"]