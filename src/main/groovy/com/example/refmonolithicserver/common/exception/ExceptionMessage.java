package com.example.refmonolithicserver.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage{

    UNDEFINED_ERROR("정의되지 않은 에러가 발생했습니다"),

    INGREDIENT_NOT_FOUND("재료 ID에 대응되는 재료가 존재하지 않습니다"),
    FOOD_NOT_FOUND("음식 ID에 대응되는 음식이 존재하지 않습니다"),
    RECIPE_NOT_FOUND("레시피 ID에 대응되는 레시피가 존재하지 않습니다"),
    RECIPE_ALREADY_EXIST("해당하는 레시피가 이미 존재합니다"),
    SETTLEMENT_NOT_FOUND("날짜에 대응되는 정산 정보가 존재하지 않습니다"),

    USER_NOT_FOUND("유저가 존재하지 않습니다"),
    USER_EMAIL_DUPLICATED("유저 이메일이 중복됩니다"),
    USER_ID_DUPLICATED("유저 아이디가 중복됩니다"),
    USER_PASSWORD_INVALID("유저 비밀번호가 유효하지 않습니다");


    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}