package com.github.neapovil.utilitycommands;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.neapovil.utilitycommands.command.EditItemCommand;
import com.github.neapovil.utilitycommands.command.FeedCommand;
import com.github.neapovil.utilitycommands.command.FlyCommand;
import com.github.neapovil.utilitycommands.command.HealCommand;
import com.github.neapovil.utilitycommands.command.PlayerInventoryCommand;
import com.github.neapovil.utilitycommands.command.RepairItemCommand;

public final class UtilityCommands extends JavaPlugin
{
    private static UtilityCommands instance;

    @Override
    public void onEnable()
    {
        instance = this;

        HealCommand.register();
        FeedCommand.register();
        RepairItemCommand.register();
        EditItemCommand.register();
        PlayerInventoryCommand.register();
        FlyCommand.register();
    }

    @Override
    public void onDisable()
    {
    }

    public static UtilityCommands getInstance()
    {
        return instance;
    }
}
