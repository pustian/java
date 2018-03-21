package tian.pusen.java.excel.common.exception;

import tian.pusen.java.excel.common.enums.CheckExceptionEnum;

/**
 * Created by tianpusen on 2017/3/21.
 */
public class CheckException extends RuntimeException{
    CheckExceptionEnum checkExceptionEnum;
    public CheckException(CheckExceptionEnum checkExceptionEnum){
        super(checkExceptionEnum.getDescription() );
    }
    public CheckException(CheckExceptionEnum checkExceptionEnum, Throwable cause){
        super(checkExceptionEnum.getDescription() , cause);
    }

}
