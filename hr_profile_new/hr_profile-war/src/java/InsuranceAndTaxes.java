/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import e.HrEmpInfo;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;
import sb.SessionBeanLocal;

/**
 *
 * @author data
 */
@ManagedBean
@ViewScoped
public class InsuranceAndTaxes {
    @EJB
    private SessionBeanLocal sessionBean;
    private HrEmpInfo hrEmpInfo;
    private DefaultDiagramModel insuranceModel;
    private DefaultDiagramModel taxTotalSal;
//    private DefaultDiagramModel taxExemption;


    private DefaultDiagramModel firstLevel;
    private DefaultDiagramModel secoundLevel;
    private DefaultDiagramModel thirdLevel;
    private DefaultDiagramModel fourthLevel;
    private DefaultDiagramModel fifthLevel;

    private int level;

    @PostConstruct
    public void init(){
        hrEmpInfo=(HrEmpInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hrEmpInfo");
        insuranceModel = new DefaultDiagramModel();
        insuranceModel.setMaxConnections(-1);

        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle("{strokeStyle:'#C7B097',lineWidth:3}");
        insuranceModel.setDefaultConnector(connector);

        Element basic = new Element(" («·√Ã— «· √„Ì‰Ï) "+(hrEmpInfo.getInsNo()==0 ? 0 : hrEmpInfo.getBasicSal()), "70em", "6em");
        basic.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        basic.setStyleClass("ui-diagram-success");

        Element basicCalc = new Element(" = "+(hrEmpInfo.getInsNo()==0 ? 0 : Math.round(hrEmpInfo.getBasicSal()*.11)), "35em", "6em");
        basicCalc.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        basicCalc.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        basicCalc.setStyleClass("ui-diagram-success");

        Element end = new Element(" = («· √„Ì‰« ) "+(hrEmpInfo.getInsNo()==0 ? 0 : Math.round(hrEmpInfo.getBasicSal()*.11)), "1em", "6em");
        end.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        end.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        end.setStyleClass("ui-diagram-final-step");
        
        insuranceModel.addElement(basic);
        insuranceModel.addElement(basicCalc);
        insuranceModel.addElement(end);

        insuranceModel.connect(createConnection(basic.getEndPoints().get(0), basicCalc.getEndPoints().get(0), " 11% "+"X"));
        insuranceModel.connect(createConnection(basicCalc.getEndPoints().get(1), end.getEndPoints().get(0), ""));

        Object[] taxData=sessionBean.findBasicTaxData(CashHandler.hscp.getTrnsMonth().toString(),CashHandler.getHscp().getTrnsYear().toString(),hrEmpInfo.getEmpNo()+"");

        taxTotalSal = new DefaultDiagramModel();
        taxTotalSal.setMaxConnections(-1);

        taxTotalSal.setDefaultConnector(connector);

        Element totSal = new Element(" («·—« » «·‘«„·) "+Math.round(new Double(taxData[1].toString())), "77em", "1em");
        totSal.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        totSal.setStyleClass("ui-diagram-fail");

        Element plus = new Element("+ («·≈÷«›Ï) "+taxData[2]+" = "+Math.round(new Double(taxData[2].toString())+new Double(taxData[1].toString())), "58em", "1em");
        plus.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        plus.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        plus.setStyleClass("ui-diagram-success");

        Element tmyz = new Element("+ ( „Ì“) "+taxData[3]+" = "+Math.round(new Double(taxData[3].toString())+new Double(taxData[2].toString())+new Double(taxData[1].toString())), "39em", "1em");
        tmyz.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        tmyz.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        tmyz.setStyleClass("ui-diagram-fail");

        Element hfz = new Element("+ (Õ«›“) "+taxData[4]+" = "+Math.round(new Double(taxData[4].toString())+new Double(taxData[3].toString())+new Double(taxData[2].toString())+new Double(taxData[1].toString())), "20em", "1em");
        hfz.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        hfz.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        hfz.setStyleClass("ui-diagram-success");

        Element totalBenfits = new Element("= («·—« » «·ﬂ·Ï) "+Math.round(new Double(taxData[1].toString())+new Double(taxData[2].toString())+new Double(taxData[3].toString())+new Double(taxData[4].toString())), "1em", "1em");
        totalBenfits.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        totalBenfits.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
        taxTotalSal.addElement(totSal);
        taxTotalSal.addElement(plus);
        taxTotalSal.addElement(tmyz);
        taxTotalSal.addElement(hfz);
        taxTotalSal.addElement(totalBenfits);

        taxTotalSal.connect(createConnection(totSal.getEndPoints().get(0), plus.getEndPoints().get(0), ""));
        taxTotalSal.connect(createConnection(plus.getEndPoints().get(1), tmyz.getEndPoints().get(0), ""));
        taxTotalSal.connect(createConnection(tmyz.getEndPoints().get(1), hfz.getEndPoints().get(0), ""));
        taxTotalSal.connect(createConnection(hfz.getEndPoints().get(1), totalBenfits.getEndPoints().get(0), ""));





//        taxExemption = new DefaultDiagramModel();
//        taxExemption.setMaxConnections(-1);
//
//        taxExemption.setDefaultConnector(connector);

        Element fixedExemption = new Element("(«·≈⁄›«¡ «·⁄«∆·Ï) 583.33", "77em", "9em");
        fixedExemption.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        fixedExemption.setStyleClass("ui-diagram-fail");

        Element empInsurance = new Element("+ ( √„Ì‰ ÿ»Ï) "+Math.round(new Double(taxData[8].toString())+new Double(taxData[7].toString()))+" = "+(583.33+new Double(taxData[8].toString())+new Double(taxData[7].toString())), "58em", "9em");
        empInsurance.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        empInsurance.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        empInsurance.setStyleClass("ui-diagram-success");

        Element basicX2 = new Element("+ (√”«”Ï * 2.1) "+Math.round(new Double(taxData[5].toString())*2.1)+" = "+Math.round((new Double(taxData[5].toString())*2.1)+583.33+new Double(taxData[8].toString())+new Double(taxData[7].toString())), "39em", "9em");
        basicX2.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        basicX2.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        basicX2.setStyleClass("ui-diagram-fail");

        Element workerPay = new Element("+ (‰’Ì» «·⁄«„·) "+taxData[6]+" = "+Math.round(new Double(taxData[6].toString())+(new Double(taxData[5].toString())*2.1)+583.33+new Double(taxData[8].toString())+new Double(taxData[7].toString())), "20em", "9em");
        workerPay.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        workerPay.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        workerPay.setStyleClass("ui-diagram-success");

        Double exemptionVal=new Double(taxData[6].toString())+Math.round((new Double(taxData[5].toString())*2.1)+583.33+new Double(taxData[8].toString())+new Double(taxData[7].toString()));
        Element totalExemption = new Element("= («·≈⁄›«¡« ) "+exemptionVal, "1em", "9em");
        totalExemption.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        totalExemption.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
        totalExemption.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
        long txtBaseValue=Math.round((new Double(taxData[1].toString())+new Double(taxData[2].toString())+new Double(taxData[3].toString())+new Double(taxData[4].toString()))-exemptionVal);
         if(txtBaseValue<=666.66)
            level=1;
        else if(666.66<txtBaseValue && txtBaseValue<=2500)
            level=2;
        else if(2500<txtBaseValue && txtBaseValue<=3750)
            level=3;
        else if(3750<txtBaseValue && txtBaseValue<=16666.6)
            level=4;
         else
             level=5;
        taxTotalSal.addElement(fixedExemption);
        taxTotalSal.addElement(empInsurance);
        taxTotalSal.addElement(basicX2);
        taxTotalSal.addElement(workerPay);
        taxTotalSal.addElement(totalExemption);

        taxTotalSal.connect(createConnection(fixedExemption.getEndPoints().get(0), empInsurance.getEndPoints().get(0), ""));
        taxTotalSal.connect(createConnection(empInsurance.getEndPoints().get(1), basicX2.getEndPoints().get(0), ""));
        taxTotalSal.connect(createConnection(basicX2.getEndPoints().get(1), workerPay.getEndPoints().get(0), ""));
        taxTotalSal.connect(createConnection(workerPay.getEndPoints().get(1), totalExemption.getEndPoints().get(0), ""));
        taxTotalSal.connect(createConnection(totalBenfits.getEndPoints().get(1), totalExemption.getEndPoints().get(2), "_"));


        Element taxBaseValue = new Element(" («·Ê⁄«¡ «·÷—Ì»Ï) "+ txtBaseValue, "1em", "17em");
        taxBaseValue.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));

