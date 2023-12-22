package com.kursova.kursovajavatu.database.insert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class InsertIntoDb {

    private Connection con;
    private final Logger logger;
    private final String databaseName;

    public InsertIntoDb(Connection con, String databaseName) {
        this.con = con;
        this.databaseName = databaseName;
        this.logger = LoggerFactory.getLogger(InsertIntoDb.class);
    }
}