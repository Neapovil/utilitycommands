package com.github.neapovil.utilitycommands;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.neapovil.utilitycommands.command.EditItemCommand;
import com.github.neapovil.utilitycommands.command.FeedCommand;
import com.github.neapovil.utilitycommands.command.FlyCommand;
import com.github.neapovil.utilitycommands.command.HealCommand;
import com.github.neapovil.utilitycommands.command.PlayerInventoryCommand;
import com.github.neapovil.utilitycommands.command.RepairItemCommand;
import com.github.neapovil.utilitycommands.command.ShowItem;

public final class UtilityCommands extends JavaPlugin
{
    private static UtilityCommands instance;

    @Override
    public void onEnable()
    {
        instance = this;

        new EditItemCommand().register();
        new FeedCommand().register();
        new FlyCommand().register();
        new HealCommand().register();
        new PlayerInventoryCommand().register();
        new RepairItemCommand().register();
        new ShowItem().register();
    }

    @Override
    public void onDisable()
    {
    }

    public static UtilityCommands instance()
    {
        return instance;
    }
}
