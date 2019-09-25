package ru.cadrider.MyFurnaceMod;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


public class TERedstoneFurnace extends TileEntity {
    private ForgeDirection direction = ForgeDirection.SOUTH;
    private boolean state;
    private int counter;

    @Override
    public void writeToNBT(NBTTagCompound par1){
        super.writeToNBT(par1);
        par1.setInteger("direction", Utilities.dirToMeta(direction));
        par1.setInteger("counter", counter);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1){
        super.readFromNBT(par1);
        direction = Utilities.MetaToDir(par1.getInteger("direction"));
        counter = par1.getInteger("counter");
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
        NBTTagCompound tag = pkt.func_148857_g();
        readFromNBT(tag);
    }

    public Packet getDescriptionPacket(){
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
    }

    public void 		  setDirection(ForgeDirection direction){ this.direction = direction; }
    public ForgeDirection getDirection()						{ return this.direction; 	  }

    public void 		  setState(boolean state) { this.state = state; }
    public boolean  	  getState() 			  { return this.state;  }
    
}
