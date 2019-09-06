package com.example.nhfintechsproutmarket.dummy;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class HomeItemContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.title, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String title;
        public final String description;
        public final String image;
        public final String price;

        public DummyItem(String id, String title, String description, String image, String price) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.image = image;
            this.price = price;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
