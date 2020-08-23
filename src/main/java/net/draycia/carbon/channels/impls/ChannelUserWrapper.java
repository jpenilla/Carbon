package net.draycia.carbon.channels.impls;

import net.draycia.carbon.channels.ChannelUser;
import net.draycia.carbon.channels.ChatChannel;
import net.draycia.carbon.storage.ChatUser;
import net.draycia.carbon.storage.UserChannelSettings;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ChannelUserWrapper implements ChannelUser {

    private ChatUser user;
    private boolean isSpyingChannel;

    public ChannelUserWrapper(ChatUser user, boolean isSpyingChannel) {
        this.user = user;
        this.isSpyingChannel = isSpyingChannel;
    }

    public ChatUser user() {
        return user;
    }

    public boolean isSpyingChannel() {
        return isSpyingChannel;
    }

    @Override
    public Player asPlayer() {
        return user.asPlayer();
    }

    @Override
    public OfflinePlayer asOfflinePlayer() {
        return user.asOfflinePlayer();
    }

    @Override
    public UUID getUUID() {
        return user.getUUID();
    }

    @Override
    public boolean isOnline() {
        return user.isOnline();
    }

    @Override
    public String getNickname() {
        return user.getNickname();
    }

    @Override
    public void setNickname(String nickname, boolean fromRemote) {
        user.setNickname(nickname, fromRemote);
    }

    @Override
    public void setNickname(String nickname) {
        user.setNickname(nickname);
    }

    @Override
    public void updateUserNickname() {
        user.updateUserNickname();
    }

    @Override
    public ChatChannel getSelectedChannel() {
        return user.getSelectedChannel();
    }

    @Override
    public void setSelectedChannel(ChatChannel channel, boolean fromRemote) {
        user.setSelectedChannel(channel, fromRemote);
    }

    @Override
    public void setSelectedChannel(ChatChannel channel) {
        user.setSelectedChannel(channel);
    }

    @Override
    public void clearSelectedChannel() {
        user.clearSelectedChannel();
    }

    @Override
    public UserChannelSettings getChannelSettings(ChatChannel channel) {
        return user.getChannelSettings(channel);
    }

    @Override
    public boolean isSpyingWhispers() {
        return user.isSpyingWhispers();
    }

    @Override
    public void setSpyingWhispers(boolean spyingWhispers, boolean fromRemote) {
        user.setSpyingWhispers(spyingWhispers, fromRemote);
    }

    @Override
    public void setSpyingWhispers(boolean spyingWhispers) {
        user.setSpyingWhispers(spyingWhispers);
    }

    @Override
    public boolean isMuted() {
        return user.isMuted();
    }

    @Override
    public void setMuted(boolean muted, boolean fromRemote) {
        user.setMuted(muted, fromRemote);
    }

    @Override
    public void setMuted(boolean muted) {
        user.setMuted(muted);
    }

    @Override
    public boolean isShadowMuted() {
        return user.isShadowMuted();
    }

    @Override
    public void setShadowMuted(boolean shadowMuted, boolean fromRemote) {
        user.setShadowMuted(shadowMuted, fromRemote);
    }

    @Override
    public void setShadowMuted(boolean shadowMuted) {
        user.setShadowMuted(shadowMuted);
    }

    @Override
    public @Nullable UUID getReplyTarget() {
        return user.getReplyTarget();
    }

    @Override
    public void setReplyTarget(@Nullable UUID target, boolean fromRemote) {
        user.setReplyTarget(target, fromRemote);
    }

    @Override
    public void setReplyTarget(@Nullable UUID target) {
        user.setReplyTarget(target);
    }

    @Override
    public void setReplyTarget(@Nullable ChatUser target, boolean fromRemote) {
        user.setReplyTarget(target, fromRemote);
    }

    @Override
    public void setReplyTarget(@Nullable ChatUser target) {
        user.setReplyTarget(target);
    }

    @Override
    public boolean isIgnoringUser(UUID uuid) {
        return user.isIgnoringUser(uuid);
    }

    @Override
    public void setIgnoringUser(UUID uuid, boolean ignoring, boolean fromRemote) {
        user.setIgnoringUser(uuid, ignoring, fromRemote);
    }

    @Override
    public void setIgnoringUser(UUID uuid, boolean ignoring) {
        user.setIgnoringUser(uuid, ignoring);
    }

    @Override
    public boolean isIgnoringUser(ChatUser user) {
        return user.isIgnoringUser(user);
    }

    @Override
    public void setIgnoringUser(ChatUser user, boolean ignoring, boolean fromRemote) {
        user.setIgnoringUser(user, ignoring, fromRemote);
    }

    @Override
    public void setIgnoringUser(ChatUser user, boolean ignoring) {
        user.setIgnoringUser(user, ignoring);
    }

    @Override
    public void sendMessage(ChatUser sender, String message) {
        user.sendMessage(sender, message);
    }
}
