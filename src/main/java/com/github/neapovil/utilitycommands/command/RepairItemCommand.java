package com.github.neapovil.utilitycommands.command;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.LiteralArgument;

public final class RepairItemCommand
{
    public static final void register()
    {
        new CommandAPICommand("repairitem")
                .withPermission("utilitycommands.command.repairitem")
                .executesPlayer((player, args) -> {
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();

                    final Damageable itemmeta = (Damageable) itemstack.getItemMeta();

                    if (!itemmeta.hasDamage())
                    {
                        throw CommandAPI.fail("This item can't be repaired");
                    }

                    itemmeta.setDamage(0);

                    itemstack.setItemMeta(itemmeta);

                    player.sendMessage("Item repaired");
                })
                .register();

        new CommandAPICommand("repairitem")
                .withPermission("utilitycommands.command.repairitem")
                .withArguments(new LiteralArgument("all").withPermission("utilitycommands.command.repairitem.all"))
                .executesPlayer((player, args) -> {
                    int count = 0;

                    for (ItemStack itemstack : player.getInventory().getContents())
                    {
                        if (itemstack == null)
                        {
                            continue;
                        }

                        final Damageable itemmeta = (Damageable) itemstack.getItemMeta();

                        if (itemmeta.hasDamage())
                        {
                            itemmeta.setDamage(0);

                            itemstack.setItemMeta(itemmeta);

                            count++;
                        }
                    }

                    player.sendMessage("Repaired " + count + " items");
                })
                .register();
    }
}
