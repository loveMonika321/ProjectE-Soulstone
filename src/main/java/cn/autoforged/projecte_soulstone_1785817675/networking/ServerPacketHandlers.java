/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  moze_intel.projecte.api.PESounds
 *  moze_intel.projecte.api.capabilities.IAlchBagProvider
 *  moze_intel.projecte.api.capabilities.PECapabilities
 *  moze_intel.projecte.gameObjs.container.AlchBagContainer
 *  moze_intel.projecte.gameObjs.container.TransmutationContainer
 *  moze_intel.projecte.utils.EMCHelper
 *  moze_intel.projecte.utils.EntityRandomizerHelper
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.animal.Rabbit
 *  net.minecraft.world.entity.animal.Rabbit$RabbitGroupData
 *  net.minecraft.world.entity.animal.Rabbit$Variant
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.item.DyeColor
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.phys.AABB
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.items.IItemHandler
 *  net.minecraftforge.items.IItemHandlerModifiable
 *  net.minecraftforge.network.NetworkHooks
 */
package cn.autoforged.projecte_soulstone_1785817675.networking;

import cn.autoforged.projecte_soulstone_1785817675.ModItems;
import cn.autoforged.projecte_soulstone_1785817675.util.SoulStoneUtil;
import java.util.List;
import java.util.Optional;
import moze_intel.projecte.api.PESounds;
import moze_intel.projecte.api.capabilities.IAlchBagProvider;
import moze_intel.projecte.api.capabilities.PECapabilities;
import moze_intel.projecte.gameObjs.container.AlchBagContainer;
import moze_intel.projecte.gameObjs.container.TransmutationContainer;
import moze_intel.projecte.utils.EMCHelper;
import moze_intel.projecte.utils.EntityRandomizerHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.network.NetworkHooks;

public final class ServerPacketHandlers {
    private static final String SAGE_ORB_COOLDOWN_KEY = "projecte_soulstone:sage_orb_cooldown";

    private ServerPacketHandlers() {
    }

    public static void handleSageOrb(ServerPlayer player) {
        if (player == null) {
            return;
        }
        if (!SoulStoneUtil.hasEffect((Player)player, (Item)ModItems.SAGE_SOUL_STONE.get())) {
            return;
        }
        CompoundTag data = player.getPersistentData();
        int cooldown = data.getInt(SAGE_ORB_COOLDOWN_KEY);
        if (cooldown > 0) {
            player.displayClientMessage((Component)Component.translatable((String)"message.projecte_soulstone_1785817675.sage_orb.cooldown", (Object[])new Object[]{cooldown / 20}), true);
            return;
        }
        data.putInt(SAGE_ORB_COOLDOWN_KEY, 12000);
        ServerLevel level = player.serverLevel();
        AABB box = player.getBoundingBox().inflate(4.0);
        List<Entity> targets = level.getEntities((Entity)player, box, e -> e instanceof Mob && !(e instanceof Player));
        int transformed = 0;
        for (Entity entity : targets) {
            if (!ServerPacketHandlers.transformMob((Player)player, level, (Mob)entity)) continue;
            ++transformed;
        }
        level.playSound(null, player.getX(), player.getY(), player.getZ(), (SoundEvent)PESounds.TRANSMUTE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
        player.displayClientMessage((Component)Component.translatable((String)"message.projecte_soulstone_1785817675.sage_orb.done", (Object[])new Object[]{transformed}), true);
    }

    private static boolean transformMob(Player player, ServerLevel level, Mob ent) {
        Rabbit rabbit;
        Mob randomized = EntityRandomizerHelper.getRandomEntity((Level)level, (Mob)ent);
        if (randomized == null || EMCHelper.consumePlayerFuel((Player)player, (long)384L) == -1L) {
            return false;
        }
        randomized.moveTo(ent.getX(), ent.getY(), ent.getZ(), ent.getYRot(), ent.getXRot());
        Rabbit.RabbitGroupData data = randomized instanceof Rabbit && (rabbit = (Rabbit)randomized).getVariant() == Rabbit.Variant.EVIL ? new Rabbit.RabbitGroupData(Rabbit.Variant.EVIL) : null;
        ForgeEventFactory.onFinalizeSpawn((Mob)randomized, (ServerLevelAccessor)level, (DifficultyInstance)level.getCurrentDifficultyAt(randomized.blockPosition()), (MobSpawnType)MobSpawnType.CONVERSION, (SpawnGroupData)data, null);
        level.addFreshEntity((Entity)randomized);
        if (randomized.isAddedToWorld()) {
            randomized.playAmbientSound();
            ent.discard();
        }
        ServerPacketHandlers.spawnTransformParticles(level, (Entity)ent);
        return true;
    }

    private static void spawnTransformParticles(ServerLevel level, Entity target) {
        level.sendParticles((ParticleOptions)ParticleTypes.PORTAL, target.getX(), target.getY() + 1.0, target.getZ(), 8, level.random.nextDouble() * 0.5 - 0.25, 0.2, level.random.nextDouble() * 0.5 - 0.25, 0.0);
    }

    public static void handleOpenTransmutation(ServerPlayer player) {
        if (player == null) {
            return;
        }
        if (!SoulStoneUtil.hasEffect((Player)player, (Item)ModItems.TRANSMUTATION_SOUL_STONE.get())) {
            return;
        }
        player.getPersistentData().putLong("projecte_soulstone:transmutation_pause_daytime", player.serverLevel().getDayTime());
        NetworkHooks.openScreen((ServerPlayer)player, (MenuProvider)new MenuProvider(){

            public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player p) {
                return new TransmutationContainer(windowId, playerInventory);
            }

            public Component getDisplayName() {
                return Component.translatable((String)"container.projecte_soulstone_1785817675.transmutation");
            }
        }, buf -> buf.writeBoolean(false));
    }

    public static void handleOpenAlchemyBag(ServerPlayer player, int colorId) {
        if (player == null) {
            return;
        }
        if (colorId < 0 || colorId >= DyeColor.values().length) {
            return;
        }
        if (!SoulStoneUtil.hasEffect((Player)player, (Item)ModItems.ALCHEMY_BAG_SOUL_STONE.get())) {
            return;
        }
        final DyeColor color = DyeColor.byId((int)colorId);
        Optional bagProvider = player.getCapability(PECapabilities.ALCH_BAG_CAPABILITY).resolve();
        if (bagProvider.isEmpty()) {
            return;
        }
        IItemHandler bagInventory = ((IAlchBagProvider)bagProvider.get()).getBag(color);
        if (!(bagInventory instanceof IItemHandlerModifiable)) {
            return;
        }
        final IItemHandlerModifiable modifiable = (IItemHandlerModifiable)bagInventory;
        NetworkHooks.openScreen((ServerPlayer)player, (MenuProvider)new MenuProvider(){

            public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player p) {
                return new AlchBagContainer(windowId, playerInventory, null, modifiable, playerInventory.selected, false);
            }

            public Component getDisplayName() {
                return Component.translatable((String)"container.projecte_soulstone_1785817675.alchemy_bag", (Object[])new Object[]{color.getName()});
            }
        }, buf -> {
            buf.writeEnum((Enum)InteractionHand.MAIN_HAND);
            buf.writeByte(player.getInventory().selected);
            buf.writeBoolean(false);
        });
    }

    public static void tickSageOrbCooldown(Player player) {
        CompoundTag data = player.getPersistentData();
        int cooldown = data.getInt(SAGE_ORB_COOLDOWN_KEY);
        if (cooldown > 0) {
            data.putInt(SAGE_ORB_COOLDOWN_KEY, cooldown - 1);
        }
    }
}

