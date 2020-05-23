CREATE DATABASE  IF NOT EXISTS `library_directory`;
USE `library_directory`;

--
-- Table structure for table `library`
--

DROP TABLE IF EXISTS `library`;

CREATE TABLE `library` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `category` varchar(45)  NOT NULL,
  `publisher` varchar(45)  NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `library`
--

INSERT INTO `library` VALUES 
	(1,'Mortal Engines','Philip Reeve','Mystery','Pearson'),
   	(2,'Harry Potter ','J.K Rowling','Fantasy','Bloomsbury');
	
