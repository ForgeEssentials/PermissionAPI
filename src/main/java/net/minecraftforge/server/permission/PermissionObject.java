package net.minecraftforge.server.permission;

public interface PermissionObject
{

    public String getPermissionNode();

    public PermissionLevel getPermissionLevel();

    default public String getPermissionDescription() {
        return getPermissionNode();
    }
    
}