        taxTotalSal.addElement(taxBaseValue);
        taxTotalSal.connect(createConnection(totalExemption.getEndPoints().get(1), taxBaseValue.getEndPoints().get(0), " = "));

        



        



        firstLevel = new DefaultDiagramModel();
        firstLevel.setMaxConnections(-1);
        firstLevel.setDefaultConnector(connector);

        Element first = new Element("«·‘—ÌÕ… «·√Ê·Ï", "77em", "1em");
        first.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        first.setStyleClass("ui-diagram-fail");

        Element firstCondition = new Element("«·Ê⁄«¡ «·÷—Ì»Ï<=666.66", "58em", "1em");
        firstCondition.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        firstCondition.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        firstCondition.setStyleClass("ui-diagram-success");

        Element firstEquation = new Element("0", "39em", "1em");
        firstEquation.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        firstEquation.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        firstEquation.setStyleClass("ui-diagram-fail");


        Element firstEquationValue = new Element("0 = («·÷—Ì»…)  ", "1em", "1em");
        firstEquationValue.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        if(level==1)
            firstEquationValue.setStyleClass("ui-diagram-final-step");

        firstLevel.addElement(first);
        firstLevel.addElement(firstCondition);
        firstLevel.addElement(firstEquation);
        firstLevel.addElement(firstEquationValue);

