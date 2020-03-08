package me.mrvaliobg.dirtymoney.commands;

import me.ablax.decode.annotation.RegisterCommand;
import me.mrvaliobg.dirtymoney.items.ItemMoney;
import me.mrvaliobg.dirtymoney.utils.ChatFormat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RegisterCommand(commandName = "dirtymoney")
public class CommandDirtyMoney implements CommandExecutor {


    private final ItemMoney itemMoney;

    public CommandDirtyMoney(ItemMoney itemMoney) {
        this.itemMoney = itemMoney;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        if (args.length == 1 && args[0].equals("get") && sender.isOp()) {
            sender.sendMessage(ChatFormat.format("&eВземи една мръсна пара на стойност 2500 чисти. - /dirtymoney get"));
            itemMoney.giveMoneyItem(sender);
            return true;
        }
        return false;
    }
}
