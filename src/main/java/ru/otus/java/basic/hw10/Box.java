package ru.otus.java.basic.hw10;

public class Box {
    private final int length; // можно дробные типы - float/double
    private final int width;
    private final int height;
    private String color;
    private BoxState state;
    private String content;

    // можно сделать доп конструкторы с state/content
    public Box(int length, int width, int height, String color) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.color = color;
        this.state = BoxState.CLOSED;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getContent() {
        return content;
    }

    public BoxState getState() {
        return state;
    }

    // info
    public void showInfo() {
        System.out.printf("""
                length: %d, width: %d, height: %d
                color: %s, state: %s, content: %s
                """, length, width, height, color, state, content);
        // =
//        System.out.println("Length: " + length + ", width: " + width + ", height: " + height);
//        System.out.println("Color: " + color + ", state: " + state + ", content: " + content);
    }

    public void open() {
        if (state == BoxState.OPEN) {
            System.out.println("Коробка уже открыта");
            return;
        }

        state = BoxState.OPEN;
        System.out.println("Коробка открыта");
    }

    public void close() {
        if (state == BoxState.CLOSED) {
            System.out.println("Коробка уже закрыта");
            return;
        }

        state = BoxState.CLOSED;
        System.out.println("Коробка закрыта");
    }

    // лучше open() + close()
    public void setState(BoxState state) {
        if (this.state == state) {
            System.out.println("Коробка уже " + state);
            return;
        }

        this.state = state;
        if (state == BoxState.OPEN) {
            System.out.println("Коробка открыта");
        } else {
            System.out.println("Коробка закрыта");
        }
    }

    // putItem
    public void put(String item) {
        if (state == BoxState.CLOSED) {
            System.out.println("Нельзя положить " + item + " - коробка закрыта");
            return;
        }

        if (content != null) {
            System.out.println("Нельзя положить " + item + " - в коробке уже есть " + content);
            return;
        }

        content = item;
        System.out.println("В коробку положили " + item);
    }

    // throwAwayItem
    public void drop() {
        if (state == BoxState.CLOSED) {
            System.out.println("Нельзя выбросить предмет - коробка закрыта");
            return;
        }

        if (content == null) {
            System.out.println("Нельзя выбросить предмет - в коробке ничего нет");
            return;
        }

        String item = content;
        content = null;
        System.out.println("Из коробки выбросили " + item);
    }
}
