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
        if (event.getEntity().m_9236_().f_46443_) {
            return;
        }
        Entity entity = event.getSource().m_7639_();
        if (entity instanceof Player && SoulStoneUtil.hasEffect(player = (Player)entity, (Item)ModItems.RED_SOUL_STONE.get())) {
            if (event.getSource().m_276093_(MyGoDamageType.TRUEDAMAGE)) {
                return;
            }
            event.setAmount(event.getAmount() * 3.0f);
            LivingEntity target = event.getEntity();
            CompoundTag data = player.getPersistentData();
            int cooldown = data.m_128451_(DISINTEGRATE_COOLDOWN_KEY);
            if (cooldown <= 0 && player.m_217043_().m_188500_() < 0.33) {
                data.m_128405_(DISINTEGRATE_COOLDOWN_KEY, 20);
                float damage = target.m_21233_() * 0.05f;
                target.m_6469_(MyGoDamageType.hasSource((Level)target.m_9236_(), (ResourceKey)MyGoDamageType.TRUEDAMAGE, (Entity)player), damage);
            }
        }
    }

    @SubscribeEvent
    public static void onElementalPlayerHurt(LivingHurtEvent event) {
        LivingEntity attacker;
        Player player;
        if (event.getEntity().m_9236_().f_46443_) {
            return;
        }
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player && SoulStoneUtil.hasEffect(player = (Player)livingEntity, (Item)ModItems.ELEMENTAL_SOUL_STONE.get()) && (attacker = ModEvents.resolveAttacker(event.getSource())) != null && attacker != player) {
            attacker.m_7292_(new MobEffectInstance(MobEffects.f_19597_, 200, 7));
        }
    }

    @SubscribeEvent
    public static void onElementalFireDamage(LivingDamageEvent event) {
        DamageSource source;
        Player player;
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player && SoulStoneUtil.hasEffect(player = (Player)livingEntity, (Item)ModItems.ELEMENTAL_SOUL_STONE.get()) && ((source = event.getSource()).m_276093_(DamageTypes.f_268631_) || source.m_276093_(DamageTypes.f_268468_) || source.m_276093_(DamageTypes.f_268546_) || source.m_276093_(DamageTypes.f_268434_) || source.m_276093_(DamageTypes.f_268444_))) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity().m_9236_().f_46443_) {
            return;
        }
        LivingEntity dead = event.getEntity();
        if (dead.m_6095_().m_20674_() != MobCategory.MONSTER) {
            return;
        }
        Player killer = ModEvents.resolveKillerPlayer(event.getSource());
        if (killer != null && SoulStoneUtil.hasEffect(killer, (Item)ModItems.SAGE_SOUL_STONE.get()) && killer.m_217043_().m_188500_() < 0.5) {
            Level level = dead.m_9236_();
            level.m_7967_((Entity)new ItemEntity(level, dead.m_20185_(), dead.m_20186_(), dead.m_20189_(), new ItemStack((ItemLike)Items.f_42417_)));
        }
    }

    private static LivingEntity resolveAttacker(DamageSource source) {
        Projectile projectile;
        Entity entity;
        Entity direct = source.m_7640_();
        Entity entity2 = source.m_7639_();
        if (entity2 instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)entity2;
            return living;
        }
        if (direct instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)direct;
            return living;
        }
        if (entity2 instanceof Projectile && (entity = (projectile = (Projectile)entity2).m_19749_()) instanceof LivingEntity) {
            LivingEntity owner = (LivingEntity)entity;
            return owner;
        }
        if (direct instanceof Projectile && (entity = (projectile = (Projectile)direct).m_19749_()) instanceof LivingEntity) {
            LivingEntity owner = (LivingEntity)entity;
            return owner;
        }
        return null;
    }

    private static Player resolveKillerPlayer(DamageSource source) {
        Projectile projectile;
        Entity entity;
        Entity direct = source.m_7640_();
        Entity entity2 = source.m_7639_();
        if (entity2 instanceof Player) {
            Player player = (Player)entity2;
            return player;
        }
        if (direct instanceof Player) {
            Player player = (Player)direct;
            return player;
        }
        if (entity2 instanceof Projectile && (entity = (projectile = (Projectile)entity2).m_19749_()) instanceof Player) {
            Player player = (Player)entity;
            return player;
        }
        if (direct instanceof Projectile && (entity = (projectile = (Projectile)direct).m_19749_()) instanceof Player) {
            Player player = (Player)entity;
            return player;
        }
        return null;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        boolean active;
        Player player = event.player;
        Level level = player.m_9236_();
        if (level.f_46443_) {
            return;
        }
        CompoundTag data = player.getPersistentData();
        int disintegrateCooldown = data.m_128451_(DISINTEGRATE_COOLDOWN_KEY);
        if (disintegrateCooldown > 0) {
            data.m_128405_(DISINTEGRATE_COOLDOWN_KEY, disintegrateCooldown - 1);
        }
        ServerPacketHandlers.tickSageOrbCooldown(player);
        if (data.m_128441_("projecte_soulstone:transmutation_pause_daytime")) {
            if (player.f_36096_ instanceof TransmutationContainer) {
                ((ServerLevel)level).m_8615_(data.m_128454_("projecte_soulstone:transmutation_pause_daytime"));
            } else {
                data.m_128473_("projecte_soulstone:transmutation_pause_daytime");
            }
        }
        boolean wearingDark = SoulStoneUtil.hasEffect(player, (Item)ModItems.DARK_SOUL_STONE.get());
        boolean sprinting = player.m_20142_();
        int phaseCooldown = data.m_128451_(PHASE_COOLDOWN_KEY);
        if (phaseCooldown > 0) {
            data.m_128405_(PHASE_COOLDOWN_KEY, phaseCooldown - 1);
        }
        boolean bl = active = data.m_128451_(PHASE_TIME_KEY) > 0;
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
                    data.m_128405_(PHASE_TIME_KEY, 100);
                    active = true;
                    ModMessages.sendPhaseSync((ServerPlayer)player, true);
                }
                player.f_19794_ = true;
                player.m_20242_(true);
                int remaining = data.m_128451_(PHASE_TIME_KEY) - 1;
                data.m_128405_(PHASE_TIME_KEY, remaining);
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
            if (player.f_19794_) {
                player.f_19794_ = false;
            }
            if (player.m_20068_()) {
                player.m_20242_(false);
            }
        }
    }

    private static boolean canPhaseThrough(Player player, Level level) {
        Direction facing = player.m_6350_();
        BlockPos ahead = player.m_20183_().m_121945_(facing);
        return !ModEvents.isBlocking(level, ahead) && !ModEvents.isBlocking(level, ahead.m_7494_());
    }

    private static boolean isBlocking(Level level, BlockPos pos) {
        BlockState state = level.m_8055_(pos);
        return state.m_280296_() && !state.m_204336_(ModTags.PHASE_PASSABLE);
    }

    private static void spawnPhaseParticles(Player player, Level level) {
        if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            Vec3 pos = player.m_20182_().m_82520_(0.0, 1.1, 0.0);
            DustParticleOptions particle = new DustParticleOptions(new Vector3f(0.3f, 0.6f, 1.0f), 1.0f);
            double angleBase = (double)level.m_46467_() * 0.3;
            RandomSource random = player.m_217043_();
            for (int i = 0; i < 3; ++i) {
                double angle = angleBase + (double)i * 2.0943951023931953;
                double x = pos.f_82479_ + Math.cos(angle) * 0.6;
                double z = pos.f_82481_ + Math.sin(angle) * 0.6;
                serverLevel.m_8767_((ParticleOptions)particle, x, pos.f_82480_, z, 1, 0.0, 0.0, 0.0, 0.0);
            }
            serverLevel.m_8767_((ParticleOptions)particle, pos.f_82479_, pos.f_82480_, pos.f_82481_, 1, random.m_188500_() * 0.2 - 0.1, random.m_188500_() * 0.2, random.m_188500_() * 0.2 - 0.1, 0.0);
        }
    }

    private static void stopPhase(Player player, CompoundTag data) {
        data.m_128405_(PHASE_TIME_KEY, 0);
        data.m_128405_(PHASE_COOLDOWN_KEY, 200);
        player.f_19794_ = false;
        player.m_20242_(false);
        if (player instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer)player;
            ModMessages.sendPhaseSync(serverPlayer, false);
        }
    }
}

