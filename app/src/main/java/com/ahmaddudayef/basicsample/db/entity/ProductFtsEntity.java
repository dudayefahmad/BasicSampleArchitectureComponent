package com.ahmaddudayef.basicsample.db.entity;

import androidx.room.Entity;
import androidx.room.Fts4;

/**
 * Created by Ahmad Dudayef on 11/22/2018.
 */
@Entity(tableName = "productsFts")
@Fts4(contentEntity = ProductEntity.class)
public class ProductFtsEntity {
    private String name;
    private String description;

    public ProductFtsEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
