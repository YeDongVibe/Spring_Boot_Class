package edu.pnu.exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 전역 예외처리
public class GlobalExceptionHandler {
	@ExceptionHandler(BoardException.class)
	public String handleCustomExcption(BoardException exception, Model model) {
		model.addAttribute("exception", exception); //model에 exception객체 전체를 전달
		return "/errors/boardError"; //templte의 boarderror를 호출
	}
	@ExceptionHandler(UsernameNotFoundException.class)
	public String handleCustomExcption1(Exception exception, Model model) {
		model.addAttribute("exception", exception); //model에 exception객체 전체를 전달
		return "/errors/loginError"; //templte의 boarderror를 호출
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/globalError";
	}
	
}
