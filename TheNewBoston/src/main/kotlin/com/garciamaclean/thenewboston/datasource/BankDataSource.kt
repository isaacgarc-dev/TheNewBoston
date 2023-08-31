package com.garciamaclean.thenewboston.datasource

import com.garciamaclean.thenewboston.model.Bank

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>
}