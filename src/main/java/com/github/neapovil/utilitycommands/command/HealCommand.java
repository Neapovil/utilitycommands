package com.github.neapovil.utilitycommands.command;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;

public final class HealCommand
{
    public static final void register()
    {
        new CommandAPICommand("heal")
                .withPermission("utilitycommands.command.heal")
                .executesPlayer((player, args) -> {
                    player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());

                    player.sendMessage("You have been healed");
                })
                .register();

        new CommandAPICommand("heal")
                .withPermission("utilitycommands.command.heal")
                .withArguments(new PlayerArgument("player").withPermission("utilitycommands.command.heal.other"))
                .executes((sender, args) -> {
                    final Player player = (Player) args[0];

                    player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                    player.sendMessage("You have been healed");

                    sender.sendMessage(player.getName() + " has been healed");
                })
                .register();
    }
}
