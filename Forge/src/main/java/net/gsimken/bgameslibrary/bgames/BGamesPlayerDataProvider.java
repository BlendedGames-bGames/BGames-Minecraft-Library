package net.gsimken.bgameslibrary.bgames;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BGamesPlayerDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

        public static Capability<BGamesPlayerData> PLAYER_DATA = CapabilityManager.get(new CapabilityToken<BGamesPlayerData>() { });

        private BGamesPlayerData data = null;
        private final LazyOptional<BGamesPlayerData> optional = LazyOptional.of(this::createPlayerData);

        private BGamesPlayerData createPlayerData() {
            if(this.data == null) {
                this.data = new BGamesPlayerData();
            }
            return this.data;
        }
         public int getPlayerId() {
            if(this.data == null) {
                this.data = new BGamesPlayerData();
            }
            return this.data.id;
        }
        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            if(cap == PLAYER_DATA) {
                return optional.cast();
            }

            return LazyOptional.empty();
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag nbt = new CompoundTag();
            createPlayerData().savePersistentData(nbt);
            return nbt;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            createPlayerData().loadPersistentData(nbt);
        }



    }

