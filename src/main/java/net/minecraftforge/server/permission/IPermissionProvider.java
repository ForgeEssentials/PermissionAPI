package net.minecraftforge.server.permission;

/**
 * Interface for permission management systems to implement
 */
public interface IPermissionProvider
{

    /**
     * Checks a permission
     * 
     * @param context
     *            The context where the permission is being checked in
     * @param permission
     *            The permission to check
     * @return Whether the permission is allowed
     */
    boolean hasPermission(PermissionContext context, String permission);

    /**
     * Gets a permission value
     *
     * @param context
     *            The context where the permission is being checked in
     * @param permission
     *            The permission to check
     * @return A String permission value that is set in the current node
     */
    default String getPermission(PermissionContext context, String permission) {
        //Use a default implementation because string permission values do not apply to the vanilla provider
        return Boolean.toString(hasPermission(context, permission));
    }
    /**
     * Notifies the permission manager about registered permissions
     * 
     * @param permission
     *            The name of the permission
     * @param level
     *            Default access level for the permission
     */
    void registerNode(String permission, DefaultPermissionLevel level, String description);

}
