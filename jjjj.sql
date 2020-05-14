CREATE SCHEMA IF NOT EXISTS `jjjj` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE jjjj;

-- -----------------------------------------------------
-- Table `jjjj`.`cliente`
-- ---------------------- -----------------------------   --

CREATE  TABLE IF NOT EXISTS `jjjj`.`cliente` (
 `id` INT NOT NULL ,
PRIMARY KEY (`id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `jjjj`.`pedido`
-- ---------------------- -----------------------------   --

CREATE  TABLE IF NOT EXISTS `jjjj`.`pedido` (
 `id` INT NOT NULL ,
 `idCliente` INT NOT NULL ,
PRIMARY KEY (`id`))
ENGINE = InnoDB;


ALTER TABLE `pedido` ADD CONSTRAINT `fk_cliente` FOREIGN KEY ( `idCliente` ) REFERENCES `jjjj`.`cliente` (`id`);


