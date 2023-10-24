package pl.ksr.logic.database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class DatabaseConnector {
    private static final String url = "jdbc:postgresql://localhost:5100/postgres";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
           throw new RuntimeException();
        }

        return conn;
    }

    public static ArrayList<Earthquake> read(Connection connection) {
        String query = "SELECT * FROM earthquakes";
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                LocalDateTime earthquakeTime = rs.getTimestamp("time").toLocalDateTime();
                Double latitude = rs.getDouble("latitude");
                Double longitude = rs.getDouble("longitude");
                Double depth = rs.getDouble("depth");
                Double magnitude = rs.getDouble("mag");
                Double dmin = rs.getDouble("dmin");
                Double gap = rs.getDouble("gap");
                Double rms = rs.getDouble("rms");
                String type = rs.getString("magtype");
                Double horizontalError = rs.getDouble("horizontalError");
                Double depthError = rs.getDouble("depthError");
                Double magError = rs.getDouble("magError");
                Earthquake.Builder builder = new Earthquake.Builder();
                Earthquake earthquake = builder.setTime(earthquakeTime)
                        .setLatitude(latitude)
                        .setLongitude(longitude)
                        .setDepth(depth)
                        .setMag(magnitude)
                        .setDmin(dmin)
                        .setGap(gap)
                        .setRms(rms)
                        .setType(type)
                        .setHorizontalError(horizontalError)
                        .setDepthError(depthError)
                        .setMagError(magError)
                        .build();
                earthquakes.add(earthquake);
            }
        } catch (SQLException e) {
           throw new RuntimeException();
        }
        return earthquakes;
    }
}
