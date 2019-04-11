package com.school.estimate.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class NotCheckPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        //不加密
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        System.err.print(charSequence);
        System.err.print(s);
        if(charSequence.toString().equals(s)){
            return true;
        }
        return false;
    }
}
