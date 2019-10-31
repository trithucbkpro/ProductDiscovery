package com.teko.proddiscovery.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.teko.proddiscovery.data.entity.TkProduct.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class TkProduct @JvmOverloads constructor(
    @ColumnInfo(name = "displayName")
    @Expose @SerializedName("displayName")
    val displayName: String = "",

    @ColumnInfo(name = "name")
    @Expose @SerializedName("name")
    val name: String = "",

    @PrimaryKey
    @ColumnInfo(name = "sku")
    @Expose @SerializedName("sku")
    val sku: Long = -1,

    @ColumnInfo(name = "url")
    @Expose @SerializedName("url")
    val url: String = "",

    @ColumnInfo(name = "sellPrice")
    @Expose @SerializedName("sellPrice")
    val sellPrice: Int = 0,

    @ColumnInfo(name = "supplierSalePrice")
    @Expose @SerializedName("supplierSalePrice")
    val supplierSalePrice: Int = 0,

    @ColumnInfo(name = "attributeGroups")
    @Expose @SerializedName("attributeGroups")
    val attributeGroups: LinkedHashMap<String, String> = LinkedHashMap(),

    @ColumnInfo(name = "magentoId")
    @Expose @SerializedName("magentoId")
    val magentoId: Long = -1,

    @ColumnInfo(name = "images")
    @Expose @SerializedName("images")
    val images: String = "[]") {

    companion object {
        const val TABLE_NAME = "table_product"
    }

    @Ignore
    fun getImageList(): List<String>{
        val listProdImage: List<TkProductImage> =  Gson().fromJson(images, object : TypeToken<List<TkProductImage>>(){}.type)
        return listProdImage.map { it.url }
    }
}