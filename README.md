🚗 Freelance Driver – Backend

Bienvenue dans le backend de Freelance Driver, l'application dédiée aux chauffeurs freelances.

Ce service central gère toute la logique métier, la gestion des utilisateurs, des courses, et expose une API REST consommée par les applications clientes.
🛠️ Technologies utilisées

    Spring Boot : Cadre principal du backend Java.

    ScyllaDB : Base de données NoSQL ultra-performante.

    Docker / Docker Compose : Pour un environnement reproductible.

📋 Prérequis

Avant de commencer, assurez-vous d’avoir les éléments suivants installés :

    Git – Pour cloner le projet.

    JDK 17 (ou plus récent) – Pour compiler et exécuter le projet Java.

    Docker et Docker Compose – Indispensables pour exécuter ScyllaDB.

    Un IDE Java (IntelliJ IDEA, VSCode avec Extension Pack for Java, Eclipse…).

🚀 Démarrage Rapide

Voici les étapes à suivre pour mettre en place votre environnement de développement en moins de 10 minutes.
git clone <URL_DE_VOTRE_DEPOT_GIT>
cd driver-backend

🐳 Étape 2 : Installer Docker (si nécessaire)

Sur Windows / macOS :

    Installez Docker Desktop.

Sur Linux (Ubuntu, Debian, Kali...) :

curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
sudo usermod -aG docker $USER
# Déconnectez-vous puis reconnectez-vous pour appliquer les permissions.
Vérifiez l’installation :
docker --version

🗃️ Étape 3 : Démarrer la base de données ScyllaDB

Depuis la racine du projet :
docker compose up -d

Vérifiez que le conteneur est bien lancé :
docker ps

Vous devriez voir un conteneur nommé scylla-node-dev avec le statut Up.

🧩 Étape 4 : Créer le schéma de la base de données

Connectez-vous à ScyllaDB :
docker exec -it scylla-node-dev cqlsh

Dans la console cqlsh>, créez le keyspace :

CREATE KEYSPACE IF NOT EXISTS freelanceBd
WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
Puis quittez :

EXIT;

🧪 Étape 5 : Lancer l'application Spring Boot

    Ouvrez le projet dans votre IDE préféré.

    Le fichier src/main/resources/application.properties est déjà configuré pour se connecter à ScyllaDB via Docker.

Depuis l'IDE :

    Lancez la classe DriverBackendApplication.java (méthode main).

Depuis le terminal :
./mvnw spring-boot:run
Si tout fonctionne, vous verrez un message de type :

... Started DriverBackendApplication in X.XXX seconds.
✅ Votre environnement est prêt !

🧰 Commandes Docker utiles
Action	Commande
Arrêter la base de données	docker compose down
Supprimer aussi les volumes (données)	docker compose down -v
Voir les logs de ScyllaDB	docker compose logs -f
