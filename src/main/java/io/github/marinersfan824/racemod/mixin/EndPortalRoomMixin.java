package io.github.marinersfan824.racemod.mixin;

import io.github.marinersfan824.racemod.ISpiralStaircase;
import net.minecraft.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(targets = "net.minecraft.structure.StrongholdPieces$EndPortalRoom")
public class EndPortalRoomMixin {
    @Inject(method="fillOpenings",at=@At("TAIL"))
    private void onFillOpenings(StructurePiece start, List<StructurePiece> pieces, Random random, CallbackInfo ci){
        if(start!=null){
            ((ISpiralStaircase)start).setPortalRoomBox(((StructurePiece)(Object)this).getBoundingBox());
        }
    }
}
