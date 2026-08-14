/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.inolia_zaicek.mine_fargo.Damage.MyGoDamageType
 *  moze_intel.projecte.gameObjs.container.TransmutationContainer
 *  moze_intel.projecte.utils.EMCHelper
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.particles.DustParticleOptions
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MobCategory
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.TickEvent$PlayerTickEvent
 *  net.minecraftforge.event.entity.living.LivingDamageEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$BreakSpeed
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  org.joml.Vector3f
 */
package cn.autoforged.projecte_soulstone_1785817675.event;

import cn.autoforged.projecte_soulstone_1785817675.ModItems;
import cn.autoforged.projecte_soulstone_1785817675.networking.ModMessages;
import cn.autoforged.projecte_soulstone_1785817675.networking.ServerPacketHandlers;
import cn.autoforged.projecte_soulstone_1785817675.util.ModTags;
import cn.autoforged.projecte_soulstone_1785817675.util.SoulStoneUtil;
import com.inolia_zaicek.mine_fargo.Damage.MyGoDamageType;
import moze_intel.projecte.gameObjs.container.TransmutationContainer;
import moze_intel.projecte.utils.EMCHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Vector3f;

@Mod.EventBusSubscriber(modid="projecte_soulstone_1785817675")
public class ModEvents {
    private static final String DISINTEGRATE_COOLDOWN_KEY = "projecte_soulstone:disintegrate_cooldown";
    private static final String PHASE_TIME_KEY = "projecte_soulstone:phase_time";
    private static final String PHASE_COOLDOWN_KEY = "projecte_soulstone:phase_cooldown";

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (SoulStoneUtil.hasEffect(player, (Item)ModItems.RED_SOUL_STONE.get())) {
            event.setNewSpeed(event.getNewSpeed() * 6.0f);
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        Player player;
        if (event.getEntity().level().isClientSide) {
            return;
        }
        Entity entity = event.getSource().getEntity();
        if (entity instanceof Player && SoulStoneUtil.hasEffect(player = (Player)entity, (Item)ModItems.RED_SOUL_STONE.get())) {
            if (event.getSource().is(MyGoDamageType.TRUEDAMAGE)) {
                return;
            }
            event.setAmount(event.getAmount() * 3.0f);
            LivingEntity target = event.getEntity();
            CompoundTag data = player.getPersistentData();
            int cooldown = data.getInt(DISINTEGRATE_COOLDOWN_KEY);
            if (cooldown <= 0 && player.getRandom().nextDouble() < 0.33) {
                data.putInt(DISINTEGRATE_COOLDOWN_KEY, 20);
                float damage = target.getMaxHealth() * 0.05f;
                target.hurt(MyGoDamageType.hasSource((Level)target.level(), (ResourceKey)MyGoDamageType.TRUEDAMAGE, (Entity)player), damage);
            }
        }
    }

