package ru.ifmo.komarov.utils

import ru.ifmo.komarov.model.Currency
import ru.ifmo.komarov.model.Product

object CurrencyConverter {
    private val currencyMultiplier = mapOf(
        Currency.RUB to 1.0,
        Currency.USD to 73.26,
        Currency.EUR to 87.57
    )

    fun convert(product: Product, neededCurrency: Currency): Product {
        val newPrice = product.price / currencyMultiplier.getValue(neededCurrency) * currencyMultiplier.getValue(product.currency)
        return Product(product.id, product.name, neededCurrency, newPrice)
    }
}
