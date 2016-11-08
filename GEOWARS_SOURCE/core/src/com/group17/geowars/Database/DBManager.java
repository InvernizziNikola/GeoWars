package com.group17.geowars.database;



import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {

    public ArrayList<String> resultselect;
    public ArrayList<String> list;


    public ArrayList DBselect(String selectValue,String tabel,String columName,String value) {
        try {
            String SQLstring2 = "SELECT * FROM profile where IDProfile = '1'";
            String SQLstring = "SELECT "+ selectValue +" FROM "+tabel+" where " +columName+"= \""+value+"\";";
            resultselect = DBconnect(SQLstring, true);
        } catch (Exception e) {
            System.out.println("Fout in select: " + e.getMessage());
        }
        return resultselect;
    }
    public boolean DBupdate(String tabel,String columName,String columValue,String whereColum,String whereColumValue) {
        boolean succes = false;
        try {
            String SQLstring = "UPDATE "+ tabel +" SET "+columName+"='"+columValue+"' where" +whereColum+"= \""+whereColumValue+"\";";
            resultselect = DBconnect(SQLstring, true);
        } catch (Exception e) {
            System.out.println("Fout in update: " + e.getMessage());
        }
        return succes;
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
       /* // gegevens van de database
        dataSource.setUser("egondebaeniuk4me");
        dataSource.setPassword("Iek3rohThoTo");
        dataSource.setPort(3306);
        dataSource.setServerName("student.howest.be");
        dataSource.setDatabaseName("egondebaeniuk4me");
*/
        dataSource.setUser("root");
        dataSource.setPassword("Laiko123");
        dataSource.setPort(3306);
        dataSource.setServerName("127.0.0.1");
        dataSource.setDatabaseName("testpagegeowars");
        try {
            java.sql.Connection conn = dataSource.getConnection();
            //System.out.println("connectie met database is gelukt");

            java.sql.Statement stmt = conn.createStatement();
            if (BoolSelect) {
                ResultSet DBMemory = stmt.executeQuery(sqlString);

                ResultSetMetaData DBMemorymd = DBMemory.getMetaData();
                int columCount = DBMemorymd.getColumnCount();

                list = new ArrayList<String>();
                while (DBMemory.next()) {

                    for (int i = 1; i <= columCount; i++) {
                        list.add(DBMemory.getString(i));

                    }

                }
                DBMemory.close();
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
            System.out.println(dataSource);
            System.out.println("Dataconnectie gefaald: " + ex.getMessage());
        }

        return list;
    }
}
