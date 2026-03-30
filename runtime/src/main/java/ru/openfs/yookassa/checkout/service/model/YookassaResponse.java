package ru.openfs.yookassa.checkout.service.model;

public record YookassaResponse<T>(
        boolean success,
        String error,
        T data
) {
    public YookassaResponse(T data) {
        this(true, null, data);
    }

    public YookassaResponse(String error) {
        this(false, error, null);
    }

    public static <T> YookassaResponse<T> success(T data) {
        return new YookassaResponse<>(data);
    }

    public static <T> YookassaResponse<T> error(String error) {
        return new YookassaResponse<>(error);
    }
}
