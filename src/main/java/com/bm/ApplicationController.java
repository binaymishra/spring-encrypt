package com.bm;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

  @PostMapping(value = {"/encrypt"},
      consumes = MediaType.TEXT_PLAIN_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> encrypt(@RequestBody String text) {

    if (StringUtils.isBlank(text))
      return ResponseEntity.badRequest().build();

    return ResponseEntity.ok(jasyptEncrypt(text));
  }

  private String jasyptEncrypt(String text) {
    AES256TextEncryptor encryptor = new AES256TextEncryptor();
    encryptor.setPassword("some_salt");
    return StringUtils.join("ENC(", encryptor.encrypt(text), ")");
  }

}
