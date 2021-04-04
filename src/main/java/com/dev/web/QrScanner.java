package com.dev.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Paths;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QrScanner extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String data = request.getParameter("URL");
		String path = request.getParameter("path") + "image.jpg";
		
		BitMatrix matrix;
		try {
			matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
			MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
			
			System.out.println("done");
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("index.jsp");
	}

}
