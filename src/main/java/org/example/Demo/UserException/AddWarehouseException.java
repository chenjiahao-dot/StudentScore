package org.example.Demo.UserException;

public class AddWarehouseException extends RuntimeException {
    public AddWarehouseException() {
        super("仓库新增异常");
    }
    public AddWarehouseException(String msg) {
        super(msg);
    }
}
