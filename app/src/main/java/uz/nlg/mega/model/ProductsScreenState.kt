package uz.nlg.mega.model

import uz.nlg.mega.utils.ProductSearchType

data class ProductsScreenState(
    val isCategorySectionState: Boolean,
    val category: Category?,
    val productType: ProductSearchType
)
