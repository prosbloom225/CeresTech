package com.prosbloom.cerestech.data;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CTCustomTags {

    public static final TagKey<Item> TEST_TAG = TagUtil.createPlatformItemTag("test/tag", "test_tag");
}
