package com.xxkj.exceptions;



public class RepeatKillException extends SeckillException{
	public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
