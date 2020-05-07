package com.tejendramachinetest.imagetaskmodule;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface iApiService {

    @GET("api/")
    Single<ImageListModel> fetchImages(@Query("key") String key,
                                             @Query("q") String searchWord,
                                             @Query("image_type") String imageType);


}
