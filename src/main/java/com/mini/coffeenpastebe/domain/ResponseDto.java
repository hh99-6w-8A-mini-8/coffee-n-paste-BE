package com.mini.coffeenpastebe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseDto<T> {
    private T data;

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(data);
    }

    public static <T> ResponseDto<T> fail(T data) {
        return new ResponseDto<>(data);
    }
}
