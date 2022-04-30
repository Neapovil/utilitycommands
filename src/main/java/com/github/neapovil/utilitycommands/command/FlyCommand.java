package com.github.neapovil.utilitycommands.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.FloatArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;

public final class FlyCommand
{
    public static final void register()
    {
        new CommandAPICommand("fly")
                .withPermission("utilitycommands.command.fly")
                .withArguments(new LiteralArgument("toggle").withPermission("utilitycommands.command.fly.toggle"))
                .executesPlayer((player, args) -> {
                    final boolean toggle = !player.getAllowFlight();

                    player.setAllowFlight(toggle);

                    player.sendMessage("Fly status changed to: " + toggle);
                })
                .register();

        new CommandAPICommand("fly")
                .withPermission("utilitycommands.command.fly")
                .withArguments(new LiteralArgument("speed").withPermission("utilitycommands.command.fly.speed"))
                .withArguments(new FloatArgument("float", -1, 1))
                .executesPlayer((player, args) -> {
                    final float speed = (float) args[0];

                    player.setFlySpeed(speed);

                    player.sendMessage("Fly speed changed to: " + speed);
                })
                .register();
    }
}
