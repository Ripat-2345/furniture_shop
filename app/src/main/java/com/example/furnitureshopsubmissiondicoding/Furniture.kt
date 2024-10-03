package com.example.furnitureshopsubmissiondicoding

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Furniture(
    val furnitureName: String,
    val furniturePrice: String,
    val furnitureDescription: String,
    val furnitureImage: Int,
): Parcelable