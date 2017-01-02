package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static final String UPLOAD_PATH = "/upimgs";
    // List<String> picNameList=new ArrayList<String>();
    String fileName = "";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @SuppressWarnings({ "unchecked" })
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String path = request.getServletContext().getRealPath(UPLOAD_PATH);

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        sfu.setHeaderEncoding("UTF-8");
        sfu.setSizeMax(2 * 1024 * 1024); // 限制文件大小

        try {
            List<FileItem> fileItems = sfu.parseRequest(request); // 解码请求 得到所有表单元素
            for (FileItem fi : fileItems) {
                if (fi.isFormField()) { // 这个选项是 文字
                    System.out.println("表单值为：" + fi.getString());
                } else {
                    // 是文件
                    String fn = fi.getName();
                    System.out.println("文件名是：" + fn); // 文件名
                    // fn 是可能是这样的 c:\abc\de\tt\fish.jpg
                    if (fn.endsWith(".jpg") || fn.endsWith(".png") || fn.endsWith(".gif")) {
                        fi.write(new File(path, fn));
                        fileName = fn;
                    }
                }
            }
        } catch (Exception e) {

        }
        ServletOutputStream out = response.getOutputStream();
        if (StringUtils.isEmpty(path)) {
            path = request.getServletContext().getRealPath("/images") + "/default.png";
        } else {
            path = request.getContextPath()+"/" + UPLOAD_PATH + "/" + fileName;
        }
        try {
            JSONObject json = new JSONObject();
            json.put("error", 0);
            json.put("url", path);
            out.print(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
    }

}
