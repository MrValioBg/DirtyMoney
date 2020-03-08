package me.mrvaliobg.dirtymoney.listeners;

import me.ablax.decode.annotation.RegisterListener;
import me.mrvaliobg.dirtymoney.DirtyMoney;
import me.mrvaliobg.dirtymoney.items.ItemMoney;
import me.mrvaliobg.dirtymoney.runnables.MoneyApplier;
import me.mrvaliobg.dirtymoney.utils.ChatFormat;
import me.mrvaliobg.dirtymoney.utils.LocationCheck;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RegisterListener
public class PlayerListener implements Listener {

    private final ItemMoney itemMoney;

    private Set<UUID> laundryMans;

    public PlayerListener(ItemMoney itemMoney) {
        this.itemMoney = itemMoney;
        laundryMans = new HashSet<>();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (laundryMans.isEmpty()) return;
        if (!laundryMans.contains(e.getPlayer().getUniqueId())) return;
        laundryMans.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        if (laundryMans.isEmpty()) return;
        if (!laundryMans.contains(e.getPlayer().getUniqueId())) return;
        laundryMans.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (itemMoney.isMoneyItem(e.getItem())) return;
        Player player = e.getPlayer();
        final Action act = e.getAction();
        if (act.toString().contains("LEFT")) return;
        if (act.toString().contains("AIR")) {
            player.sendMessage(ChatFormat.format("&4HINT: &eИзпери мръсните пари на пералнята до KOTH EVENT, около spawn."));
            return;
        }

        final Block clickedBlock = e.getClickedBlock();
        if (clickedBlock == null) return;
        if (!clickedBlock.getType().toString().contains("DISPENSER")) return;
        if (!LocationCheck.isLaundry(e.getClickedBlock().getLocation())) return;
        if (!LocationCheck.isIn(player.getLocation())) return;
        if (laundryMans.contains(player.getUniqueId())) {
            e.setCancelled(true);
            return;
        }
        e.setCancelled(true);
        laundryMans.add(player.getUniqueId());
        player.sendMessage(ChatFormat.format("&eВМОМЕНТА ПЕРЕШ ПАРИ! &4Внимавай някой да не те избута от пералнята!"));
        MoneyApplier moneyApplier = new MoneyApplier(player, itemMoney, this);
        moneyApplier.run(DirtyMoney.p);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (laundryMans.isEmpty()) return;
        if (!laundryMans.contains(e.getPlayer().getUniqueId())) return;
        if(e.getFrom().getBlock().equals(e.getTo().getBlock())) return;
        if (LocationCheck.isIn(e.getTo())) return;
        laundryMans.remove(e.getPlayer().getUniqueId());
    }

    public Set<UUID> getLaundryMans() {
        return laundryMans;
    }
}
