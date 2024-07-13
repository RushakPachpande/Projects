CREATE DATABASE atm;

USE atm;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;

CREATE TABLE `accounts` (
  `acc_no` bigint NOT NULL,
  `email` varchar(45) NOT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  `security_pin` char(4) DEFAULT NULL,
  PRIMARY KEY (`acc_no`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `id` int NOT NULL,
  `trnc_date` varchar(100) NOT NULL,
  `type` varchar(15) DEFAULT NULL,
  `acc_no` bigint NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `transfer_acc` bigint DEFAULT NULL,
  `execution` varchar(15) DEFAULT NULL,
  `balance` decimal(10,2) NOT NULL,
  `security_pin` char(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
