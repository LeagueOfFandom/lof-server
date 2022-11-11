

package com.lof.lofserver.controller.user.response;

public record UserInfoResponse(String jwtToken, Boolean isNewUser) {
}