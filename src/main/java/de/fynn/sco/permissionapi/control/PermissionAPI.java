package de.fynn.sco.permissionapi.control;

import org.bukkit.plugin.Plugin;

import java.util.UUID;

/**
 * @author Freddyblitz
 * @version 1.0
 */
public class PermissionAPI {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private static final PermissionManager permissionManager = PermissionManager.getInstance();
    private final Plugin parent;

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    public PermissionAPI(Plugin plugin){
        this.parent = plugin;
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     *
     * @param permission
     * @return
     */
    public boolean registerPermission(String permission){
        return permissionManager.registerPermission(this.parent, permission);
    }

    /**
     *
     * @param permission
     * @param subPermission
     * @return
     */
    public boolean registerSubPermission(String permission, String subPermission){
        return permissionManager.registerSubPermission(this.parent, permission, subPermission);
    }

    /**
     *
     * @param permission
     * @return
     */
    public boolean deletePermission(String permission){
        return permissionManager.deletePermission(this.parent, permission);
    }

    /**
     *
     * @param playerUUID
     * @param permission
     * @return
     */
    public boolean addPermission(UUID playerUUID, String permission){
        return permissionManager.addPermission(this.parent, playerUUID, permission);
    }

    /**
     *
     * @param playerUUID
     * @return
     */
    public boolean addAllPermissions(UUID playerUUID){
        return permissionManager.addAllPermissions(this.parent, playerUUID);
    }

    /**
     *
     * @param playerUUID
     * @param permission
     * @return
     */
    public boolean removePermission(UUID playerUUID, String permission){
        return permissionManager.removePermission(this.parent, playerUUID, permission);
    }

    /**
     *
     * @param playerUUID
     * @return
     */
    public boolean removeAllPermissions(UUID playerUUID){
        return permissionManager.removeAllPermissions(this.parent, playerUUID);
    }

    /**
     *
     * @param playerUUID
     * @param permission
     * @return
     */
    public boolean hasPermission(UUID playerUUID, String permission){
        return permissionManager.hasPermission(this.parent, playerUUID, permission);
    }

    /**
     *
     * @param playerUUID
     * @param plugin
     * @param permission
     * @return
     */
    public boolean hasPermission(UUID playerUUID, String plugin, String permission){
        return permissionManager.hasPermission(this.parent, playerUUID, plugin, permission);
    }


}
