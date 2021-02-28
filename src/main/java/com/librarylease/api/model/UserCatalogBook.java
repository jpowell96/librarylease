package com.librarylease.api.model;

import com.librarylease.api.auth.ApplicationUser;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name="user_catalog")
public class UserCatalogBook {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private ApplicationUser user;

    @ManyToOne
    @JoinColumn(name="book_id", nullable = false)
    private Book book;

    private Integer stock;
    private Integer available;
    private Integer borrowed;
    private Integer onHold;

    public UserCatalogBook() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Integer borrowed) {
        this.borrowed = borrowed;
    }

    public Integer getOnHold() {
        return onHold;
    }

    public void setOnHold(Integer onHold) {
        this.onHold = onHold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCatalogBook)) return false;
        UserCatalogBook that = (UserCatalogBook) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(book, that.book) &&
                Objects.equals(stock, that.stock) &&
                Objects.equals(available, that.available) &&
                Objects.equals(borrowed, that.borrowed) &&
                Objects.equals(onHold, that.onHold);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, book, stock, available, borrowed, onHold);
    }

    @Override
    public String toString() {
        return "UserCatalogBook{" +
                "user=" + user +
                ", book=" + book +
                ", stock=" + stock +
                ", available=" + available +
                ", borrowed=" + borrowed +
                ", onHold=" + onHold +
                '}';
    }
}
