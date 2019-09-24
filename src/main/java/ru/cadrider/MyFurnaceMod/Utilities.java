package ru.cadrider.MyFurnaceMod;

import net.minecraftforge.common.util.ForgeDirection;

public class Utilities {
    public static final int DIR_NORTH = 0;
    public static final int DIR_EAST = 1;
    public static final int DIR_SOUTH = 2;
    public static final int DIR_WEST = 3;


    public static int dirToMeta(ForgeDirection dir){
        if(dir == ForgeDirection.NORTH)
            return DIR_NORTH;
        else if(dir == ForgeDirection.EAST)
            return DIR_EAST;
        else if(dir == ForgeDirection.SOUTH)
            return DIR_SOUTH;
        return DIR_WEST;
    }

    public static ForgeDirection MetaToDir(int meta){
        if(meta == DIR_NORTH)
            return ForgeDirection.NORTH;
        else if(meta == DIR_EAST)
            return ForgeDirection.EAST;
        else if(meta == DIR_SOUTH)
            return ForgeDirection.SOUTH;
        return ForgeDirection.WEST;
    }


}
