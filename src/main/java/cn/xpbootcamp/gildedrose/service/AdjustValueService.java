package cn.xpbootcamp.gildedrose.service;

import cn.xpbootcamp.gildedrose.model.Item;

public class AdjustValueService {
    public void removeSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    public void removeQuality(Item item, int amount) {
        item.quality = Math.max((item.quality - amount), 0);
    }

    public void addQuality(Item item, int amount) {
        item.quality = Math.min((item.quality + amount), 50);
    }
}
