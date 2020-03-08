package me.mrvaliobg.dirtymoney.runnables;

import me.mrvaliobg.dirtymoney.items.ItemMoney;
import me.mrvaliobg.dirtymoney.listeners.PlayerListener;
import me.mrvaliobg.dirtymoney.utils.ChatFormat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class MoneyApplier {

    private final ItemMoney itemMoney;
    private final PlayerListener playerListener;
    private Player player;
    private int task;

    public MoneyApplier(Player p, ItemMoney itemMoney, PlayerListener playerListener) {
        this.player = p;
        this.itemMoney = itemMoney;
        this.playerListener = playerListener;
    }

    public void run(Plugin plugin) {
        this.task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (!this.playerListener.getLaundryMans().contains(getPlayer().getUniqueId())) {
                Bukkit.getScheduler().cancelTask(this.task);
                return;
            }
            if (this.itemMoney.isMoneyItem(getPlayer().getItemInHand())) {
                getPlayer().sendMessage(ChatFormat.format("&eТрябва да държиш мръсните пари докато ги переш!"));
                return;
            }
            int amount = getPlayer().getItemInHand().getAmount();
            ItemStack item = this.itemMoney.getMoneyItem().clone();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + getPlayer().getName() + " 2500");
            if(amount == 1) {
                getPlayer().setItemInHand(new ItemStack(Material.AIR));
                getPlayer().updateInventory();
                this.playerListener.getLaundryMans().remove(getPlayer().getUniqueId());
                Bukkit.getScheduler().cancelTask(this.task);
            } else {
                item.setAmount(amount - 1);
                getPlayer().setItemInHand(item);
                getPlayer().updateInventory();
            }
        }, 100, 100);
    }

    private Player getPlayer() {
        return this.player;
    }
}
