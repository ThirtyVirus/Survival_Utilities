import events.InventoryEvents;
import events.UberEvent;
import items.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import thirtyvirus.uber.UberItems;
import thirtyvirus.uber.UberMaterial;
import thirtyvirus.uber.helpers.*;

import java.util.Arrays;
import java.util.Collections;

public class SurvivalUtilities extends JavaPlugin {

    public void onEnable() {

        // enforce UberItems dependancy
        if (Bukkit.getPluginManager().getPlugin("UberItems") == null) {
            this.getLogger().severe("UberItems Addons requires UberItems! disabled because UberItems dependency not found");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // register events and UberItems
        registerEvents();
        registerUberMaterials();
        registerUberItems();

        // post confirmation in chat
        getLogger().info(getDescription().getName() + " V: " + getDescription().getVersion() + " has been enabled");
    }
    public void onDisable() {
        // posts exit message in chat
        getLogger().info(getDescription().getName() + " V: " + getDescription().getVersion() + " has been disabled");
    }
    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new InventoryEvents(), this);
        getServer().getPluginManager().registerEvents(new UberEvent(), this);
    }

    private void registerUberItems() {

        // TODO add Atlas

        UberItems.putItem("pillow", new pillow(Utilities.getSkull("http://textures.minecraft.net/texture/3088895e90ea5899499e32a3176ecdacd88656cba734d1345175fd11f2844893"), "Pillow",
                UberRarity.COMMON, false, false, true,
                Collections.singletonList(new UberAbility("Well Rested", AbilityType.NONE, "When sleeping in a bed while holding this pillow, you regain health. Cure any active poison effects.", 300)),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.STRING, 8),
                        new ItemStack(Material.FEATHER, 16),
                        new ItemStack(Material.STRING, 8),
                        new ItemStack(Material.FEATHER, 16),
                        new ItemStack(Material.FEATHER, 16),
                        new ItemStack(Material.FEATHER, 16),
                        new ItemStack(Material.STRING, 8),
                        new ItemStack(Material.FEATHER, 16),
                        new ItemStack(Material.STRING, 8)), false, 1)));

        UberItems.putItem("omlette", new omlette(Utilities.getSkull("http://textures.minecraft.net/texture/4e947fe09ebb7e7b3769bb8a5da5cb734646b2c7973ab6a35b6627a7dc1245d1"), "Omlette",
                UberRarity.UNCOMMON, true, true, false, Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.EGG, 4),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.EGG, 4),
                        UberItems.getMaterial("flammable_substance").makeItem(1),
                        new ItemStack(Material.EGG, 4),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.EGG, 4),
                        new ItemStack(Material.AIR)), false, 4)));

        UberItems.putItem("experience_bottle", new experience_bottle(Material.EXPERIENCE_BOTTLE, "Experience Bottle", UberRarity.COMMON,
                true, true, false, Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.LAPIS_LAZULI),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.LAPIS_LAZULI),
                        new ItemStack(Material.GLASS_BOTTLE),
                        new ItemStack(Material.LAPIS_LAZULI),
                        new ItemStack(Material.LAPIS_LAZULI),
                        new ItemStack(Material.LAPIS_LAZULI),
                        new ItemStack(Material.LAPIS_LAZULI)), false, 1)));

        // TODO add way to convert enchantments into experience bottles?

        // TODO add livna (reverse anvil)

        UberItems.putItem("small_backpack", new small_backpack(Utilities.getSkull("http://textures.minecraft.net/texture/2308bf5cc3e9decaf0770c3fdad1e042121cf39cc2505bbb866e18c6d23ccd0c"), "Small Backpack",
                UberRarity.RARE, false, false, false,
                Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.WHITE_WOOL, 8),
                        new ItemStack(Material.WHITE_WOOL, 8),
                        new ItemStack(Material.WHITE_WOOL, 8),
                        new ItemStack(Material.WHITE_WOOL, 8),
                        UberItems.getMaterial("enchanted_leather").makeItem(1),
                        new ItemStack(Material.WHITE_WOOL, 8),
                        new ItemStack(Material.WHITE_WOOL, 8),
                        new ItemStack(Material.WHITE_WOOL, 8),
                        new ItemStack(Material.WHITE_WOOL, 8)), false, 1)));

        UberItems.putItem("big_backpack", new big_backpack(Utilities.getSkull("http://textures.minecraft.net/texture/a2bb38516b29504186e11559cd5250ae218db4ddd27ae438726c847ce6b3c98"), "Big Backpack",
                UberRarity.EPIC, false, false, false,
                Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.WHITE_WOOL, 64),
                        UberItems.getMaterial("enchanted_leather").makeItem(1),
                        new ItemStack(Material.WHITE_WOOL, 64),
                        UberItems.getMaterial("enchanted_leather").makeItem(1),
                        new ItemStack(Material.CHEST),
                        UberItems.getMaterial("enchanted_leather").makeItem(1),
                        new ItemStack(Material.WHITE_WOOL, 64),
                        UberItems.getMaterial("enchanted_leather").makeItem(1),
                        new ItemStack(Material.WHITE_WOOL, 64)), false, 1)));

        // TODO add foods on a stick

        UberItems.putItem("calamari", new calamari(Material.COOKED_COD, "Calamari",
                UberRarity.UNCOMMON, true, true, false, Collections.emptyList(), null));

        UberItems.putItem("homemade_portal_frame", new homemade_portal_frame(Material.END_PORTAL_FRAME, "Homemade Portal Frame",
                UberRarity.EPIC, true, true, false,
                Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.END_STONE, 12),
                        new ItemStack(Material.END_STONE, 12),
                        new ItemStack(Material.END_STONE, 12),
                        new ItemStack(Material.ENDER_PEARL, 16),
                        new ItemStack(Material.NETHER_STAR),
                        new ItemStack(Material.ENDER_PEARL, 16),
                        new ItemStack(Material.END_STONE, 12),
                        new ItemStack(Material.END_STONE, 12),
                        new ItemStack(Material.END_STONE, 12)), false, 12)));

        UberItems.putItem("crystal_ball", new crystal_ball(Utilities.getSkull("http://textures.minecraft.net/texture/5a5f29a76d1f91c165f63baac048670e7b1d37ce785a4d9c21d8c3a177b5"), "Crystal Ball",
                UberRarity.EPIC, false, false, false,
                Arrays.asList(new UberAbility("Mana Battery", AbilityType.LEFT_CLICK, "Deposit an experience level into the crystal ball, crouch to deposit all."),
                        new UberAbility("Mana Discharge", AbilityType.RIGHT_CLICK, "Withdraw an experience level from the crystal ball, crouch to withdraw all.")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.GLASS),
                        new ItemStack(Material.SEA_LANTERN),
                        new ItemStack(Material.GLASS),
                        new ItemStack(Material.SEA_LANTERN),
                        new ItemStack(Material.BEACON),
                        new ItemStack(Material.SEA_LANTERN),
                        new ItemStack(Material.GLASS),
                        new ItemStack(Material.SEA_LANTERN),
                        new ItemStack(Material.GLASS)), false, 1)));

        UberItems.putItem("soul_anchor", new soul_anchor(Utilities.getSkull("https://textures.minecraft.net/texture/38be8abd66d09a58ce12d377544d726d25cad7e979e8c2481866be94d3b32f"), "Soul Anchor",
                UberRarity.LEGENDARY, false, true, false,
                Arrays.asList(new UberAbility("Soulbound", AbilityType.NONE, "Stores location on death, returns to the player inventory after respawn"),
                        new UberAbility("Do-Over", AbilityType.RIGHT_CLICK, "Return to your previous death point")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENDER_PEARL, 16),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENDER_PEARL, 16),
                        new ItemStack(Material.CHORUS_FRUIT),
                        new ItemStack(Material.ENDER_PEARL, 16),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENDER_PEARL, 16),
                        new ItemStack(Material.AIR)), false, 1)));

        UberItems.putItem("multi_bench", new multi_bench(Material.OBSIDIAN, "Multi-Bench",
                UberRarity.UNFINISHED, false, false, false,
                Collections.singletonList(new UberAbility("asd", AbilityType.LEFT_CLICK, "asdasd")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.STICK),
                        new ItemStack(Material.OBSIDIAN, 16),
                        new ItemStack(Material.STICK),
                        new ItemStack(Material.OBSIDIAN, 16),
                        new ItemStack(Material.REDSTONE_BLOCK, 32),
                        new ItemStack(Material.OBSIDIAN, 16),
                        new ItemStack(Material.STICK),
                        new ItemStack(Material.OBSIDIAN, 16),
                        new ItemStack(Material.STICK)), false, 1)));

        UberItems.putItem("throwing_torch", new throwing_torch(Material.TORCH, "Throwing Torch", UberRarity.COMMON,
                true, true, false, Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.TORCH),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.STICK),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.STICK),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR)), false, 1)));

        // TODO add jerky?

        // TODO add go-cart

        // TODO add hang glider

        // TODO add metal detector

    }
    private void registerUberMaterials() {

        UberItems.putMaterial("enchanted_crafting_table", new UberMaterial(Material.CRAFTING_TABLE, "Enchanted Crafting Table", UberRarity.UNCOMMON, true, false, false, "",
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.CRAFTING_TABLE, 32),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.CRAFTING_TABLE, 32),
                        new ItemStack(Material.CRAFTING_TABLE, 32),
                        new ItemStack(Material.CRAFTING_TABLE, 32),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.CRAFTING_TABLE, 32),
                        new ItemStack(Material.AIR)), false, 1)));

        UberItems.putMaterial("enchanted_furnace", new UberMaterial(Material.FURNACE, "Enchanted Furnace", UberRarity.UNCOMMON, true, false, false, "",
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.FURNACE, 32),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.FURNACE, 32),
                        new ItemStack(Material.FURNACE, 32),
                        new ItemStack(Material.FURNACE, 32),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.FURNACE, 32),
                        new ItemStack(Material.AIR)), false, 1)));

        UberItems.putMaterial("enchanted_brewing_stand", new UberMaterial(Material.BREWING_STAND, "Enchanted Brewing Stand", UberRarity.UNCOMMON, true, false, false, "",
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.BREWING_STAND, 32),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.BREWING_STAND, 32),
                        new ItemStack(Material.BREWING_STAND, 32),
                        new ItemStack(Material.BREWING_STAND, 32),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.BREWING_STAND, 32),
                        new ItemStack(Material.AIR)), false, 1)));

        UberItems.putMaterial("enchanted_anvil", new UberMaterial(Material.ANVIL, "Enchanted Anvil", UberRarity.UNCOMMON, true, false, false, "",
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ANVIL, 4),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ANVIL, 4),
                        new ItemStack(Material.ANVIL, 4),
                        new ItemStack(Material.ANVIL, 4),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ANVIL, 4),
                        new ItemStack(Material.AIR)), false, 1)));

        UberItems.putMaterial("enchanted_enchanting_table", new UberMaterial(Material.ENCHANTING_TABLE, "Enchanted Enchanting Table", UberRarity.UNCOMMON, true, false, false, "",
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENCHANTING_TABLE, 4),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENCHANTING_TABLE, 4),
                        new ItemStack(Material.ENCHANTING_TABLE, 4),
                        new ItemStack(Material.ENCHANTING_TABLE, 4),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.ENCHANTING_TABLE, 4),
                        new ItemStack(Material.AIR)), false, 1)));

        UberItems.putMaterial("enchanted_leather", new UberMaterial(Material.LEATHER, "Enchanted Leather", UberRarity.UNCOMMON, true, true, false, "",
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.LEATHER, 64),
                        new ItemStack(Material.LEATHER, 64),
                        new ItemStack(Material.LEATHER, 64),
                        new ItemStack(Material.LEATHER, 64),
                        new ItemStack(Material.LEATHER, 64),
                        new ItemStack(Material.LEATHER, 64),
                        new ItemStack(Material.LEATHER, 64),
                        new ItemStack(Material.LEATHER, 64),
                        new ItemStack(Material.LEATHER, 64)), false, 1)));

    }
}