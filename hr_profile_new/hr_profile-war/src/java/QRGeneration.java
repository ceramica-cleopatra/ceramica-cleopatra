/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.CrmkBranch;
import e.CrmkColor;
import e.CrmkCrmkDekala;
import e.CrmkCrmkSize;
import e.CrmkCrmkType;
import e.CrmkDcreDekala;
import e.CrmkDcreSize;
import e.CrmkDcreType;
import e.CrmkSehyGrp;
import e.CrmkSehyName;
import e.CrmkSehyType;
import e.HrEmpInfo;
import e.ShowBathHd;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class QRGeneration {

    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private List<String[]> qRList;
    private String crmkSehy = "C";
    private List<CrmkCrmkType> crmkTypes;
    private List<CrmkDcreType> dcreTypes;
    private List<SelectItem> typeList;
    private Long typeId;
    private List<CrmkCrmkSize> crmkSizes;
    private List<CrmkDcreSize> dcreSizes;
    private List<Object[]> sizeList;
    private Object[] selectedSize;
    private Object[] filteredSize;
    private List<CrmkBranch> showRoomList;
    private List<CrmkCrmkDekala> crmkDekalas;
    private List<CrmkDcreDekala> dcreDekalas;
    private List<Object[]> dekalaList;
    private Object[] selectedDekala;
    private Object[] filteredDekala;
    private CrmkBranch showroomSelected;
    private List<CrmkColor> dcreColors;
    private List<Object[]> colorList;
    private Object[] selectedColor;
    private Object[] filteredColor;
    private Long factoryNo;
    private String showType;
    private Long floor;
    private Long grpId;
    private List<SelectItem> bathList;
    private CrmkBranch currentShowroom;
    private String standNo;
    private Integer qrHeight;
    private Integer qrWidth;
    private Integer imageHeight;
    private Integer imageWidth;
    private Integer productFontSize;
    private Integer priceFontSize;
    private Integer productFarFromTop;
    private Integer priceFarFromBottom;
    private Long how2show;
    private List<CrmkSehyGrp> sehyGrpList;
    private CrmkSehyGrp grpSelected;
    private CrmkSehyGrp filteredGrp;
    private List<ShowBathHd> showBathdList;
    private ShowBathHd bathSelected;
    private ShowBathHd filteredBath;
    private List<CrmkSehyName> crmkSehyNameList;
    private List<CrmkSehyType> crmkSehyTypeList;
    private CrmkSehyName sehyNameSelected;
    private CrmkSehyName filteredSehyName;
    private CrmkSehyType sehyTypeSelected;
    private CrmkSehyType filteredSehyType;
    @PostConstruct
    public void init() {
        hrEmpInfo = (HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        crmkTypes = sessionBean.getCrmkTypes();
        dcreTypes = sessionBean.getDcreTypes();
        crmkSizes = sessionBean.getCrmkSizes();
        dcreSizes = sessionBean.getDcreSizes();
        crmkDekalas = sessionBean.getCrmkDekala();
        dcreDekalas = sessionBean.getDcreDekala();
        dcreColors = sessionBean.getColors();
        showBathdList = sessionBean.getShowBaths();
        bathList = new ArrayList<SelectItem>();
        for(ShowBathHd showBathHd : showBathdList){
                SelectItem selectItem = new SelectItem(showBathHd.getId(), showBathHd.getName()+(showBathHd.getColor() == null ? "" : " - "+showBathHd.getColor().getName()));
                bathList.add(selectItem);
        }
        showRoomList = sessionBean.getShow();
        typeList = new ArrayList<SelectItem>();
        typeList.add(new SelectItem(null, null));
        sizeList = new ArrayList<Object[]>();
        dekalaList = new ArrayList<Object[]>();
        colorList = new ArrayList<Object[]>();

        for(CrmkCrmkType crmkCrmkType : crmkTypes){
                SelectItem selectItem = new SelectItem(crmkCrmkType.getId(), crmkCrmkType.getName());
                typeList.add(selectItem);
        }
        for(CrmkCrmkSize crmkCrmkSize : crmkSizes){
            Object[] size = new Object[2];
            size[0] = crmkCrmkSize.getId();
            size[1] = crmkCrmkSize.getName();
            sizeList.add(size);
        }
        for(CrmkCrmkDekala crmkCrmkDekala : crmkDekalas){
            Object[] dekala = new Object[2];
            dekala[0] = crmkCrmkDekala.getId();
            dekala[1] = crmkCrmkDekala.getName();
            dekalaList.add(dekala);
        }

        sehyGrpList = sessionBean.findAllSehyGrp();
        crmkSehyNameList = sessionBean.getCrmkSehyNameList();
        crmkSehyTypeList = sessionBean.getCrmkSehyTypeList();
    }

    public String generateQRcode() {
        FacesContext fc = FacesContext.getCurrentInstance();
        qRList = new ArrayList<String[]>();
        listFilesForFolder(new File(callQRGenerationAPI()));
        return null;
    }

    private String callQRGenerationAPI() {
        try {
            String uri = "crmkSehy="+crmkSehy;
            if(typeId != null){
                uri += "&typeId="+typeId;
            }
            if(filteredSize != null && filteredSize[1] != null){
                uri += "&sizeId="+filteredSize[0];
            }
            if(filteredDekala != null && filteredDekala[1] != null){
                uri += "&dekalaId="+filteredDekala[0];
            }
            if(filteredColor != null && filteredColor[1] != null){
                uri += "&colorId="+filteredColor[0];
            }

            if(factoryNo != null){
                uri += "&factoryNo="+factoryNo;
            }

            if(showType != null){
                uri += "&showType="+showType;
            }

            if(bathSelected != null && bathSelected.getId() != null){
                uri += "&grpId="+bathSelected.getId();
            }

            if(floor != null){
                uri += "&floor="+floor;
            }

             if(showroomSelected != null && showroomSelected.getId() != null){
                uri += "&brnId="+showroomSelected.getId();
            }

            if(standNo != null){
                uri += "&standNo="+standNo.toUpperCase();
            }

            if(qrHeight != null){
                uri += "&height="+qrHeight;
            }

            if(qrWidth != null){
                uri += "&width="+qrWidth;
            }

            if(imageHeight != null){
                uri += "&imageHeight="+imageHeight;
            }

            if(imageWidth != null){
                uri += "&imageWidth="+imageWidth;
            }

            if(productFarFromTop != null){
                uri += "&productFarFromTop="+productFarFromTop;
            }

            if(priceFarFromBottom != null){
                uri += "&priceFarFromBottom="+priceFarFromBottom;
            }

            if(productFontSize != null){
                uri += "&productFontSize="+productFontSize;
            }

            if(priceFontSize != null){
                uri += "&priceFontSize="+priceFontSize;
            }

            if(how2show != null){
                uri += "&how2show="+how2show;
            }

            if(grpSelected != null && grpSelected.getId() != null){
                uri += "&sehyGrpId="+grpSelected.getId();
            }

            if(sehyNameSelected != null && sehyNameSelected.getId() != null){
                uri += "&nameId="+sehyNameSelected.getId();
            }

            if(crmkSehy!=null && crmkSehy.equals("S") && sehyTypeSelected != null && sehyTypeSelected.getId() != null){
                uri += "&typeId="+sehyTypeSelected.getId();
            }

            URL url = new URL("http://localhost:8080/qr/generateQR?"+uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            
            String output;
            while ((output = br.readLine()) != null) {
                break;
            }
            conn.disconnect();
            return output;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                if(!fileEntry.getName().equals(".zip")){
                    qRList.add(new String[]{fileEntry.getName(),"/opt/web/query/QR/" + folder.getName() + "/" + fileEntry.getName()});
                }
            }
        }
        if(folder.listFiles().length>0){
            qRList.add(0,new String[]{"CompressedFile.zip","/opt/web/query/QR/" + folder.getName() + "/" + ".zip"});
       }
    }


     public void downloadFile(String fileName,String fullFileName) throws IOException {
        HttpServletResponse response =
                (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("image/png; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" +fileName);
        try {
            OutputStream out = response.getOutputStream();
            InputStream in = new FileInputStream(new File(fullFileName));
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

    public void downloadQrFile() {
        try {
            int index = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("indx").toString());
            System.out.println(qRList.get(index)[0]);
            downloadFile(qRList.get(index)[0],qRList.get(index)[1]);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

     public void onCrmkSehyListChanged(ValueChangeEvent e) {
        typeList = new ArrayList<SelectItem>();
        typeList.add(new SelectItem(null, null));
        sizeList = new ArrayList<Object[]>();
        dekalaList = new ArrayList<Object[]>();
        colorList = new ArrayList<Object[]>();
        if (e.getNewValue().toString().equals("C")) {
            for(CrmkCrmkType crmkCrmkType : crmkTypes){
                SelectItem selectItem = new SelectItem(crmkCrmkType.getId(), crmkCrmkType.getName());
                typeList.add(selectItem);
            }
            for(CrmkCrmkSize crmkCrmkSize : crmkSizes){
                Object[] size = new Object[2];
                size[0] = crmkCrmkSize.getId();
                size[1] = crmkCrmkSize.getName();
                sizeList.add(size);
            }
            for(CrmkCrmkDekala crmkCrmkDekala : crmkDekalas){
                Object[] dekala = new Object[2];
                dekala[0] = crmkCrmkDekala.getId();
                dekala[1] = crmkCrmkDekala.getName();
                dekalaList.add(dekala);
            }
        } else if (e.getNewValue().toString().equals("D")) {
            for(CrmkDcreType crmkDcreType : dcreTypes){
                SelectItem selectItem = new SelectItem(crmkDcreType.getId(), crmkDcreType.getName());
                typeList.add(selectItem);
            }
            for(CrmkDcreSize crmkDcreSize : dcreSizes){
                Object[] size = new Object[2];
                size[0] = crmkDcreSize.getId();
                size[1] = crmkDcreSize.getName();
                sizeList.add(size);
            }
            for(CrmkDcreDekala crmkDcreDekala : dcreDekalas){
                Object[] dekala = new Object[2];
                dekala[0] = crmkDcreDekala.getId();
                dekala[1] = crmkDcreDekala.getName();
                dekalaList.add(dekala);
            }
            for(CrmkColor crmkColor : dcreColors){
                Object[] color = new Object[2];
                color[0] = crmkColor.getId();
                color[1] = crmkColor.getName();
                colorList.add(color);
            }
        }
    }

   public void updateSelectedSize() {
       System.out.println(selectedSize[1]);
        filteredSize = selectedSize;
    }

    public void updateSelectedDekala() {
       System.out.println(selectedDekala[1]);
        filteredDekala = selectedDekala;
    }

    public void updateSelectedColor() {
       System.out.println(selectedColor[1]);
        filteredColor = selectedColor;
    }

    public void updateShowroomName() {
        System.out.println(showroomSelected.getName());
        currentShowroom = showroomSelected;
    }

    public void updateGrpName() {
        System.out.println(grpSelected.getName());
        filteredGrp = grpSelected;
    }

    public void updateSehyName() {
        System.out.println(sehyNameSelected.getName());
        filteredSehyName = sehyNameSelected;
    }

    public void updateSehyType() {
        System.out.println(sehyTypeSelected.getName());
        filteredSehyType = sehyTypeSelected;
    }

     public void updateBathName() {
        System.out.println(bathSelected.getName());
        filteredBath = bathSelected;
    }

    /** Creates a new instance of ActivatePreviousDoc */
    public QRGeneration() {
    }

    public List<String[]> getqRList() {
        return qRList;
    }

    public void setqRList(List<String[]> qRList) {
        this.qRList = qRList;
    }

    public String getCrmkSehy() {
        return crmkSehy;
    }

    public void setCrmkSehy(String crmkSehy) {
        this.crmkSehy = crmkSehy;
    }

    public List<SelectItem> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<SelectItem> typeList) {
        this.typeList = typeList;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public List<Object[]> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<Object[]> sizeList) {
        this.sizeList = sizeList;
    }

    public Object[] getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(Object[] selectedSize) {
        this.selectedSize = selectedSize;
    }

    public Object[] getFilteredSize() {
        return filteredSize;
    }

    public void setFilteredSize(Object[] filteredSize) {
        this.filteredSize = filteredSize;
    }

    public List<CrmkCrmkDekala> getCrmkDekalas() {
        return crmkDekalas;
    }

    public void setCrmkDekalas(List<CrmkCrmkDekala> crmkDekalas) {
        this.crmkDekalas = crmkDekalas;
    }

    public List<CrmkDcreDekala> getDcreDekalas() {
        return dcreDekalas;
    }

    public void setDcreDekalas(List<CrmkDcreDekala> dcreDekalas) {
        this.dcreDekalas = dcreDekalas;
    }

    public List<Object[]> getDekalaList() {
        return dekalaList;
    }

    public void setDekalaList(List<Object[]> dekalaList) {
        this.dekalaList = dekalaList;
    }

    public Object[] getFilteredDekala() {
        return filteredDekala;
    }

    public void setFilteredDekala(Object[] filteredDekala) {
        this.filteredDekala = filteredDekala;
    }

    public Object[] getSelectedDekala() {
        return selectedDekala;
    }

    public void setSelectedDekala(Object[] selectedDekala) {
        this.selectedDekala = selectedDekala;
    }

    public List<Object[]> getColorList() {
        return colorList;
    }

    public void setColorList(List<Object[]> colorList) {
        this.colorList = colorList;
    }

    public List<CrmkColor> getDcreColors() {
        return dcreColors;
    }

    public void setDcreColors(List<CrmkColor> dcreColors) {
        this.dcreColors = dcreColors;
    }

    public Object[] getFilteredColor() {
        return filteredColor;
    }

    public void setFilteredColor(Object[] filteredColor) {
        this.filteredColor = filteredColor;
    }

    public Object[] getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Object[] selectedColor) {
        this.selectedColor = selectedColor;
    }

    public Long getFactoryNo() {
        return factoryNo;
    }

    public void setFactoryNo(Long factoryNo) {
        this.factoryNo = factoryNo;
    }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

    public Long getGrpId() {
        return grpId;
    }

    public void setGrpId(Long grpId) {
        this.grpId = grpId;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public List<SelectItem> getBathList() {
        return bathList;
    }

    public void setBathList(List<SelectItem> bathList) {
        this.bathList = bathList;
    }

    public List<CrmkBranch> getShowRoomList() {
        return showRoomList;
    }

    public void setShowRoomList(List<CrmkBranch> showRoomList) {
        this.showRoomList = showRoomList;
    }

    public CrmkBranch getShowroomSelected() {
        return showroomSelected;
    }

    public void setShowroomSelected(CrmkBranch showroomSelected) {
        this.showroomSelected = showroomSelected;
    }

    public String getStandNo() {
        return standNo;
    }

    public void setStandNo(String standNo) {
        this.standNo = standNo;
    }

    public Integer getQrHeight() {
        return qrHeight;
    }

    public void setQrHeight(Integer qrHeight) {
        this.qrHeight = qrHeight;
    }

    public Integer getQrWidth() {
        return qrWidth;
    }

    public void setQrWidth(Integer qrWidth) {
        this.qrWidth = qrWidth;
    }

    public Integer getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    public Integer getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    public Integer getPriceFarFromBottom() {
        return priceFarFromBottom;
    }

    public void setPriceFarFromBottom(Integer priceFarFromBottom) {
        this.priceFarFromBottom = priceFarFromBottom;
    }

    public Integer getPriceFontSize() {
        return priceFontSize;
    }

    public void setPriceFontSize(Integer priceFontSize) {
        this.priceFontSize = priceFontSize;
    }

    public Integer getProductFarFromTop() {
        return productFarFromTop;
    }

    public void setProductFarFromTop(Integer productFarFromTop) {
        this.productFarFromTop = productFarFromTop;
    }

    public Integer getProductFontSize() {
        return productFontSize;
    }

    public void setProductFontSize(Integer productFontSize) {
        this.productFontSize = productFontSize;
    }

    public Long getHow2show() {
        return how2show;
    }

    public void setHow2show(Long how2show) {
        this.how2show = how2show;
    }

    public List<CrmkSehyGrp> getSehyGrpList() {
        return sehyGrpList;
    }

    public void setSehyGrpList(List<CrmkSehyGrp> sehyGrpList) {
        this.sehyGrpList = sehyGrpList;
    }

    public CrmkSehyGrp getFilteredGrp() {
        return filteredGrp;
    }

    public void setFilteredGrp(CrmkSehyGrp filteredGrp) {
        this.filteredGrp = filteredGrp;
    }

    public CrmkSehyGrp getGrpSelected() {
        return grpSelected;
    }

    public void setGrpSelected(CrmkSehyGrp grpSelected) {
        this.grpSelected = grpSelected;
    }

    public ShowBathHd getBathSelected() {
        return bathSelected;
    }

    public void setBathSelected(ShowBathHd bathSelected) {
        this.bathSelected = bathSelected;
    }

    public ShowBathHd getFilteredBath() {
        return filteredBath;
    }

    public void setFilteredBath(ShowBathHd filteredBath) {
        this.filteredBath = filteredBath;
    }

    public List<ShowBathHd> getShowBathdList() {
        return showBathdList;
    }

    public void setShowBathdList(List<ShowBathHd> showBathdList) {
        this.showBathdList = showBathdList;
    }

    public List<CrmkSehyName> getCrmkSehyNameList() {
        return crmkSehyNameList;
    }

    public void setCrmkSehyNameList(List<CrmkSehyName> crmkSehyNameList) {
        this.crmkSehyNameList = crmkSehyNameList;
    }

    public List<CrmkSehyType> getCrmkSehyTypeList() {
        return crmkSehyTypeList;
    }

    public void setCrmkSehyTypeList(List<CrmkSehyType> crmkSehyTypeList) {
        this.crmkSehyTypeList = crmkSehyTypeList;
    }

    public CrmkSehyName getFilteredSehyName() {
        return filteredSehyName;
    }

    public void setFilteredSehyName(CrmkSehyName filteredSehyName) {
        this.filteredSehyName = filteredSehyName;
    }

    public CrmkSehyType getFilteredSehyType() {
        return filteredSehyType;
    }

    public void setFilteredSehyType(CrmkSehyType filteredSehyType) {
        this.filteredSehyType = filteredSehyType;
    }

    public CrmkSehyName getSehyNameSelected() {
        return sehyNameSelected;
    }

    public void setSehyNameSelected(CrmkSehyName sehyNameSelected) {
        this.sehyNameSelected = sehyNameSelected;
    }

    public CrmkSehyType getSehyTypeSelected() {
        return sehyTypeSelected;
    }

    public void setSehyTypeSelected(CrmkSehyType sehyTypeSelected) {
        this.sehyTypeSelected = sehyTypeSelected;
    }


}
