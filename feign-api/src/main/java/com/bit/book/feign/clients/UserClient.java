package com.bit.book.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "other-service")
public interface UserClient {
/*    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);*/
}
