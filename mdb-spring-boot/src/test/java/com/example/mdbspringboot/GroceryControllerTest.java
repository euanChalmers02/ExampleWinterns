package com.example.mdbspringboot;


import com.example.mdbspringboot.model.GroceryItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ExtendWith(MockitoExtension.class)
public class GroceryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private GroceryController groceryController;

    @Mock
    private GroceryService groceryService;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(groceryController).build();
    }

    @Test
    public void testGetAllItems() throws Exception {
//
//        MockHttpServletResponse response = mockMvc.perform(get("/items")).andReturn().getResponse();
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect((ResultMatcher) jsonPath("$", hasSize(4)));
    }

    @Test
    public void testRandom(){
        System.out.println("testRandom");
    }
}
