package ru.cadrider.MyFurnaceMod;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = Main.MODID, version = Main.VERSION, name = Main.MODNAME)
public class Main {
    public static final String MODID = "myfurnacemod";
    public static final String VERSION = "1.0";
    public static  final String MODNAME = "My furnace mod";

    public Block furnace;


    @EventHandler
    public void init(FMLInitializationEvent event){
        furnace = new RedstoneFurnace("furnaceFace_0", Material.rock);
    }
}
