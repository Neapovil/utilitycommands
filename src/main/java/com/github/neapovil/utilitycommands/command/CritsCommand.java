package com.github.neapovil.utilitycommands.command;

import org.bukkit.World;
import org.bukkit.persistence.PersistentDataType;

import com.github.neapovil.utilitycommands.UtilityCommands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.WorldArgument;
import io.papermc.paper.util.MCUtil;
import net.minecraft.server.level.ServerLevel;

public class CritsCommand implements ICommand
{
    @Override
    public void register()
    {
        new CommandAPICommand("crits")
                .withPermission("utilitycommands.command")
                .withArguments(new WorldArgument("world"))
                .withArguments(new BooleanArgument("bool"))
                .executes((sender, args) -> {
                    final World world = (World) args.get("world");
                    final boolean bool = !(boolean) args.get("bool");
                    final ServerLevel serverlevel = MCUtil.getNMSWorld(world);

                    serverlevel.paperConfig().entities.behavior.disablePlayerCrits = bool;

                    final UtilityCommands plugin = UtilityCommands.instance();

                    if (bool)
                    {
                        world.getPersistentDataContainer().set(plugin.disablePlayerCrits, PersistentDataType.BOOLEAN, bool);
                    }
                    else
                    {
                        world.getPersistentDataContainer().remove(plugin.disablePlayerCrits);
                    }

                    sender.sendMessage("World crits set to: " + !bool);
                })
                .register();
    }
}
