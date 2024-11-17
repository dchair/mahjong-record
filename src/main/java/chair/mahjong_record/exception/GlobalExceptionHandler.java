package chair.mahjong_record.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

//全局例外處理器
@ControllerAdvice

public class GlobalExceptionHandler {
    //註解表明handleResponseStatusException 方法會處理 ResponseStatusException 類型的例外。
    @ExceptionHandler(ResponseStatusException.class)
    //此方法會在 ResponseStatusException 被拋出時執行。方法接收 ResponseStatusException 物件 ex 作為參數，用於取得例外的詳細訊息（例如錯誤原因）。
    public ModelAndView handleResponseStatusException(ResponseStatusException ex){
        //將頁面導向error.html
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errormessage",ex.getReason());
        return modelAndView;
    }
}
