package com.example.demo.controller;

import com.example.demo.json.UploadFileCate;
import com.example.demo.scope.ResizeImg;
import com.example.demo.scope.SessionUser;
import com.google.gson.Gson;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

@WebServlet({ "/UploadFile" })
public class UploadFile<E> extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SessionUser su = new SessionUser();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("cate"));
		if (request.getParameter("cate") != null) {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {

				DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

				ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);

				try {
					List items = upload.parseRequest(request);
					Iterator iterator = items.iterator();
					while (iterator.hasNext()) {
						FileItem item = (FileItem) iterator.next();
						if (!item.isFormField()) {
							String fileName = item.getName();
							String root = getServletContext().getRealPath("/");

							File path = new File(String.valueOf(root) + "/temp");
							if (!path.exists())
								path.mkdirs();
							path = new File(String.valueOf(root) + "/temp/img");
							if (!path.exists()) {
								path.mkdirs();
							}
							String fName = SessionUser.generateRandomString();
							File uploadedFile = new File(path + "/" + fName + getFileExtension(item));

							BufferedInputStream is = new BufferedInputStream(item.getInputStream());
							BufferedImage image = ImageIO.read(is);

							response.setContentType("text/html");
							response.setCharacterEncoding("utf-8");
							PrintWriter out = response.getWriter();

							JSONObject json = new JSONObject();
							item.write(uploadedFile);
							json.put("url",
									(new Gson()).toJson((new UploadFileCate()).addUrlImage(request,
											/* 91 */ String.valueOf(fName) + getFileExtension(item),
											request.getParameter("cate"))));

							out.print(json);
						}

					}

				} catch (FileUploadException fileUploadException) {

				} catch (Exception ex) {
					Logger.getLogger(UploadFile.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	public static boolean saveImgInFolder(HttpServletRequest request, String image_name) {
		try {
			String root = request.getServletContext().getRealPath("/");
			return ResizeImg.imgsizeConvertor(root, image_name);
		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	private String getFileExtension(FileItem file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return "." + fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		return "";
	}
}
