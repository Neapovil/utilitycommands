package com.github.neapovil.utilitycommands;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.neapovil.utilitycommands.command.FeedCommand;
import com.github.neapovil.utilitycommands.command.HealCommand;

public final class UtilityCommands extends JavaPlugin
{
    private static UtilityCommands instance;

    @Override
    public void onEnable()
    {
        instance = this;

        HealCommand.register();
        FeedCommand.register();
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
