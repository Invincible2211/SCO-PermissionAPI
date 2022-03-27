package de.fynn.sco.permissionapi.control;

import de.fynn.sco.permissionapi.control.database.DatabaseConnector;
import org.bukkit.plugin.Plugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class PermissionManager {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private static final PermissionManager instance = new PermissionManager();

    private final HashMap<Plugin,HashMap<String, List<String>>> permissionsByPlugin = new HashMap<>();
    private final HashMap<UUID, List<String>> playerPermissions = new HashMap<>();

    private final DatabaseConnector databaseConnector = DatabaseConnector.getInstance();

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    private PermissionManager(){

    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    public boolean registerPermission(Plugin plugin, String permission){

    }

    public boolean registerSubPermission(Plugin plugin, String permission, String subPermission){

    }

    public boolean deletePermission(Plugin plugin, String permission){

    }

    public boolean addPermission(Plugin plugin, UUID playerUUID, String permission){

    }

    public boolean addAllPermissions(Plugin plugin, UUID playerUUID){

    }

    public boolean removePermission(Plugin plugin, UUID playerUUID, String permission){

    }

    public boolean removeAllPermissions(Plugin plugin, UUID playerUUID){

    }

    public boolean hasPermission(Plugin plugin, UUID playerUUID, String permission){

    }

    public boolean hasPermission(Plugin plugin, UUID playerUUID, String pluginname, String permission){

    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/

    protected static PermissionManager getInstance() {
        return instance;
    }

}
