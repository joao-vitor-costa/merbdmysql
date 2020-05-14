CREATE SCHEMA IF NOT EXISTS `Banco` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE Banco;

-- -----------------------------------------------------
-- Table `Banco`.`departamento`
-- ---------------------- -----------------------------   --

CREATE  TABLE IF NOT EXISTS `Banco`.`departamento` (
 `dataIni` DATE NULL ,
 `gerente` INT NULL ,
 `nome` VARCHAR(50) NOT NULL ,
)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `Banco`.`projeto`
-- ---------------------- -----------------------------   --

CREATE  TABLE IF NOT EXISTS `Banco`.`projeto` (
 `idDepartamento` INT NOT NULL ,
 `localproj` VARCHAR(35) NULL ,
 `nome` VARCHAR(50) NULL ,
)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `Banco`.`dependente`
-- ---------------------- -----------------------------   --

CREATE  TABLE IF NOT EXISTS `Banco`.`dependente` (
 `id` INT NOT NULL ,
 `sexo` CHAR(1) NULL ,
 `nome` VARCHAR(50) NULL ,
 `idEmpregado` INT NOT NULL ,
PRIMARY KEY (`id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `Banco`.`alocacao`
-- ---------------------- -----------------------------   --

CREATE  TABLE IF NOT EXISTS `Banco`.`alocacao` (
 `id` INT NOT NULL ,
 `id` INT NOT NULL ,
 `horas` INT NULL ,
PRIMARY KEY (`id`,`id`))
ENGINE = InnoDB;



