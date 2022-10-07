package com.lof.lofserver.controller.user.request;

public record UserInfo(String email, String name, String picture, String fcmToken) {
}
