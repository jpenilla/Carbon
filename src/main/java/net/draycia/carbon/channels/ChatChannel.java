package net.draycia.carbon.channels;

import net.draycia.carbon.storage.ChatUser;
import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public abstract class ChatChannel implements ChannelAudience, ChannelConfigurationSettings {

    public abstract TextColor getChannelColor(ChatUser user);

    public abstract String getFormat(String group);

    public abstract String getName();

    public abstract String getKey();

    @Nullable
    public abstract String getMessagePrefix();

    public abstract String getAliases();

    public abstract boolean testContext(ChatUser sender, ChatUser target);

    public abstract Object getContext(String key);

    public abstract Boolean canPlayerUse(ChatUser user);

    public abstract Boolean canPlayerSee(ChatUser sender, ChatUser target, boolean checkSpying);

    public abstract Boolean canPlayerSee(ChatUser target, boolean checkSpying);

    public abstract List<Pattern> getItemLinkPatterns();

    public abstract Component sendMessage(ChatUser user, String message, boolean fromBungee);

    public abstract Component sendMessage(ChatUser user, Iterable<? extends ChatUser> recipients, String message, boolean fromBungee);

    public abstract void sendComponent(ChatUser user, Component component);

    public String processPlaceholders(ChatUser user, String input) { return input; }

}
