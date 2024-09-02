DROP TABLE IF EXISTS `cabinet_medecin`;
CREATE TABLE `cabinet_medecin` (
  `id_cabinet` int DEFAULT NULL,
  `matricule` int DEFAULT NULL,
  KEY `id_cabinet` (`id_cabinet`),
  KEY `matricule` (`matricule`),
  CONSTRAINT `cabinet_medecin_ibfk_1` FOREIGN KEY (`id_cabinet`) REFERENCES `cabinet_medical` (`id_cabinet`),
  CONSTRAINT `cabinet_medecin_ibfk_2` FOREIGN KEY (`matricule`) REFERENCES `medecin` (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `cabinet_medecin` WRITE;

INSERT INTO `cabinet_medecin` VALUES (1,1),(2,2),(3,3),(1,4);

DROP TABLE IF EXISTS `cabinet_medical`;

CREATE TABLE `cabinet_medical` (
  `id_cabinet` int NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) NOT NULL,
  `nom` varchar(100) NOT NULL,
  PRIMARY KEY (`id_cabinet`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `cabinet_medical` WRITE;
/*!40000 ALTER TABLE `cabinet_medical` DISABLE KEYS */;
INSERT INTO `cabinet_medical` VALUES (1,'123 Rue Cheikh Anta Diop, Dakar','Cabinet Médical Dakar'),(2,'456 Avenue Léopold Sédar Senghor, Thiès','Cabinet Santé Thiès'),(3,'789 Boulevard du Président, Saint-Louis','Clinique Saint-Louis'),(4,'101 Rue Blaise Diagne, Ziguinchor','Cabinet Médical Ziguinchor'),(5,'123 Rue Cheikh Anta Diop, Dakar','Cabinet Médical Dakar Dakar Dakar'),(6,'123 Rue Cheikh Anta Diop, Dakar','Cabinet Médical Dakar Dakar Dakar updated'),(7,'123 Rue Cheikh Anta Diop, Dakar','Cabinet Médical Dakar Dakar Dakar updated'),(8,'123 Rue Keur masssar, Dakar','Cabinet Médical Dakar');
/*!40000 ALTER TABLE `cabinet_medical` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medecin`
--

DROP TABLE IF EXISTS `medecin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medecin` (
  `matricule` int NOT NULL AUTO_INCREMENT,
  `specialite` varchar(100) NOT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mot_de_passe` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`matricule`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medecin`
--

LOCK TABLES `medecin` WRITE;
/*!40000 ALTER TABLE `medecin` DISABLE KEYS */;
INSERT INTO `medecin` VALUES (1,'Cardiologie','77 123 45 67','Mamadou','Fall','mamadou.fall@example.sn','mdp12345'),(2,'Pédiatrie','77 234 56 78','Aissatou','Diouf','aissatou.diouf@example.sn','mdp23456'),(3,'Radiologie','77 345 67 89','Ousmane','Ba','ousmane.ba@example.sn','mdp34567'),(4,'Dermatologie','77 456 78 90','Fatou','Ndoye','fatou.ndoye@example.sn','mdp45678');
/*!40000 ALTER TABLE `medecin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id_notification` int NOT NULL AUTO_INCREMENT,
  `contenu` text,
  `date_envoie` date DEFAULT NULL,
  `id_rendezvous` int DEFAULT NULL,
  PRIMARY KEY (`id_notification`),
  KEY `id_rendezvous` (`id_rendezvous`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`id_rendezvous`) REFERENCES `rendezvous` (`id_rendezvous`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'Contenu de la notification 1','2024-08-01',1),(2,'Contenu de la notification 2','2024-08-02',2),(4,'Contenu de la notification 4','2024-08-04',4),(5,'Contenu de la notification 5','2024-08-05',5),(6,'Contenu de la notification 1 inconnu inconnu inconnu 66','2024-08-01',1),(7,'Contenu de la notification 1 inconnu inconnu inconnu','2024-08-01',1),(8,'Contenu de la notification nouvelle','2024-08-01',2),(9,'Expliquer c vraiment difiicile','2024-08-19',NULL),(10,'Expliquer c vraiment difiicile hhh','2024-08-19',NULL);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paiement`
--

DROP TABLE IF EXISTS `paiement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paiement` (
  `id_paiement` int NOT NULL AUTO_INCREMENT,
  `mode_paiement` varchar(50) DEFAULT NULL,
  `montant` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_paiement`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paiement`
--

LOCK TABLES `paiement` WRITE;
/*!40000 ALTER TABLE `paiement` DISABLE KEYS */;
INSERT INTO `paiement` VALUES (1,'Mode11',200.50),(2,'Mode2',150.75),(3,'Mode3',300.20),(4,'Mode4',250.00),(5,'Mode5',100.00);
/*!40000 ALTER TABLE `paiement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription` (
  `id_prescription` int NOT NULL AUTO_INCREMENT,
  `medicament` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `id_rendezvous` int DEFAULT NULL,
  PRIMARY KEY (`id_prescription`),
  KEY `id_rendezvous` (`id_rendezvous`),
  CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`id_rendezvous`) REFERENCES `rendezvous` (`id_rendezvous`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` VALUES (2,'Medicament2','2024-08-02',2),(3,'Medicament3','2024-08-03',3),(4,'Medicament4','2024-08-04',4);
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rendezvous`
--

DROP TABLE IF EXISTS `rendezvous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rendezvous` (
  `id_rendezvous` int NOT NULL AUTO_INCREMENT,
  `date_rv` date DEFAULT NULL,
  `heure_rv` time DEFAULT NULL,
  `duree` int DEFAULT NULL,
  `id_paiement` int DEFAULT NULL,
  `id_cabinet` int DEFAULT NULL,
  PRIMARY KEY (`id_rendezvous`),
  KEY `id_paiement` (`id_paiement`),
  KEY `id_cabinet` (`id_cabinet`),
  CONSTRAINT `rendezvous_ibfk_1` FOREIGN KEY (`id_paiement`) REFERENCES `paiement` (`id_paiement`),
  CONSTRAINT `rendezvous_ibfk_2` FOREIGN KEY (`id_cabinet`) REFERENCES `cabinet_medical` (`id_cabinet`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rendezvous`
--

LOCK TABLES `rendezvous` WRITE;
/*!40000 ALTER TABLE `rendezvous` DISABLE KEYS */;
INSERT INTO `rendezvous` VALUES (1,'2024-08-06','10:30:00',60,1,1),(2,'2024-08-07','11:00:00',45,2,2),(3,'2024-08-08','12:00:00',30,3,3),(4,'2024-08-09','13:30:00',90,4,NULL),(5,'2025-08-10','21:55:00',60,5,NULL),(7,'2055-08-10','21:55:00',11,5,NULL),(9,'2024-08-06','17:30:00',60,1,NULL),(14,'2024-08-19','05:41:26',1150,NULL,NULL),(15,'2024-08-19','05:41:26',1150,NULL,NULL),(16,'2024-08-19','05:41:26',1150,NULL,NULL);
/*!40000 ALTER TABLE `rendezvous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salle`
--

DROP TABLE IF EXISTS `salle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salle` (
  `id_salle` int NOT NULL AUTO_INCREMENT,
  `numero_salle` varchar(100) DEFAULT NULL,
  `nom_salle` varchar(100) DEFAULT NULL,
  `id_cabinet` int DEFAULT NULL,
  PRIMARY KEY (`id_salle`),
  KEY `id_cabinet` (`id_cabinet`),
  CONSTRAINT `salle_ibfk_1` FOREIGN KEY (`id_cabinet`) REFERENCES `cabinet_medical` (`id_cabinet`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salle`
--

LOCK TABLES `salle` WRITE;
/*!40000 ALTER TABLE `salle` DISABLE KEYS */;
INSERT INTO `salle` VALUES (1,'BLOCA10','Consultation 1',1),(2,'BLOCB12','Consultation 2',1),(3,'BLOCC2','Radiologie',2),(4,'BLOCD3','Pédiatrie',3);
/*!40000 ALTER TABLE `salle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `traitement`
--

DROP TABLE IF EXISTS `traitement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `traitement` (
  `id_traitement` int NOT NULL AUTO_INCREMENT,
  `id_salle` int DEFAULT NULL,
  `matricule_medecin` int DEFAULT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `id_rendezvous` int DEFAULT NULL,
  PRIMARY KEY (`id_traitement`),
  KEY `id_salle` (`id_salle`),
  KEY `matricule_medecin` (`matricule_medecin`),
  KEY `id_rendezvous` (`id_rendezvous`),
  CONSTRAINT `traitement_ibfk_1` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id_salle`),
  CONSTRAINT `traitement_ibfk_2` FOREIGN KEY (`matricule_medecin`) REFERENCES `medecin` (`matricule`),
  CONSTRAINT `traitement_ibfk_3` FOREIGN KEY (`id_rendezvous`) REFERENCES `rendezvous` (`id_rendezvous`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `traitement`
--

LOCK TABLES `traitement` WRITE;
/*!40000 ALTER TABLE `traitement` DISABLE KEYS */;
INSERT INTO `traitement` VALUES (1,1,1,'TraitementA',1),(2,2,2,'TraitementB',2),(3,3,3,'TraitementC',3),(4,4,4,'TraitementD',4),(8,1,1,'TraitementXXX',1);
/*!40000 ALTER TABLE `traitement` ENABLE KEYS */;
UNLOCK TABLES;

