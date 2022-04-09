docker-compose up -d --remove-orphans

Sleep 5

docker exec mongo1 /scripts/rs-init.sh

Sleep 5

function Sleep([int]$seconds) {
    Write-Host "Sleeping for $seconds seconds..." -ForegroundColor yellow
    Start-Sleep -s $seconds
}