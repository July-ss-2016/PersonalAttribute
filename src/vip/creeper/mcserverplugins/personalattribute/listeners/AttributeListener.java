package vip.creeper.mcserverplugins.personalattribute.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.projectiles.ProjectileSource;
import vip.creeper.mcserverplugins.personalattribute.PersonalAttribute;
import vip.creeper.mcserverplugins.personalattribute.PersonalAttributeType;
import vip.creeper.mcserverplugins.personalattribute.managers.PersonalAttributeManager;

public class AttributeListener implements Listener {
    private PersonalAttributeManager personalAttributeManager;

    public AttributeListener(PersonalAttribute plugin) {
        this.personalAttributeManager = plugin.getPersonalAttributeManager();
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        player.setHealthScale(20D); //无论有多少血，只显示一排血
        //初始化设置
        player.setMaxHealth(personalAttributeManager.getPlayerAttributeLevel(playerName, PersonalAttributeType.HEALTH));
        player.setWalkSpeed((float) (player.getWalkSpeed() + personalAttributeManager.getPlayerAttributeLevel(playerName, PersonalAttributeType.SPEED)));
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        Player player = null;
        Entity damager = event.getDamager();

        if (damager instanceof Player) {
            player = (Player) damager;
        } else if (damager instanceof Arrow) {
            ProjectileSource projectileSource = ((Arrow) damager).getShooter();

            player = projectileSource instanceof Player ? (Player) projectileSource : null;
        }

        if (player == null) {
            return;
        }

        event.setDamage(event.getFinalDamage() + personalAttributeManager.getPlayerAttributeLevel(player.getName(), PersonalAttributeType.DAMAGE));
    }
}
