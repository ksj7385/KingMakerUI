package com.gachon.kingmaker;

public class ListItem {
    enum ItemType {
        RADIO_BUTTON,
        SLIDER,
        OPTION_BUTTON,
        TEXT_VIEW
    }

    private ItemType itemType;
    private String data; // 이 데이터는 예시입니다. 실제 데이터 구조에 맞게 조정해야 합니다.

    public ListItem(ItemType itemType, String data) {
        this.itemType = itemType;
        this.data = data;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getData() {
        return data;
    }
}