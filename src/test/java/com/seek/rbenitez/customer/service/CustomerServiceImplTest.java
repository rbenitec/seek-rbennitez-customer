package com.seek.rbenitez.customer.service;

import com.seek.rbenitez.customer.controller.dto.CustomerMetricsResponse;
import com.seek.rbenitez.customer.controller.dto.CustomerRequestDto;
import com.seek.rbenitez.customer.controller.dto.CustomerResponseDto;
import com.seek.rbenitez.customer.entities.Customer;
import com.seek.rbenitez.customer.exception.BusinessException;
import com.seek.rbenitez.customer.repository.CustomersRepository;
import com.seek.rbenitez.customer.service.impl.CustomerServiceImpl;
import com.seek.rbenitez.customer.service.mapper.CustomerMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomersRepository customersRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;
    private CustomerRequestDto customerRequestDto;
    private CustomerResponseDto customerResponseDto;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setNames("Roberto");
        customer.setAge(30);
        customer.setBirthDate("12/05/1993");
        customer.setAge(31);
        customer.setAddress("Av. los sauces");
        customer.setLastNames("Benitez Cruzado");
        customer.setCreatedAt("18/02/2025");
        customer.setCountry("Peru");
        customer.setPhone("987654321");
        customer.setDni("12345678");
        customer.setCompanyName("Seek");

        customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setNames("Roberto");
        customerRequestDto.setAge(30);
        customerRequestDto.setBirthDate("12/05/1993");
        customerRequestDto.setAddress("Av. los sauces");
        customerRequestDto.setLastName("Benitez Cruzado");
        customerRequestDto.setCountry("Peru");
        customerRequestDto.setPhone("987654321");
        customerRequestDto.setCompanyName("Seek");
        customerRequestDto.setDni("12345678");
        customerRequestDto.setCompanyName("Seek");

        customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setCustomerId(1);
        customerResponseDto.setNames("Roberto");

        //Mockito.when(customerMapper.toEntity(Mockito.any(CustomerRequestDto.class))).thenReturn(customer);
//        Mockito.when(customerMapper.toDto(Mockito.any(Customer.class), Mockito.any())).thenReturn(customerResponseDto);
    }

    /**
     * Test para guardar un cliente exitosamente.
     */
    @Test
    void saveCustomer_Success() {
        Mockito.when(customerMapper.toEntity(Mockito.any(CustomerRequestDto.class))).thenReturn(customer);
        Mockito.when(customerMapper.toDto(Mockito.any(Customer.class), Mockito.any())).thenReturn(customerResponseDto);
        Mockito.when(customersRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        CustomerResponseDto result = customerService.saveCustomer(customerRequestDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getCustomerId());
        Assertions.assertEquals("Roberto", result.getNames());
        Mockito.verify(customersRepository, Mockito.times(1)).save(Mockito.any(Customer.class));
    }

    /**
     * Test para obtener clientes con expectativa de vida.
     */
    @Test
    void getCustomersLifeExpectancy_Success() {

        List<Customer> customerList = Arrays.asList(customer);
        Mockito.when(customersRepository.findAll()).thenReturn(customerList);

        List<CustomerResponseDto> result = customerService.getCustomersLifeExpectancy();

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Mockito.verify(customersRepository, Mockito.times(1)).findAll();
    }

    /**
     * Test para manejar excepción cuando no hay clientes en `getCustomersLifeExpectancy`.
     */
    @Test
    void getCustomersLifeExpectancy_ThrowsBusinessException() {
        Mockito.when(customersRepository.findAll()).thenReturn(List.of());

        BusinessException exception = Assertions.assertThrows(BusinessException.class,
                () -> customerService.getCustomersLifeExpectancy());

        Assertions.assertEquals("RBEN-201-01", exception.getCode());
        Assertions.assertEquals("Error al obtener los clientes", exception.getMessage());
        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getHttpStatus());
    }

    /**
     * Test para obtener métricas de clientes correctamente.
     */
    @Test
    void getCustomerMetrics_Success() {
        Customer customer2 = new Customer();
        customer2.setCustomerId(2);
        customer2.setAge(40);
        List<Customer> customerList = Arrays.asList(customer, customer2);

        Mockito.when(customersRepository.findAll()).thenReturn(customerList);

        CustomerMetricsResponse result = customerService.getCustomerMetrics();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(35.5, result.averageAge());
        Mockito.verify(customersRepository, Mockito.times(1)).findAll();
    }

    /**
     * Test para manejar excepción cuando hay menos de 2 clientes en `getCustomerMetrics`.
     */
    @Test
    void getCustomerMetrics_ThrowsBusinessException() {
        Mockito.when(customersRepository.findAll()).thenReturn(List.of(customer));

        BusinessException exception = Assertions.assertThrows(BusinessException.class,
                () -> customerService.getCustomerMetrics());

        Assertions.assertEquals("RBEN-202-01", exception.getCode());
        Assertions.assertEquals("La lista de clientes es vacia o con menos de 2 elementos no se puede calcular la desviación estándar ", exception.getMessage());
        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getHttpStatus());
    }

}
