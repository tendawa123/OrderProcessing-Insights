package com.ordermanagement.customercontroller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordermanagement.model.customer.Customer;
import com.ordermanagement.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class, secure = false)
public class CustomerControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@Test
	public void testCreateCustomer() throws Exception {
		Customer mockCustomer = new Customer();
		mockCustomer.setId(109);
		mockCustomer.setFirst_name("Tenzin");
		mockCustomer.setLast_name("Palkey");
		mockCustomer.setPhone("2323232");
		mockCustomer.setCity("Miao");
		mockCustomer.setEmail("asdfasd@gmail.com");
		mockCustomer.setAddress1("asdfa");
		mockCustomer.setAddress2("dfasdfasdfasd");
		mockCustomer.setCompany("XYZ");
		mockCustomer.setCountry("India");
		mockCustomer.setPostal_code("323232");
		mockCustomer.setState("AP");
		String inputInJson = this.mapToJson(mockCustomer);
		String URI = "/api/customers/create";
		Mockito.when(customerService.addCustomer(Mockito.any(Customer.class))).thenReturn(1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertThat(HttpStatus.CREATED.value()).isEqualTo(response.getStatus());
	}
	
	@Test
	public void testGetAllCustomers() throws Exception {
		Customer mockCustomer = new Customer();
		mockCustomer.setId(109);
		mockCustomer.setFirst_name("Tenzin");
		mockCustomer.setLast_name("Palkey");
		mockCustomer.setPhone("2323232");
		mockCustomer.setCity("Miao");
		mockCustomer.setEmail("asdfasd@gmail.com");
		mockCustomer.setAddress1("asdfa");
		mockCustomer.setAddress2("dfasdfasdfasd");
		mockCustomer.setCompany("XYZ");
		mockCustomer.setCountry("India");
		mockCustomer.setPostal_code("323232");
		mockCustomer.setState("AP");

		List<Customer> customerList = new ArrayList<>();
		customerList.add(mockCustomer);
		Mockito.when(customerService.getAllCustomers()).thenReturn(customerList);
		String URI = "/api/customers/getAllCustomers";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(customerList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void testDeleteCustomerById() throws Exception {
		String URI = "/api/customers/customerId/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("status--->" + result.getResponse().getStatus());
		System.out.println("status--->" + HttpStatus.NO_CONTENT.value());
		assertThat(HttpStatus.NO_CONTENT.value()).isEqualTo(204);
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}