package me.mrvaliobg.dirtymoney.items;

import me.ablax.decode.annotation.Component;
import me.mrvaliobg.dirtymoney.utils.ChatFormat;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemMoney {

    private final ItemStack dirtyMoney;

    public ItemMoney(ChatFormat format) {
        ItemStack money = new ItemStack(Material.PAPER, 1);
        ItemMeta moneyMeta = money.getItemMeta();
        moneyMeta.setDisplayName(format.format("&cМръсни &2Пари"));
        List<String> lore = new ArrayList<>();
        lore.add(format.format("&eСтойност в чисти пари:"));
        lore.add(format.format("  &32500&2$ "));
        moneyMeta.setLore(lore);
        money.setItemMeta(moneyMeta);
        dirtyMoney = money;
    }


    public ItemStack getMoneyItem() {
        return dirtyMoney;
    }

    public void giveMoneyItem(CommandSender sender) {
        Player player = (Player) sender;
        player.getInventory().addItem(dirtyMoney);
        player.updateInventory();
    }

    public boolean isMoneyItem(ItemStack likelyMoney) {
        if(likelyMoney == null) return true;
        ItemStack copy = likelyMoney.clone();
        copy.setAmount(1);
        return !copy.equals(dirtyMoney);
    }


}
