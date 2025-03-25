package net.minecraftforge.server.permission;

public interface PermissionObject
{

    public String getPermissionNode();

    public DefaultPermissionLevel getPermissionLevel();

    default public String getPermissionDescription() {
        return getPermissionNode();
    }
    
}
