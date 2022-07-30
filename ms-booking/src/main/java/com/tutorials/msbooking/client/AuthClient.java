package com.tutorials.msbooking.client;

import com.tutorials.msbooking.model.ExtractJwtRqModel;
import com.tutorials.msbooking.model.ExtractJwtRsModel;
import com.tutorials.msbooking.model.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auth-ms", url = "${client.url.auth}")
public interface AuthClient {

    @PostMapping("/jwt/extract")
    ResponseEntity<ResponseModel<ExtractJwtRsModel>> extractToken(@RequestBody ExtractJwtRqModel requestBody);
}
