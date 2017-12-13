package vip.creeper.mcserverplugins.personalattribute;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import vip.creeper.mcserverplugins.personalattribute.listeners.AttributeListener;
import vip.creeper.mcserverplugins.personalattribute.managers.PersonalAttributeManager;

import java.io.File;

public class PersonalAttribute extends JavaPlugin {
    private static PersonalAttribute instance;
    private PersonalAttributeManager personalAttributeManager;

    public void onEnable() {
        instance = this;
        this.personalAttributeManager = new PersonalAttributeManager(this);
        File playerDataFolder = new File(getDataFolder().getAbsolutePath() + File.separator + "data");

        if (!playerDataFolder.exists()) {
            if (playerDataFolder.mkdirs()) {
                getLogger().info("创建数据文件夹失败!");
                setEnabled(false);
                return;
            }
        }

        Bukkit.getPluginManager().registerEvents(new AttributeListener(this), this);
        personalAttributeManager.promotePlayerAttributeLevel("player", PersonalAttributeType.DAMAGE, 1);
    }

    public static PersonalAttribute getInstance() {
        return instance;
    }

    public PersonalAttributeManager getPersonalAttributeManager() {
        return personalAttributeManager;
    }
}
