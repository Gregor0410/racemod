package racemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.world.BlockEvent;

public class BedrockEventHandler {
	public ExampleWorldSavedData seedInfo;
	
	/**
	 * Little workaround for the tournament organizers to not get starter-staircased if they
	 * decide to advance eye RNG by throwing eyes. Instead, dropping a fire charge (with Q)
	 * has the same effect on the ender eye RNG sequence as throwing an eye.
	 * 
	 * @param e - the event being handled
	 */
	@SubscribeEvent
	public void onFireChargeDropped(ItemTossEvent e) {
		if (e.entityItem.getEntityItem().getItem() == ItemBlock.getItemFromBlock(Blocks.bedrock)) {
			e.player.worldObj.playSoundAtEntity(e.player, "random.bow", 0.5F, 0.4F / 0.8F);
			e.player.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1002, (int)e.player.posX, (int)e.player.posY, (int)e.player.posZ, 0);
			seedInfo = ExampleWorldSavedData.get((World)DimensionManager.getWorld(0));
			seedInfo.updateEyeSeed();
		}
	}
	
	@SubscribeEvent
	public void onBlockPlaced(BlockEvent.PlaceEvent e) {
		if (e.block == Blocks.bedrock) {
			ExampleWorldSavedData.tellPlayerInitialRates(e.world);
		}
	}
}
