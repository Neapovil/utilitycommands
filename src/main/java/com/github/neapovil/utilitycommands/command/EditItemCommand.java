package com.github.neapovil.utilitycommands.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;

public final class EditItemCommand
{
    public static final void register()
    {
        new CommandAPICommand("edititem")
                .withPermission("utilitycommands.command.edititem")
                .withArguments(new LiteralArgument("displayName").withPermission("utilitycommands.command.edititem.displayname"))
                .withArguments(new LiteralArgument("set"))
                .withArguments(new GreedyStringArgument("content"))
                .executesPlayer((player, args) -> {
                    final String text = (String) args[0];

                    final ItemStack itemstack = player.getInventory().getItemInMainHand();

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.fail("Item in main hand not found");
                    }

                    final ItemMeta itemmeta = itemstack.getItemMeta();

                    final Component component = Component.text("")
                            .decoration(TextDecoration.ITALIC, false)
                            .append(MiniMessage.miniMessage().deserialize(text));

                    itemmeta.displayName(component);

                    itemstack.setItemMeta(itemmeta);

                    player.sendMessage("Item display name changed");
                })
                .register();

        new CommandAPICommand("edititem")
                .withPermission("utilitycommands.command.edititem")
                .withArguments(new LiteralArgument("displayName").withPermission("utilitycommands.command.edititem.displayname"))
                .withArguments(new LiteralArgument("reset"))
                .executesPlayer((player, args) -> {
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.fail("Item in main hand not found");
                    }

                    final ItemMeta itemmeta = itemstack.getItemMeta();

                    itemmeta.displayName(null);

                    itemstack.setItemMeta(itemmeta);

                    player.sendMessage("Item display name reset to default value");
                })
                .register();

        new CommandAPICommand("edititem")
                .withPermission("utilitycommands.command.edititem")
                .withArguments(new LiteralArgument("lore").withPermission("utilitycommands.command.edititem.lore"))
                .withArguments(new LiteralArgument("addLine"))
                .withArguments(new GreedyStringArgument("content"))
                .executesPlayer((player, args) -> {
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();
                    final String content = (String) args[0];

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.fail("Item in main hand not found");
                    }

                    final ItemMeta itemmeta = itemstack.getItemMeta();

                    final List<Component> lore = new ArrayList<>();

                    if (itemmeta.hasLore())
                    {
                        lore.addAll(itemmeta.lore());
                    }

                    final Component component = Component.text("", NamedTextColor.WHITE)
                            .decoration(TextDecoration.ITALIC, false)
                            .append(MiniMessage.miniMessage().deserialize(content));

                    lore.add(component);

                    itemmeta.lore(lore);

                    itemstack.setItemMeta(itemmeta);

                    player.sendMessage("Item lore changed");
                })
                .register();

        new CommandAPICommand("edititem")
                .withPermission("utilitycommands.command.edititem")
                .withArguments(new LiteralArgument("lore").withPermission("utilitycommands.command.edititem.lore"))
                .withArguments(new LiteralArgument("removeLine"))
                .withArguments(new IntegerArgument("index", 0))
                .executesPlayer((player, args) -> {
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();
                    final int index = (int) args[0];

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.fail("Item in main hand not found");
                    }

                    final ItemMeta itemmeta = itemstack.getItemMeta();

                    if (!itemmeta.hasLore())
                    {
                        throw CommandAPI.fail("This item doesn't have a lore");
                    }

                    if (index >= itemmeta.lore().size())
                    {
                        throw CommandAPI.fail("This lore line doesn't exist");
                    }

                    final List<Component> lore = itemmeta.lore();

                    lore.remove(index);

                    itemmeta.lore(lore);

                    itemstack.setItemMeta(itemmeta);

                    player.sendMessage("Item lore line " + index + " removed");
                })
                .register();

        new CommandAPICommand("edititem")
                .withPermission("utilitycommands.command.edititem")
                .withArguments(new LiteralArgument("lore").withPermission("utilitycommands.command.edititem.lore"))
                .withArguments(new LiteralArgument("reset"))
                .executesPlayer((player, args) -> {
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.fail("Item in main hand not found");
                    }

                    final ItemMeta itemmeta = itemstack.getItemMeta();

                    if (!itemmeta.hasLore())
                    {
                        throw CommandAPI.fail("This item doesn't have a lore");
                    }

                    itemmeta.lore(null);

                    itemstack.setItemMeta(itemmeta);

                    player.sendMessage("Item lore reset to default value");
                })
                .register();

        new CommandAPICommand("edititem")
                .withPermission("utilitycommands.command.edititem")
                .withArguments(new LiteralArgument("lore").withPermission("utilitycommands.command.edititem.lore"))
                .withArguments(new LiteralArgument("shift"))
                .withArguments(new IntegerArgument("indexFrom", 0))
                .withArguments(new IntegerArgument("indexTo", 0))
                .executesPlayer((player, args) -> {
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();
                    final int index = (int) args[0];
                    final int index1 = (int) args[1];

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.fail("Item in main hand not found");
                    }

                    final ItemMeta itemmeta = itemstack.getItemMeta();

                    if (!itemmeta.hasLore())
                    {
                        throw CommandAPI.fail("This item doesn't have a lore");
                    }

                    final List<Component> lore = itemmeta.lore();

                    if (index >= lore.size())
                    {
                        throw CommandAPI.fail("Item lore line " + index + " not found");
                    }

                    if (index1 >= lore.size())
                    {
                        throw CommandAPI.fail("Item lore line " + index1 + " not found");
                    }

                    if (index == index1)
                    {
                        throw CommandAPI.fail("You can't shift the line to the same index");
                    }

                    final Component component = lore.remove(index);

                    lore.add(index1, component);

                    itemmeta.lore(lore);

                    itemstack.setItemMeta(itemmeta);

                    player.sendMessage("Item lore updated");
                })
                .register();

        new CommandAPICommand("edititem")
                .withPermission("utilitycommands.command.edititem")
                .withArguments(new LiteralArgument("lore").withPermission("utilitycommands.command.edititem.lore"))
                .withArguments(new LiteralArgument("editLine"))
                .withArguments(new IntegerArgument("index", 0))
                .withArguments(new GreedyStringArgument("content"))
                .executesPlayer((player, args) -> {
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();
                    final int index = (int) args[0];
                    final String content = (String) args[1];

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.fail("Item in main hand not found");
                    }

                    final ItemMeta itemmeta = itemstack.getItemMeta();

                    if (!itemmeta.hasLore())
                    {
                        throw CommandAPI.fail("This item doesn't have a lore");
                    }

                    final List<Component> lore = itemmeta.lore();

                    if (index >= lore.size())
                    {
                        throw CommandAPI.fail("Item lore line " + index + " not found");
                    }

                    final Component component = Component.text("", NamedTextColor.WHITE)
                            .decoration(TextDecoration.ITALIC, false)
                            .append(MiniMessage.miniMessage().deserialize(content));

                    lore.set(index, component);

                    itemmeta.lore(lore);

                    itemstack.setItemMeta(itemmeta);

                    player.sendMessage("Item lore line " + index + " updated");
                })
                .register();
    }
}
