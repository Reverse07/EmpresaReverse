# Etapa 1: Build con Node
FROM node:20 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build -- --configuration production

# Etapa 2: Servir con Nginx
FROM nginx:alpine
# CAMBIO IMPORTANTE: Copiar desde browser/ que es donde Angular 17+ genera los archivos
COPY --from=build /app/dist/empresa-reverse-frontend/browser /usr/share/nginx/html
EXPOSE 80