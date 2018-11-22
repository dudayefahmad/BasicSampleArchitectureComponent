package com.ahmaddudayef.basicsample;

import com.ahmaddudayef.basicsample.db.AppDatabase;
import com.ahmaddudayef.basicsample.db.entity.CommentEntity;
import com.ahmaddudayef.basicsample.db.entity.ProductEntity;
import com.ahmaddudayef.basicsample.model.Product;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

/**
 * Created by Ahmad Dudayef on 11/22/2018.
 */

public class DataRepository {
    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private MediatorLiveData<List<ProductEntity>> mObservableProducts;


    public DataRepository(final AppDatabase database) {
        this.mDatabase = database;
        mObservableProducts = new MediatorLiveData<>();

        mObservableProducts.addSource(mDatabase.productDao().loadAllProducts(),
                productEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null){
                        mObservableProducts.postValue(productEntities);
                    }
                });
    }

    public static DataRepository getsInstance(final AppDatabase database){
        if (sInstance == null){
            synchronized (DataRepository.class){
                if (sInstance == null){
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of products from the database and get notified when the data changes.
     */
    public LiveData<List<ProductEntity>> getProducts() {
        return mObservableProducts;
    }

    public LiveData<ProductEntity> loadProduct(final int productId){
        return mDatabase.productDao().loadProduct(productId);
    }

    public LiveData<List<CommentEntity>> loadComments(final int productId){
        return mDatabase.commentDao().loadComments(productId);
    }

    public LiveData<List<ProductEntity>> searchProducts(String query){
        return mDatabase.productDao().searchAllProducts(query);
    }
}