    @SubscribeEvent
    public static void onElementalPlayerHurt(LivingHurtEvent event) {
        LivingEntity attacker;
        Player player;
        if (event.getEntity().level().isClientSide) {
            return;
        }
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player && SoulStoneUtil.hasEffect(player = (Player)livingEntity, (Item)ModItems.ELEMENTAL_SOUL_STONE.get()) && (attacker = ModEvents.resolveAttacker(event.getSource())) != null && attacker != player) {
            attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 7));
        }
    }

    @SubscribeEvent
    public static void onElementalFireDamage(LivingDamageEvent event) {
        DamageSource source;
        Player player;
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player && SoulStoneUtil.hasEffect(player = (Player)livingEntity, (Item)ModItems.ELEMENTAL_SOUL_STONE.get()) && ((source = event.getSource()).is(DamageTypes.IN_FIRE) || source.is(DamageTypes.ON_FIRE) || source.is(DamageTypes.LAVA) || source.is(DamageTypes.HOT_FLOOR) || source.is(DamageTypes.FREEZE))) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }
        LivingEntity dead = event.getEntity();
        if (dead.getType().getCategory() != MobCategory.MONSTER) {
            return;
        }
        Player killer = ModEvents.resolveKillerPlayer(event.getSource());
        if (killer != null && SoulStoneUtil.hasEffect(killer, (Item)ModItems.SAGE_SOUL_STONE.get()) && killer.getRandom().nextDouble() < 0.5) {
            Level level = dead.level();
            level.addFreshEntity((Entity)new ItemEntity(level, dead.getX(), dead.getY(), dead.getZ(), new ItemStack((ItemLike)Items.GOLD_INGOT)));
        }
    }

    private static LivingEntity resolveAttacker(DamageSource source) {
        Projectile projectile;
        Entity entity;
        Entity direct = source.getDirectEntity();
        Entity entity2 = source.getEntity();
        if (entity2 instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)entity2;
            return living;
        }
        if (direct instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)direct;
            return living;
        }
        if (entity2 instanceof Projectile && (entity = (projectile = (Projectile)entity2).getOwner()) instanceof LivingEntity) {
            LivingEntity owner = (LivingEntity)entity;
            return owner;
        }
        if (direct instanceof Projectile && (entity = (projectile = (Projectile)direct).getOwner()) instanceof LivingEntity) {
            LivingEntity owner = (LivingEntity)entity;
            return owner;
        }
        return null;
    }

    private static Player resolveKillerPlayer(DamageSource source) {
        Projectile projectile;
        Entity entity;
        Entity direct = source.getDirectEntity();
        Entity entity2 = source.getEntity();
        if (entity2 instanceof Player) {
            Player player = (Player)entity2;
            return player;
        }
        if (direct instanceof Player) {
            Player player = (Player)direct;
            return player;
        }
        if (entity2 instanceof Projectile && (entity = (projectile = (Projectile)entity2).getOwner()) instanceof Player) {
            Player player = (Player)entity;
            return player;
        }
        if (direct instanceof Projectile && (entity = (projectile = (Projectile)direct).getOwner()) instanceof Player) {
            Player player = (Player)entity;
            return player;
        }
        return null;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        boolean active;
        Player player = event.player;
        Level level = player.level();
        if (level.isClientSide) {
            return;
        }
        CompoundTag data = player.getPersistentData();
        int disintegrateCooldown = data.getInt(DISINTEGRATE_COOLDOWN_KEY);
        if (disintegrateCooldown > 0) {
            data.putInt(DISINTEGRATE_COOLDOWN_KEY, disintegrateCooldown - 1);
        }
        ServerPacketHandlers.tickSageOrbCooldown(player);
        if (data.contains("projecte_soulstone:transmutation_pause_daytime")) {
            if (player.containerMenu instanceof TransmutationContainer) {
                ((ServerLevel)level).setDayTime(data.getLong("projecte_soulstone:transmutation_pause_daytime"));
            } else {
                data.remove("projecte_soulstone:transmutation_pause_daytime");
            }
        }
        boolean wearingDark = SoulStoneUtil.hasEffect(player, (Item)ModItems.DARK_SOUL_STONE.get());
        boolean sprinting = player.isSprinting();
        int phaseCooldown = data.getInt(PHASE_COOLDOWN_KEY);
        if (phaseCooldown > 0) {
            data.putInt(PHASE_COOLDOWN_KEY, phaseCooldown - 1);
        }
        boolean bl = active = data.getInt(PHASE_TIME_KEY) > 0;
        if (wearingDark && sprinting && phaseCooldown <= 0) {
            if (ModEvents.canPhaseThrough(player, level)) {
                long consumed = EMCHelper.consumePlayerFuel((Player)player, (long)50L);
                if (consumed == -1L) {
                    if (active) {
                        ModEvents.stopPhase(player, data);
                    }
                    return;
                }
                if (!active) {
                    data.putInt(PHASE_TIME_KEY, 100);
                    active = true;
                    ModMessages.sendPhaseSync((ServerPlayer)player, true);
                }
                player.noPhysics = true;
                player.setNoGravity(true);
                int remaining = data.getInt(PHASE_TIME_KEY) - 1;
                data.putInt(PHASE_TIME_KEY, remaining);
                if (remaining <= 0) {
                    ModEvents.stopPhase(player, data);
                    return;
                }
                ModEvents.spawnPhaseParticles(player, level);
            } else if (active) {
                ModEvents.stopPhase(player, data);
            }
        } else if (active) {
            ModEvents.stopPhase(player, data);
        } else if (wearingDark) {
            if (player.noPhysics) {
                player.noPhysics = false;
            }
            if (player.isNoGravity()) {
                player.setNoGravity(false);
            }
        }
    }

    private static boolean canPhaseThrough(Player player, Level level) {
        Direction facing = player.getDirection();
        BlockPos ahead = player.blockPosition().relative(facing);
        return !ModEvents.isBlocking(level, ahead) && !ModEvents.isBlocking(level, ahead.above());
    }

    private static boolean isBlocking(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.isSolid() && !state.is(ModTags.PHASE_PASSABLE);
    }

    private static void spawnPhaseParticles(Player player, Level level) {
        if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            Vec3 pos = player.position().add(0.0, 1.1, 0.0);
            DustParticleOptions particle = new DustParticleOptions(new Vector3f(0.3f, 0.6f, 1.0f), 1.0f);
            double angleBase = (double)level.getGameTime() * 0.3;
            RandomSource random = player.getRandom();
            for (int i = 0; i < 3; ++i) {
                double angle = angleBase + (double)i * 2.0943951023931953;
                double x = pos.x + Math.cos(angle) * 0.6;
                double z = pos.z + Math.sin(angle) * 0.6;
                serverLevel.sendParticles((ParticleOptions)particle, x, pos.y, z, 1, 0.0, 0.0, 0.0, 0.0);
            }
            serverLevel.sendParticles((ParticleOptions)particle, pos.x, pos.y, pos.z, 1, random.nextDouble() * 0.2 - 0.1, random.nextDouble() * 0.2, random.nextDouble() * 0.2 - 0.1, 0.0);
        }
    }

    private static void stopPhase(Player player, CompoundTag data) {
        data.putInt(PHASE_TIME_KEY, 0);
        data.putInt(PHASE_COOLDOWN_KEY, 200);
        player.noPhysics = false;
        player.setNoGravity(false);
        if (player instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer)player;
            ModMessages.sendPhaseSync(serverPlayer, false);
        }
    }
}

