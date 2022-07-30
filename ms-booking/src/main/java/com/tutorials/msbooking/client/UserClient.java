package com.tutorials.msbooking.client;

import com.tutorials.msbooking.model.UserModel;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-user", url = "${client.url.user}")
public interface UserClient {
    @GetMapping("/users/{id}")
    ResponseEntity<UserModel> getUser(@PathVariable UUID id);

}
