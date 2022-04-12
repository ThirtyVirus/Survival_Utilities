package helpers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import thirtyvirus.uber.UberItems;
import thirtyvirus.uber.helpers.Utilities;

public class SurvivalMenuUtils {

    public static final ItemStack emptySlot = Utilities.nameItem(Material.BLACK_STAINED_GLASS_PANE, " ");

    public static void openBackpack(Player player, ItemStack itemStack) {
        // verify that the player isn't null
        if (player == null) return;

        // set the inventory size
        int inventorySize = 0;
        if (UberItems.getItem("small_backpack").compare(itemStack))
            inventorySize = 27;
        else if (UberItems.getItem("big_backpack").compare(itemStack))
            inventorySize = 54;
        else return;

        // create the inventory
        Inventory inv = Bukkit.createInventory(null, inventorySize, "UberItems - Backpack");
        for (int counter = 0; counter < 9; counter++) inv.setItem(counter, emptySlot);
        ItemStack backpack = itemStack.clone();
        inv.setItem(4, backpack);

        // populate the inventory
        ItemStack[] items = Utilities.getCompactInventory(backpack);
        if (items.length == 0) items = new ItemStack[inventorySize];

        // store a value to verify that an item comes from a backpack, will be used later for anti-dupe
        for (ItemStack item : items) Utilities.storeIntInItem(item, 1, "frombackpack");

        for (int counter = 9; counter < inventorySize; counter++) {
            inv.setItem(counter, items[counter - 9]);
        }

        // open the inventory
        player.openInventory(inv);
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
    }
}
