package fileupload;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/13FileUpload/UploadProcess2.do")
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 1,
	maxRequestSize = 1024 * 1024 * 10
)

public class UploadProcess2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println();
		System.out.println("=".repeat(80));
		System.out.println("POST HTTP/1.1");
		
		String bnd = "";
		Enumeration<String> headerNames = req.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String h = headerNames.nextElement();
			String v = req.getHeader(h);
			System.out.println(h + ":" + v);
			if (h.equals("content-type")&& v.contains("boundary=")){
				String arr[] = v.split("boundary=");
				bnd = arr[1];
			}
		}
		System.out.println();
		
		Collection<Part> col = req.getParts();
		for(Part p : col) {
			System.out.println(bnd);
			System.out.println(p.getName());
			System.out.println();
			System.out.println(new String(p.getInputStream().readAllBytes()));
		}
		
	}
	
	
	
}
