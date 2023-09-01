package com.garciamaclean.thenewboston.datasource.mock

import com.garciamaclean.thenewboston.datasource.BankDataSource
import com.garciamaclean.thenewboston.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockDataSource: BankDataSource {
    val banks = listOf(
        Bank("2213", 3.14 ,17),
        Bank("1234", 23.14 ,4),
        Bank("1111", 3.2 ,2)
    )
    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String): Bank =
        banks.firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number '$accountNumber'")
}