package cn.xpbootcamp.gildedrose;

import static org.junit.Assert.*;

import cn.xpbootcamp.gildedrose.model.Item;
import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {
    private GildedRose app;

    @Before
    public void setup() {
        Item[] items = new Item[] {
                new Item("foo", 0, 0),
                new Item("foo", 1, 1),
                new Item("foo", 0, 10),
                new Item("Aged Brie", 1, 49),
                new Item("Aged Brie", 0, 8),
                new Item("Sulfuras, Hand of Ragnaros", 10, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 12, 1),
                new Item("Backstage passes to a TAFKAL80ETC concert", 1, 7)
        };
        app = new GildedRose(items);
    }

    @Test
    public void should_keep_same_name_when_one_day_pass_given_normal_product() {
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    public void should_decrease_sellIn_when_one_day_pass_given_normal_product() {
        app.updateQuality();
        assertEquals(0, app.items[1].sellIn);
    }

    @Test
    public void should_decrease_1_point_quality_when_one_day_pass_given_normal_product_and_not_reach_expired_day() {
        app.updateQuality();
        assertEquals(0, app.items[1].quality);
    }

    @Test
    public void should_not_lower_than_0_when_one_day_pass_given_normal_product_and_reach_expired_day() {
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void should_decrease_2_pints_when_one_day_pass_given_normal_product_and_reach_expired_day() {
        app.updateQuality();
        assertEquals(8, app.items[2].quality);
    }

    @Test
    public void should_increase_1_point_when_one_day_pass_given_aged_brie_and_not_reach_expired_day() {
        app.updateQuality();
        assertEquals(50, app.items[3].quality);
    }

    @Test
    public void should_increase_2_points_when_one_day_pass_given_aged_brie_and_reach_expired_day() {
        app.updateQuality();
        assertEquals(10, app.items[4].quality);
    }

    @Test
    public void should_not_higher_than_50_when_two_day_pass_given_aged_brie_and_reach_expired_day() {
        app.updateQuality();
        app.updateQuality();
        assertEquals(50, app.items[3].quality);
    }

    @Test
    public void should_not_decrease_sellIn_and_quality_when_one_day_pass_given_sulfuras() {
        app.updateQuality();
        assertEquals(80, app.items[5].quality);
        assertEquals(10, app.items[5].sellIn);
    }

    @Test
    public void should_increase_1_point_when_one_day_pass_given_backstage_pass_and_expired_day_bigger_than_10() {
        app.updateQuality();
        assertEquals(2, app.items[6].quality);
    }

    @Test
    public void should_increase_3_points_when_two_days_pass_given_backstage_pass_and_one_day_bigger_than_10_another_day_smaller_than_10() {
        app.updateQuality();
        app.updateQuality();
        assertEquals(4, app.items[6].quality);
    }

    @Test
    public void should_increase_3_points_when_one_day_pass_give_backstage_pass_and_expired_day_smaller_than_5() {
        app.updateQuality();
        assertEquals(10, app.items[7].quality);
    }

    @Test
    public void should_be_0_when_two_day_pass_given_one_day_not_expired_another_day_expired() {
        app.updateQuality();
        app.updateQuality();
        assertEquals(0, app.items[7].quality);
    }


}