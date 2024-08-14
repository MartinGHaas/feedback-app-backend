package com.mghaas.feedbackapp.core.models.feedback;

import com.mghaas.feedbackapp.core.models.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedBack {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String comment;

    @Column
    private byte rating;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    public FeedBack(String comment, byte rating, Item item) {
        this.comment = comment;
        this.rating = rating;
        this.item = item;
    }
}
