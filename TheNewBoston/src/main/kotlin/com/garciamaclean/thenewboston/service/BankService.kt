package com.garciamaclean.thenewboston.service

import com.garciamaclean.thenewboston.datasource.BankDataSource
import com.garciamaclean.thenewboston.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService (private val dataSource: BankDataSource ) {
    fun getBanks(): Collection<Bank> {
        return dataSource.retrieveBanks()
    }

}