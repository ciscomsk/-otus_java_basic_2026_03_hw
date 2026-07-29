package ru.otus.java.basic.hw33.app;

import ru.otus.java.basic.hw33.app.dto.ItemDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemsService {
    private List<ItemDto> items;

    public ItemsService() {
        items = new ArrayList<>(Arrays.asList(
                new ItemDto(1L, "Milk", 80),
                new ItemDto(2L, "Bread", 40),
                new ItemDto(3L, "Cheese", 400)
        ));
    }

    public List<ItemDto> getAll() {
        return Collections.unmodifiableList(items);
    }

    public ItemDto getById(long id) {
        return items.stream().filter(i -> i.getId() == id).findFirst().get();
    }

    public ItemDto create(ItemDto itemDto) {
        long newId = items.stream().mapToLong(ItemDto::getId).max().orElse(0L) + 1L;
        itemDto.setId(newId);
        items.add(itemDto);

        return itemDto;
    }
}
