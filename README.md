# 🌻 Projet PVZ (Plantes contre Zombies)

[![Java](https://img.shields.io/badge/Java-17%2B-blue?logo=java)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-orange?logo=mysql)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-BuildTool-red?logo=apachemaven)](https://maven.apache.org/)

---

## 📋 Sommaire
- [Description](#-description)
- [Prérequis](#-prérequis)
- [Configuration de la base de données](#-configuration-de-la-base-de-données)
- [Installation](#-installation)
- [Auteur](#-auteur)

---

## 📖 Description
Application Java inspirée du jeu *Plants vs Zombies, utilisant **Spring JDBC* pour la gestion de la base de données.  
L'objectif est de reproduire une mécanique de jeu stratégique entre plantes et zombies.

---

## 🔧 Prérequis
- Java JDK 17 ou supérieur
- MySQL Server
- Apache Maven


## Configuration Base de données
1. Créer une base de données MySQL nommée `pvz`
2. Créer un utilisateur MySQL :
   ```sql
   CREATE USER 'epf'@'localhost' IDENTIFIED BY 'admin';
   GRANT ALL PRIVILEGES ON pvz.* TO 'epf'@'localhost';
   FLUSH PRIVILEGES;
   ```
   
## Installation
1. Cloner le projet
2. Configurer la base de données comme indiqué ci-dessus
3. Compiler le projet avec Maven :
   ```bash
   mvn clean install
   ----
## Auteur 
Réaliseé par **Nehade EL Mokhtari**
