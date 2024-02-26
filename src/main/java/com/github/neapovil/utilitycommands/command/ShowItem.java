package com.github.neapovil.utilitycommands.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;

public class ShowItem implements ICommand
{
    @Override
    public void register()
    {
        new CommandAPICommand("showitem")
                .withPermission("utilitycommands.command")
                .executesPlayer((player, args) -> {
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.failWithString("You must have an item in your main hand");
                    }

                    final Component component = player.displayName()
                            .append(Component.text(" is showing an item in chat: "))
                            .append(itemstack.displayName().hoverEvent(itemstack.asHoverEvent()));

                    Bukkit.getServer().sendMessage(component);
                })
                .register();
    }
}
