package com.corso.lombok;

import lombok.Builder;
import lombok.Data;

@Data
//permette di concatenare i metodi quando si richiamano
@Builder
public class LombokStudent {

    private String name;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private int age;
    private boolean enabled;

}
