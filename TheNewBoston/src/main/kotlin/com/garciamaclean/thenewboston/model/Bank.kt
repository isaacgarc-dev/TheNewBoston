package com.garciamaclean.thenewboston.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Bank (
        @JsonProperty("account-number")
        val accountNumber: String,
        @JsonProperty("trust")
        val trust: Double,
        @JsonProperty("default-transaction-fee")
        val transactionFee: Int
)