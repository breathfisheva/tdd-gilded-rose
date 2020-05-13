package cn.xpbootcamp.gildedrose;

import cn.xpbootcamp.gildedrose.model.Item;
import cn.xpbootcamp.gildedrose.service.AdjustValueService;

class GildedRose {
    Item[] items;
    private AdjustValueService adjustValueService;

    GildedRose(Item[] items) {
        this.items = items;
        this.adjustValueService = new AdjustValueService();
    }

    void updateQuality() {
        for (Item item : items) {
            if (!item.name.toLowerCase().contains("sulfuras")) {
                adjustValueService.removeSellIn(item);
                if (item.name.toLowerCase().contains("aged brie")) {
                    agedBrie(item);
                } else if (item.name.toLowerCase().contains("backstage passes")) {
                    backstagePasses(item);
                } else {
                    normalItem(item);
                }
            }
        }
    }



    private void agedBrie(Item item) {
        if (item.sellIn < 0) {
            adjustValueService.addQuality(item, 2);
        } else {
            adjustValueService.addQuality(item, 1);
        }
    }

    private void backstagePasses(Item item) {
        if (item.sellIn < 0) {
            item.quality = 0;
        } else if (item.sellIn <= 5) {
            adjustValueService.addQuality(item, 3);
        } else if (item.sellIn <= 10) {
            adjustValueService.addQuality(item, 2);
        } else {
            adjustValueService.addQuality(item, 1);
        }
    }

    private void normalItem(Item item) {
        if (item.sellIn < 0) {
            adjustValueService.removeQuality(item, 2);
        } else {
            adjustValueService.removeQuality(item, 1);
        }
    }
}