package com.group17.geowars.database;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private Connection conn;
    private static DBManager instance;
    public ArrayList<String> resultselect;
    public ArrayList<String> list;
    public ArrayList<String> SpelersId;
    public ArrayList<String> ShipId;
    public ArrayList<String> resultData;
    private PreparedStatement prep;

    private DBManager() {
        this.init();
    }

    private void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            MysqlDataSource dataSource = new MysqlDataSource();
            //add datasource
            dataSource.setUser("geowars");
            dataSource.setPassword("12345");
            dataSource.setPort(3306);
            dataSource.setServerName("www.egondebaene.be");
            dataSource.setDatabaseName("GeoWars");
            conn = dataSource.getConnection();
        } catch (ClassNotFoundException ex) {
            //ex.printStackTrace();
        } catch (SQLException e) {
           // e.printStackTrace();
        }
    }

    public static DBManager getInstance() {
        if (DBManager.instance == null) {
            instance = new DBManager();
        }
        return DBManager.instance;
    }

    public ArrayList<String> DbExecute(PreparedStatement statment) {
        try {
            ResultSet rs = statment.executeQuery();
            resultData = RsToArrayList(rs);
            if (!resultData.isEmpty()) {
                return resultData;
            } else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*----------------Select----------------------------*/
    public ArrayList DBselect(String selectValue, String tabel, String columName, String value) {
        try {
            String SQLstring = "SELECT " + selectValue + " FROM " + tabel + " where " + columName + "= \"" + value + "\";";
            resultselect = DBconnect(SQLstring, true);
        } catch (Exception e) {
            System.out.println("Fout in select: " + e.getMessage());
        }
        return resultselect;
    }

    public ArrayList DBselectProfileShips(String PlayerName) {

        String SQLstring = "SELECT Ship.name,Ship.image,Ship.hitpoints,Ship.attack,Ship.speed,Profile.name,Profile.profileLvl,Profile.credits,Profile.HoursPlayed,Profile.GamesPlayed FROM `Ship`\n" +
                "JOIN ShipsForProfile ON Ship.IDShip = ShipsForProfile.IDShipForProfile\n" +
                "JOIN Profile ON ShipsForProfile.profile = Profile.ships\n" +
                "WHERE Profile.name = ?";
        try {
            prep = this.conn.prepareStatement(SQLstring);
            prep.setString(1, PlayerName);
        } catch (SQLException e) {
            System.out.println("error with preparing statment. Are you trying to hack us?");
        }
        return DbExecute(prep);
    }

    public ArrayList DBselectPlayersHighscore(String Name) {
        String SQLstring = "SELECT score FROM HighScore where nameProfile = ? ORDER BY score DESC LIMIT 1";
        try {
            prep = this.conn.prepareStatement(SQLstring);
            prep.setString(1, Name);
        } catch (SQLException e) {
            System.out.println("error with preparing statment. Are you trying to hack us?");
        }
        return DbExecute(prep);
    }

    public ArrayList DBselectTOP10Highscore(String Gamemode) {
        String SQLstring = "SELECT nameProfile,score FROM HighScore where gamemode = ? ORDER BY score DESC LIMIT 10;";
        try {
            prep = this.conn.prepareStatement(SQLstring);
            prep.setString(1, Gamemode);
        } catch (SQLException e) {
            System.out.println("error with preparing statment. Are you trying to hack us?");
        }
        return DbExecute(prep);
    }
    public ArrayList DBSelectEnemy(String Enemy) {
        String SQLstring = "SELECT name,speed,maxHP,fireDelay,fireRange,difficultyGrade,spread FROM Enemy where name = ?;";
        try {
            prep = this.conn.prepareStatement(SQLstring);
            prep.setString(1, Enemy);
        } catch (SQLException e) {
            System.out.println("error with preparing statment. Are you trying to hack us?");
        }
        return DbExecute(prep);
    }
    public ArrayList DBSelectAllEnemys() {
        String SQLstring = "SELECT name,speed,maxHP,fireDelay,fireRange,difficultyGrade,spread FROM Enemy;";
        try {
            prep = this.conn.prepareStatement(SQLstring);
        } catch (SQLException e) {
            System.out.println("error with preparing statment. Are you trying to hack us?");
        }
        return DbExecute(prep);
    }

    public ArrayList DBselectGeom() {
        try {
            String SQLstring = "SELECT name,muliplier,experience,points FROM Geom;";
            resultselect = DBconnect(SQLstring, true);
        } catch (Exception e) {
            System.out.println("Fout in select: " + e.getMessage());
        }
        return resultselect;
    }

    public ArrayList DBselectSpawnLocation(Integer Wave) {
        try {
            String SQLstring = "SELECT x,y FROM SpawnLocation where Wave='" + Wave + "';";
            resultselect = DBconnect(SQLstring, true);
        } catch (Exception e) {
            System.out.println("Fout in select: " + e.getMessage());
        }
        return resultselect;
    }

    public ArrayList DBselectDrone(String name) {
        try {
            String SQLstring = "SELECT name,image,hitpoints,hpinfinite,attack,speed,type FROM drone where name='" + name + "';";
            resultselect = DBconnect(SQLstring, true);
        } catch (Exception e) {
            System.out.println("Fout in select: " + e.getMessage());
        }
        return resultselect;
    }

    public ArrayList DBselectEnemy(String name) {
        try {
            String SQLstring = "SELECT name,image,hitpoints,geom,attack,speed,type FROM enemy where name='" + name + "';";
            resultselect = DBconnect(SQLstring, true);
        } catch (Exception e) {
            System.out.println("Fout in select: " + e.getMessage());
        }
        return resultselect;
    }

    public ArrayList DBselectShip(String name) {
        try {
            String SQLstring = "SELECT name,image,hitpoints,attack,speed,type FROM Ship where name='" + name + "';";
            resultselect = DBconnect(SQLstring, true);
        } catch (Exception e) {
            System.out.println("Fout in select: " + e.getMessage());
        }
        return resultselect;
    }

    public ArrayList DBselectAllShips() {

        String SQLstring = "SELECT name,hitpoints,attack,speed,price FROM Ship;";
        try {
            prep = this.conn.prepareStatement(SQLstring);
        } catch (SQLException e) {
            System.out.println("error with preparing statment. Are you trying to hack us?");
        }
        return DbExecute(prep);

    }

    public ArrayList DBselectLogin(String Name, String Pass) {

        String SQLstring = "SELECT * FROM Profile where name = ? and Password= ?;";
        try {
            prep = this.conn.prepareStatement(SQLstring);
            prep.setString(1, Name);
            prep.setString(2, Pass);
        } catch (SQLException e) {
            System.out.println("error with preparing statment. Are you trying to hack us?");
        }
        return DbExecute(prep);
    }

    public ArrayList DBselectAllDrones() {
        String SQLstring = "SELECT name,hitpoints,hpinfinite,attack,speed,price FROM Drone;";
        try {
            prep = this.conn.prepareStatement(SQLstring);
        } catch (SQLException e) {
            System.out.println("error with preparing statment. Are you trying to hack us?");
        }
        return DbExecute(prep);
    }

    public ArrayList DBselectWaveData(Integer WaveNumber) {
        String SQLstring = "SELECT Enemy.name,Waves.Amount " +
                "FROM Waves Join Enemy ON Waves.EnemyType=Enemy.IDEnemy WHERE WaveNumber = ?;";
        try {
            prep = this.conn.prepareStatement(SQLstring);
            prep.setInt(1, WaveNumber);
        } catch (SQLException e) {
            System.out.println("error with preparing statment. Are you trying to hack us?");
        }
        return DbExecute(prep);
    }

    public ArrayList DBselectPowerUp(String name) {
        try {
            String SQLstring = "SELECT name,type,amount,description FROM PowerUp where name='" + name + "';";
            resultselect = DBconnect(SQLstring, true);
        } catch (Exception e) {
            System.out.println("Fout in select: " + e.getMessage());
        }
        return resultselect;
    }

    public ArrayList DBselectProfile(String Name) {
        String SQLstring = "SELECT profileLvl,HoursPlayed,GamesPlayed FROM Profile where name= ? limit 1;";
        try {
            prep = this.conn.prepareStatement(SQLstring);
            prep.setString(1, Name);
        } catch (SQLException e) {
            System.out.println("error with preparing statment. Are you trying to hack us?");
        }
        return DbExecute(prep);
    }

    public ArrayList DBselectCampainLvl(String Name) {
        String SQLstring = "SELECT CampaignLvl FROM CampaignProfile where name= ? limit 1;";
        try {
            prep = this.conn.prepareStatement(SQLstring);
            prep.setString(1, Name);
        } catch (SQLException e) {
            System.out.println("error with preparing statment. Are you trying to hack us?");
        }
        return DbExecute(prep);
    }

    /*----------------------------update------------------------------------*/
    public boolean DBupdate(String tabel, String columName, String columValue, String whereColum, String whereColumValue) {
        boolean succes = false;
        try {
            String SQLstring = "UPDATE " + tabel + " SET " + columName + "='" + columValue + "' where " + whereColum + "= \"" + whereColumValue + "\";";
            resultselect = DBconnect(SQLstring, false);
            succes = true;
        } catch (Exception e) {
            System.out.println("Fout in update: " + e.getMessage());
        }
        return succes;
    }

    public boolean DBupdateCampainProfileCredits(Integer CreditsAmount, String playername) {
        boolean succes = false;
        try {
            String SQLstring = "UPDATE CampaignProfile SET credits='" + CreditsAmount + "' where name= '" + playername + "';";
            resultselect = DBconnect(SQLstring, false);
            succes = true;
        } catch (Exception e) {
            System.out.println("Fout in update: " + e.getMessage());
        }
        return succes;
    }

    public boolean DBupdateProfileCredits(Integer CreditsAmount, String playername) {
        boolean succes = false;
        try {
            String SQLstring = "UPDATE Account SET credits='" + CreditsAmount + "' where name= '" + playername + "';";
            resultselect = DBconnect(SQLstring, false);
            succes = true;
        } catch (Exception e) {
            System.out.println("Fout in update: " + e.getMessage());
        }
        return succes;
    }

    public boolean DBupdateProfileLvl(Integer ProfileLvl, String playername) {
        boolean succes = false;
        try {
            String SQLstring = "UPDATE Account SET profileLvl='" + ProfileLvl + "' where name= '" + playername + "';";
            resultselect = DBconnect(SQLstring, false);
            succes = true;
        } catch (Exception e) {
            System.out.println("Fout in update: " + e.getMessage());
        }
        return succes;
    }

    public boolean DBupdateCampaignShipLvl(Integer ShipLvl, String playername) {
        boolean succes = false;
        try {
            String SQLstring = "UPDATE CampaignProfile SET shipLvl='" + ShipLvl + "' where name= '" + playername + "';";
            resultselect = DBconnect(SQLstring, false);
            succes = true;
        } catch (Exception e) {
            System.out.println("Fout in update: " + e.getMessage());
        }
        return succes;
    }

    public boolean DBupdateCampaignLvl(Integer campaignLvl, String playername) {
        boolean succes = false;
        try {
            String SQLstring = "UPDATE CampaignProfile SET campaignLvl='" + campaignLvl + "' where name= '" + playername + "';";
            resultselect = DBconnect(SQLstring, false);
            succes = true;
        } catch (Exception e) {
            System.out.println("Fout in update: " + e.getMessage());
        }
        return succes;
    }

    /*----------------------------insert------------------------------------*/
    public boolean DBInsert(String tabel, String columone, String valueone) {
        String SQLstring = "INSERT INTO " + tabel + " (" + columone + ") VALUES ('" + valueone + "');";
        boolean succes = false;
        try {
            resultselect = DBconnect(SQLstring, false);
            succes = true;
        } catch (Exception e) {
            System.out.println("Fout in update: " + e.getMessage());
        }
        return succes;
    }

    public boolean DBInsertCampainProfile(String name, Integer credits, Integer ShipLvl, Integer CampainLvl) {
        String SQLstring = "INSERT INTO CampaignProfile(name,credits,shipLvl,campainLvl) VALUES ('" + name + "','" + credits + "','" + ShipLvl + "','" + CampainLvl + "');";
        boolean succes = false;
        try {
            resultselect = DBconnect(SQLstring, false);
            succes = true;
        } catch (Exception e) {
            System.out.println("Fout in update: " + e.getMessage());
        }
        return succes;
    }

    public boolean DBInsertProfile(String name, String Password) {

        String SQLstring = "INSERT INTO Profile(name,password) VALUES (?,?);";
        boolean succes = false;
        try {
            resultselect = DBconnect(SQLstring, false);
            succes = true;
        } catch (Exception e) {
            System.out.println("Fout in update: " + e.getMessage());
        }
        return succes;
    }

    public boolean DBInsertHighscore(String nameProfile, Integer Score, String Gamemode) {


        try {

            String SQLstring = "INSERT INTO HighScore (nameProfile,Score,gamemode) VALUES (?,?,?);";

            prep = this.conn.prepareStatement(SQLstring);
            prep.setString(1, nameProfile);
            prep.setInt(2, Score);
            prep.setString(3, Gamemode);
            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fout in update: " + e.getMessage());
            boolean succes = false;
        }
        boolean succes = true;
        return succes;
    }

    public boolean DBInsertShipsInProfile(String nameProfile, String nameShip) {
        boolean succes = false;
        String SQLstring = "select IDProfile FROM Account WHERE name='" + nameProfile + "' Limit 1;";

        try {
            SpelersId = DBconnect(SQLstring, true);

        } catch (Exception e) {
            System.out.println("Fout in ophalen van spelersnaam: " + e.getMessage());
        }
        SQLstring = "select IDShip FROM Ship WHERE name='" + nameShip + "' Limit 1;";

        try {
            ShipId = DBconnect(SQLstring, true);

        } catch (Exception e) {
            System.out.println("Fout in ophalen van shipnaam: " + e.getMessage());
        }
        if (ShipId.isEmpty() | SpelersId.isEmpty()) {
            succes = false;
            return succes;
        } else {
            SQLstring = "INSERT INTO ShipsForProfile (ship,profile) VALUES ('" + ShipId.get(0) + "','" + SpelersId.get(0) + "');";

            try {
                resultselect = DBconnect(SQLstring, false);
                succes = true;
            } catch (Exception e) {
                System.out.println("Fout in update: " + e.getMessage());
            }
            return succes;
        }
    }

    public ArrayList RsToArrayList(ResultSet resultSet) throws SQLException {

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();

        list = new ArrayList<String>();
        while (resultSet.next()) {

            for (int i = 1; i <= columnCount; i++) {
                list.add(resultSet.getString(i));

            }

        }
        return list;
    }

    /*----------------------------Connectie Naar DB------------------------------------*/
    public ArrayList DBconnect(String sqlString, boolean BoolSelect) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Driver niet gevonden, toevoegen via de properties: " + ex.getMessage());
        }
        MysqlDataSource dataSource = new MysqlDataSource();

        dataSource.setUser("geowars");
        dataSource.setPassword("12345");
        dataSource.setPort(3306);
        dataSource.setServerName("www.egondebaene.be");
        dataSource.setDatabaseName("GeoWars");
        try {
            java.sql.Connection conn = dataSource.getConnection();

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
            } else {
                stmt.executeUpdate(sqlString);
                //sluiten van statement
                stmt.close();
            }
            //sluiten van connectie
            conn.close();
        } catch (Exception ex) {
            System.out.println("Dataconnectie gefaald: " + ex.getMessage());
        }

        return list;
    }
}
