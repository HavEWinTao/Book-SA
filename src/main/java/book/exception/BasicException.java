package book.exception;

import lombok.Data;

/**
 * @author wangwei
 * @version 1.0.0
 * @date 2021/9/29 8:47
 * @description: TODO
 */
@Data
public class BasicException extends RuntimeException {

    private int code;
    private String msg;

    public BasicException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
