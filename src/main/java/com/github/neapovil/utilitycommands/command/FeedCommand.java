package com.github.neapovil.utilitycommands.command;

import org.bukkit.entity.Player;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;

public final class FeedCommand
{
    public static final void register()
    {
        new CommandAPICommand("feed")
                .withPermission("utilitycommands.command.feed")
                .executesPlayer((player, args) -> {
                    player.setFoodLevel(20);
                    
                    player.sendMessage("You have been feed");
                })
                .register();
        
        new CommandAPICommand("feed")
                .withPermission("utilitycommands.command.feed")
                .withArguments(new PlayerArgument("player").withPermission("utilitycommands.command.feed.other"))
                .executes((sender, args) -> {
                    final Player player = (Player) args[0];
                    
                    player.setFoodLevel(20);
                    player.sendMessage("You have been fed");
                    
                    sender.sendMessage(player.getName() + " has been fed");
                })
                .register();
    }
}
