package net.gsimken.bgameslibrary.mixin;

import net.gsimken.bgameslibrary.BgamesLibrary;
import net.gsimken.bgameslibrary.utils.IBGamesDataSaver;
import net.gsimken.bgameslibrary.utils.JsonUtils;
import net.gsimken.bgameslibrary.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


import java.util.ArrayList;
@Mixin(Entity.class)
public abstract class BGamesPlayerDataMixin implements IBGamesDataSaver {
   private NbtCompound persistentData;
   @Override
    public NbtCompound getPersistentData(){
       if(this.persistentData==null){
           this.persistentData = new NbtCompound();
       }
       return this.persistentData;
   }
    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info) {
        if(persistentData != null) {
            nbt.put("bgameslibrary.bgames_data", persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("bgameslibrary.bgames_data", 10)) {
            persistentData = nbt.getCompound("bgameslibrary.bgames_data");
        }
    }

}
