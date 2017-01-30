package com.example.common;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public final class MultipartFileTool {

	public static class NotMultipartRequestException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6914411669766632049L;

		public NotMultipartRequestException(HttpServletRequest request) {
			super(request + " is not a multipart request");
		}

	}

	public static class InvalidUploadedFileNameException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2866317267206404026L;

		public InvalidUploadedFileNameException(String fileName) {
			super(fileName + " contains invalid characters");
		}

	}

	static final String DEFAULT_FLAG = ".";
	static final String DEFAULT_PREFIX_BEFORE_TIMESTAMP = "-";

	private static final Logger logger = Logger.getLogger(MultipartFileTool.class);

	private MultipartFileTool() {
	}

	public static List<File> saveFiles(HttpServletRequest request, String dirPath)
			throws IOException {
		File dir = new File(dirPath);
		return saveFiles(request, dir);
	}

	public static List<File> saveFilesWithTimestamp(HttpServletRequest request, String dirPath)
			throws IOException {
		File dir = new File(dirPath);
		return saveFilesWithTimestamp(request, dir);
	}

	public static List<File> saveFiles(HttpServletRequest request, File dir) throws IOException {
		return saveFilesWithTimestamp(request, dir);
	}

	public static List<File> saveFilesWithTimestamp(HttpServletRequest request, File dir)
			throws IOException {
		boolean isMultipart = isMultipartRequest(request);
		if (!isMultipart) {
			throw new NotMultipartRequestException(request);
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		return saveFilesWithTimestamp(multipartRequest, dir, new DateTime());
	}

	// -------------------------------------------------------------------------

	public static List<File> saveFiles(MultipartHttpServletRequest request, String dirPath)
			throws IOException {
		File dir = new File(dirPath);
		return saveFilesWithTimestamp(request, dir, null);
	}

	public static List<File> saveFiles(MultipartHttpServletRequest request, File dir)
			throws IOException {
		return saveFilesWithTimestamp(request, dir, null);
	}

	public static List<File> saveFilesWithTimestamp(MultipartHttpServletRequest request, File dir)
			throws IOException {
		return saveFilesWithTimestamp(request, dir, new DateTime());
	}

	public static List<File> saveFilesWithTimestamp(MultipartHttpServletRequest request, File dir, DateTime dateTime)
			throws IOException {
		// validate original files' names
		List<File> ret = Lists.newArrayList();
		Iterator<String> iterator = request.getFileNames();
		Set<String> fileNames = Sets.newHashSet();
		Set<String> set = Sets.newHashSet();
		while (iterator.hasNext()) {
			String fileName = iterator.next();
			fileNames.add(fileName);
			List<MultipartFile> files = request.getFiles(fileName);
			for (MultipartFile file : files) {
				String originalFileName = file.getOriginalFilename();
				set.add(originalFileName);
			}
		}
		checkUploadedFileNames(set);
		if (!dir.exists()) {
			// create the directories if necessary
			boolean result = dir.mkdirs();
			logger.debug("create directory '" + dir.getAbsolutePath() + "' ok? " + result);
			if (!result) {
				return Lists.newArrayList();
			}
		}
		// save the uploaded files
		for (String fileName : fileNames) {
			logger.debug("processing '" + fileName + "'");
			List<MultipartFile> files = request.getFiles(fileName);
			for (MultipartFile file : files) {
				String originalFileName = file.getOriginalFilename();
				logger.debug("original file name: " + originalFileName);
				// 上传
				String savedPath = Joiner.on(System.getProperty("file.separator")).join(dir.getAbsolutePath(),
						originalFileName);
				logger.debug("save to " + savedPath);
				File saved = new File(savedPath);
				if (dateTime != null) {
					saved = addTimestamp(saved, DEFAULT_FLAG, DEFAULT_PREFIX_BEFORE_TIMESTAMP, dateTime);
				}
				file.transferTo(saved);
				ret.add(saved);
			}
		}
		return ret;
	}

	static File addTimestamp(File file, String flag, String prefixBeforeTimestamp, DateTime dateTime) {
		String path = file.getAbsolutePath();
		int index = path.lastIndexOf(flag);
		if (index == -1) {
			index = path.length() - 1;
		}
		String pre = path.substring(0, index);
		String post = path.substring(index);
		StringBuilder sb = new StringBuilder();
		sb.append(pre);
		sb.append(prefixBeforeTimestamp);
		sb.append(dateTime.getMillis());
		sb.append(post);
		String newPath = sb.toString();
		return new File(newPath);
	}

	static void checkUploadedFileNames(Collection<String> fileNames) {
		for (String e : fileNames) {
			boolean result = isValidUploadedFileName(e);
			logger.debug("uploaded file's name '" + e + "' is valid? " + result);
			if (!result) {
				throw new InvalidUploadedFileNameException(e);
			}
		}
	}

	static boolean isValidUploadedFileName(String str) {
		if (Strings.isNullOrEmpty(str)) {
			return false;
		}
		String fileName = str.trim();
		if (Strings.isNullOrEmpty(fileName)) {
			return false;
		}
		if (fileName.startsWith(".") || fileName.contains("/") || fileName.contains("\\")) {
			return false;
		}
		return true;
	}

	static boolean isMultipartRequest(HttpServletRequest request) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		return multipartResolver.isMultipart(request);
	}

}
