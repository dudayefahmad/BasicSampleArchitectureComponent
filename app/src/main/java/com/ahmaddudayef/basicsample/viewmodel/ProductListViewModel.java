package com.ahmaddudayef.basicsample.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.ahmaddudayef.basicsample.BasicApp;
import com.ahmaddudayef.basicsample.DataRepository;
import com.ahmaddudayef.basicsample.db.entity.ProductEntity;

import java.util.List;

/**
 * Created by Ahmad Dudayef on 11/22/2018.
 */

public class ProductListViewModel extends AndroidViewModel {
    private final DataRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<ProductEntity>> mObservableProducts;

    public ProductListViewModel(Application application) {
        super(application);

        mObservableProducts = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableProducts.setValue(null);

        mRepository = ((BasicApp) application).getRepository();
        LiveData<List<ProductEntity>> products = mRepository.getProducts();

        // observe the changes of the products from the database and forward them
        mObservableProducts.addSource(products, mObservableProducts::setValue);
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<ProductEntity>> getProducts() {
        return mObservableProducts;
    }

    public LiveData<List<ProductEntity>> searchProducts(String query) {
        return mRepository.searchProducts(query);
    }
}
