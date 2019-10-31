package com.teko.proddiscovery.data.source.remote.response

import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.teko.proddiscovery.data.entity.TkProduct
import com.teko.proddiscovery.data.entity.TkProductImage

data class TkProdResponse(@Expose @SerializedName("result") val result: TkProdResultData): BaseResponse()

data class TkProdResultData(@Expose @SerializedName("product") val product: TkRemoteProduct)

data class TkRemoteProduct(@Expose @SerializedName("displayName")
                           val displayName: String = "",
                           @Expose @SerializedName("name")
                           val name: String = "",
                           @Expose @SerializedName("url")
                           val url: String = "",
                           @Expose @SerializedName("sku")
                           val sku: Long = -1,
                           @Expose @SerializedName("magentoId")
                           val magentoId: Long = -1,
                           @Expose @SerializedName("price")
                           val price: TkProdPrice,
                           @Expose @SerializedName("attributeGroups")
                           val attributeGroups: List<TkProdAttr>?,
                           @Expose @SerializedName("images")
                           val images: List<TkProductImage>){
    fun convertToTkProduct(): TkProduct{
        val imagesStr = Gson().toJson(images)
        val tkProduct = TkProduct(displayName, name, sku, url, price.sellPrice, price.supplierSalePrice, magentoId = magentoId, images = imagesStr)
        val attrs = LinkedHashMap<String, String>()
        attributeGroups?.forEach {
            attrs.put(it.name, it.value)
        }
        tkProduct.attributeGroups.clear()
        tkProduct.attributeGroups.putAll(attrs)

        return tkProduct
    }
}

data class TkProdAttr(@Expose @SerializedName("name")
                      val name: String = "",
                      @Expose @SerializedName("value")
                      val value: String = "")

data class TkProdPrice(@Expose @SerializedName("sellPrice")
                      val sellPrice: Int = 0,
                      @Expose @SerializedName("supplierSalePrice")
                      val supplierSalePrice: Int = 0)
