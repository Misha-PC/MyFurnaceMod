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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class RedstoneFurnace extends BlockContainer implements ITileEntityProvider {
    private boolean activated = false;
    @SideOnly(Side.CLIENT)
    private IIcon iconFace;
    @SideOnly(Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(Side.CLIENT)
    private IIcon iconDown;


    protected RedstoneFurnace(String name, Material material) {
        super(material);

        this.setBlockName(name);
//        this.setBlockTextureName(Main.MODID + ":" + name);
//        this.canPlaceBlockOnSide();
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(6F);
        this.setHarvestLevel("picaxe", 2);
//        this.setLightOpacity(0);

//        this.maxY = 0.25;

        GameRegistry.registerBlock(this, name);
        GameRegistry.registerTileEntity(TERedstoneFurnace.class, "name");
    }

    public boolean getActiv() {
        return this.activated;
    }
    /*
    public void setActiv(boolean b) {
        super(Material.iron);
        this.activated = b;
    }
*/

    @SideOnly(Side.CLIENT)
    public boolean onBlockActivated(World world, int p2, int p3, int p4, EntityPlayer player, int p6, float p7, float p8, float p9) {
        this.activated = !this.activated;
        if(this.activated){
          Minecraft.getMinecraft().thePlayer.sendChatMessage("True");
        }
        else{
            Minecraft.getMinecraft().thePlayer.sendChatMessage("False");
        }
        return true;
    }

//    @Override
//    public boolean isOpaqueCube(){
//        return false;
//    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int x, int y)
    {
        if(x == 1)
            return this.iconTop;
        else if(x == 0)
            return this.iconDown;
        else if(x != y)
            return this.blockIcon;
        else
            return this.iconFace;
//        return x == 1 ? this.iconTop : (x == 0 ? this.iconTop : (x != y ? this.blockIcon : this.iconFace));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon)
    {
        String side    = Main.MODID + ":furnaceSide_0";
        String top     = Main.MODID + ":furnaceTop_0";
        String faceOn  = Main.MODID + ":furnaceFaceOn_0";
        String faceOff = Main.MODID + ":furnaceFaceOff_0";
        String down    = Main.MODID + ":furnaceDown_0";

        this.blockIcon = icon.registerIcon(side);
        this.iconFace  = icon.registerIcon(this.activated ? faceOn : faceOff);
        this.iconTop   = icon.registerIcon(top);
        this.iconDown  = icon.registerIcon(down);

    }
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase elb, ItemStack stack)
    {
        int l = MathHelper.floor_double((double)(elb.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (stack.hasDisplayName())
        {
            ((TileEntityFurnace)world.getTileEntity(x, y, z)).func_145951_a(stack.getDisplayName());
        }
    }



    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TERedstoneFurnace();
    }
}
