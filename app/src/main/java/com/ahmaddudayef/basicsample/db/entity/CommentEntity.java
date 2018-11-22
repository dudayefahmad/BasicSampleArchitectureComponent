package com.ahmaddudayef.basicsample.db.entity;

import com.ahmaddudayef.basicsample.model.Comment;
import com.ahmaddudayef.basicsample.model.Product;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Created by Ahmad Dudayef on 11/22/2018.
 */
@Entity(tableName = "comments",
        foreignKeys = {
            @ForeignKey(entity = ProductEntity.class,
            parentColumns = "id",
            childColumns = "productId",
            onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = "productId")
        })
public class CommentEntity implements Comment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int productId;
    private String text;
    private Date postedAt;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public CommentEntity() {
    }

    @Ignore
    public CommentEntity(int id, int productId, String text, Date postedAt) {
        this.id = id;
        this.productId = productId;
        this.text = text;
        this.postedAt = postedAt;
    }
}
