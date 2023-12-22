package com.kursova.kursovajavatu.database.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class ReadFromDb {

    private Connection con;
    private final Logger logger;
    private final String databaseName;

    public ReadFromDb(Connection con, String databaseName){
        this.con = con;
        this.databaseName = databaseName;
        this.logger = LoggerFactory.getLogger(ReadFromDb.class);
    }

}
