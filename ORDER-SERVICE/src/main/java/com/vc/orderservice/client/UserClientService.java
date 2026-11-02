package com.vc.orderservice.client;

import com.vc.common.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface UserClientService {

    @GetExchange("/{id}")
    UserDTO getUserById(@PathVariable Long id);
}
