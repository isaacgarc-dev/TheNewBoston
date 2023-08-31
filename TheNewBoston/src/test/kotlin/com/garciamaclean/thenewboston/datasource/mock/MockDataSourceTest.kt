package com.garciamaclean.thenewboston.datasource.mock

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MockDataSourceTest{
    private val mockDataSource = MockDataSource()
    @Test
    fun `should provide a collection of banks`() {
        // when
        val banks = mockDataSource.retrieveBanks()

        //  then
        assertThat(banks).isNotEmpty
    }
    
    @Test
    fun `should provide some data`() {
        // when
        val banks = mockDataSource.retrieveBanks()
        
        // then
        assertThat(banks).allMatch{it.accountNumber.isNotBlank()}
    }
}