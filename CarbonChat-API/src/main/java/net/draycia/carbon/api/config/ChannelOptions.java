package net.draycia.carbon.api.config;

import net.draycia.carbon.api.CarbonChatProvider;
import net.draycia.carbon.api.Context;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.objectmapping.Setting;
import org.spongepowered.configurate.serialize.ConfigSerializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigSerializable
public class ChannelOptions {

  @Setting(comment = "What this channel is identified as. This will be what's typed ingame to use the channel.")
  private String key = "channel";
  
  @Setting(comment = "This is what the <color> placeholder will typically be replaced with.\n" +
    "Hex RGB (#B19CD9), named colors (light_purple), legacy (&d), and legacy RGB (&x&b&1&2&c&d&9) are all supported.\n" +
    "If on a platform that supports PlaceholderAPI, this option will be ran through that as well.\n" +
    "Note that the <color> placeholder is also used for personal and global user colors.")
  private String color;
  
  @Setting(comment = "The contexts for this channel, ")
  private Map<String, Context> contexts = new HashMap<>(); // TODO: set defaults
  
  @Setting(comment = "The formats for this channel. The key is the name of the group as your permissions plugin reports it.")
  private Map<String, String> formats = new HashMap<>(); // TODO: set defaults
  
  @Setting(comment = "The name of the format that the plugin will fall back to when it cannot find a matching format for the player's groups.")
  private String defaultFormatName;
  
  @Setting(comment = "If this channel is the default channel players join in.\n" +
    "Also used as a fallback in case the player's selected channel cannot be found.")
  private boolean isDefault = false; // primitive because missing = false
  
  @Setting(comment = "If this channel can be ignored / hidden with the /ignore command")
  private Boolean ignorable; // boxed because missing = use defaults
  
  @Setting(comment = "If this channel syncs to other servers (cross-server chat), requires a messaging system setup")
  private Boolean crossServer;
  
  @Setting(comment = "If this channel should respect the bukkit recipient list, you normally shouldn't touch this")
  private Boolean honorsRecipientList;
  
  @Setting(comment = "If players with the permission carbonchat.group.groupname are considered to have the group groupname")
  private Boolean permissionGroupMatching;
  
  @Setting(comment = "A custom (ordered) list of group priorities")
  private List<String> groupOverrides;
  
  @Setting(comment = "The display name of this channel, supports minimessage. Used in command feedback")
  private String name = "";
  
  @Setting(comment = "If the player's chat message starts with whatever this is set to, the player speaks in this channel instead of their selected one")
  private String messagePrefix = "";
  
  @Setting(comment = "The command aliases for this channel")
  private List<String> aliases = new ArrayList<>();
  
  @Setting(comment = "If the bukkit chat event should be cancelled, you probably don't want to change this")
  private Boolean shouldCancelChatEvent;
  
  @Setting(comment = "If the player's format should be decided by their primary group only")
  private Boolean primaryGroupOnly;
  
  @Setting(comment = "")
  private String switchMessage;
  
  @Setting(comment = "")
  private String switchOtherMessage;
  
  @Setting(comment = "")
  private String switchFailureMessage;
  
  @Setting(comment = "")
  private String toggleOnMessage;
  
  @Setting(comment = "")
  private String toggleOffMessage;
  
  @Setting(comment = "")
  private String toggleOtherOnMessage;
  
  @Setting(comment = "")
  private String toggleOtherOffMessage;
  
  @Setting(comment = "")
  private String cannotUseMessage;
  
  @Setting(comment = "")
  private String cannotIgnoreMessage;
  
  private SharedChannelOptions defaultOptions() {
    return CarbonChatProvider.carbonChat().channelSettings().defaultChannelOptions();
  }

  public static ChannelOptions defaultChannel() {
    final ChannelOptions settings = new ChannelOptions();
    settings.key = "global";
    settings.isDefault = true;
    settings.name = "Global";
    settings.color = "#FFFFFF";
    settings.defaultFormatName = "default";
    settings.formats = Collections.singletonMap("default", "<color><<displayname><reset><color>> <message>");

    return settings;
  }

  @NonNull
  public String key() {
    return this.key;
  }

  public @Nullable String color() {
    if (this.color == null) {
      return this.defaultOptions().color();
    }

    return this.color;
  }

  public @Nullable Context context(final @NonNull String key) {
    final Context localContext;

    if (this.contexts != null) {
      localContext = this.contexts.get(key);
    } else {
      localContext = null;
    }

    if (localContext == null) {
      if (this.defaultOptions().contexts() != null) {
        return this.defaultOptions().contexts().get(key);
      } else {
        return null;
      }
    }

    return localContext;
  }

  public @Nullable Map<String, Context> contexts() {
    if (this.contexts == null) {
      return this.defaultOptions().contexts();
    }

    return this.contexts;
  }

