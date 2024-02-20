package com.github.neapovil.utilitycommands.command;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.LiteralArgument;

public final class RepairItemCommand implements ICommand
{
    public void register()
    {
        new CommandAPICommand("repairitem")
                .withPermission("utilitycommands.command")
                .executesPlayer((player, args) -> {
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();

                    if (!(itemstack.getItemMeta() instanceof Damageable meta))
                    {
                        throw CommandAPI.failWithString("This item can't be repaired.");
                    }

                    if (!meta.hasDamage())
                    {
                        throw CommandAPI.failWithString("Nothing to repair.");
                    }

                    meta.setDamage(0);

                    itemstack.setItemMeta(meta);

                    player.sendMessage("Item repaired");
                })
                .register();

        new CommandAPICommand("repairitem")
                .withPermission("utilitycommands.command")
                .withArguments(new LiteralArgument("all"))
                .executesPlayer((player, args) -> {
                    int count = 0;

                    for (ItemStack itemstack : player.getInventory().getContents())
                    {
                        if (itemstack == null)
                        {
                            continue;
                        }

                        if (!(itemstack.getItemMeta() instanceof Damageable meta))
                        {
                            continue;
                        }

                        if (meta.hasDamage())
                        {
                            meta.setDamage(0);

                            itemstack.setItemMeta(meta);

                            count++;
                        }
                    }

                    player.sendMessage("Repaired " + count + " items");
                })
                .register();
    }
}
