package ru.otus.java.basic.hw32.dto;

public class ItemDto {
    private long id;
    private String title;
    private int price;

    public ItemDto() {
    }

    public ItemDto(long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public long getIs() {
        return id;
    }

    public void setIs(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
