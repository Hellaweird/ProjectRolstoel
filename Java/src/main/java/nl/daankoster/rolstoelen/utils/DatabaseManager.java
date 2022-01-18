package nl.daankoster.rolstoelen.utils;

import com.google.gson.Gson;
import nl.daankoster.rolstoelen.Main;
import nl.daankoster.rolstoelen.objects.Region;
import nl.daankoster.rolstoelen.objects.Tag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    Database database;
    Gson gson = new Gson();


    public DatabaseManager(Database database){
        this.database = database;
    }

    public void registerRegions(){
        try {
            ResultSet rs = database.getConnection().createStatement().executeQuery("SELECT * FROM locaties");

            while (rs.next()){
                String name = rs.getString("naam");
                int x1 = rs.getInt("x1");
                int x2 = rs.getInt("x2");
                int y1 = rs.getInt("y1");
                int y2 = rs.getInt("y2");
                Region region = new Region(name, x1, x2, y1, y2);
                Main.regionManager.registerRegion(region);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRolstoel(Tag tag){
        try {

            String query = "UPDATE rolstoelen SET locatie = ? where tag = ?";
            PreparedStatement preparedStmt = database.getConnection().prepareStatement(query);
            preparedStmt.setString   (1, tag.locatie);
            preparedStmt.setString(2, tag.getTagID());
            preparedStmt.executeUpdate();

            preparedStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
