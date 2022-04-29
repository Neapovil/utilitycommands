package com.github.neapovil.utilitycommands.command;

import org.bukkit.entity.Player;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;

public final class PlayerInventoryCommand
{
    public static final void register()
    {
        new CommandAPICommand("playerinventory")
                .withPermission("utilitycommands.command.playerinventory")
                .withArguments(new EntitySelectorArgument("player", EntitySelectorArgument.EntitySelector.ONE_PLAYER))
                .executesPlayer((player, args) -> {
                    final Player target = (Player) args[0];

                    player.openInventory(target.getInventory());
                })
                .register();
    }
}
