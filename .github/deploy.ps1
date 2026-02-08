# ========================================
# 🚀 EmpresaReverse - Deploy Script
# ========================================
# Ejecuta deploy en la VM local desde Windows
# Uso: .\deploy.ps1

# Configuración
$SSH_KEY = "$env:USERPROFILE\.ssh\id_ed25519"
$SSH_HOST = "127.0.0.1"
$SSH_PORT = "2222"
$SSH_USER = "user01"
$DEPLOY_PATH = "/opt/empresa-reverse"

# Colores
$COLOR_GREEN = "Green"
$COLOR_YELLOW = "Yellow"
$COLOR_RED = "Red"
$COLOR_CYAN = "Cyan"

Write-Host "`n========================================" -ForegroundColor $COLOR_CYAN
Write-Host "🚀 EmpresaReverse Deployment" -ForegroundColor $COLOR_CYAN
Write-Host "========================================`n" -ForegroundColor $COLOR_CYAN

# Verificar llave SSH
if (-Not (Test-Path $SSH_KEY)) {
    Write-Host "❌ ERROR: SSH key not found at $SSH_KEY" -ForegroundColor $COLOR_RED
    exit 1
}

Write-Host "📡 Connecting to VM..." -ForegroundColor $COLOR_YELLOW

# Script a ejecutar en el servidor
$REMOTE_SCRIPT = @"
set -e

echo '📦 Pulling latest images from Docker Hub...'
docker pull diegoarroyo7/empresa-backend:latest
docker pull diegoarroyo7/empresa-frontend:latest

echo ''
echo '🔄 Updating Docker Stack...'
cd $DEPLOY_PATH
./deploy-swarm.sh

echo ''
echo '⏳ Waiting for services to stabilize...'
sleep 30

echo ''
echo '✅ Current services status:'
docker service ls | grep empresa-stack

echo ''
echo '🎉 Deployment completed successfully!'
"@

# Ejecutar deploy vía SSH
try {
    $REMOTE_SCRIPT | ssh -i $SSH_KEY -p $SSH_PORT "${SSH_USER}@${SSH_HOST}" "bash -s"
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "`n========================================" -ForegroundColor $COLOR_GREEN
        Write-Host "✅ DEPLOYMENT SUCCESSFUL!" -ForegroundColor $COLOR_GREEN
        Write-Host "========================================`n" -ForegroundColor $COLOR_GREEN
        
        Write-Host "📝 Next steps:" -ForegroundColor $COLOR_CYAN
        Write-Host "  • Check logs: ssh -i $SSH_KEY -p $SSH_PORT ${SSH_USER}@${SSH_HOST}" -ForegroundColor $COLOR_CYAN
        Write-Host "  • Monitor: docker service ls" -ForegroundColor $COLOR_CYAN
    } else {
        Write-Host "`n❌ Deployment failed with exit code $LASTEXITCODE" -ForegroundColor $COLOR_RED
        exit 1
    }
} catch {
    Write-Host "`n❌ ERROR: $_" -ForegroundColor $COLOR_RED
    exit 1
}