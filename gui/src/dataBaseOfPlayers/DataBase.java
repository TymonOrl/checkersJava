package dataBaseOfPlayers;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DataBase {

    public DataBase() throws SQLException {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(	"jdbc:h2:./data/Players", "admin", "admin");
            Statement statement = conn.createStatement();
            try {
                statement.execute("SELECT * FROM Players;");
            } catch (SQLException e){
                statement.executeUpdate("CREATE TABLE `Players` ("+
                        "`Name` varchar(50) NOT NULL,"+
                        "`gamesPlayed` int default NULL,"+
                        "`wins` int default NULL,"+
                        "`winRate` int default NULL,"+
                        "PRIMARY KEY  (`Name`)"+
                        ") ;");
                System.out.println("Utworzono tabele danych" );
            }
        } finally {
            if (conn!= null){
                conn.close();
            }
        }
    }


    public ArrayList<Player> readData() {
        ArrayList<Player> listOfPlayers = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:h2:./data/Players", "admin",
                    "admin");

            Statement statement = conn.createStatement();
            statement.execute("SELECT * FROM Players");
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                listOfPlayers.add(new Player((String) rs.getObject(1),(int)rs.getObject(2),(int)rs.getObject(3)));
            }
        } catch ( SQLException e){
            throw new RuntimeException(e);
        } finally {
            if (conn!= null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return listOfPlayers;
    }

    public Player getPlayer(String inString){
        Connection conn = null;
        Player player = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:h2:./data/Players", "admin",
                    "admin");

            Statement statement = conn.createStatement();
            statement.execute("SELECT * FROM Players WHERE Name='"+inString+"'");
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                player = new Player((String) rs.getObject(1), (int) rs.getObject(2), (int) rs.getObject(3));
            }
        } catch ( SQLException e){
            throw new RuntimeException(e);
        } finally {
            if (conn!= null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return player;
    }

    public void updatePlayer(Player player){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(	"jdbc:h2:./data/Players", "admin", "admin");
            Statement statement = conn.createStatement();

            if(isPartOfDataBase(player.getName())){
                PreparedStatement prep = conn.prepareStatement("UPDATE Players SET"
                        + " gamesPlayed = ?"
                        + " ,wins = ?"
                        + " ,winRate = ?"
                        + " WHERE Name='"+player.getName()+"'");
                prep.setString(1, Integer.toString(player.getGamesPlayed()));
                prep.setString(2, Integer.toString(player.getWins()));
                prep.setString(3, Integer.toString(player.getWinRate()));
                prep.executeUpdate();

            } else {
                PreparedStatement prep = conn.prepareStatement("INSERT into Players(`Name`,`gamesPlayed`,`wins`,`winRate`) values (?, ?, ?, ?)");
                prep.setString(1, player.getName());
                prep.setString(2, Integer.toString(player.getGamesPlayed()));
                prep.setString(3, Integer.toString(player.getWins()));
                prep.setString(4, Integer.toString(player.getWinRate()));
                prep.executeUpdate();
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally {
            if (conn!= null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public boolean isPartOfDataBase(String inString){
        Connection conn = null;
        boolean endResult = false;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:h2:./data/Players", "admin",
                    "admin");

            Statement statement = conn.createStatement();
            statement.execute("SELECT * FROM Players WHERE Name='"+inString+"'");
            ResultSet rs = statement.getResultSet();

            if(rs.next()){
                endResult = true;
            }
        } catch ( SQLException e){
            throw new RuntimeException(e);
        } finally {
            if (conn!= null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return endResult;
    }

    public JTable getData(){
        JTable jTable;
        String returnString ="";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:h2:./data/Players", "admin",
                    "admin");

            Statement statement = conn.createStatement();

            //Wyswietlanie calej tabeli:
            statement.execute("SELECT * FROM Players ORDER BY 'winRate' DESC");

            ResultSet rs = statement.getResultSet();
            ResultSetMetaData md  = rs.getMetaData();

            String[] columnNames = new String[4];
            for (int ii = 1; ii <= md.getColumnCount(); ii++){
                if(ii == 0){
                    returnString += md.getColumnName(ii);
                } else{
                    returnString += ( String.format("%-10s",md.getColumnName(ii)) + " | ");
                }
                columnNames[ii-1] = md.getColumnName(ii);
            }
            ArrayList<String> tmpList = new ArrayList<>();
            returnString += "\n";
            int p = 0;

            while (rs.next()) {
                for (int ii = 1; ii <= md.getColumnCount(); ii++){
                    if(ii == 1){
                        returnString += String.format("%-30s",rs.getObject(ii));
                    } else{
                        returnString += ( String.format("%-10s",rs.getObject(ii)) + " | ");
                    }
                    tmpList.add(rs.getObject(ii).toString());
                }
                returnString += "\n";
                p++;
            }
            Object[][] data = new Object[p][4];
            for (int i = 0; i < p; i++) {
                for (int j = 0; j <4; j++) {
                    data[i][j] = tmpList.get(i*4+j);
                }
            }
            jTable = new JTable(data, columnNames);

        } catch ( SQLException e){
            throw new RuntimeException(e);
        } finally {
            if (conn!= null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return jTable;
    }
}

