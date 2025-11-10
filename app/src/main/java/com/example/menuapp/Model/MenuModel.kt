package com.example.menuapp.Model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class MenuModel (
    val name: String,
    val address: Address,

    @Json(name = "offered_burgers")
    val offeredBurgers: List<OfferedBurger>,

    @Json(name = "offered_drinks")
    val offeredDrinks: List<OfferedDrink>
): Parcelable

@Parcelize
data class Address (
    val street: String,
    val city: String,
    val country: Country
): Parcelable

@Parcelize
data class Country (
    @Json(name = "country_code")
    val countryCode: String,

    val name: String
): Parcelable

@Parcelize
data class OfferedBurger (
    val name: String,
    val description: String,

    @Json(name = "weight_grams")
    val weightGrams: Long? = null,

    @Json(name = "price_usd")
    val priceUsd: Double,

    val info: Info,
    val tags: List<String>? = null
): Parcelable, ItemDetails {
    override val itemName: String get() = name
    override val itemImg: String get() = info.img
    override val itemPrice: Double get() = priceUsd
    override val itemDescription: String get() = description
    override val itemWeightGrams: Long? get() = weightGrams
    override val itemIsVegetarian: Boolean? get() = info.vegetarian
    override val itemAllergens: List<String>? get() = info.allergensContainedIn
    override val itemTags: List<String>? get() = tags
}

@Parcelize
data class Info (
    @Json(name = "in_stock")
    val inStock: Boolean? = null,

    val vegetarian: Boolean,

    @Json(name = "allergens_contained_in")
    val allergensContainedIn: List<String>? = null,

    val availableSince: Long? = null,

    @Json(name = "available_until")
    val availableUntil: Date? = null,

    val img: String
): Parcelable

@Parcelize
data class OfferedDrink (
    val name: String,
    val description: String,
    val price: Double,
    val allergens: List<String>? = null,
    val img: String
): Parcelable, ItemDetails {
    override val itemName: String get() = name
    override val itemImg: String get() = img
    override val itemPrice: Double get() = price
    override val itemDescription: String get() = description
    override val itemWeightGrams: Long? get() = null
    override val itemIsVegetarian: Boolean? get() = null
    override val itemAllergens: List<String>? get() = allergens
    override val itemTags: List<String>? get() = null
}

interface ItemDetails {
    val itemName: String
    val itemImg: String
    val itemPrice: Double
    val itemDescription: String

    val itemWeightGrams: Long?
    val itemIsVegetarian: Boolean?
    val itemAllergens: List<String>?
    val itemTags: List<String>?
}