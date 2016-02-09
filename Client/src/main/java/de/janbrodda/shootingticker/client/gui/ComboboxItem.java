package de.janbrodda.shootingticker.client.gui;

/**
 *
 * @param <E> The Value Class
 */
public class ComboboxItem<E> {

    public String name;
    public E value;

    public ComboboxItem(String name, E value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }
}
