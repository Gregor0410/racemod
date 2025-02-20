package io.github.marinersfan824.racemod.mixin;

import io.github.marinersfan824.racemod.RNGStreamGenerator;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelProperties.class)
public abstract class LevelPropertiesMixin {
    private RNGStreamGenerator instance = RNGStreamGenerator.getInstance(this.toString());

    @Shadow private long seed;

    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/nbt/CompoundTag;)V")
    public void initInject(CompoundTag worldNbt, CallbackInfo ci) {
        if (worldNbt.contains("enderEyeSeed")) {
            long enderEyeSeed = worldNbt.getLong("enderEyeSeed");
            if (enderEyeSeed == 0) {
                instance.initializeEyeSeed(this.seed);
            } else {
                instance.setEnderEyeSeed(enderEyeSeed);
            }
        } else {
            instance.initializeEyeSeed(this.seed);
        }
        if (worldNbt.contains("enderPearlSeed")) {
            long enderPearlSeed = worldNbt.getLong("enderPearlSeed");
            if (enderPearlSeed == 0) {
                instance.initializePearlSeed(this.seed);
            } else {
                instance.setEnderPearlSeed(enderPearlSeed);
            }
        } else {
            instance.initializePearlSeed(this.seed);
        }
        if (worldNbt.contains("blazeRodSeed")) {
            long blazeRodSeed = worldNbt.getLong("blazeRodSeed");
            if (blazeRodSeed == 0) {
                instance.initializeBlazeRodSeed(this.seed);
            } else {
                instance.setBlazeRodSeed(blazeRodSeed);
            }
        } else {
            instance.initializeBlazeRodSeed(this.seed);
        }
        if (worldNbt.contains("featherSeed")) {
            long featherSeed = worldNbt.getLong("featherSeed");
            if (featherSeed == 0) {
                instance.initializeFeatherSeed(this.seed);
            } else {
                instance.setFeatherSeed(featherSeed);
            }
        } else {
            instance.initializeFeatherSeed(this.seed);
        }
    }

    @Inject(at = @At("HEAD"), method = "putNbt")
    private void writeSeedsToNBT(CompoundTag worldNBT, CompoundTag playerNBT, CallbackInfo ci) {
        worldNBT.putLong("enderEyeSeed", instance.getEnderEyeSeed());
        worldNBT.putLong("enderPearlSeed", instance.getEnderPearlSeed());
        worldNBT.putLong("blazeRodSeed", instance.getBlazeRodSeed());
        worldNBT.putLong("featherSeed", instance.getFeatherSeed());
    }
}
