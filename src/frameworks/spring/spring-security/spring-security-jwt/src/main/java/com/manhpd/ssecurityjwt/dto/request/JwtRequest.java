package com.manhpd.ssecurityjwt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String userName;

    private String password;

}