        firstLevel.connect(createConnection(first.getEndPoints().get(0), firstCondition.getEndPoints().get(0), ""));
        firstLevel.connect(createConnection(firstCondition.getEndPoints().get(1), firstEquation.getEndPoints().get(0), ""));
        firstLevel.connect(createConnection(firstEquation.getEndPoints().get(1), firstEquationValue.getEndPoints().get(0), ""));




        secoundLevel = new DefaultDiagramModel();
        secoundLevel.setMaxConnections(-1);
        secoundLevel.setDefaultConnector(connector);

        Element secound = new Element("«·‘—ÌÕ… «·À«‰Ì…", "77em", "1em");
        secound.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        secound.setStyleClass("ui-diagram-fail");

        Element secoundCondition = new Element("666.66< «·Ê⁄«¡ «·÷—Ì»Ï <=2500", "58em", "1em");
        secoundCondition.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        secoundCondition.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        secoundCondition.setStyleClass("ui-diagram-success");

        Element secoundEquation = new Element("(0.1*0.15*(«·Ê⁄«¡ «·÷—Ì»Ï-666.66)", "39em", "1em");
        secoundEquation.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        secoundEquation.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        secoundEquation.setStyleClass("ui-diagram-fail");

        Element secoundEquationValue = new Element("= («·÷—Ì»…) "+Math.round((txtBaseValue-666.66)*0.1*0.15), "1em", "1em");
        secoundEquationValue.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        if(level==2)
            secoundEquationValue.setStyleClass("ui-diagram-final-step");

        secoundLevel.addElement(secound);
        secoundLevel.addElement(secoundCondition);
        secoundLevel.addElement(secoundEquation);
        secoundLevel.addElement(secoundEquationValue);

        secoundLevel.connect(createConnection(secound.getEndPoints().get(0), secoundCondition.getEndPoints().get(0), ""));
        secoundLevel.connect(createConnection(secoundCondition.getEndPoints().get(1), secoundEquation.getEndPoints().get(0), ""));
        secoundLevel.connect(createConnection(secoundEquation.getEndPoints().get(1), secoundEquationValue.getEndPoints().get(0), ""));


        thirdLevel = new DefaultDiagramModel();
        thirdLevel.setMaxConnections(-1);
        thirdLevel.setDefaultConnector(connector);

        Element third = new Element("«·‘—ÌÕ… «·À«·À…", "77em", "1em");
        third.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        third.setStyleClass("ui-diagram-fail");

        Element thirdCondition = new Element("2500< «·Ê⁄«¡ «·÷—Ì»Ï <=3750", "58em", "1em");
        thirdCondition.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        thirdCondition.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        thirdCondition.setStyleClass("ui-diagram-success");

