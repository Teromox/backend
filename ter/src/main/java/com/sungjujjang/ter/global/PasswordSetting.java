package com.sungjujjang.ter.global;

import jakarta.validation.constraints.Null;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordSetting {
    public String encode(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }

    public Boolean check(String plain, String hashed) {
        return BCrypt.checkpw(plain, hashed);
    }

    public Boolean compatibilityCheck(String Plain) {
        if (!(8 <= Plain.length() && Plain.length() <= 16)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
