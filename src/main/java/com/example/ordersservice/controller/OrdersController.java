package com.example.ordersservice.controller;

import com.example.ordersservice.dto.OrdersDto;
import com.example.ordersservice.entity.OrdersEntity;
import com.example.ordersservice.service.OrdersService;
import com.example.ordersservice.vo.RequestOrder;
import com.example.ordersservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders-service")
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping(value = "/{userId}/orders")
    public ResponseEntity<ResponseOrder> createdOrder(@PathVariable("userId") String userId, @RequestBody RequestOrder orderDetails){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrdersDto orderDto = modelMapper.map(orderDetails,OrdersDto.class);
        orderDto.setUserId(userId);
        OrdersDto returnOrders = ordersService.createOrder(orderDto);

        ResponseOrder responseOrder = modelMapper.map(returnOrders,ResponseOrder.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping(value="/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable("userId") String userId){
        Iterable<OrdersEntity> orderList = ordersService.getOrdersByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(v ->{
            result.add(new ModelMapper().map(v,ResponseOrder.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value="/ordersInfo/{orderId}")
    public ResponseEntity<ResponseOrder> getOrdersInfo(@PathVariable("orderId") String orderId){
        OrdersDto orderByOrderId = ordersService.getOrderByOrderId(orderId);
        ResponseOrder responseOrder = new ModelMapper().map(orderByOrderId,ResponseOrder.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }
}
