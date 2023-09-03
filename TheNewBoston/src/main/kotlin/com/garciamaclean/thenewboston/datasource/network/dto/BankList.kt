package com.garciamaclean.thenewboston.datasource.network.dto

import com.garciamaclean.thenewboston.model.Bank

data class BankList(
    val results: Collection<Bank>
)