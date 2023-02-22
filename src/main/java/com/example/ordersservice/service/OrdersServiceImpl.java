package com.example.ordersservice.service;

import com.example.ordersservice.dto.OrdersDto;
import com.example.ordersservice.entity.OrdersEntity;
import com.example.ordersservice.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    @Override
    public OrdersDto createOrder(OrdersDto ordersDto) {
        ordersDto.setOrderId(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        OrdersEntity ordersEntity = modelMapper.map(ordersDto,OrdersEntity.class);

        OrdersEntity save = ordersRepository.save(ordersEntity);
        return modelMapper.map(save,OrdersDto.class);
    }

    @Override
    public OrdersDto getOrderByOrderId(String orderId) {

        OrdersEntity ordersEntity = ordersRepository.findByOrderId(orderId);
        OrdersDto ordersDto =  new ModelMapper().map(ordersEntity,OrdersDto.class);

        return ordersDto;
    }

    @Override
    public Iterable<OrdersEntity> getOrdersByUserId(String UserId) {
        return ordersRepository.findByUserId(UserId);
    }
}
