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
import scala.collection.parallel.ParIterableLike;


public class RedstoneFurnace extends BlockContainer implements ITileEntityProvider {
    private boolean activated = true;
    private long lastClick;
    @SideOnly(Side.CLIENT)
    private IIcon iconFace;
    @SideOnly(Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(Side.CLIENT)
    private IIcon iconDown;


    protected RedstoneFurnace(String name, Material material, boolean state) {
        super(material);
        this.activated = state;
//        this.setBlockTextureName(Main.MODID + ":" + name);
//        this.canPlaceBlockOnSide();
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(6F);
        this.setHarvestLevel("picaxe", 2);
//        this.setLightOpacity(0);

//        this.maxY = 0.25;

        if(state) {
        	
            this.setBlockName("Redstone furnace lit");
	        GameRegistry.registerBlock(this, "lit_furnace");
	        GameRegistry.registerTileEntity(TERedstoneFurnace.class, "lit_furnace");        	
        }else {
            this.setBlockName("Redstone furnace");
	        GameRegistry.registerBlock(this, "furnace");
	        GameRegistry.registerTileEntity(TERedstoneFurnace.class, "furnace");
        }
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
    
//public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)

    @SideOnly(Side.CLIENT)
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p6, float p7, float p8, float p9) {
        long time = Minecraft.getMinecraft().theWorld.getWorldTime();
        if (time - 4 > lastClick){
        	this.activated = !this.activated;
        	  if (world.isRemote){
        		  return true;
        	  }
        	    else{
        	        TERedstoneFurnace tileentityfurnace = (TERedstoneFurnace)world.getTileEntity(x, y, z);
        	        if (tileentityfurnace != null){
        	        	Minecraft.getMinecraft().thePlayer.sendChatMessage("state:"+this.activated);
        	        	tileentityfurnace.setState(this.activated);
//        	            player.func_146101_a(tileentityfurnace);
        	        }
        	    }
        	}
        return true;
        }
    
    public static void updateMyFurnaceBlockState(boolean state, World world, int x, int y, int z)
    {
        int l = world.getBlockMetadata(x, y, z);
        TileEntity tileentity = world.getTileEntity(x, y, z);
//        field_149934_M = true;

        if (state)
        {
            world.setBlock(x, y, z, Blocks.lit_furnace);
        }
        else
        {
            world.setBlock(x, y, z, Blocks.furnace);
        }

//        field_149934_M = false;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            world.setTileEntity(x, y, z, tileentity);
        }
    }

   

//    @Override
//    public boolean isOpaqueCube(){
//        return false;
//    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if(side == 1)
            return this.iconTop;
        else if(side == 0)
            return this.iconDown;
        else if(side != meta)
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
