package net.draycia.carbon.channels;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Collections;
import java.util.List;

@FunctionalInterface
public interface ChannelConfigurationSettings {

    ConfigurationSection getConfig();

    default Boolean isDefault() {
        return getBoolean("default");
    }

    default Boolean isIgnorable() {
        return getBoolean("ignorable");
    }

    default Boolean shouldBungee() {
        return getBoolean("should-bungee");
    }

    default Boolean honorsRecipientList() {
        return getBoolean("honors-recipient-list");
    }

    default Boolean permissionGroupMatching() {
        return getBoolean("permission-group-matching");
    }

    default List<String> getGroupOverrides() {
        return getStringList("group-overrides");
    }

    default String getSwitchMessage() {
        return getString("switch-message");
    }

    default String getSwitchOtherMessage() {
        return getString("switch-other-message");
    }

    default String getSwitchFailureMessage() {
        return getString("switch-failure-message");
    }

    default String getToggleOffMessage() {
        return getString("toggle-off-message");
    }

    default String getToggleOnMessage() {
        return getString("toggle-on-message");
    }

    default String getToggleOtherOnMessage() {
        return getString("toggle-other-on");
    }

    default String getToggleOtherOffMessage() {
        return getString("toggle-other-off");
    }

    default String getCannotUseMessage() {
        return getString("cannot-use-channel");
    }

    default Boolean primaryGroupOnly() {
        return getBoolean("primary-group-only");
    }

    default boolean shouldCancelChatEvent() {
        return getBoolean("cancel-message-event");
    }

    default String getDefaultFormatName() {
        return getString("default-group");
    }

    default ConfigurationSection getCarbonConfig() {
        return Bukkit.getPluginManager().getPlugin("CarbonChat").getConfig();
    }

    default String getString(String key) {
        if (getConfig() != null && getConfig().contains(key)) {
            return getConfig().getString(key);
        }

        ConfigurationSection defaultSection = getCarbonConfig().getConfigurationSection("default");

        if (defaultSection != null && defaultSection.contains(key)) {
            return defaultSection.getString(key);
        }

        return null;
    }

    default List<String> getStringList(String key) {
        if (getConfig() != null && getConfig().contains(key)) {
            return getConfig().getStringList(key);
        }

        ConfigurationSection defaultSection = getCarbonConfig().getConfigurationSection("default");

        if (defaultSection != null && defaultSection.contains(key)) {
            return defaultSection.getStringList(key);
        }

        return Collections.emptyList();
    }

    default boolean getBoolean(String key) {
        if (getConfig() != null && getConfig().contains(key)) {
            return getConfig().getBoolean(key);
        }

        ConfigurationSection defaultSection = getCarbonConfig().getConfigurationSection("default");

        if (defaultSection != null && defaultSection.contains(key)) {
            return defaultSection.getBoolean(key);
        }

        return false;
    }

    default double getDouble(String key) {
        if (getConfig() != null && getConfig().contains(key)) {
            return getConfig().getDouble(key);
        }

        ConfigurationSection defaultSection = getCarbonConfig().getConfigurationSection("default");

        if (defaultSection != null && defaultSection.contains(key)) {
            return defaultSection.getDouble(key);
        }

        return 0;
    }

}
