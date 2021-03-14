package ru.ifmo.komarov.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.beans.ConstructorProperties

@Suppress("unused")
@Document
class User @ConstructorProperties("id", "username", "accountCurrency") constructor(
    @Id val id: Long,
    val username: String,
    val accountCurrency: Currency
)
