package com.github.neapovil.utilitycommands;

import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.neapovil.utilitycommands.command.CritsCommand;
import com.github.neapovil.utilitycommands.command.EditItemCommand;
import com.github.neapovil.utilitycommands.command.FeedCommand;
import com.github.neapovil.utilitycommands.command.FlyCommand;
import com.github.neapovil.utilitycommands.command.HealCommand;
import com.github.neapovil.utilitycommands.command.PlayerInventoryCommand;
import com.github.neapovil.utilitycommands.command.RepairItemCommand;
import com.github.neapovil.utilitycommands.command.ShowItem;

import io.papermc.paper.util.MCUtil;
import net.minecraft.server.level.ServerLevel;

public final class UtilityCommands extends JavaPlugin
{
    private static UtilityCommands instance;
    public final NamespacedKey disablePlayerCrits = new NamespacedKey(this, "disable-player-crits");

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
        new CritsCommand().register();

        for (World i : this.getServer().getWorlds())
        {
            if (i.getPersistentDataContainer().has(this.disablePlayerCrits))
            {
                final ServerLevel serverlevel = MCUtil.getNMSWorld(i);
                serverlevel.paperConfig().entities.behavior.disablePlayerCrits = true;
            }
        }
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
