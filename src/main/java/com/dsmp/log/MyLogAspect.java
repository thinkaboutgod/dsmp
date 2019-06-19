package com.dsmp.log;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.dsmp.mapper.TbLogMapper;
import com.dsmp.pojo.TbLog;
import com.dsmp.pojo.TbManager;
import com.dsmp.pojo.TbStudent;

@Component
@Aspect
@EnableAspectJAutoProxy
public class MyLogAspect {
	@Autowired
	private HttpSession session;
	@Autowired
	private TbLogMapper tbLogMapper;

	@AfterReturning("execution(* com.dsmp.service..*(..))&& @annotation(com.dsmp.log.MyLog) ")

//	 @AfterReturning("execution(* com.dsmp.service.PlateformService.* (..))&& @annotation(com.dsmp.log.MyLog) ")
	public void insertLog(JoinPoint joinPoint) throws NoSuchMethodException, SecurityException {
		TbManager tbManager = null;// 管理员对象
		String logOperatoraccount = "";//操作人账号
		Object[] objects = joinPoint.getArgs();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		MyLog myLog = method.getAnnotation(MyLog.class);// 获取方法的注解
		String methodName = method.getName();
		switch (methodName) {
		case "changeStudentState":
			logOperatoraccount = ((TbManager) session.getAttribute("manager")).getManAccount();
			String state = ((HttpServletRequest) objects[0]).getParameter("state");
			String stuId = ((HttpServletRequest) objects[0]).getParameter("stuId");
			tbLogMapper.insertSelective(new TbLog("账号" + state + ",学员id:" + stuId, logOperatoraccount, "管理员"));
			
			break;
		case "deletVideoByVidId":
			logOperatoraccount = ((TbManager) session.getAttribute("manager")).getManAccount();
			tbLogMapper.insertSelective(new TbLog("删除视频", logOperatoraccount, "管理员"));

			break;
		case "":

			break;

		default:
			break;
		}
	}

	 @AfterReturning("execution(* com.soft.controller..*getMainPage(..))&& @annotation(com.dsmp.log.MyLog) ")
		public void insertMoneyLog(JoinPoint joinPoint) throws NoSuchMethodException, SecurityException {
			TbManager tbManager = null;// 管理员对象
			String logOperatoraccount = "";//操作人账号
			Object[] objects = joinPoint.getArgs();
			MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
			Method method = methodSignature.getMethod();
			MyLog myLog = method.getAnnotation(MyLog.class);// 获取方法的注解
			String methodName = method.getName();
			if ("getMainPage".equals(methodName)) {
				logOperatoraccount = ((TbStudent) session.getAttribute("student")).getStuAccount();
				String schId = ((TbStudent) session.getAttribute("student")).getSchId().toString();
				String money = ((HttpServletRequest) objects[0]).getParameter("total_amount");//付款金额
				tbLogMapper.insertSelective(new TbLog("学员报名，报名驾校id:"+schId+"，付款金额："+money+"元", logOperatoraccount, "学员"));
			}
			
	 }
}
