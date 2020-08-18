package com.f1soft.pki.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class RequestWrapper {

    private String signature;
    private String data;
    private String secretKey;
    private String clientKey;
}