        Element thirdEquation = new Element("(0.15+183.33*(«·Ê⁄«¡ «·÷—Ì»Ï-2500)*0.55", "39em", "1em");
        thirdEquation.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        thirdEquation.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        thirdEquation.setStyleClass("ui-diagram-fail");

        Element thirdEquationValue = new Element("= («·÷—Ì»…) "+ Math.round((183.33+(txtBaseValue-2500)*0.15)*.55), "1em", "1em");
        thirdEquationValue.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        if(level==3)
            thirdEquationValue.setStyleClass("ui-diagram-final-step");

        thirdLevel.addElement(third);
        thirdLevel.addElement(thirdCondition);
        thirdLevel.addElement(thirdEquation);
        thirdLevel.addElement(thirdEquationValue);

        thirdLevel.connect(createConnection(third.getEndPoints().get(0), thirdCondition.getEndPoints().get(0), ""));
        thirdLevel.connect(createConnection(thirdCondition.getEndPoints().get(1), thirdEquation.getEndPoints().get(0), ""));
        thirdLevel.connect(createConnection(thirdEquation.getEndPoints().get(1), thirdEquationValue.getEndPoints().get(0), ""));


        fourthLevel = new DefaultDiagramModel();
        fourthLevel.setMaxConnections(-1);
        fourthLevel.setDefaultConnector(connector);

        Element fourth = new Element("«·‘—ÌÕ… «·—«»⁄…", "77em", "1em");
        fourth.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        fourth.setStyleClass("ui-diagram-fail");

        Element fourthCondition = new Element("3750< «·Ê⁄«¡ «·÷—Ì»Ï <=16666.6", "58em", "1em");
        fourthCondition.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        fourthCondition.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        fourthCondition.setStyleClass("ui-diagram-success");

        Element fourthEquation = new Element("(0.2+370.83*(«·Ê⁄«¡ «·÷—Ì»Ï-3750))*0.925", "39em", "1em");
        fourthEquation.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        fourthEquation.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        fourthEquation.setStyleClass("ui-diagram-fail");

        Element fourthEquationValue = new Element("= («·÷—Ì»…) "+ Math.round((370.83+(txtBaseValue-3750)*0.2)*0.925), "1em", "1em");
        fourthEquationValue.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        if(level==4)
            fourthEquationValue.setStyleClass("ui-diagram-final-step");

        fourthLevel.addElement(fourth);
        fourthLevel.addElement(fourthCondition);
        fourthLevel.addElement(fourthEquation);
        fourthLevel.addElement(fourthEquationValue);

        fourthLevel.connect(createConnection(fourth.getEndPoints().get(0), fourthCondition.getEndPoints().get(0), ""));
        fourthLevel.connect(createConnection(fourthCondition.getEndPoints().get(1), fourthEquation.getEndPoints().get(0), ""));
        fourthLevel.connect(createConnection(fourthEquation.getEndPoints().get(1), fourthEquationValue.getEndPoints().get(0), ""));

        
    }

    public DefaultDiagramModel getInsuranceModel() {
        return insuranceModel;
    }

    public void setInsuranceModel(DefaultDiagramModel insuranceModel) {
        this.insuranceModel = insuranceModel;
    }


    public DefaultDiagramModel getTaxTotalSal() {
        return taxTotalSal;
    }

    public DefaultDiagramModel getFirstLevel() {
        return firstLevel;
    }

    public void setFirstLevel(DefaultDiagramModel firstLevel) {
        this.firstLevel = firstLevel;
    }

    public DefaultDiagramModel getSecoundLevel() {
        return secoundLevel;
    }

    public void setSecoundLevel(DefaultDiagramModel secoundLevel) {
        this.secoundLevel = secoundLevel;
    }

    public DefaultDiagramModel getThirdLevel() {
        return thirdLevel;
    }

    public void setThirdLevel(DefaultDiagramModel thirdLevel) {
        this.thirdLevel = thirdLevel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }



   

    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if(label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.3));
        }

        return conn;
    }
    /** Creates a new instance of InsuranceAndTaxes */
    public InsuranceAndTaxes() {
    }

}
