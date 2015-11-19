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
import com.example.persist.must.FileRMapper;
import com.example.persist.must.SdCardOrderWMapper;
import com.example.webapi.RouteDefine;
import com.google.common.base.Strings;

@Controller
public class WebGuiSdCardOrderController {

	static final String FILE_ID = "fileId";
	static final String UUID = "uuid";
	static final String FILE_NAME = "fileName";
	static final String PRICE = "price";

	static final String VIEW_NAME_PREFIX = "sd-card-order/";
	static final String VIEW_NAME_LIST = VIEW_NAME_PREFIX + WebGuiDefine.LIST;
	static final String VIEW_NAME_NEW = VIEW_NAME_PREFIX + WebGuiDefine.NEW;

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private FileRMapper fileRMapper;
	@Autowired
	private SdCardOrderWMapper sdCardOrderWMapper;

	@RequestMapping(value = RouteDefine.I_SD_CARD_ORDERS, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_LIST);
	}

	@RequestMapping(value = RouteDefine.I_SD_CARD_ORDERS_NEW, method = RequestMethod.GET)
	public ModelAndView gotoNew(HttpServletRequest request,
			HttpServletResponse response) {
		String fileIdStr = request.getParameter(FILE_ID);
		if (Strings.isNullOrEmpty(fileIdStr)) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		final long fileId = Long.parseLong(fileIdStr);
		File file = fileRMapper.selectById(fileId);
		if (file == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig,
				VIEW_NAME_NEW);
		ret.getModel().put(FILE_ID, fileIdStr);
		ret.getModel().put(UUID, UuidTool.newUuid());
		ret.getModel().put(FILE_NAME, file.getName());
		Double price = new Double(file.getSdCardPriceFen());
		;
		price /= 100;
		ret.getModel().put(PRICE, price);
		return ret;
	}

	@RequestMapping(value = RouteDefine.I_SD_CARD_ORDERS, method = RequestMethod.POST)
	public ModelAndView newOne(HttpServletRequest request,
			HttpServletResponse response) {
		String fileIdStr = request.getParameter(FILE_ID);
		if (Strings.isNullOrEmpty(fileIdStr)) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		String uuid = request.getParameter(UUID);
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
		return ModelAndViewTool.newModelAndViewAndRedirect(appConfig,
				RouteDefine.I_SD_CARD_ORDERS);
	}

}
