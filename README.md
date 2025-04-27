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
- [Exécution](#-exécution)
- [Structure du projet](#-structure-du-projet)
- [Aperçu du projet](#-aperçu-du-projet)
- [Auteur](#-auteur)

---

## 📖 Description
Application Java inspirée du jeu *Plants vs Zombies, utilisant **Spring JDBC* pour la gestion de la base de données.  
L'objectif est de reproduire une mécanique de jeu stratégique entre plantes et zombies, avec une structure backend propre et scalable.

---

## 🔧 Prérequis
- Java JDK 17 ou supérieur
- MySQL Server
- Apache Maven

---

## 🛠️ Configuration de la base de données

1. Créer une base de données MySQL nommée pvz :

```sql
CREATE DATABASE pvz;
