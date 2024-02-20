package com.github.neapovil.utilitycommands.command;

import org.bukkit.entity.Player;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;

public final class PlayerInventoryCommand implements ICommand
{
    public void register()
    {
        new CommandAPICommand("playerinventory")
                .withPermission("utilitycommands.command")
                .withArguments(new EntitySelectorArgument.OnePlayer("player"))
                .executesPlayer((player, args) -> {
                    final Player target = (Player) args.get("player");
                    player.openInventory(target.getInventory());
                })
                .register();
    }
}
