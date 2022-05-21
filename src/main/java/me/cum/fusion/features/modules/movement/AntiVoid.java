
package me.cum.fusion.features.modules.movement;

import me.cum.fusion.features.modules.*;
import me.cum.fusion.features.setting.*;
import net.minecraft.util.math.*;

public class AntiVoid extends Module
{
    public Setting<Double> yLevel;
    public Setting<Double> yForce;
    
    public AntiVoid() {
        super("AntiVoid", "Glitches you up from void.", Module.Category.MOVEMENT, false, false, false);
        this.yLevel = (Setting<Double>)this.register(new Setting("YLevel", (T)1.0, (T)0.1, (T)5.0));
        this.yForce = (Setting<Double>)this.register(new Setting("YMotion", (T)0.1, (T)0.0, (T)1.0));
    }
    
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        if (!AntiVoid.mc.player.noClip && AntiVoid.mc.player.posY <= this.yLevel.getValue()) {
            final RayTraceResult trace = AntiVoid.mc.world.rayTraceBlocks(AntiVoid.mc.player.getPositionVector(), new Vec3d(AntiVoid.mc.player.posX, 0.0, AntiVoid.mc.player.posZ), false, false, false);
            if (trace != null && trace.typeOfHit == RayTraceResult.Type.BLOCK) {
                return;
            }
            AntiVoid.mc.player.motionY = this.yForce.getValue();
            if (AntiVoid.mc.player.getRidingEntity() != null) {
                AntiVoid.mc.player.getRidingEntity().motionY = this.yForce.getValue();
            }
        }
    }
}