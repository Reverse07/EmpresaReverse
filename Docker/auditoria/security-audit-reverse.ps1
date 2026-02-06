# ============================================
# Script de reconstrucción y auditoría
# EmpresaReverse - Remediación de vulnerabilidades
# ============================================

Write-Host "================================================" -ForegroundColor Cyan
Write-Host "  REMEDIACIÓN DE VULNERABILIDADES - EmpresaReverse" -ForegroundColor Cyan
Write-Host "================================================`n" -ForegroundColor Cyan

# Variables
$BACKEND_PATH = "C:\Users\USER\EmpresaReverse\empresa-reverse-backend"
$FRONTEND_PATH = "C:\Users\USER\EmpresaReverse\empresa-reverse-frontend"
$INFRA_PATH = "C:\Users\USER\EmpresaReverse\Docker"
$STACK_NAME = "empresa-stack"

# ============================================
# PASO 1: Detener el stack actual
# ============================================
Write-Host "[PASO 1/6] Deteniendo stack actual..." -ForegroundColor Yellow
docker stack rm $STACK_NAME
Write-Host "Esperando 20 segundos para que los servicios se detengan completamente..." -ForegroundColor Gray
Start-Sleep -Seconds 20

# ============================================
# PASO 2: Limpiar imágenes antiguas
# ============================================
Write-Host "`n[PASO 2/6] Limpiando imágenes antiguas..." -ForegroundColor Yellow
docker image rm empresa-backend:secure -ErrorAction SilentlyContinue
docker image rm empresa-frontend:latest -ErrorAction SilentlyContinue
Write-Host "Imágenes antiguas eliminadas." -ForegroundColor Green

# ============================================
# PASO 3: Actualizar pom.xml del Backend
# ============================================
Write-Host "`n[PASO 3/6] Actualizando pom.xml del Backend..." -ForegroundColor Yellow
Write-Host "⚠️  ACCIÓN MANUAL REQUERIDA:" -ForegroundColor Red
Write-Host "   1. Abre el archivo: $BACKEND_PATH\pom.xml" -ForegroundColor White
Write-Host "   2. Cambia <version>3.3.5</version> a <version>3.4.1</version>" -ForegroundColor White
Write-Host "   3. Guarda el archivo y presiona ENTER para continuar..." -ForegroundColor White
Read-Host

# ============================================
# PASO 4: Reconstruir Backend
# ============================================
Write-Host "`n[PASO 4/6] Construyendo Backend con dependencias actualizadas..." -ForegroundColor Yellow
Set-Location $BACKEND_PATH

Write-Host "   → Limpiando y descargando dependencias..." -ForegroundColor Gray
docker run --rm -v "${BACKEND_PATH}:/app" -w /app eclipse-temurin:21-jdk-alpine sh -c "apk add --no-cache maven && mvn clean dependency:resolve"

Write-Host "   → Construyendo imagen Docker..." -ForegroundColor Gray
docker build -f Dockerfile -t empresa-backend:secure-fixed .

if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Error al construir backend. Revisa los logs arriba." -ForegroundColor Red
    exit 1
}
Write-Host "✅ Backend construido exitosamente." -ForegroundColor Green

# ============================================
# PASO 5: Reconstruir Frontend
# ============================================
Write-Host "`n[PASO 5/6] Construyendo Frontend con Nginx actualizado..." -ForegroundColor Yellow
Set-Location $FRONTEND_PATH

docker build -f Dockerfile -t empresa-frontend:secure-fixed .

if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Error al construir frontend. Revisa los logs arriba." -ForegroundColor Red
    exit 1
}
Write-Host "✅ Frontend construido exitosamente." -ForegroundColor Green

# ============================================
# PASO 6: Escanear vulnerabilidades
# ============================================
Write-Host "`n[PASO 6/6] Escaneando vulnerabilidades con Trivy..." -ForegroundColor Yellow

Write-Host "`n📦 Escaneando Backend..." -ForegroundColor Cyan
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock `
    aquasec/trivy:latest image `
    --severity HIGH,CRITICAL `
    --format table `
    empresa-backend:secure-fixed

Write-Host "`n📦 Escaneando Frontend..." -ForegroundColor Cyan
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock `
    aquasec/trivy:latest image `
    --severity HIGH,CRITICAL `
    --format table `
    empresa-frontend:secure-fixed

Write-Host "`n📦 Escaneando PostgreSQL 16..." -ForegroundColor Cyan
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock `
    aquasec/trivy:latest image `
    --severity HIGH,CRITICAL `
    --format table `
    postgres:16-alpine

# ============================================
# PASO 7: Redesplegar stack
# ============================================
Write-Host "`n[PASO 7/6] Desplegando stack actualizado..." -ForegroundColor Yellow
Set-Location $INFRA_PATH

Write-Host "   → Desplegando servicios..." -ForegroundColor Gray
docker stack deploy -c docker-compose.yml $STACK_NAME

Write-Host "   → Esperando 40 segundos para que los servicios inicien..." -ForegroundColor Gray
Start-Sleep -Seconds 40

Write-Host "`n✅ Estado de los servicios:" -ForegroundColor Green
docker service ls

Write-Host "`n============================================" -ForegroundColor Cyan
Write-Host "  RECONSTRUCCIÓN COMPLETADA" -ForegroundColor Green
Write-Host "============================================" -ForegroundColor Cyan
Write-Host "`nPasos siguientes:" -ForegroundColor Yellow
Write-Host "1. Verifica que los servicios estén en estado 1/1 RUNNING" -ForegroundColor White
Write-Host "2. Ejecuta: .\security-audit-reverse.ps1" -ForegroundColor White
Write-Host "3. Valida que las vulnerabilidades CRITICAL hayan sido eliminadas`n" -ForegroundColor White