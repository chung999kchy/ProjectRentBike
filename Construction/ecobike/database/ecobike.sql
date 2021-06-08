-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ecobike
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ecobike
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ecobike` DEFAULT CHARACTER SET utf8 ;
USE `ecobike` ;

-- -----------------------------------------------------
-- Table `ecobike`.`Station`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecobike`.`Station` (
  `stationId` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(255) NULL,
  PRIMARY KEY (`stationId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecobike`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecobike`.`Category` (
  `categoryId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `description` VARCHAR(255) NULL,
  `cost_per_hour` FLOAT NULL,
  `n_pedals` INT NULL,
  `n_seats` INT NULL,
  PRIMARY KEY (`categoryId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecobike`.`Bike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecobike`.`Bike` (
  `bikeId` INT NOT NULL AUTO_INCREMENT,
  `bikeName` VARCHAR(255) NULL,
  `stationId` INT NULL,
  `pin` FLOAT NULL,
  `status` INT NULL,
  `categoryId` INT NULL,
  PRIMARY KEY (`bikeId`),
  INDEX `fk_Bike_Category1_idx` (`categoryId` ASC) VISIBLE,
  INDEX `fk_Bike_Station1_idx` (`stationId` ASC) VISIBLE,
  CONSTRAINT `fk_Bike_Station1`
    FOREIGN KEY (`stationId`)
    REFERENCES `ecobike`.`Station` (`stationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bike_Category1`
    FOREIGN KEY (`categoryId`)
    REFERENCES `ecobike`.`Category` (`categoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecobike`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecobike`.`User` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `Usercol` VARCHAR(45) NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecobike`.`Rent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecobike`.`Rent` (
  `userId` INT NOT NULL,
  `bikeId` INT NOT NULL,
  `start_time` TIMESTAMP NULL,
  `end_time` TIMESTAMP NULL,
  PRIMARY KEY (`userId`, `bikeId`),
  INDEX `fk_Rent_Bike1_idx` (`bikeId` ASC) VISIBLE,
  CONSTRAINT `fk_Rent_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `ecobike`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rent_Bike1`
    FOREIGN KEY (`bikeId`)
    REFERENCES `ecobike`.`Bike` (`bikeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecobike`.`Transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecobike`.`Transaction` (
  `transactionId` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NULL,
  `total_payment` FLOAT NULL,
  `time` TIMESTAMP NULL,
  `bike_name` VARCHAR(255) NULL,
  `rent_duration` VARCHAR(45) NULL,
  INDEX `fk_Transaction_User_idx` (`userId` ASC) VISIBLE,
  PRIMARY KEY (`transactionId`),
  CONSTRAINT `fk_Transaction_User`
    FOREIGN KEY (`userId`)
    REFERENCES `ecobike`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
