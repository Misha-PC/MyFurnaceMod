package ru.cadrider.MyFurnaceMod;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.*;
//import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;


public class RedstoneFurnace extends BlockContainer implements ITileEntityProvider {
    private boolean activated = false;
    @SideOnly(Side.CLIENT)
    private IIcon iconFace;
    @SideOnly(Side.CLIENT)
    private IIcon iconTop;


    protected RedstoneFurnace(String name, Material material) {
        super(material);

        this.setBlockName(name);
        this.setBlockTextureName(Main.MODID + ":" + name);
//        this.canPlaceBlockOnSide();
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(6F);
        this.setHarvestLevel("axe", 2);
//        this.setLightOpacity(0);

//        this.maxY = 0.25;

        GameRegistry.registerBlock(this, name);
        GameRegistry.registerTileEntity(TERedstoneFurnace.class, "name");
    }

    @SideOnly(Side.CLIENT)
    public boolean onBlockActivated(World world, int p2, int p3, int p4, EntityPlayer player, int p6, float p7, float p8, float p9) {
        
//        int click_count++;
        Minecraft.getMinecraft().thePlayer.sendChatMessage("Ты кликнул " + " раз." );

        return true;
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int x, int y)
    {
        return x == 1 ? this.iconTop : (x == 0 ? this.iconTop : (x != y ? this.blockIcon : this.iconFace));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon)
    {
        this.blockIcon = icon.registerIcon("furnaceSide_0");
        this.iconFace = icon.registerIcon(this.activated ? "furnaceFaceOn_0" : "furnaceFaceOff_0");
        this.iconTop = icon.registerIcon("furnaceTop_0");
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TERedstoneFurnace();
    }
}
