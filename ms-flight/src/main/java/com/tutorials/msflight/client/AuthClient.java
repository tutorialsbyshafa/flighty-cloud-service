package com.tutorials.msflight.client;


import com.tutorials.msflight.model.ExtractJwtRqModel;
import com.tutorials.msflight.model.ExtractJwtRsModel;
import com.tutorials.msflight.model.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auth-ms", url = "${client.url.auth}")
public interface AuthClient {

    @PostMapping("/jwt/extract")
    ResponseEntity<ResponseModel<ExtractJwtRsModel>> extractToken(@RequestBody ExtractJwtRqModel requestBody);

}
