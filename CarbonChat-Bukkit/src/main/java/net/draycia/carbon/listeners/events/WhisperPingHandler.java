package net.draycia.carbon.listeners.events;

import net.draycia.carbon.CarbonChatBukkit;
import net.draycia.carbon.api.events.misc.CarbonEvents;
import net.draycia.carbon.api.events.PrivateMessageEvent;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.event.PostOrders;
import org.bukkit.Bukkit;
import org.checkerframework.checker.nullness.qual.NonNull;

public class WhisperPingHandler {

  public WhisperPingHandler(@NonNull final CarbonChatBukkit carbonChat) {

    CarbonEvents.register(PrivateMessageEvent.class, PostOrders.LAST, false, event -> {
      if (event.sender().uuid().equals(event.target().uuid())) {
        return;
      }

      final String senderName = Bukkit.getOfflinePlayer(event.sender().uuid()).getName();

      if (senderName == null || !event.message().contains(senderName)) {
        return;
      }

      if (!carbonChat.getConfig().getBoolean("whisper.pings.enabled")) {
        return;
      }

      final Key key = Key.of(carbonChat.getConfig().getString("whisper.pings.sound"));
      final Sound.Source source = Sound.Source.valueOf(carbonChat.getConfig().getString("whisper.pings.source"));
      final float volume = (float) carbonChat.getConfig().getDouble("whisper.pings.volume");
      final float pitch = (float) carbonChat.getConfig().getDouble("whisper.pings.pitch");

      event.sender().playSound(Sound.of(key, source, volume, pitch));
    });
  }

}