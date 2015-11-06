package com.example.webgui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.HttpServletResponseUtil;
import com.example.common.ModelAndViewTool;
import com.example.common.UuidTool;
import com.example.config.AppConfig;
import com.example.domain.File;
import com.example.domain.SdCardOrder;
import com.example.filter.LoginInterceptor;
import com.example.persist.rdbms.FileRMapper;
import com.example.persist.rdbms.SdCardOrderWMapper;
import com.example.webapi.RouteDefine;
import com.google.common.base.Strings;

@Controller
public class WebGuiSdCardOrderController {

	static final String SD_CARD_ORDER = "sd_card_order/";
	static final String SD_CARD_ORDER_LIST = SD_CARD_ORDER
			+ "sd_card_order_list";
	static final String SD_CARD_ORDER_NEW = SD_CARD_ORDER + "sd_card_order_new";

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private FileRMapper fileRMapper;
	@Autowired
	private SdCardOrderWMapper sdCardOrderWMapper;

	@RequestMapping(value = RouteDefine.I_SD_CARD_ORDERS, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, SD_CARD_ORDER_LIST);
	}

	@RequestMapping(value = RouteDefine.I_SD_CARD_ORDERS_NEW, method = RequestMethod.GET)
	public ModelAndView gotoNewOrderPage(HttpServletRequest request,
			HttpServletResponse response) {
		String fileIdStr = request.getParameter("fileId");
		if (Strings.isNullOrEmpty(fileIdStr)) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		final long fileId = Long.parseLong(fileIdStr);
		File file = fileRMapper.selectById(fileId);
		if (file == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, SD_CARD_ORDER_NEW);
		ret.getModel().put("fileId", fileIdStr);
		ret.getModel().put("uuid", UuidTool.newUuid());
		ret.getModel().put("fileName", file.getName());
		ret.getModel().put("price", file.getSdCardPriceFen() / 100);
		return ret;
	}

	@RequestMapping(value = RouteDefine.I_SD_CARD_ORDERS, method = RequestMethod.POST)
	public ModelAndView newOrder(HttpServletRequest request,
			HttpServletResponse response) {
		String fileIdStr = request.getParameter("fileId");
		if (Strings.isNullOrEmpty(fileIdStr)) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		String uuid = request.getParameter("uuid");
		if (Strings.isNullOrEmpty(uuid)) {
			HttpServletResponseUtil.setStatusAsNotFound(response);
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		final long fileId = Long.parseLong(fileIdStr);
		File file = fileRMapper.selectById(fileId);
		if (file == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		uuid = uuid.toLowerCase();
		SdCardOrder order = new SdCardOrder();
		order.setUuid(uuid);
		order.reset();
		order.setFileId(Long.parseLong(fileIdStr));
		order.setPriceFen(file.getSdCardPriceFen());
		order.setUserId(LoginInterceptor.getAccountId(request));
		sdCardOrderWMapper.insert(order);
		return ModelAndViewTool.newModelAndView(appConfig, SD_CARD_ORDER_LIST);
	}

}
