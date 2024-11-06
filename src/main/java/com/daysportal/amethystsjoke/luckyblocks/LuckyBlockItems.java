package com.daysportal.amethystsjoke.luckyblocks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SkullItem;
import net.minecraft.nbt.NbtByteArray;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LuckyBlockItems {

    public static List<ItemStack> ALL = new ArrayList<>();

    public static ItemStack YELLOW = head("Yellow",  new int[] { -940982597, -1981660917, -1797831442, 692841550 }, "NGI5MmNiNDMzMzNhYTYyMWM3MGVlZjRlYmYyOTliYTQxMmI0NDZmZTEyZTM0MWNjYzU4MmYzMTkyMTg5In19fQ==");

    public static ItemStack RED = head("Red",  new int[] { -2078853511, -232436813, -1986631548, -1501835100 }, "YzEyNjExNjU2M2U5MDRjZGU3ZjUyYWUwZmI1ZTA3NjZlNjBhYmY0NzU3OTU3ZGU5ZGQzYjA2ZWRmMWY4YmQ4ZSJ9fX0=");

    public static ItemStack GREEN = head("Green",  new int[] { 1316956978, 631392215, -1925594645, 1146629506 }, "MTk5N2QzNTVlMjNmNGRhN2JlNjkxMzllZWMxNTg2MDljNzBiZGFiNGJiOTNlM2ZhODI2NzlkNTNiMmRiZGZkIn19fQ==");

    public static ItemStack LIME = head("Lime",  new int[] { 1225423023, 826491828, -2003912230, 179620251 }, "MTFhNWJkNmZjZDE3OTY4NTZiZDhhNGM2OTk4ODc5NGY4ZWM0NjY0ZTU1MDk4ZDhjZGU5YzdhNzg0MjNjNzFmIn19fQ==");

    public static ItemStack BLUE = head("Blue",  new int[] { 1379736441, 693388003, -1687888951, 912855918 }, "MzY2ZDZjMTllNGY1MDUxODgyODUxYTRhZWFkNzlmMGYxZjM4YWE2ODk3MTliNmIzMzAzMTdlYTJiOGIwZTUwMCJ9fX0=");

    public static ItemStack BLACK = head("Black",  new int[] { 2038550775, 745097733, -1268820310, -736954411 }, "MzNmMmNkOWY4MWEyNzcyYmRjNDg2NDQ3MmU4MzMzNjIzMTA0ODVjOGE2YmMwYjc2YjgxNzAzMzkwYTliMDMyZSJ9fX0=");

    public static ItemStack CYAN = head("Cyan",  new int[] { -1304207463, -1974582606, -1961674938, 375511284 }, "ZmMxYTliNjY2MTJlYTBmOWVhMzVjMzNjMjZhMTY4YjY1M2U1ODIwMDVlYTA5Y2NjMTc5N2Y1MzI5ZTJiZDJjIn19fQ==");

    public static ItemStack WHITE = head("White",  new int[] { 290723766, -666155030, -2004620844, 1525000514 }, "YzhlMTZlN2ZmMTNkM2UzMGEzYTJiODM1YTg2ZmQwNWVjYmMwODkwOWY2MDU2ZGUxNjQ2ZmUyNWIzZGVmOTU4NCJ9fX0=");

    public static ItemStack PURPLE = head("Purple",  new int[] { 1131740179, -1091416382, -1363783807, -1190834623 }, "NmNmYjRlYmMzYmE0ZmVjMDRkODk3OGExNjYwYmI3Yzk4ZTk2MzQyZmYyYTIzOGFkYTlkZjJmNzRiZjAzMmI0MCJ9fX0=");

    public static ItemStack BROWN = head("Brown",  new int[] { 1916877376, -1241102717, -1664806708, 613427668 }, "YzEzMzQxNDc1OWY5ZTUzMTUxNTYwMTcwMjNhOGIxYjMxY2ZiZjE3N2RjYWZiMDYwM2I4ZTlkOTQwMzZhMjNmMSJ9fX0=");

    private static final String START = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";

    private static ItemStack head(String name, int[] id, String texture) {
        ItemStack head = Items.PLAYER_HEAD.getDefaultStack();
        NbtCompound owner = head.getOrCreateSubNbt(SkullItem.SKULL_OWNER_KEY);
        owner.putIntArray("Id", id);
        NbtCompound props = new NbtCompound();
        NbtCompound b = new NbtCompound();
        NbtList c = new NbtList();
        b.putString("Value", START + texture);
        c.add(b);
        props.put("textures", c);
        owner.put("Properties", props);

        NbtCompound display = head.getOrCreateSubNbt("display");
        display.putString("Name", "{\"text\":\"Lucky Block "+name+"\"}");

        head.setSubNbt(SkullItem.SKULL_OWNER_KEY, owner);
        head.setSubNbt("display", display);

        ALL.add(head);
        return head;
    };
}