  public @Nullable Map<String, Context> contextsAndDefault() {
    if (this.contexts == null) {
      return this.defaultOptions().contexts();
    }

    if (this.defaultOptions().contexts() == null) {
      return this.contexts;
    }

    final Map<String, Context> contexts = new HashMap<>(this.defaultOptions().contexts());
    contexts.putAll(this.contexts);

    return contexts;
  }

  public @Nullable String format(final @NonNull String key) {
    final String localFormat;

    if (this.formats() != null) {
      localFormat = this.formats().get(key);
    } else {
      localFormat = null;
    }

    if (localFormat == null) {
      if (this.defaultOptions().formats() != null) {
        return this.defaultOptions().formats().get(key);
      } else {
        return null;
      }
    }

    return localFormat;
  }

  public @Nullable Map<String, String> formats() {
    if (this.formats == null) {
      return this.defaultOptions().formats();
    }

    return this.formats;
  }

  public @Nullable Map<String, String> formatsAndDefault() {
    if (this.formats == null) {
      return this.defaultOptions().formats();
    }

    if (this.defaultOptions().formats() == null) {
      return this.formats;
    }

    final Map<String, String> formats = new HashMap<>(this.defaultOptions().formats());
    formats.putAll(this.formats);

    return formats;
  }

  public @Nullable String defaultFormatName() {
    if (this.defaultFormatName == null || this.defaultFormatName.isEmpty()) {
      return this.defaultOptions().defaultFormatName();
    }

    return this.defaultFormatName;
  }

  public boolean isDefault() {
    return this.isDefault;
  }

  public Boolean ignorable() {
    if (this.ignorable == null) {
      return this.defaultOptions().ignorable();
    }

    return this.ignorable;
  }

  public Boolean crossServer() {
    if (this.crossServer == null) {
      return this.defaultOptions().crossServer();
    }

    return this.crossServer;
  }

  public Boolean honorsRecipientList() {
    if (this.honorsRecipientList == null) {
      return this.defaultOptions().honorsRecipientList();
    }

    return this.honorsRecipientList;
  }

  public Boolean permissionGroupMatching() {
    if (this.permissionGroupMatching == null) {
      return this.defaultOptions().permissionGroupMatching();
    }

    return this.permissionGroupMatching;
  }

  @NonNull
  public List<@NonNull String> groupOverrides() {
    if (this.groupOverrides == null) {
      return this.defaultOptions().groupOverrides();
    }

    return this.groupOverrides;
  }

  @NonNull
  public String name() {
    if (this.name == null) {
      return this.key;
    }

    return this.name;
  }

  public @Nullable String messagePrefix() {
    return this.messagePrefix;
  }

  public @Nullable String switchMessage() {
    if (this.switchMessage == null) {
      return this.defaultOptions().switchMessage();
    }

    return this.switchMessage;
  }

  public @Nullable String switchOtherMessage() {
    if (this.switchOtherMessage == null) {
      return this.defaultOptions().switchOtherMessage();
    }

    return this.switchOtherMessage;
  }

  public @Nullable String switchFailureMessage() {
    if (this.switchFailureMessage == null) {
      return this.defaultOptions().switchFailureMessage();
    }

    return this.switchFailureMessage;
  }

  public @Nullable String cannotIgnoreMessage() {
    if (this.cannotIgnoreMessage == null) {
      return this.defaultOptions().cannotIgnoreMessage();
    }

    return this.cannotIgnoreMessage;
  }

  public @Nullable String toggleOffMessage() {
    if (this.toggleOffMessage == null) {
      return this.defaultOptions().toggleOffMessage();
    }

    return this.toggleOffMessage;
  }

  public @Nullable String toggleOnMessage() {
    if (this.toggleOnMessage == null) {
      return this.defaultOptions().toggleOnMessage();
    }

    return this.toggleOnMessage;
  }

  public @Nullable String toggleOtherOnMessage() {
    if (this.toggleOtherOnMessage == null) {
      return this.defaultOptions().toggleOtherOnMessage();
    }

    return this.toggleOtherOnMessage;
  }

  public @Nullable String toggleOtherOffMessage() {
    if (this.toggleOtherOffMessage == null) {
      return this.defaultOptions().toggleOtherOffMessage();
    }

    return this.toggleOtherOffMessage;
  }

  public @Nullable String cannotUseMessage() {
    if (this.cannotUseMessage == null) {
      return this.defaultOptions().cannotUseMessage();
    }

    return this.cannotUseMessage;
  }

  public Boolean primaryGroupOnly() {
    if (this.primaryGroupOnly == null) {
      return this.defaultOptions().primaryGroupOnly();
    }

    return this.primaryGroupOnly;
  }

  public Boolean shouldCancelChatEvent() {
    if (this.shouldCancelChatEvent == null) {
      return this.defaultOptions().shouldCancelChatEvent();
    }

    return this.shouldCancelChatEvent;
  }

  public @Nullable List<String> aliases() {
    return this.aliases;
  }

}