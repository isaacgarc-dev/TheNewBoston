package com.garciamaclean.thenewboston.datasource.mock

import com.garciamaclean.thenewboston.datasource.BankDataSource
import com.garciamaclean.thenewboston.model.Bank
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository
class MockDataSource: BankDataSource {
    val banks = mutableListOf(
        Bank("2213", 3.14 ,17),
        Bank("1234", 23.14 ,4),
        Bank("1111", 3.2 ,2)
    )
    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String): Bank =
        banks.firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException(
                "Could not find a bank with account number '$accountNumber'.")

    override fun createBank(bank:Bank): Bank {
        if ( banks.any {it.accountNumber == bank.accountNumber}) {
            throw IllegalArgumentException(
                "Bank with account number ${bank.accountNumber} already exists.")
        }
        banks.add(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber }
            ?: throw NoSuchElementException(
                "Could not find a bank with account number '${bank.accountNumber}'.")

        banks.remove(currentBank)
        banks.add(bank)

        return bank
    }
}