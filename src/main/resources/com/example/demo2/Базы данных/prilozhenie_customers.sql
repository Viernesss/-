-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: prilozhenie
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `idcustomers` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `fio` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `otv_l` varchar(45) NOT NULL,
  `otv_l_im` varchar(150) NOT NULL,
  `plan` varchar(300) NOT NULL,
  `dogovor` varchar(300) NOT NULL,
  PRIMARY KEY (`idcustomers`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (6,'ООО Надежда','Ковалёва Ольга Дмитриевна','г.Ростов-на-Дону, пер.Университетский, 115','Енина Анастасия Евгеньевна','Дербинина Александра Ивановна','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Ковалёва Ольга Дмитриевна/План.jpg','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Ковалёва Ольга Дмитриевна'),(9,'Калинин Евгений Анатольевич','Калинин Евгений Анатольевич','г.Ростов-на-Дону, пер. Семашко, 12','Деревянкина Дарья Сергеевна','Солнцева Дарина Алексеевна','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Калинин Евгений Анатольевчи/План.jpg','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Калинин Евгений Анатольевчи'),(10,'ОАО Славянка','Плужникова Никита Андреевич','г.Старый Оскол, ул.Иванова, 15','Плужникова Яна Андреевна','Деревянкина Дарья Дмитриевна','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Плужникова Никита Андреевич/План.jpg','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Плужникова Никита Андреевич'),(11,'ООО Контекст','Юрьев Александр Олегович','г.Рязань, ул.Сомова, 15','Елизарова Инга Валерьевна','Кортышов Никита Сергеевич','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Юрьев Александр Олегович/План.jpg','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Юрьев Александр Олегович'),(12,'Ерёмин Олег Анатольевич','Ерёмин Олег Анатольевич','г.Владивосток, ул. Кирова,11','Пиратова Юлия Евгеньевна','Пиратов Юлия Евгеньевна','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Ерёмин Олег Анатольевич/План.jpg','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Ерёмин Олег Анатольевич'),(13,'ИП Символ','Круг Елизавета Романовна','г.Сочи, ул.Красноармейская, 23','Зенина Олеся Александровна','Зенина Антон Антонович','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Круг Елизавета Романовна/План.jpg','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Круг Елизавета Романовна'),(14,'Хурда Катарина Романовна','Хурда Катарина Романовна','г.Белгород, ул.Губкина, 16','Хурда Катарина Романовна','Хурда Катарина Романовна','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Хурда Катарина Романовна/План.jpg','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Хурда Катарина Романовна'),(16,'Лемина Елизавета Георгиевна','Лемина Елизавета Георгиевна','Лемина Елизавета Георгиевна','Лемина Елизавета Георгиевна','Лемина Елизавета Георгиевна','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Лемина Елизавета Георгиевна/План.jpg','C:/Users/veron/IdeaProjects/Prilozhenie/src/main/resources/com/example/demo2/Лемина Елизавета Георгиевна');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-13 10:30:54
