/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class DownloadFiles {

    private String destination = "/opt/web/Hr/";

    /** Creates a new instance of DownloadFiles */
    public DownloadFiles() {
    }
    private static final long serialVersionUID = 1L;

    /**
     * Download file.
     */
    public void downloadFile(String fileName) throws IOException {
        HttpServletResponse response =
                (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        try {
            OutputStream out = response.getOutputStream();
            InputStream in = new FileInputStream(new File(destination + fileName));
            if (in == null) {
                out.close();
            } else {
                byte[] buffer = new byte[4096];
                int len;

                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }

                out.flush();
                in.close();
                out.close();
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public String downloadMedicalFile() {
        try {
            downloadFile("medical_insurance.xls");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String downloadPhoneListFile() {
        try {
            downloadFile("phone_list.xls");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
