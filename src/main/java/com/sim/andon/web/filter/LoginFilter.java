package com.sim.andon.web.filter;

import com.sim.andon.web.base.AbstractBaseFilter;
import com.sim.andon.web.entity.Persons;
import com.sim.andon.web.utils.Constants;
import com.sim.andon.web.utils.RedisUtils;
import com.sim.andon.web.utils.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录过滤器
 */
public class LoginFilter extends AbstractBaseFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5848533774295974270L;

	private RedisUtils redisUtils;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)) {
			throw new ServletException("OncePerRequestFilter just supports HTTP requests");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		HttpSession session = httpRequest.getSession(true);

		// 将客户端ip放入session用于sessionListener获取
		// SessionUtils.getInstance().putUserInSession(ConstantUtils.USER_IN_SESSION,
		// session);

		// 项目根路径
		String projPath = httpRequest.getContextPath();

		// 请求路径
		String url = httpRequest.getRequestURI();

		// 请求路径扩展名
		String extention = "";

		if (url.contains(".")) {
			extention = url.substring(url.lastIndexOf("."));
		}
		// this.logger.debug("登录过滤器，[prjPath:" + projPath + "]");
		// this.logger.debug("登录过滤器，[url:" + url + "]");
		// this.logger.debug("登录过滤器，[extention:" + extention + "]");

		Persons user = SessionUtils.getInstance().getUserInSession(session);

		// pad登录token
		String tokenParam = httpRequest.getParameter("token");

		// 缓存中的token
		String tokenInCache = null;
		if (StringUtils.isNotEmpty(tokenParam)) {
			tokenInCache = redisUtils.getString(Constants.LOGIN_TOKEN_KEY_PREFIX + tokenParam);

			/*
			 * if(StringUtils.isNotEmpty(tokenInCache)) { // 重新设置token生命周期
			 * redisUtils.setString(Constants.LOGIN_TOKEN_KEY_IN_REDIS +
			 * tokenInCache, tokenInCache, Constants.LOGIN_TOKEN_TIMEOUT);
			 * 
			 * }
			 */
		}

		// pad登录是否验证通过
		boolean isTokenPass = false;

		if (StringUtils.isNotEmpty(tokenParam) && StringUtils.isNotEmpty(tokenInCache)
				&& tokenInCache.equals(tokenParam)) {
			isTokenPass = true;
		}

		// 已登录 或 请求路径为除jsp文件以外资源文件路径 或 请求路径是白名单，放行
		if (isTokenPass || user != null || (!extention.equals("") && (!extention.equals(".jsp")))// 除jsp文件以外资源文件路径
		// if (user != null || (!extention.equals(""))// 资源文件路径
				|| url.endsWith(projPath + "/")// 项目根路径
				|| url.endsWith("toLogin")// 登录页controller
				|| url.endsWith("persons/index")// 登录页controller
				|| url.endsWith("login.jsp")// 登录页controller
				|| url.indexOf("mobile") > 0// APP访问
				|| url.endsWith("login")// APP访问
				|| url.endsWith("swagger-ui.html")// APP访问
				|| url.endsWith("/v2/api-docs")// APP访问
				|| url.endsWith("pad/productionLine/queryAllList")// pad查询产线
				|| url.endsWith("pad/queryAllList")// pad查询部门
				|| url.endsWith("pad/queryAllTrackList")// pad查询工位
				|| url.endsWith("pad/queryAllMachinesList")// pad查询设备
				|| url.endsWith("pad/base64")// pad上传图片
				|| url.endsWith("watch/grab")// 手表测试
				|| url.endsWith("watch/cancelOrder")// 取消抢单
				|| url.endsWith("watch/logout")// 手表测试
				|| url.endsWith("watch/getRepairTimeOut")// 查询维修人员的阈值
				|| url.endsWith("watch/getLeaderTimeOut")// 查询线长的阈值
				|| url.endsWith("watch/leaderConfirm")// 线长确认
				//|| url.contains("/bigScr/")// 大屏主页
				//|| url.contains("/webScr/")// 大屏主页
				|| url.contains("/statistics/")// 大屏jsp根路径
				|| url.contains("/projectOpl/")// projectOpl
				|| url.contains("/idcOpl/")// idcOpl
				|| url.contains("/pivotTable/")// idc 统计
				|| url.contains("/productionLine/queryAllList")// productionLine
				|| url.contains("/track/queryAllList")// track
				|| url.contains("/machineList/queryAllList")// machineList
				//|| url.contains("/opl/")// opl
				|| url.contains("/internalProjects/")// 大屏jsp根路径
				//|| url.contains("/web/")// 大屏jsp根路径
				//|| url.contains("/persons/")// 大屏人员信息查询
				|| url.contains("/pad/login")// pad登录
				|| url.contains("/pad/persons/queryList")// 负责人列表
				|| url.contains("/pad/echart")// pad 柱状图
				|| url.contains("/pad/planOff")// pad 放班相关
				|| url.contains("/planOff/")// pad 放班相关
				//|| url.contains("/pad/")// pad 放班相关
				|| url.contains(projPath + "/pad/audit")// 分层审核的请求
				|| url.contains(projPath + "/companyIntroduction/getCompanyIntroductionType")// 分层审核的请求
				|| url.endsWith("/padV2/andon/autoAlarm") //mes报警接口
				|| url.endsWith("/padV2") // pad版本v2，pad web端业务
				|| url.endsWith("/padV2/doLogin")
				|| url.endsWith("/andon/getPushUser")
				|| url.contains("/error") // 错误页
				|| url.contains("/test")
				|| url.contains("timeOut")// 登录超时
		// || url.endsWith("getErrorCodeListByDate")// 登录页controller

		) {
			// this.logger.debug("--pass--放行请求，[url:" + url + "]");
			filterChain.doFilter(httpRequest, httpResponse);

			// 重新设置超时时间
			// mu.resetLoginTimeOut(httpRequest.getSession());
			
		} else {// 未登录 并且 请求地址不是白名单

			String returnUrl = projPath + "/persons/toLogin";

			if(url.contains("/pad")) {
				returnUrl = projPath + "/padV2";
			}
			httpRequest.setCharacterEncoding("UTF-8");
			httpResponse.setContentType("text/html; charset=UTF-8"); // 转码
			httpResponse.getWriter()
					.println("<script language=\"javascript\">if(window.opener==null){window.top.location.href=\""
							+ returnUrl + "\";}else{window.opener.top.location.href=\"" + returnUrl
							+ "\";window.close();}</script>");
			
			// ajax请求
			if (isAjaxRequest(httpRequest)) {
//				httpRequest.setCharacterEncoding("UTF-8");
//				httpResponse.setContentType("application/json; charset=UTF-8"); // 转码
//				httpResponse.getWriter().println("{\"success\":\"false\",\"msg\":\"登录超时，请重新登录\",\"code\":\"103\"}");
				if(url.contains("/pad")) {
					returnUrl = projPath + "/padV2";
				}
				httpResponse.setHeader("sessionstatus", "timeout");
				httpResponse.setHeader("projPath", returnUrl);
				filterChain.doFilter(httpRequest, httpResponse);
				//return;
			} //else {// 非ajax请求
				// this.logger.debug("--XXX--拦截请求--XXX--，[returnUrl:" +
				// returnUrl + "]");
				
				return;
			//}
		}

	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		// return request.getRequestURI().startsWith("/api");
		String requestType = request.getHeader("X-Requested-With");
		return requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest");
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		redisUtils = (RedisUtils) ctx.getBean("redisUtils");
	}

	@Override
	public void destroy() {

	}
}
