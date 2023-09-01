package com.garciamaclean.thenewboston.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.garciamaclean.thenewboston.model.Bank
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val baseURL = "/api/banks"

    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks  {

        @Test
        fun `should return all banks`() {
            // when, then
            mockMvc.get(baseURL)
                .andDo {print()}
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") { value("2213") }

                }
        }
    }

    @Nested
    @DisplayName("GET /api/banks/{accountNumber}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun `should return the bank with the given account number`() {
            // given
            val accountNumber = 2213

            // when / then
            mockMvc.get("$baseURL/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {  contentType(MediaType.APPLICATION_JSON)}
                    jsonPath("$.trust") { value(3.14) }
                    jsonPath("$.transactionFee") { value(17)}
                }
        }
        
        @Test
        fun `should return NOT FOUND when account number does not exists`() {
            // given
            val accountNumber = "does not exists"
            
            // when / then
            mockMvc.get("$baseURL/$accountNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
            
        }
        
    }
    
    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewBank{
    
        @Test
        fun `should add the new bank`() {
            // given
            val newBank = Bank("newaccount", 0.0, 23)
            
            // when
            val performPost = mockMvc.post(baseURL){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }
            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newBank))
                    }
                }

            mockMvc.get("$baseURL/${newBank.accountNumber}")
                .andExpect {
                    content {
                        json(objectMapper.writeValueAsString(newBank))
                    }
                }
        }
        
        @Test
        fun `should return BAD REQUEST if already exists`() {
            // given
            val invalidBank = Bank("2213", 1.0, 1)
            
            // when
            val performPost = mockMvc.post(baseURL){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }
            
            //  then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() }}

        }

    }

    @Nested
    @DisplayName("PATCH /api/banks/{accountNumber}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PatchExistingBank {
    
        @Test
        fun `should update an existing bank`() {
            // given
            val updatedBank = Bank("2213", 1.0, 1)
            
            // when
            val performPatchRequest = mockMvc.patch(baseURL){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)
            }
            
            //  then
            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedBank))
                    }
                }
            mockMvc.get("$baseURL/${updatedBank.accountNumber}")
                .andExpect {
                    content {
                        json(objectMapper.writeValueAsString(updatedBank))
                    }
                }
        }
    }

}