package com.garciamaclean.thenewboston.service

import com.garciamaclean.thenewboston.datasource.BankDataSource
import com.garciamaclean.thenewboston.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService (private val dataSource: BankDataSource ) {
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()

    fun getBank(accountNumber: String): Bank = dataSource.retrieveBank(accountNumber)
    fun addBank(bank : Bank): Bank = dataSource.createBank( bank )
    fun updateBank(bank : Bank): Bank = dataSource.updateBank(bank)

}