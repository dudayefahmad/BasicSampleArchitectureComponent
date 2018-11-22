package com.ahmaddudayef.basicsample.db.dao;

import com.ahmaddudayef.basicsample.db.entity.ProductEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by Ahmad Dudayef on 11/22/2018.
 */
@Dao
public interface ProductDao {
    @Query("SELECT * FROM products")
    LiveData<List<ProductEntity>> loadAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ProductEntity> products);

    @Query("select * from products where id = :productId")
    LiveData<ProductEntity> loadProduct(int productId);

    @Query("select * from products where id = :productId")
    ProductEntity loadProductSync(int productId);

    @Query("SELECT products.* FROM products JOIN productsFts ON (products.id = productsFts.rowid) "
            + "WHERE productsFts MATCH :query")
    LiveData<List<ProductEntity>> searchAllProducts(String query);
}
