package entity;

import java.util.Objects;
import java.util.UUID;

public class Book {

    private UUID id;

    private String name;

    private String isbn;

    public Book(String name, String isbn) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.isbn = isbn;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Book book = (Book) o;
        return Objects.equals(this.isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.isbn);
    }
}
