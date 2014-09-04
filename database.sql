DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `ID`   INT(10) UNSIGNED     NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100)         NOT NULL,
  `AGE`  SMALLINT(5) UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =4
  DEFAULT CHARSET =utf8;


INSERT INTO `customer` (`ID`, `NAME`, `AGE`) VALUES
  (1, 'mkyong1', 28),
  (2, 'mkyong2', 28),
  (3, 'mkyong3', 28);