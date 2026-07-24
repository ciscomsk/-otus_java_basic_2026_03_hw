package ru.otus.java.basic.hw32.dto;

public class ItemDto {
    private long is;
    private String title;
    private int price;

    public ItemDto() {
    }

    public ItemDto(long is, String title, int price) {
        this.is = is;
        this.title = title;
        this.price = price;
    }

    public long getIs() {
        return is;
    }

    public void setIs(long id) {
        this.is = is;
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
