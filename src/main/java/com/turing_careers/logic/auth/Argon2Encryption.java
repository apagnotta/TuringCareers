package com.turing_careers.logic.auth;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

/**
 * @author Antonino Lorenzo
 * */
public class Argon2Encryption implements EncryptionStrategy {

    private static Argon2PasswordEncoder encoder;

    public Argon2Encryption() {
        if (encoder == null) {
            // default parameters
            int saltLength =  16; // Length of the salt
            int hashLength =  32; // Length of the hash
            int parallelism =  4; // Number of threads to use for hashing
            int memory =  65536; // Memory cost in kilobytes
            int iterations =  3; // Number of iterations

            encoder = new Argon2PasswordEncoder(
                    saltLength,
                    hashLength,
                    parallelism,
                    memory,
                    iterations
            );
        }
    }

    @Override
    public String encrypt(String str) {
        return encoder.encode(str);
    }

    @Override
    public void verify(String raw, String original) throws InvalidCredentialsException {
        if (!encoder.matches(raw, original))
            throw new InvalidCredentialsException();
    }
}


