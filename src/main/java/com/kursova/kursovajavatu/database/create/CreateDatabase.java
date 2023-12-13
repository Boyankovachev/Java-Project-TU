package com.kursova.kursovajavatu.database.create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {

    private String connString;
    private Connection con;
    public String databaseName = "defaultdb";

    public CreateDatabase(){
        this.connString = "jdbc:mysql://java-project-tu-java-project.a.aivencloud.com:12762/defaultdb?sslmode=require";
        try {
            this.con = DriverManager.getConnection(connString, "avnadmin", "AVNS_AU_YiFdrRjYF2jIbAXR");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createWholeDb(){
        //creates whole relational database
//        createDb();
        createUserTable();
        createStockTable();
        createNotificationTable();
        createStockPurchaseInfoTable();
        createPassiveResourceInfoTable();
        createIndexTable();
        createIndexPurchaseInfoTable();
        createCryptoTable();
        createCryptoPurchaseInfoTable();
        createCommodityTable();
        createCommodityPurchaseInfoTable();
    }

//    private void createDb(){
//        //create database
//        try{
//            Statement st = con.createStatement();
//            String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName + ";";
//            st.execute(sql);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    private void createUserTable(){
        //create user table
        try {
            Statement DbStatement = con.createStatement();
            String sql =
                    "CREATE TABLE IF NOT EXISTS `"+databaseName+"`.`user`(\n" +
                            "    `user_id` INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,\n" +
                            "    `username` VARCHAR(32) NOT NULL,\n" +
                            "    `password_hash` VARCHAR(64) NOT NULL,\n" +
                            "    `salt` VARCHAR(45) NOT NULL,\n" +
                            "    `email` VARCHAR(32),\n" +
                            "    `is_2fa_required` BOOL);";
            DbStatement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createStockTable(){
        //create stock table
        try {
            Statement DbStatement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `"+databaseName+"`.`stock`(\n" +
                    "    `symbol` VARCHAR(12) NOT NULL PRIMARY KEY,\n" +
                    "    `stock_name` VARCHAR(64) NOT NULL,\n" +
                    "    `currency` VARCHAR(32) NOT NULL,\n" +
                    "    `currency_symbol` VARCHAR(12) NOT NULL,\n" +
                    "    `exchange_name` VARCHAR(32),\n" +
                    "    `description` TEXT,\n" +
                    "    `current_market_price` DOUBLE,\n" +
                    "    `is_market_open` BOOL,\n" +
                    "    `recommendation_key` VARCHAR(32));\n";
            DbStatement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createNotificationTable(){
        //create notification table
        // 24.12.2020 - vij zapiskite
        try {
            Statement DbStatement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `"+databaseName+"`.`notification`(\n" +
                    "    `user_id` INT NOT NULL,\n" +
                    "    `asset_type` ENUM('stock', 'passive_resource', 'index', 'crypto', 'commodity', 'global') NOT NULL,\n" +
                    "    `asset_type_settings` BOOL,\n" +
                    "    `notification_target` VARCHAR(12),\n" +
                    "    `notification_price` DOUBLE NOT NULL,\n" +
                    "    `notification_name` VARCHAR(32) NOT NULL,\n" +
                    "    FOREIGN KEY (`user_id`) REFERENCES `" + databaseName + "`.user(`user_id`));\n";
            DbStatement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createStockPurchaseInfoTable(){
        //create stock purchase info table
        try {
            Statement DbStatement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `" + databaseName + "`.`stock_purchase_info`(\n" +
                    "    `user_id` INT NOT NULL,\n" +
                    "    `stock_symbol` VARCHAR(12) NOT NULL,\n" +
                    "    `price` DOUBLE NOT NULL,\n" +
                    "    `quantity` DOUBLE NOT NULL,\n" +
                    "    `purchase_date` DATE,\n" +
                    "    FOREIGN KEY(`user_id`) REFERENCES `" + databaseName + "`.user(`user_id`),\n" +
                    "    FOREIGN KEY(`stock_symbol`) REFERENCES `" + databaseName + "`.stock(`symbol`));\n";
            DbStatement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createPassiveResourceInfoTable(){
        //create passive resource table
        try {
            Statement DbStatement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `" + databaseName + "`.`passive_resource`(\n" +
                    "    `user_id` INT NOT NULL,\n" +
                    "    `name` VARCHAR(32) NOT NULL,\n" +
                    "    `purchase_price` DOUBLE NOT NULL,\n" +
                    "    `purchase_date` DATE,\n" +
                    "    `current_price` DOUBLE,\n" +
                    "    `description` TEXT,\n" +
                    "    `currency` VARCHAR(32),\n" +
                    "    `currency_symbol` VARCHAR(12),\n" +
                    "    FOREIGN KEY(`user_id`) REFERENCES `" + databaseName + "`.user(`user_Id`));\n";
            DbStatement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createIndexTable(){
        //create index table
        try {
            Statement DbStatement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `"+databaseName+"`.`index`(\n" +
                    "    `symbol` VARCHAR(12) NOT NULL PRIMARY KEY,\n" +
                    "    `index_name` VARCHAR(64) NOT NULL,\n" +
                    "    `currency` VARCHAR(32) NOT NULL,\n" +
                    "    `currency_symbol` VARCHAR(12) NOT NULL,\n" +
                    "    `exchange_name` VARCHAR(32),\n" +
                    "    `description` TEXT,\n" +
                    "    `current_market_price` DOUBLE,\n" +
                    "    `is_market_open` BOOL);\n";
            DbStatement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createIndexPurchaseInfoTable(){
        //create index purchase info table
        try {
            Statement DbStatement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `" + databaseName + "`.`index_purchase_info`(\n" +
                    "    `user_id` INT NOT NULL,\n" +
                    "    `index_symbol` VARCHAR(12) NOT NULL,\n" +
                    "    `price` DOUBLE NOT NULL,\n" +
                    "    `quantity` DOUBLE NOT NULL,\n" +
                    "    `purchase_date` DATE,\n" +
                    "    FOREIGN KEY(`user_id`) REFERENCES `" + databaseName + "`.user(`user_id`),\n" +
                    "    FOREIGN KEY(`index_symbol`) REFERENCES `" + databaseName + "`.index(`symbol`));\n";
            DbStatement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createCryptoTable(){
        //create crypto table
        try {
            Statement DbStatement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `"+databaseName+"`.`crypto`(\n" +
                    "    `symbol` VARCHAR(12) NOT NULL PRIMARY KEY,\n" +
                    "    `crypto_name` VARCHAR(64) NOT NULL,\n" +
                    "    `currency` VARCHAR(32) NOT NULL,\n" +
                    "    `currency_symbol` VARCHAR(12) NOT NULL,\n" +
                    "    `description` TEXT,\n" +
                    "    `current_market_price` DOUBLE);\n";
            DbStatement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createCryptoPurchaseInfoTable(){
        //create crypto purchase info table
        try {
            Statement DbStatement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `" + databaseName + "`.`crypto_purchase_info`(\n" +
                    "    `user_id` INT NOT NULL,\n" +
                    "    `crypto_symbol` VARCHAR(12) NOT NULL,\n" +
                    "    `price` DOUBLE NOT NULL,\n" +
                    "    `quantity` DOUBLE NOT NULL,\n" +
                    "    `purchase_date` DATE,\n" +
                    "    FOREIGN KEY(`user_id`) REFERENCES `" + databaseName + "`.user(`user_id`),\n" +
                    "    FOREIGN KEY(`crypto_symbol`) REFERENCES `" + databaseName + "`.crypto(`symbol`));\n";
            DbStatement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createCommodityTable(){
        //create commodity table
        try {
            Statement DbStatement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `"+databaseName+"`.`commodity`(\n" +
                    "    `commodity_name` VARCHAR(64) NOT NULL PRIMARY KEY,\n" +
                    "    `currency` VARCHAR(64) NOT NULL,\n" +
                    "    `currency_symbol` VARCHAR(12) NOT NULL,\n" +
                    "    `exchange_name` VARCHAR(32),\n" +
                    "    `description` TEXT,\n" +
                    "    `current_market_price` DOUBLE);\n";
            DbStatement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createCommodityPurchaseInfoTable(){
        //create commodity purchase info table
        try {
            Statement DbStatement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `" + databaseName + "`.`commodity_purchase_info`(\n" +
                    "    `user_id` INT NOT NULL,\n" +
                    "    `commodity_name` VARCHAR(64) NOT NULL,\n" +
                    "    `price` DOUBLE NOT NULL,\n" +
                    "    `quantity` DOUBLE NOT NULL,\n" +
                    "    `purchase_date` DATE,\n" +
                    "    FOREIGN KEY(`user_id`) REFERENCES `" + databaseName + "`.user(`user_id`),\n" +
                    "    FOREIGN KEY(`commodity_name`) REFERENCES `" + databaseName + "`.commodity(`commodity_name`));\n";
            DbStatement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
