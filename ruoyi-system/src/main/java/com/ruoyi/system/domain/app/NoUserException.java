package com.ruoyi.system.domain.app;

/**
 * @author ming
 * @ClassName: NoUserException
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/23 18:40
 */
public class NoUserException extends Exception{

    public NoUserException() {
    }

    public NoUserException(String message) {
        super(message);
    }
}
