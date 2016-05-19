CREATE DATABASE  IF NOT EXISTS `openauthdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `openauthdb`;
-- MySQL dump 10.13  Distrib 5.6.19, for Win32 (x86)
--
-- Host: localhost    Database: openauthdb
-- ------------------------------------------------------
-- Server version	5.6.23-enterprise-commercial-advanced-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `CascadeId` varchar(255) NOT NULL DEFAULT ' ',
  `Name` varchar(255) NOT NULL DEFAULT ' ',
  `ParentId` int(10) NOT NULL,
  `Status` int(10) NOT NULL,
  `SortNo` int(10) NOT NULL,
  `RootKey` varchar(100) NOT NULL DEFAULT ' ',
  `RootName` varchar(200) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_CATEGORY` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (7,'0.1','报表资源',0,0,0,'','');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dicdetail`
--

DROP TABLE IF EXISTS `dicdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dicdetail` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Value` varchar(100) NOT NULL DEFAULT ' ',
  `Text` varchar(100) NOT NULL DEFAULT '0',
  `DicId` int(10) NOT NULL,
  `SortNo` int(10) NOT NULL,
  `Status` int(10) NOT NULL,
  `Description` varchar(100) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_DICDETAIL` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dicdetail`
--

LOCK TABLES `dicdetail` WRITE;
/*!40000 ALTER TABLE `dicdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `dicdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dicindex`
--

DROP TABLE IF EXISTS `dicindex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dicindex` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL DEFAULT ' ',
  `Key` varchar(100) NOT NULL DEFAULT ' ',
  `SortNo` int(10) NOT NULL,
  `CategoryId` int(10) NOT NULL,
  `Description` varchar(200) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_DICINDEX` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dicindex`
--

LOCK TABLES `dicindex` WRITE;
/*!40000 ALTER TABLE `dicindex` DISABLE KEYS */;
/*!40000 ALTER TABLE `dicindex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `module` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `CascadeId` varchar(255) NOT NULL DEFAULT ' ',
  `Name` varchar(255) NOT NULL DEFAULT ' ',
  `Url` varchar(255) NOT NULL DEFAULT ' ',
  `HotKey` varchar(255) NOT NULL DEFAULT ' ',
  `ParentId` int(10) NOT NULL,
  `IsLeaf` bit(1) NOT NULL,
  `IsAutoExpand` bit(1) NOT NULL,
  `IconName` varchar(255) NOT NULL DEFAULT ' ',
  `Status` int(10) NOT NULL,
  `ParentName` varchar(255) NOT NULL DEFAULT ' ',
  `Vector` varchar(255) NOT NULL DEFAULT ' ',
  `SortNo` int(10) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_aos_sys_module` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (1,'0.1.1','模块管理','ModuleManager/Index',' ',2,'','\0',' ',1,' ',' ',0),(2,'0.1','基础配置',' ',' ',0,'','\0',' ',1,' ',' ',0),(4,'0.1.3','部门管理','OrgManager/Index','',2,'\0','\0','',0,'基础配置','',0),(5,'0.1.4','角色管理','RoleManager/Index','',2,'\0','\0','',0,'基础配置','',0),(6,'0.2','应用功能','/','',0,'\0','\0','',0,'根节点','',0),(7,'0.2.1','进出库管理','StockManager/Index','',6,'\0','\0','',0,'应用功能','',0),(8,'0.1.5','分类管理','CategoryManager/Index','',2,'\0','\0','',0,'基础配置','',0),(9,'0.1.2','用户管理','UserManager/Index','',2,'\0','\0','',0,'基础配置','',0),(10,'0.1.6','资源管理','ResourceManager/Index','',2,'\0','\0','',0,'基础配置','',0);
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moduleelement`
--

DROP TABLE IF EXISTS `moduleelement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moduleelement` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `DomId` varchar(255) NOT NULL DEFAULT ' ',
  `Name` varchar(255) NOT NULL DEFAULT ' ',
  `Type` varchar(50) NOT NULL DEFAULT ' ',
  `ModuleId` int(10) NOT NULL,
  `Attr` text NOT NULL,
  `Script` text NOT NULL,
  `Icon` varchar(255) NOT NULL DEFAULT ' ',
  `Classify` varchar(255) NOT NULL DEFAULT ' ',
  `Remark` varchar(200) NOT NULL DEFAULT ' ',
  `Sort` int(10) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_MODULEELEMENT` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moduleelement`
--

LOCK TABLES `moduleelement` WRITE;
/*!40000 ALTER TABLE `moduleelement` DISABLE KEYS */;
INSERT INTO `moduleelement` VALUES (2,'btnAdd','添加','a',3,'href=\'/UserManager/Add/\' data-toggle=\'dialog\' data-id=\'dialog-mask\' data-mask=\'true\' data-on-close=\'refreshGrid\'','javascript:;','plus','btn btn-green ','',0),(3,'btnEdit','编辑','button',3,'','editUser()','pencil','btn-green','',0),(4,'btnAccessModule','为用户分配模块','button',3,'','openUserModuleAccess(this)','pencil','btn-green','',0),(5,'btnAccessRole','为用户分配角色','button',3,'','openUserRoleAccess(this)','pencil','btn-green ','',0),(6,'btnRefresh','刷新','button',3,'','refreshUserGrid()','refresh','btn-green','',0),(7,'btnDel','删除','button',3,'','delUser()','pencil','btn-red ','',0),(10,'btnRefresh','刷新','button',4,'','refreshOrgGrid()','refresh','btn-green','',0),(11,'btnAdd','添加','a',4,'href=\'/OrgManager/AddOrg/\' data-toggle=\'dialog\' data-id=\'dialog-mask\' data-mask=\'true\' data-on-close=\'refreshGrid\'','javascript:;','plus','btn btn-green','',0),(12,'btnDel','删除','button',4,'','delOrg()','del','btn-red','',0),(19,'btnAdd','添加','a',5,'href=\'/RoleManager/Add/\' data-toggle=\'dialog\' data-id=\'dialog-mask\' data-mask=\'true\' data-on-close=\'refreshGrid\'','javascript:;','plus','btn btn-green ','',0),(20,'btnEdit','编辑','button',5,'','editRole()','pencil','btn-green','',0),(21,'btnAccessModule','为角色分配模块','button',5,'','assignRoleModule(this)','pencil','btn-green','',0),(23,'btnRefresh','刷新','button',5,'','refreshRoleGrid()','refresh','btn-green','',0),(24,'btnDel','删除','button',5,'','delRole()','pencil','btn-red ','',0),(25,'btnAdd','添加','a',1,'href=\'/ModuleManager/Add/\' data-toggle=\'dialog\' data-id=\'dialog-mask\' data-mask=\'true\' data-on-close=\'refreshGrid\'','javascript:;','plus','btn btn-green ','',0),(26,'btnEdit','编辑','button',1,'','editModule()','pencil','btn-green','',0),(27,'btnAssign','为模块分配按钮','button',1,'','assignButton()','pencil','btn-green','',0),(28,'btnRefresh','刷新','button',1,'','refreshModuleGrid()','refresh','btn-green','',0),(29,'btnDel','删除','button',1,'','delModule()','pencil','btn-red ','',0),(30,'btnAssignElement','为用户分配菜单','button',3,'','openAssignUserElement(this)','pencil','btn-green','',0),(31,'btnAssignElement','为角色分配菜单','button',5,'','assignRoleElement(this)','pencil','btn-green','',0),(32,'btnAdd','添加','a',8,'href=\'/CategoryManager/Add/\' data-toggle=\'dialog\' data-id=\'dialog-mask\' data-mask=\'true\' data-on-close=\'refreshGrid\'','','plus','btn btn-green','',0),(33,'btnDel','删除','button',8,' ','delCategory()','pencil','btn-red','',0),(34,'btnEdit','编辑','button',8,' ','editCategory()','pencil','btn-green','',0),(35,'btnAdd','添加','a',9,'href=\'/UserManager/Add/\' data-toggle=\'dialog\' data-id=\'dialog-mask\' data-mask=\'true\' data-on-close=\'refreshGrid\'','javascript:;','plus','btn btn-green ','',0),(36,'btnEdit','编辑','button',9,' ','editUser()','pencil','btn-green','',0),(37,'btnAccessModule','为用户分配模块','button',9,' ','openUserModuleAccess(this)','pencil','btn-green','',0),(38,'btnAccessRole','为用户分配角色','button',9,' ','openUserRoleAccess(this)','pencil','btn-green ','',0),(39,'btnRefresh','刷新','button',9,' ','refreshUserGrid()','refresh','btn-green','',0),(40,'btnDel','删除','button',9,' ','delUser()','pencil','btn-red ','',0),(41,'btnAssignElement','为用户分配菜单','button',9,' ','openAssignUserElement(this)','pencil','btn-green','',0),(43,'btnAdd','添加','a',10,'href=\'/ResourceManager/Add/\' data-toggle=\'dialog\' data-id=\'dialog-mask\' data-mask=\'true\' data-on-close=\'refreshGrid\'','javascript:;','pencil','btn btn-green','',0),(44,'btnEdit','编辑','button',10,' ','editResource()','pencil','btn-green','',0),(45,'btnDel','删除','button',10,' ','delResource()','plus','btn-red','',0),(46,'btnAssignReource','为用户分配资源','button',9,' ','openUserReourceAccess(this)','pencil','btn-green','',0),(47,'btnAssignRes','为角色分配资源','button',5,' ','openRoleReourceAccess(this)','pencil','btn-green','',0),(48,'btnAddStock','添加','a',7,'href=\'/StockManager/Add/\' data-toggle=\'dialog\' data-id=\'dialog-mask\' data-mask=\'true\' data-on-close=\'refreshGrid\'','javascript:;','pencil','btn btn-green','',0),(50,'btnDelStock','删除','button',7,' ','delStock()','plus','btn-red','',0),(51,'btnEditStock','编辑','button',7,' ','editStock()','pencil','btn-green','',0),(52,'btnAccessOrg','为角色分配部门','button',5,' ','assignRoleOrg(this)','pencil','btn-green','',0),(53,'btnOpenUserOrgAccess','为用户分配部门权限','button',9,' ','openUserOrgAccess(this)','pencil','btn-green','',0);
/*!40000 ALTER TABLE `moduleelement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org`
--

DROP TABLE IF EXISTS `org`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `CascadeId` varchar(255) NOT NULL DEFAULT ' ',
  `Name` varchar(255) NOT NULL DEFAULT ' ',
  `HotKey` varchar(255) NOT NULL DEFAULT ' ',
  `ParentId` int(10) NOT NULL,
  `ParentName` varchar(255) NOT NULL DEFAULT ' ',
  `IsLeaf` bit(1) NOT NULL,
  `IsAutoExpand` bit(1) NOT NULL,
  `IconName` varchar(255) NOT NULL DEFAULT ' ',
  `Status` int(10) NOT NULL,
  `Type` int(10) NOT NULL,
  `BizCode` varchar(255) NOT NULL DEFAULT ' ',
  `CustomCode` text NOT NULL,
  `CreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CreateId` int(10) NOT NULL,
  `SortNo` int(10) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_ORG` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org`
--

LOCK TABLES `org` WRITE;
/*!40000 ALTER TABLE `org` DISABLE KEYS */;
INSERT INTO `org` VALUES (1,'0.1','集团总部','',0,'根节点','\0','\0','',0,0,'','','2015-12-05 13:51:36',0,0),(2,'0.1.1','研发部','',1,'集团总部','\0','\0','',0,0,'','','2015-12-29 09:03:45',0,0),(3,'0.1.2','生产部','',1,'集团总部','\0','\0','',0,0,'','','2015-12-29 09:03:58',0,0),(4,'0.1.2.1','生产1组','',3,'生产部','\0','\0','',0,0,'','','2015-12-29 09:04:45',0,0);
/*!40000 ALTER TABLE `org` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `param`
--

DROP TABLE IF EXISTS `param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `param` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Value` varchar(100) NOT NULL DEFAULT ' ',
  `Key` varchar(100) NOT NULL DEFAULT ' ',
  `CategoryId` int(10) NOT NULL,
  `SortNo` int(10) NOT NULL,
  `Status` int(10) NOT NULL,
  `Description` varchar(100) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_PARAM` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `param`
--

LOCK TABLES `param` WRITE;
/*!40000 ALTER TABLE `param` DISABLE KEYS */;
/*!40000 ALTER TABLE `param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relevance`
--

DROP TABLE IF EXISTS `relevance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relevance` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `FirstId` int(10) NOT NULL,
  `SecondId` int(10) NOT NULL,
  `Description` varchar(100) NOT NULL DEFAULT ' ',
  `Key` varchar(100) NOT NULL DEFAULT ' ',
  `Status` int(10) NOT NULL,
  `OperateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `OperatorId` int(10) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_RELEVANCE` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relevance`
--

LOCK TABLES `relevance` WRITE;
/*!40000 ALTER TABLE `relevance` DISABLE KEYS */;
INSERT INTO `relevance` VALUES (50,21,10,'','UserElement',0,'2015-12-07 16:20:55',0),(51,21,11,'','UserElement',0,'2015-12-07 16:20:55',0),(52,1,1,'','UserOrg',0,'2015-12-19 08:46:26',0),(53,1,2,'','RoleModule',0,'2015-12-19 15:01:08',0),(54,1,1,'','RoleModule',0,'2015-12-19 15:01:08',0),(55,1,4,'','RoleModule',0,'2015-12-19 15:01:08',0),(56,1,5,'','RoleModule',0,'2015-12-19 15:01:08',0),(57,1,8,'','RoleModule',0,'2015-12-19 15:01:08',0),(58,1,9,'','RoleModule',0,'2015-12-19 15:01:08',0),(59,1,10,'','RoleModule',0,'2015-12-19 15:01:08',0),(64,1,1,'','UserRole',0,'2015-12-22 03:34:12',0),(65,1,2,'','UserRole',0,'2015-12-22 03:34:12',0),(66,1,2,'','UserResource',0,'2015-12-22 06:59:47',0),(67,1,3,'','UserResource',0,'2015-12-22 06:59:49',0),(68,1,4,'','RoleResource',0,'2015-12-22 07:00:17',0),(69,2,6,'','RoleModule',0,'2015-12-29 09:06:33',0),(70,2,7,'','RoleModule',0,'2015-12-29 09:06:33',0),(84,1,10,'','RoleElement',0,'2015-12-29 09:11:30',0),(85,1,11,'','RoleElement',0,'2015-12-29 09:11:30',0),(86,1,12,'','RoleElement',0,'2015-12-29 09:11:30',0),(92,2,1,'','UserAccessedOrg',0,'2016-01-07 03:19:46',0),(93,2,3,'','UserAccessedOrg',0,'2016-01-07 03:19:46',0),(94,2,4,'','UserAccessedOrg',0,'2016-01-07 03:19:46',0),(95,1,25,'','RoleElement',0,'2016-01-07 08:14:01',0),(96,1,26,'','RoleElement',0,'2016-01-07 08:14:01',0),(97,1,27,'','RoleElement',0,'2016-01-07 08:14:01',0),(98,1,28,'','RoleElement',0,'2016-01-07 08:14:01',0),(99,1,21,'','RoleElement',0,'2016-01-07 08:14:09',0),(100,1,23,'','RoleElement',0,'2016-01-07 08:14:09',0),(101,1,24,'','RoleElement',0,'2016-01-07 08:14:09',0),(102,2,48,'','RoleElement',0,'2016-01-08 02:28:00',0),(103,2,50,'','RoleElement',0,'2016-01-08 02:28:00',0),(104,2,51,'','RoleElement',0,'2016-01-08 02:28:00',0),(105,2,1,'','RoleAccessedOrg',0,'2016-01-08 02:28:11',0),(106,2,3,'','RoleAccessedOrg',0,'2016-01-08 02:28:11',0),(107,2,4,'','RoleAccessedOrg',0,'2016-01-08 02:28:11',0),(109,5,3,'','UserOrg',0,'2016-01-08 02:30:50',0),(110,5,2,'','UserRole',0,'2016-01-08 02:31:07',0);
/*!40000 ALTER TABLE `relevance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `CascadeId` varchar(255) NOT NULL DEFAULT ' ',
  `Key` varchar(200) NOT NULL DEFAULT ' ',
  `Name` varchar(255) NOT NULL DEFAULT ' ',
  `ParentId` int(10) NOT NULL,
  `Status` int(10) NOT NULL,
  `SortNo` int(10) NOT NULL,
  `CategoryId` int(10) NOT NULL,
  `Description` text NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_RESOURCE` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES (2,'','REPORT_NAME','报表名称',0,0,0,7,'报表名称'),(3,'','REPORT_1','报表1月数据',0,0,0,7,'报表1月数据'),(4,'','REPORT_2','报表2月数据',0,0,0,7,'报表1月数据');
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL DEFAULT ' ',
  `Status` int(10) NOT NULL,
  `Type` int(10) NOT NULL,
  `CreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CreateId` varchar(64) NOT NULL DEFAULT ' ',
  `OrgId` int(10) NOT NULL,
  `OrgCascadeId` varchar(255) NOT NULL DEFAULT ' ',
  `OrgName` varchar(255) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_ROLE` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'管理员',0,0,'2015-12-05 14:26:57','',1,'0.1','集团总部'),(2,'生产部主管',0,1,'2015-12-19 15:00:26','',0,'0.1.2','生产部');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` text NOT NULL,
  `Number` int(10) NOT NULL,
  `Price` decimal(10,1) NOT NULL,
  `Status` int(10) NOT NULL,
  `User` varchar(50) NOT NULL DEFAULT ' ',
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `OrgId` int(10) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_STOCK` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (2,'权限管理软件一套',1,10000.0,0,'System','2016-01-08 01:15:24',2),(3,'高级权限管理',1,100000.0,0,'System','2016-01-08 01:15:49',4);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Account` varchar(255) NOT NULL DEFAULT ' ',
  `Password` varchar(255) NOT NULL DEFAULT ' ',
  `Name` varchar(255) NOT NULL DEFAULT ' ',
  `Sex` int(10) NOT NULL,
  `Status` int(10) NOT NULL,
  `Type` int(10) NOT NULL,
  `BizCode` varchar(255) NOT NULL DEFAULT ' ',
  `CreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CreateId` int(10) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_USER` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','管理员',0,0,0,'','2015-12-19 08:46:26',0),(5,'test','test','test',0,0,0,'','2016-01-08 02:30:50',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usercfg`
--

DROP TABLE IF EXISTS `usercfg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usercfg` (
  `Id` int(10) NOT NULL,
  `Theme` varchar(255) NOT NULL DEFAULT ' ',
  `Skin` varchar(255) NOT NULL DEFAULT ' ',
  `NavBarStyle` varchar(255) NOT NULL DEFAULT ' ',
  `TabFocusColor` varchar(255) NOT NULL DEFAULT ' ',
  `NavTabIndex` int(10) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_USERCFG` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercfg`
--

LOCK TABLES `usercfg` WRITE;
/*!40000 ALTER TABLE `usercfg` DISABLE KEYS */;
/*!40000 ALTER TABLE `usercfg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userext`
--

DROP TABLE IF EXISTS `userext`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userext` (
  `Id` int(10) NOT NULL,
  `Email` varchar(255) NOT NULL DEFAULT ' ',
  `Phone_` varchar(255) NOT NULL DEFAULT ' ',
  `Mobile` varchar(255) NOT NULL DEFAULT ' ',
  `Address` varchar(255) NOT NULL DEFAULT ' ',
  `Zip` varchar(255) NOT NULL DEFAULT ' ',
  `Birthday` varchar(255) NOT NULL DEFAULT ' ',
  `IdCard` varchar(255) NOT NULL DEFAULT ' ',
  `QQ` varchar(255) NOT NULL DEFAULT ' ',
  `DynamicField` text NOT NULL,
  `ByteArrayId` int(10) NOT NULL,
  `Remark` text NOT NULL,
  `Field1` varchar(255) NOT NULL DEFAULT ' ',
  `Field2` varchar(255) NOT NULL DEFAULT ' ',
  `Field3` varchar(255) NOT NULL DEFAULT ' ',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `PK_USEREXT` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userext`
--

LOCK TABLES `userext` WRITE;
/*!40000 ALTER TABLE `userext` DISABLE KEYS */;
/*!40000 ALTER TABLE `userext` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'openauthdb'
--

--
-- Dumping routines for database 'openauthdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-19 22:51:06
