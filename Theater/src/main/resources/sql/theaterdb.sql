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
  `u_firstname` VARCHAR(255) NOT NULL,
  `u_lastname` VARCHAR(255) NOT NULL,
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
  `t_date` TIMESTAMP NOT NULL,
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


-- -----------------------------------------------------
-- Table `theaterdb`.`airDates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theaterdb`.`airDates` (
  `ad_id` INT NOT NULL AUTO_INCREMENT,
  `ad_date` DATE NOT NULL,
  `ev_id` INT NOT NULL,
  PRIMARY KEY (`ad_id`),
  INDEX `fk_airDates_event1_idx` (`ev_id` ASC),
  CONSTRAINT `fk_airDates_event1`
    FOREIGN KEY (`ev_id`)
    REFERENCES `theaterdb`.`event` (`ev_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theaterdb`.`auditorium_date`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theaterdb`.`auditorium_date` (
  `aud_id` INT NOT NULL AUTO_INCREMENT,
  `aud_date` DATE NOT NULL,
  PRIMARY KEY (`aud_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theaterdb`.`auditorium_has_auditorium_date`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theaterdb`.`auditorium_has_auditorium_date` (
  `au_id` INT NOT NULL,
  `aud_id` INT NOT NULL,
  PRIMARY KEY (`au_id`, `aud_id`),
  INDEX `fk_auditorium_has_auditorium_date_auditorium_date1_idx` (`aud_id` ASC),
  INDEX `fk_auditorium_has_auditorium_date_auditorium1_idx` (`au_id` ASC),
  CONSTRAINT `fk_auditorium_has_auditorium_date_auditorium1`
    FOREIGN KEY (`au_id`)
    REFERENCES `theaterdb`.`auditorium` (`au_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_auditorium_has_auditorium_date_auditorium_date1`
    FOREIGN KEY (`aud_id`)
    REFERENCES `theaterdb`.`auditorium_date` (`aud_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theaterdb`.`statistic_counter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theaterdb`.`statistic_counter` (
  `cs_id` INT NOT NULL AUTO_INCREMENT,
  `cs_get_name_times` INT NOT NULL DEFAULT 0,
  `cs_ticket_was_booking_times` INT NOT NULL DEFAULT 0,
  `cs_get_price_times` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`cs_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theaterdb`.`statistic_discount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theaterdb`.`statistic_discount` (
  `sd_id` INT NOT NULL AUTO_INCREMENT,
  `sd_user_email` VARCHAR(255) NOT NULL,
  `sd_user_discount` VARCHAR(45) NOT NULL DEFAULT 0,
  PRIMARY KEY (`sd_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `theaterdb`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `theaterdb`;
INSERT INTO `theaterdb`.`user` (`u_id`, `u_firstname`, `u_lastname`, `u_email`) VALUES (DEFAULT, 'Nikita', 'Mihalchuk', 'Nikita_mihalchuk@epam.com');
INSERT INTO `theaterdb`.`user` (`u_id`, `u_firstname`, `u_lastname`, `u_email`) VALUES (DEFAULT, 'Andrey', 'Meleh', 'Andrey_Meleh@epam.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `theaterdb`.`auditorium`
-- -----------------------------------------------------
START TRANSACTION;
USE `theaterdb`;
INSERT INTO `theaterdb`.`auditorium` (`au_id`, `au_name`, `au_number_of_seats`) VALUES (DEFAULT, 'audit1', 150);
INSERT INTO `theaterdb`.`auditorium` (`au_id`, `au_name`, `au_number_of_seats`) VALUES (DEFAULT, 'audit2', 100);
INSERT INTO `theaterdb`.`auditorium` (`au_id`, `au_name`, `au_number_of_seats`) VALUES (DEFAULT, 'audit3', 200);
INSERT INTO `theaterdb`.`auditorium` (`au_id`, `au_name`, `au_number_of_seats`) VALUES (DEFAULT, 'audit4', 75);

COMMIT;


-- -----------------------------------------------------
-- Data for table `theaterdb`.`vipSeats`
-- -----------------------------------------------------
START TRANSACTION;
USE `theaterdb`;
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 1, 1);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 2, 1);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 3, 1);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 4, 1);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 1, 2);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 2, 2);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 3, 2);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 4, 2);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 1, 3);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 2, 3);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 3, 3);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 4, 3);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 1, 4);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 2, 4);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 3, 4);
INSERT INTO `theaterdb`.`vipSeats` (`vp_id`, `vp_seat_number`, `au_id`) VALUES (DEFAULT, 4, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `theaterdb`.`event`
-- -----------------------------------------------------
START TRANSACTION;
USE `theaterdb`;
INSERT INTO `theaterdb`.`event` (`ev_id`, `ev_name`, `ev_price`, `ev_raiting`) VALUES (DEFAULT, 'Event #1', 50, 'LOW');
INSERT INTO `theaterdb`.`event` (`ev_id`, `ev_name`, `ev_price`, `ev_raiting`) VALUES (DEFAULT, 'Event #2', 500, 'MID');
INSERT INTO `theaterdb`.`event` (`ev_id`, `ev_name`, `ev_price`, `ev_raiting`) VALUES (DEFAULT, 'Event #3', 150, 'HIGH');
INSERT INTO `theaterdb`.`event` (`ev_id`, `ev_name`, `ev_price`, `ev_raiting`) VALUES (DEFAULT, 'Event #4', 320, 'MID');
INSERT INTO `theaterdb`.`event` (`ev_id`, `ev_name`, `ev_price`, `ev_raiting`) VALUES (DEFAULT, 'Event #5', 75, 'LOW');

COMMIT;


-- -----------------------------------------------------
-- Data for table `theaterdb`.`ticket`
-- -----------------------------------------------------
START TRANSACTION;
USE `theaterdb`;
INSERT INTO `theaterdb`.`ticket` (`t_id`, `t_seat`, `u_id`, `ev_id`, `t_date`) VALUES (DEFAULT, 50, 1, 1, '2018-07-09');

COMMIT;


-- -----------------------------------------------------
-- Data for table `theaterdb`.`event_has_auditorium`
-- -----------------------------------------------------
START TRANSACTION;
USE `theaterdb`;
INSERT INTO `theaterdb`.`event_has_auditorium` (`ev_id`, `au_id`) VALUES (1, 1);
INSERT INTO `theaterdb`.`event_has_auditorium` (`ev_id`, `au_id`) VALUES (2, 2);
INSERT INTO `theaterdb`.`event_has_auditorium` (`ev_id`, `au_id`) VALUES (3, 3);
INSERT INTO `theaterdb`.`event_has_auditorium` (`ev_id`, `au_id`) VALUES (4, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `theaterdb`.`airDates`
-- -----------------------------------------------------
START TRANSACTION;
USE `theaterdb`;
INSERT INTO `theaterdb`.`airDates` (`ad_id`, `ad_date`, `ev_id`) VALUES (DEFAULT, '2018-07-09', 1);
INSERT INTO `theaterdb`.`airDates` (`ad_id`, `ad_date`, `ev_id`) VALUES (DEFAULT, '2018-01-12', 2);
INSERT INTO `theaterdb`.`airDates` (`ad_id`, `ad_date`, `ev_id`) VALUES (DEFAULT, '2017-07-09', 3);
INSERT INTO `theaterdb`.`airDates` (`ad_id`, `ad_date`, `ev_id`) VALUES (DEFAULT, '2018-12-12', 4);
INSERT INTO `theaterdb`.`airDates` (`ad_id`, `ad_date`, `ev_id`) VALUES (DEFAULT, '2018-02-04', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `theaterdb`.`auditorium_date`
-- -----------------------------------------------------
START TRANSACTION;
USE `theaterdb`;
INSERT INTO `theaterdb`.`auditorium_date` (`aud_id`, `aud_date`) VALUES (DEFAULT, '2018-01-09');
INSERT INTO `theaterdb`.`auditorium_date` (`aud_id`, `aud_date`) VALUES (DEFAULT, '2018-02-10');
INSERT INTO `theaterdb`.`auditorium_date` (`aud_id`, `aud_date`) VALUES (DEFAULT, '2018-05-05');
INSERT INTO `theaterdb`.`auditorium_date` (`aud_id`, `aud_date`) VALUES (DEFAULT, '2017-01-01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `theaterdb`.`auditorium_has_auditorium_date`
-- -----------------------------------------------------
START TRANSACTION;
USE `theaterdb`;
INSERT INTO `theaterdb`.`auditorium_has_auditorium_date` (`au_id`, `aud_id`) VALUES (1, 1);
INSERT INTO `theaterdb`.`auditorium_has_auditorium_date` (`au_id`, `aud_id`) VALUES (2, 2);
INSERT INTO `theaterdb`.`auditorium_has_auditorium_date` (`au_id`, `aud_id`) VALUES (3, 3);
INSERT INTO `theaterdb`.`auditorium_has_auditorium_date` (`au_id`, `aud_id`) VALUES (4, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `theaterdb`.`statistic_counter`
-- -----------------------------------------------------
START TRANSACTION;
USE `theaterdb`;
INSERT INTO `theaterdb`.`statistic_counter` (`cs_id`, `cs_get_name_times`, `cs_ticket_was_booking_times`, `cs_get_price_times`) VALUES (DEFAULT, 0, 0, 0);

COMMIT;

