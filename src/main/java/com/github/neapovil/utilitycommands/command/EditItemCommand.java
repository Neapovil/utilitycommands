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
import dev.jorel.commandapi.arguments.MultiLiteralArgument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class EditItemCommand implements ICommand
{
    public void register()
    {
        new CommandAPICommand("edititem")
                .withPermission("utilitycommands.command")
                .withArguments(new LiteralArgument("displayName"))
                .withArguments(new MultiLiteralArgument("option", "set", "reset"))
                .withArguments(new GreedyStringArgument("text"))
                .executesPlayer((player, args) -> {
                    final String option = (String) args.get("option");
                    final String text = (String) args.get("text");

                    final ItemStack itemstack = player.getInventory().getItemInMainHand();

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.failWithString("Item in main hand not found");
                    }

                    if (option.equals("set"))
                    {
                        final Component component = Component.text("")
                                .decoration(TextDecoration.ITALIC, false)
                                .append(MiniMessage.miniMessage().deserialize(text));

                        itemstack.editMeta(meta -> meta.displayName(component));

                        player.sendRichMessage("Item display name changed to: " + text);
                    }

                    if (option.equals("reset"))
                    {
                        itemstack.editMeta(meta -> meta.displayName(null));
                        player.sendMessage("Item display name reset to default value");
                    }
                })
                .register();

        new CommandAPICommand("edititem")
                .withPermission("utilitycommands.command")
                .withArguments(new LiteralArgument("lore"))
                .withArguments(new LiteralArgument("addLine"))
                .withArguments(new GreedyStringArgument("content"))
                .executesPlayer((player, args) -> {
                    final String content = (String) args.get("content");
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.failWithString("Item in main hand not found");
                    }

                    final List<Component> lore = new ArrayList<>();

                    itemstack.editMeta(meta -> {
                        if (meta.hasLore())
                        {
                            lore.addAll(meta.lore());
                        }

                        final Component component = Component.text("", NamedTextColor.WHITE)
                                .decoration(TextDecoration.ITALIC, false)
                                .append(MiniMessage.miniMessage().deserialize(content));

                        lore.add(component);

                        meta.lore(lore);
                    });

                    player.sendMessage(Component.text("Item lore changed to: ").append(Component.text("hover me").hoverEvent(itemstack.asHoverEvent())));
                })
                .register();

        new CommandAPICommand("edititem")
                .withPermission("utilitycommands.command")
                .withArguments(new LiteralArgument("lore"))
                .withArguments(new LiteralArgument("removeLine"))
                .withArguments(new IntegerArgument("index", 0))
                .executesPlayer((player, args) -> {
                    final int index = (int) args.get("index");
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.failWithString("Item in main hand not found");
                    }

                    final ItemMeta itemmeta = itemstack.getItemMeta();

                    if (!itemmeta.hasLore())
                    {
                        throw CommandAPI.failWithString("This item doesn't have a lore");
                    }

                    if (index >= itemmeta.lore().size())
                    {
                        throw CommandAPI.failWithString("This lore line doesn't exist");
                    }

                    final List<Component> lore = itemmeta.lore();

                    lore.remove(index);

                    itemmeta.lore(lore);

                    itemstack.setItemMeta(itemmeta);

                    player.sendMessage("Item lore line " + index + " removed");
                })
                .register();

        new CommandAPICommand("edititem")
                .withPermission("utilitycommands.command")
                .withArguments(new LiteralArgument("lore"))
                .withArguments(new LiteralArgument("reset"))
                .executesPlayer((player, args) -> {
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.failWithString("Item in main hand not found");
                    }

                    final ItemMeta itemmeta = itemstack.getItemMeta();

                    if (!itemmeta.hasLore())
                    {
                        throw CommandAPI.failWithString("This item doesn't have a lore");
                    }

                    itemmeta.lore(null);

                    itemstack.setItemMeta(itemmeta);

                    player.sendMessage("Item lore reset to default value");
                })
                .register();

        new CommandAPICommand("edititem")
                .withPermission("utilitycommands.command")
                .withArguments(new LiteralArgument("lore"))
                .withArguments(new LiteralArgument("shift"))
                .withArguments(new IntegerArgument("indexFrom", 0))
                .withArguments(new IntegerArgument("indexTo", 0))
                .executesPlayer((player, args) -> {
                    final int indexfrom = (int) args.get("indexFrom");
                    final int indexto = (int) args.get("indexTo");
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.failWithString("Item in main hand not found");
                    }

                    final ItemMeta itemmeta = itemstack.getItemMeta();

                    if (!itemmeta.hasLore())
                    {
                        throw CommandAPI.failWithString("This item doesn't have a lore");
                    }

                    final List<Component> lore = itemmeta.lore();

                    if (indexfrom >= lore.size())
                    {
                        throw CommandAPI.failWithString("Item lore line " + indexfrom + " not found");
                    }

                    if (indexto >= lore.size())
                    {
                        throw CommandAPI.failWithString("Item lore line " + indexto + " not found");
                    }

                    if (indexfrom == indexto)
                    {
                        throw CommandAPI.failWithString("You can't shift the line to the same index");
                    }

                    final Component component = lore.remove(indexfrom);

                    lore.add(indexto, component);

                    itemmeta.lore(lore);

                    itemstack.setItemMeta(itemmeta);

                    player.sendMessage("Item lore updated");
                })
                .register();

        new CommandAPICommand("edititem")
                .withPermission("utilitycommands.command")
                .withArguments(new LiteralArgument("lore"))
                .withArguments(new LiteralArgument("editLine"))
                .withArguments(new IntegerArgument("index", 0))
                .withArguments(new GreedyStringArgument("content"))
                .executesPlayer((player, args) -> {
                    final int index = (int) args.get("index");
                    final String content = (String) args.get("content");
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.failWithString("Item in main hand not found");
                    }

                    final ItemMeta itemmeta = itemstack.getItemMeta();

                    if (!itemmeta.hasLore())
                    {
                        throw CommandAPI.failWithString("This item doesn't have a lore");
                    }

                    final List<Component> lore = itemmeta.lore();

                    if (index >= lore.size())
                    {
                        throw CommandAPI.failWithString("Item lore line " + index + " not found");
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
