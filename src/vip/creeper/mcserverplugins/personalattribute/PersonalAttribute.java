package vip.creeper.mcserverplugins.personalattribute;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
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

        Bukkit.getPluginManager().registerEvents(new AttributeListener(this), this);
    }

    public static PersonalAttribute getInstance() {
        return instance;
    }

    public PersonalAttributeManager getPersonalAttributeManager() {
        return personalAttributeManager;
    }
}
