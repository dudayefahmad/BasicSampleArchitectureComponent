package com.ahmaddudayef.basicsample.ui;

import android.text.Editable;
import android.view.View.OnClickListener;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmaddudayef.basicsample.R;
import com.ahmaddudayef.basicsample.databinding.FragmentProductListBinding;
import com.ahmaddudayef.basicsample.db.entity.ProductEntity;
import com.ahmaddudayef.basicsample.model.Product;
import com.ahmaddudayef.basicsample.viewmodel.ProductListViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {
    public static final String TAG = "ProductListViewModel";

    private ProductAdapter mProductAdapter;

    private FragmentProductListBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false);

        mProductAdapter = new ProductAdapter(mProductClickCallback);
        mBinding.productsList.setAdapter(mProductAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ProductListViewModel viewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);

        mBinding.productsSearchBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable query = mBinding.productsSearchBox.getText();
                if (query == null || query.toString().isEmpty()) {
                    subscribeUi(viewModel.getProducts());
                } else {
                    subscribeUi(viewModel.searchProducts("*" + query + "*"));
                }
            }
        });

        subscribeUi(viewModel.getProducts());
    }

    private void subscribeUi(LiveData<List<ProductEntity>> liveData) {
        // Update the list when the data changes
        liveData.observe(this, new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(@Nullable List<ProductEntity> myProducts) {
                if (myProducts != null) {
                    mBinding.setIsLoading(false);
                    mProductAdapter.setProductList(myProducts);
                } else {
                    mBinding.setIsLoading(true);
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings();
            }
        });
    }

    private final ProductClickCallback mProductClickCallback = new ProductClickCallback() {
        @Override
        public void onClick(Product product) {

            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).show(product);
            }
        }
    };

}
