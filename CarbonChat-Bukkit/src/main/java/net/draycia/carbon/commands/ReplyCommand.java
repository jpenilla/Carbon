package net.draycia.carbon.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import net.draycia.carbon.CarbonChatBukkit;
import net.draycia.carbon.api.users.ChatUser;
import net.draycia.carbon.api.commands.CommandSettings;
import net.draycia.carbon.util.CommandUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.LinkedHashMap;

public class ReplyCommand {

  @NonNull
  private final CarbonChatBukkit carbonChat;

  public ReplyCommand(@NonNull final CarbonChatBukkit carbonChat, @NonNull final CommandSettings commandSettings) {
    this.carbonChat = carbonChat;

    if (!commandSettings.enabled()) {
      return;
    }

    CommandUtils.handleDuplicateCommands(commandSettings);

    final LinkedHashMap<String, Argument> arguments = new LinkedHashMap<>();
    arguments.put("message", new GreedyStringArgument());

    new CommandAPICommand(commandSettings.name())
      .withArguments(arguments)
      .withAliases(commandSettings.aliases())
      .withPermission(CommandPermission.fromString("carbonchat.reply"))
      .executesPlayer(this::execute)
      .register();
  }

  private void execute(@NonNull final Player player, @NonNull final Object @NonNull [] args) {
    final String input = (String) args[0];

    final ChatUser user = this.carbonChat.userService().wrap(player.getUniqueId());

    if (input.isEmpty()) {
      final String message = this.carbonChat.translations().replyMessageBlank();
      final Component component = this.carbonChat.messageProcessor().processMessage(message, "br", "\n");

      user.sendMessage(component);

      return;
    }

    if (user.replyTarget() == null) {
      final String message = this.carbonChat.translations().noReplyTarget();
      final Component component = this.carbonChat.messageProcessor().processMessage(message, "br", "\n");

      user.sendMessage(component);

      return;
    }

    final ChatUser targetUser = this.carbonChat.userService().wrap(user.replyTarget());

    targetUser.sendMessage(user, input);
  }

}