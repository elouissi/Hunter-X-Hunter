@echo off
docker build -t deploy-app:latest .

docker stop deploy-container || echo Le conteneur est déjà arrêté.
docker rm deploy-container || echo Le conteneur est déjà supprimé.

docker run -d --name deploy-container -p 8085:8085 deploy-app:latest

echo Déploiement terminé avec succès.
