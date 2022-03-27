package de.fynn.sco.permissionapi.control.database;

import de.fynn.sco.permissionapi.control.file.ConfigurationLoader;
import de.fynn.sco.permissionapi.model.DatabaseData;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class DatabaseConnector {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private Connection connection;
    private final DatabaseData databaseData;

    private static DatabaseConnector instance;

    static {
        try {
            instance = new DatabaseConnector();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Prepared Statements
    private final PreparedStatement createSchema;
    private final PreparedStatement registerPermission;
    private final PreparedStatement deletePermission;
    private final PreparedStatement addPermission;
    private final PreparedStatement removePermission;
    private final PreparedStatement hasPermission;
    private final PreparedStatement createPluginTable;
    private final PreparedStatement tableExists;

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der private Konstruktor baut eine Verbindung mit der Datenbank auf und bereitet die SQL-Statements vor.
     * @throws SQLException Gibt auftretende SQL-Exceptions weiter
     */
    private DatabaseConnector() throws SQLException{
        databaseData = ConfigurationLoader.getDatabaseData();
        try {
            connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        createSchema = connection.prepareStatement("CREATE SCHEMA IF NOT EXISTS " + databaseData.getSchema() + ";");
        createSchema.execute();
        registerPermission = connection.prepareStatement("ALTER TABLE " + databaseData.getSchema() + ".? ADD ? TINYINT DEFAULT 0;");
        deletePermission = connection.prepareStatement("ALTER TABLE " + databaseData.getSchema() + ".? DROP  COLUMN ?;");
        addPermission = connection.prepareStatement("UPDATE " + databaseData.getSchema() + ".? SET ? = 1 WHERE uuid = ?");
        removePermission = connection.prepareStatement("UPDATE " + databaseData.getSchema() + ".? SET ? = 0 WHERE uuid = ?");
        hasPermission = connection.prepareStatement("SELECT ? FROM " + databaseData.getSchema() + ".? WHERE uuid = ?;");
        createPluginTable = connection.prepareStatement("CREATE TABLE " + databaseData.getSchema() + ".? (uuid VARCHAR(150), PRIMARY KEY uuid);");
        tableExists = connection.prepareStatement("SELECT IF (EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE' AND TABLE_NAME=?), 1, 0);");
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Diese Methode baut eine Verbindung mit der Datenbank auf.
     * @throws SQLException Gibt auftretende SQL-Exceptions weiter
     */
    private void connect() throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://"+databaseData.getIpAdress()+":"
                +databaseData.getPort(),databaseData.getUsername(), databaseData.getPassword());
    }

    /**
     *
     * @param plugin
     * @param permission
     */
    public void registerPermission(String plugin, String permission){
        try {
            registerPermission.setString(1, plugin);
            registerPermission.setString(2, permission);
            registerPermission.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param plugin
     * @param permission
     */
    public void deletePermission(String plugin, String permission){
        try {
            deletePermission.setString(1, plugin);
            deletePermission.setString(2, permission);
            deletePermission.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param plugin
     * @param permission
     * @param uuid
     */
    public void addPermission(String plugin, String permission, String uuid){
        try {
            addPermission.setString(1, plugin);
            addPermission.setString(2, permission);
            addPermission.setString(3, uuid);
            addPermission.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param plugin
     * @param permission
     * @param uuid
     */
    public void removePermission(String plugin, String permission, String uuid){
        try {
            removePermission.setString(1, plugin);
            removePermission.setString(2, permission);
            removePermission.setString(3, uuid);
            removePermission.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param plugin
     * @param permission
     * @param uuid
     * @return
     */
    public boolean hasPermission(String plugin, String permission, String uuid){
        try {
            hasPermission.setString(1, permission);
            hasPermission.setString(2, plugin);
            hasPermission.setString(3, uuid);
            ResultSet result = hasPermission.executeQuery();
            //TODO result set ueberpruefen und ergebnis returnen
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param plugin
     * @return
     */
    public List<String> getAlreadyRegisteredPermissionsByPlugin(String plugin){
        List<String> permissionsByPlugin = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?");
            statement.setString(1, plugin);
            ResultSet result = statement.executeQuery();
            //TODO result durchgehen und Spaltennamen in Liste speichern -> muss im PermissionManger noch sortiert werden nach permission und sub permissions etc.
        } catch (SQLException e){
            e.printStackTrace();
        }
        return permissionsByPlugin;
    }

    /**
     *
     * @param plugin
     * @return
     */
    public HashMap<UUID, List<Boolean>> getPlayerPermissions(String plugin){
        HashMap<UUID, List<Boolean>> permissions = new HashMap<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + databaseData.getSchema() + ".?");
            statement.setString(1, plugin);
            ResultSet result = statement.executeQuery();
            //TODO permissions der player auslesen -> muss im PermissionManger noch umgewandelt werden (UUID, List<String>)
        } catch (SQLException e){
            e.printStackTrace();
        }
        return permissions;
    }

    /**
     *
     * @param plugin
     * @return
     */
    public boolean containsPlugin(String plugin){
        try {
            tableExists.setString(1, plugin);
            ResultSet result = tableExists.executeQuery();
            result.next();
            return result.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param plugin
     */
    public void setCreatePluginTable(String plugin){
        try {
            createPluginTable.setString(1, plugin);
            createPluginTable.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/

    public static DatabaseConnector getInstance() {
        return instance;
    }

}
