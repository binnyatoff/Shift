package com.binnyatoff.shift.data.network

import com.binnyatoff.shift.data.network.models.Binlist
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/{bin}")
    suspend fun getBinlist(@Path("bin") bin:Int): Result<Binlist>
}