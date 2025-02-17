package com.rbenitez.lambdas.northwindx.controller.rest;

import com.rbenitez.lambdas.northwindx.controller.dto.OrderDto;
import com.rbenitez.lambdas.northwindx.entities.*;
import com.rbenitez.lambdas.northwindx.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/lambda")
public class NorthwindxController {

    private final CustomersService customersService;
    private final ProductsService productsService;
    private final OrderService orderService;
    private final CategoriesService categoriesService;
    private final OrderDetailsService orderDetailsService;

    @ApiOperation(value = "Obtener todos los Clientes",notes = "¨Podemos obtener todos nuestros clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Success, se obtuvieron los clientes correctamente!", response = Customers.class),
            @ApiResponse(code = 400, message = "Bad Request.Esta vez cambiamos el tipo de dato de la respuesta (String)", response = String.class),
            @ApiResponse(code = 500, message = "Error inesperado del sistema")
    })
    @GetMapping("/customers")
    public List<Customers> getAllCustomer(){
        return customersService.getCustomers();
    }

    @GetMapping("/products")
    public List<Products> getAllProducts(){
        return productsService.getProducts();
    }


    @GetMapping("/contact")
    public List<Customers> findByContactTitle(@Param("contactTitle") String contactTitle){
        log.info("Call all customers with contact title: {}",contactTitle);
        return customersService.findByContactTitle(contactTitle);
    }

    @GetMapping("/quantities/{header}")
    public Map<String, Long> findCustomerByHeader(@PathVariable("header") String header){
        log.info("Find quantities by: {}", header);
        return customersService.findCustomerByHeader(header);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Categories>> getCategories(){
        List<Categories> categories = categoriesService.getAllCategories();
        if(categories!=null){
            return ResponseEntity.ok(categories);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/order")
    public ResponseEntity<Optional<Orders>> getOrderById(@RequestBody OrderDto orderDto){
        Optional<Orders> order = orderService.getAllOrderById(orderDto.getOrderId());
        if(order==null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(order);
        }
    }

    @GetMapping("/details/{productId}")
    public ResponseEntity<Optional<List<OrderDetails>>> findOrderDetailsByProductId(@PathVariable("productId") Integer productId){
        List<OrderDetails> orderDetails = orderDetailsService.getOrderDetailsByProductId(productId);
        if(orderDetails==null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(Optional.of(orderDetails));
        }
    }

    @GetMapping("/transaction")
    public ResponseEntity<Optional<List<OrderDetails>>> findTransactionByOrderId(@RequestParam("productId") Integer productId){
        List<OrderDetails> orderDetails = orderDetailsService.getOrderDetailsByProductId(productId);
        if(orderDetails==null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(Optional.of(orderDetails));
        }
    }
}
