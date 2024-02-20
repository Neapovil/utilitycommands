package com.github.neapovil.utilitycommands.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.FloatArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;

public final class FlyCommand implements ICommand
{
    public void register()
    {
        new CommandAPICommand("fly")
                .withPermission("utilitycommands.command")
                .withArguments(new LiteralArgument("toggle"))
                .executesPlayer((player, args) -> {
                    final boolean toggle = !player.getAllowFlight();
                    player.setAllowFlight(toggle);
                    player.sendMessage("Fly status changed to: " + toggle);
                })
                .register();

        new CommandAPICommand("fly")
                .withPermission("utilitycommands.command")
                .withArguments(new LiteralArgument("speed"))
                .withArguments(new FloatArgument("speed", -1, 1))
                .executesPlayer((player, args) -> {
                    final float speed = (float) args.get("speed");
                    player.setFlySpeed(speed);
                    player.sendMessage("Fly speed changed to: " + speed);
                })
                .register();
    }
}
