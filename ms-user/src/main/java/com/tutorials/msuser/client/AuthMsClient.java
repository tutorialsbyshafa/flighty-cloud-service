package com.tutorials.msuser.client;

import static com.tutorials.msuser.util.UrlConstant.GENERATE_JWT_URL;

import com.tutorials.msuser.model.GenerateJwtRqModel;
import com.tutorials.msuser.model.GenerateJwtRsModel;
import com.tutorials.msuser.model.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auth-ms", url = "${client.url.auth}")
public interface AuthMsClient {

    @PostMapping(GENERATE_JWT_URL)
    ResponseEntity<ResponseModel<GenerateJwtRsModel>> generateToken(@RequestBody GenerateJwtRqModel requestBody);
}
