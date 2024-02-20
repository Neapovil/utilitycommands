package com.github.neapovil.utilitycommands.command;

import org.bukkit.entity.Player;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;

public final class FeedCommand implements ICommand
{
    public void register()
    {
        new CommandAPICommand("feed")
                .withPermission("utilitycommands.command")
                .executesPlayer((player, args) -> {
                    player.setFoodLevel(20);

                    player.sendMessage("You have been feed");
                })
                .register();

        new CommandAPICommand("feed")
                .withPermission("utilitycommands.command")
                .withArguments(new PlayerArgument("player"))
                .executes((sender, args) -> {
                    final Player player = (Player) args.get("player");

                    player.setFoodLevel(20);
                    player.sendMessage("You have been fed");

                    sender.sendMessage(player.getName() + " has been fed");
                })
                .register();
    }
}
