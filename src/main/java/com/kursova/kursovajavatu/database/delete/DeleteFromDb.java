package com.kursova.kursovajavatu.database.delete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class DeleteFromDb {

    private Connection con;
    private final Logger logger;
    private final String databaseName;

    public DeleteFromDb(Connection con, String databaseName){
        this.con = con;
        this.databaseName = databaseName;
        this.logger = LoggerFactory.getLogger(DeleteFromDb.class);
    }


}
