package com.kasasa.loan;

import com.kasasa.loan.api.LoanController;
import com.kasasa.loan.model.Loan;
import com.kasasa.loan.model.LoanType;
import com.kasasa.loan.service.LoanService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LoanController.class)
public class LoanApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    private String jsonRequest = "{\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Miguel Monraz\",\n" +
            "    \"ssn\": \"55-412-6645\",\n" +
            "    \"dob\": \"1979-11-21\",\n" +
            "    \"loan\": 15000.00,\n" +
            "    \"rate\": 18.5,\n" +
            "    \"loanType\": \"PERSONAL\",\n" +
            "    \"term\": 365,\n" +
            "    \"apr\": 6757.5\n" +
            "}";

    private static Loan loan;

    @BeforeClass
    public static void setup() throws Exception {
        loan = new Loan(1, "Miguel Monraz", "55-412-6645", LocalDate.of(1979, 11, 21),
                15000.00, 18.5, LoanType.PERSONAL, 365,6757.5);
    }


    @Test
    public void retrieveLoanDetails() throws Exception {
        when(loanService.getLoan(anyInt())).thenReturn(loan);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/loans/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println("Result from api " + result.getResponse()
                .getContentAsString());
        JSONAssert.assertEquals(jsonRequest, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void createLoan() throws Exception {
        when(loanService.saveLoan(Mockito.any(Loan.class))).thenReturn(loan);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/loans")
                .accept(MediaType.APPLICATION_JSON).content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
