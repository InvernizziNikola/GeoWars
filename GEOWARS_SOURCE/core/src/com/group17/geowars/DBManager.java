package com.group17.geowars;



import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {

    public ArrayList<String> resultselect;
    public ArrayList<String> lijst;


    public ArrayList DBselect() {
        try {
            String select = "SELECT * FROM howest.student;";
            resultselect = DBconnect(select, true);
        } catch (Exception e) {
            System.out.println("Fout in select: " + e.getMessage());
        }
        return resultselect;
    }
    // connectie naar DB
    public ArrayList DBconnect(String sqlString, boolean BoolSelect) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("Driver loaded!");

        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Driver niet gevonden, toevoegen via de properties: " + ex.getMessage());
        }
        MysqlDataSource dataSource = new MysqlDataSource();
        // gegevens van de database
        dataSource.setUser("root");
        dataSource.setPassword("Laiko123");
        dataSource.setPort(3306);
        dataSource.setServerName("127.0.0.1");
        dataSource.setDatabaseName("howest");

        try {
            java.sql.Connection conn = dataSource.getConnection();
            //System.out.println("connectie met database is gelukt");

            java.sql.Statement stmt = conn.createStatement();
            if (BoolSelect) {
                ResultSet databankgegevens = stmt.executeQuery(sqlString);

                ResultSetMetaData databankgegevensmd = databankgegevens.getMetaData();
                int aantalKolommen = databankgegevensmd.getColumnCount();

                lijst = new ArrayList<String>();
                while (databankgegevens.next()) {

                    for (int i = 1; i <= aantalKolommen; i++) {
                        lijst.add(databankgegevens.getString(i));

                    }

                }
                databankgegevens.close();
                stmt.close();
                //System.out.println("select geslaagd");
            } else {
                stmt.executeUpdate(sqlString);
                //System.out.println("query geslaagd");
                //sluiten van statement
                stmt.close();
            }
            //sluiten van connectie
            conn.close();
        } catch (Exception ex) {
            System.out.println("Dataconnectie gefaald: " + ex.getMessage());
        }

        return lijst;
    }
}
