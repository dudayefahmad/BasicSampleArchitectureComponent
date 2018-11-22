package com.ahmaddudayef.basicsample.model;

import java.util.Date;

/**
 * Created by Ahmad Dudayef on 11/22/2018.
 */

public interface Comment {
    int getId();
    int getProductId();
    String getText();
    Date getPostedAt();
}
