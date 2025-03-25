package net.minecraftforge.server.permission;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOpsEntry;

import com.mojang.authlib.GameProfile;

public class DefaultPermissionProvider implements IPermissionProvider
{

    private static final String PERM_SEED = PermissionAPI.DEFAULT_COMMAND_NODE + ".seed";
    private static final String PERM_TELL = PermissionAPI.DEFAULT_COMMAND_NODE + ".tell";
    private static final String PERM_HELP = PermissionAPI.DEFAULT_COMMAND_NODE + ".help";
    private static final String PERM_ME = PermissionAPI.DEFAULT_COMMAND_NODE + ".me";

    protected static final Map<String, DefaultPermissionLevel> permissions = new HashMap<String, DefaultPermissionLevel>();
    protected static final Map<String, String> permissionDescriptions = new HashMap<String, String>();

    @Override
    public boolean hasPermission(PermissionContext context, String permission)
    {
        // Special permission checks from EntityPlayerMP
        if (PERM_SEED.equals(permission) && !MinecraftServer.getServer().isDedicatedServer())
            return true;
        if (PERM_TELL.equals(permission) || PERM_HELP.equals(permission) || PERM_ME.equals(permission))
            return true;

        DefaultPermissionLevel level = permissions.get(permission);
        if (level == null)
            return true;
        int opLevel = context.isPlayer() ? getOpLevel(context.getPlayer().getGameProfile()) : 0;
        return level.opLevel <= opLevel;
    }

    @Override
    public void registerNode(String permission, DefaultPermissionLevel level, String description)
    {
        permissions.put(permission, level);
        permissionDescriptions.put(permission, description);
    }

    protected int getOpLevel(GameProfile profile)
    {
        if (!MinecraftServer.getServer().getConfigurationManager().func_152596_g(profile))
            return 0;
        UserListOpsEntry entry = (UserListOpsEntry) MinecraftServer.getServer().getConfigurationManager().func_152603_m().func_152683_b(profile);
        return entry != null ? entry.func_152644_a() : MinecraftServer.getServer().getOpPermissionLevel();
    }

}
