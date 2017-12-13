package vip.creeper.mcserverplugins.personalattribute.managers;

import org.bukkit.configuration.file.YamlConfiguration;
import vip.creeper.mcserverplugins.personalattribute.PersonalAttribute;
import vip.creeper.mcserverplugins.personalattribute.PersonalAttributeType;

import java.io.File;
import java.io.IOException;

public class PersonalAttributeManager {
    private PersonalAttribute plugin;

    public PersonalAttributeManager(PersonalAttribute plugin) {
        this.plugin = plugin;
    }

    public double getPlayerAttributeLevel(String playerName, PersonalAttributeType type) {
        return  getPlayerAttributeYml(playerName).getDouble("attributes." + type.name(), 0);
    }

    public boolean promotePlayerAttributeLevel(String playerName, PersonalAttributeType type, double amount) {
        return setPlayerAttributeLevel(playerName, type, getPlayerAttributeLevel(playerName, type) + amount);
    }

    public boolean reducePlayerAttributeLevel(String playerName, PersonalAttributeType type, double amount) {
        double temp = getPlayerAttributeLevel(playerName, type) - amount;

        return temp >= 0 && setPlayerAttributeLevel(playerName, type, temp);
    }

    public boolean setPlayerAttributeLevel(String playerName, PersonalAttributeType type, double level) {
        File playerDataFile = getPlayerAttributeFile(playerName);
        YamlConfiguration playerDataYml = YamlConfiguration.loadConfiguration(playerDataFile);

        if (!playerDataFile.exists()) {
            try {
                if (!playerDataFile.createNewFile()) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        playerDataYml.set("attributes." + type.name(), level);

        try {
            playerDataYml.save(playerDataFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private File getPlayerAttributeFile(String playerName) {
        return new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "data" + File.separator + playerName + ".yml");
    }

    private YamlConfiguration getPlayerAttributeYml(String playerName) {
        return YamlConfiguration.loadConfiguration(getPlayerAttributeFile(playerName));
    }
}
