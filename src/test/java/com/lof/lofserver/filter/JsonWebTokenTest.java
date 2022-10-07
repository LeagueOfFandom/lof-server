package com.lof.lofserver.filter;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class JsonWebTokenTest {

    @InjectMocks
    private JsonWebToken jsonWebToken;

    @Test
    @DisplayName("JWT 생성 테스트")
    void createJsonWebToken() {
        //given
        Long id = 1L;
        ReflectionTestUtils.setField(jsonWebToken, "secretKey", "secretKey");

        //when
        String token = jsonWebToken.createJsonWebTokenById(id);
        Claims claims = jsonWebToken.getClaimsByToken(token);

        //then
        assertNotNull(token);
        assertThat(claims.get("id", Long.class)).isEqualTo(id);
    }
}