package com.lof.lofserver.controller.user.request;

import javax.validation.constraints.NotNull;

public record UserInfoRequest(@NotNull String email, String name, String picture, String fcmToken) {
}
