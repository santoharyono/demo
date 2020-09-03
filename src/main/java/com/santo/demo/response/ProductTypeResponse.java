package com.santo.demo.response;

public class ProductTypeResponse<T> {
    private String message;

    private T body;

    public ProductTypeResponse() {
    }

    public ProductTypeResponse(T body) {
        this.body = body;
    }

    /**
     * Getter method for property message.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for property message.
     *
     * @param message value to be assigned to property message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter method for property body.
     *
     * @return property value of body
     */
    public T getBody() {
        return body;
    }

    /**
     * Setter method for property body.
     *
     * @param body value to be assigned to property body
     */
    public void setBody(T body) {
        this.body = body;
    }
}
