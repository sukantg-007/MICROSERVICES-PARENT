package com.vc.orderservice.client;

import com.vc.common.dto.ShowDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface ShowClientService {
    @GetExchange("/{id}")
    ShowDTO getShowById(@PathVariable Long id);
}
