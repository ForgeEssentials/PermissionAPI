package net.minecraftforge.server.permission;

public enum DefaultPermissionLevel
{

    ALL(0),

    OP_1(1),

    OP_2(2),

    OP_3(3),

    OP_4(4),

    NONE(5);

    //TODO: Investigate changing this to OP_2 to be consistent with default op level settings
    //Alias to OP_4.  Allows api to respect op levels
    public static final DefaultPermissionLevel OP = OP_4;;
    public final int opLevel;

    private DefaultPermissionLevel(int opLevel)
    {
        this.opLevel = opLevel;
    }

    public static DefaultPermissionLevel fromBoolean(boolean value)
    {
        return value ? ALL : NONE;
    }

    public static DefaultPermissionLevel fromInteger(int value)
    {
        switch (value)
        {
        case 0:
            return ALL;
        case 1:
            return OP_1;
        case 2:
            return OP_2;
        case 3:
            return OP_3;
        case 4:
            return OP_4;
        default:
            return NONE;
        }
    }

}
