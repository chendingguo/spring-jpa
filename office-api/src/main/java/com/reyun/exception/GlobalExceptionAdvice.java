package com.reyun.exception;

import com.reyun.framework.model.ResultModel;
import com.reyun.framework.model.ResultStatus;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nolan on 18/11/2016.
 * description:
 */

public class GlobalExceptionAdvice {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResultModel> handleUnauthorizedException(UnauthorizedException ex) {
        logger.error("handleUnauthorizedException......", ex);
        return new ResponseEntity<ResultModel>(ResultModel.ERROR(ResultStatus.NO_AUTH), HttpStatus.OK);
    }

}
