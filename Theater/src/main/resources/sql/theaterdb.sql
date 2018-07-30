-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema theaterdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema theaterdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `theaterdb` DEFAULT CHARACTER SET utf8 ;
USE `theaterdb` ;

-- -----------------------------------------------------
-- Table `theaterdb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theaterdb`.`user` (
  `u_id` INT NOT NULL AUTO_INCREMENT,
  `u_name` VARCHAR(255) NOT NULL,
  `u_surname` VARCHAR(255) NOT NULL,
  `u_email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`u_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theaterdb`.`auditorium`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theaterdb`.`auditorium` (
  `au_id` INT NOT NULL AUTO_INCREMENT,
  `au_name` VARCHAR(255) NOT NULL,
  `au_number_of_seats` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`au_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theaterdb`.`vipSeats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theaterdb`.`vipSeats` (
  `vp_id` INT NOT NULL AUTO_INCREMENT,
  `vp_seat_number` INT NOT NULL,
  `au_id` INT NOT NULL,
  PRIMARY KEY (`vp_id`),
  INDEX `fk_vipSeats_auditorium_idx` (`au_id` ASC),
  CONSTRAINT `fk_vipSeats_auditorium`
    FOREIGN KEY (`au_id`)
    REFERENCES `theaterdb`.`auditorium` (`au_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theaterdb`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theaterdb`.`event` (
  `ev_id` INT NOT NULL AUTO_INCREMENT,
  `ev_name` VARCHAR(45) NOT NULL,
  `ev_price` DOUBLE NOT NULL DEFAULT 0,
  `ev_raiting` ENUM('LOW', 'MID', 'HIGH') NOT NULL DEFAULT 'MID',
  PRIMARY KEY (`ev_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theaterdb`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theaterdb`.`ticket` (
  `t_id` INT NOT NULL AUTO_INCREMENT,
  `t_seat` INT NOT NULL,
  `u_id` INT NOT NULL,
  `ev_id` INT NOT NULL,
  `t_date` TIMESTAMP(6) NOT NULL,
  PRIMARY KEY (`t_id`),
  INDEX `fk_ticket_user1_idx` (`u_id` ASC),
  INDEX `fk_ticket_event1_idx` (`ev_id` ASC),
  CONSTRAINT `fk_ticket_user1`
    FOREIGN KEY (`u_id`)
    REFERENCES `theaterdb`.`user` (`u_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_event1`
    FOREIGN KEY (`ev_id`)
    REFERENCES `theaterdb`.`event` (`ev_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theaterdb`.`event_has_auditorium`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theaterdb`.`event_has_auditorium` (
  `ev_id` INT NOT NULL,
  `au_id` INT NOT NULL,
  PRIMARY KEY (`ev_id`, `au_id`),
  INDEX `fk_event_has_auditorium_auditorium1_idx` (`au_id` ASC),
  INDEX `fk_event_has_auditorium_event1_idx` (`ev_id` ASC),
  CONSTRAINT `fk_event_has_auditorium_event1`
    FOREIGN KEY (`ev_id`)
    REFERENCES `theaterdb`.`event` (`ev_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_has_auditorium_auditorium1`
    FOREIGN KEY (`au_id`)
    REFERENCES `theaterdb`.`auditorium` (`au_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
