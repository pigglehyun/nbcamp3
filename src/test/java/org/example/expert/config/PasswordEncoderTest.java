package org.example.expert.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class PasswordEncoderTest {

    @InjectMocks
    private PasswordEncoder passwordEncoder;

    @Test
    void matches_메서드가_정상적으로_동작한다() {
        // given
        String rawPassword = "testPassword";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        /**
         * public boolean matches (String rawPassword, String encodedPassword)
         * -> 첫 번째 인자가 raw, 두 번째 인자가 encoded임.
         */
        // when
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword );

        // then
        assertTrue(matches);
    }
}
