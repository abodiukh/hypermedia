package com.bodiukh.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bodiukh.exceptions.InvalidAdStateTransitionException;
import com.bodiukh.infrastructure.LocalDateTimeConverter;

import org.springframework.hateoas.Identifiable;

@Entity
public class Ad implements Identifiable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    public Ad() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public enum Type {
        BUY,
        SELL
    }

    @Column(nullable = false)
    private BigInteger amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    public enum Currency {
        USD,
        EUR
    }

    @Column(nullable = false)
    private BigDecimal rate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    private Location location;

    private String comment;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime publishedAt;

    @Embeddable
    public static class Location {

        @Column(nullable = false)
        private String city;

        private String area;

        public Location() {
        }

        public Location(final String city, final String area) {
            this.city = city;
            this.area = area;
        }

        public String getCity() {
            return city;
        }

        public void setCity(final String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(final String area) {
            this.area = area;
        }
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    public enum Status {

        NEW,

        PUBLISHED,

        OUTDATED

    }

    public void publish() {
        if (status == Status.NEW) {
            publishedAt = LocalDateTime.now();
            status = Status.PUBLISHED;
        } else {
            throw new InvalidAdStateTransitionException("Ad is already published");
        }
    }

    public void expire() {
        if (status == Status.PUBLISHED) {
            status = Status.OUTDATED;
        } else {
            throw new InvalidAdStateTransitionException(
                    "Ad can be finished only when it is in the " + Status.PUBLISHED + " state");
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(final BigInteger amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(final BigDecimal rate) {
        this.rate = rate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(final Location location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(final LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Ad ad = (Ad) o;

        return id.equals(ad.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
