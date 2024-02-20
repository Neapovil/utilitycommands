package com.github.neapovil.utilitycommands.command;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;

public final class HealCommand implements ICommand
{
    public void register()
    {
        new CommandAPICommand("heal")
                .withPermission("utilitycommands.command")
                .executesPlayer((player, args) -> {
                    player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                    player.sendMessage("You have been healed");
                })
                .register();

        new CommandAPICommand("heal")
                .withPermission("utilitycommands.command")
                .withArguments(new PlayerArgument("player"))
                .executes((sender, args) -> {
                    final Player player = (Player) args.get("player");

                    player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                    player.sendMessage("You have been healed");

                    sender.sendMessage(player.getName() + " has been healed");
                })
                .register();
    }
}
