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
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.title, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem("5d725257e119a519afcf6aab", position + " 번째 아이템", position + " 입니다", "asdf", Integer.toString(position * 1000));
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
