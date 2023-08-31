package com.garciamaclean.thenewboston.datasource.mock

import com.garciamaclean.thenewboston.datasource.BankDataSource
import com.garciamaclean.thenewboston.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockDataSource: BankDataSource {
    val banks = listOf(Bank("2213", 0.0 ,0))
    override fun retrieveBanks(): Collection<Bank> {
        return banks
    }

}