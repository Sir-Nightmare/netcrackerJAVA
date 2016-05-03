package ru.ncedu.java.tasks;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SAAJResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by tumas on 03.05.2016.
 */
public class XPathCallerImpl implements XPathCaller {

    private Element[] nodeListToElementArray(NodeList nodeList){
        Element[] elements = new Element[nodeList.getLength()];
        for (int i=0;i<nodeList.getLength();++i) {
                elements[i] = (Element) nodeList.item(i);
           // System.out.println(elements[i]);
            System.out.println(nodeList.item(i).getTextContent());
           // System.out.println(elements[i].getTextContent());
            }
        return elements;
    }
    /**
     * Для заданного отдела выбрать всех сотрудников.
     *
     * @param src     XML документ для поиска
     * @param deptno  Номер отдела deptno
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */@Override public Element[] getEmployees(Document src, String deptno, String docType) {

        NodeList nodeList=null;
        XPath path= XPathFactory.newInstance().newXPath();
        String xpathDeptno = ".//employee[@deptno = '"+deptno+"']";
        try {
            nodeList=(NodeList)path.evaluate(xpathDeptno,src,XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return nodeListToElementArray(nodeList) ;
    }
    /**
     * Выбрать имя самого высокооплачиваемого сотрудника.
     *
     * @param src     XML документ для поиска
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */ @Override  public String getHighestPayed(Document src, String docType) {
        XPath path=XPathFactory.newInstance().newXPath();
        String xpathEmployeeSal=".//employee/sal";
        NodeList nodeList= null;
        String boss=null;
        try {
            nodeList = (NodeList) path.evaluate(xpathEmployeeSal,src, XPathConstants.NODESET);
            Double max=Double.valueOf(nodeList.item(0).getTextContent());
            int index=0;
            for (int i=0;i<nodeList.getLength();i++){
                Double temp = Double.valueOf(nodeList.item(i).getTextContent());
                if(max<temp){
                    max=temp;
                    index=i;
                }
            }
            NodeList names = (NodeList)path.evaluate(".//employee/ename",src,XPathConstants.NODESET);
            boss=names.item(index).getTextContent();
        } catch (XPathExpressionException e) {
           e.printStackTrace();
        }
        return boss;
    }
    /**
     * Выбрать имя самого высокооплачиваемого сотрудника (любого, если таких несколько).
     *
     * @param src     XML документ для поиска
     * @param deptno  Номер отдела deptno
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */@Override public String getHighestPayed(Document src, String deptno, String docType) {
        NodeList nodeListInRightDeptno=null;//список челове по заданному депртаменту
        XPath path= XPathFactory.newInstance().newXPath();

        int index=0;
        try {
            nodeListInRightDeptno=(NodeList)path.evaluate(".//employee[@deptno = '"+deptno+"']",src,XPathConstants.NODESET);
            Double max=Double.valueOf(nodeListInRightDeptno.item(0).getChildNodes().item(7).getTextContent());
            for (int i=0;i<nodeListInRightDeptno.getLength();i++){
                nodeListInRightDeptno.item(i);
                Double temp=Double.valueOf(nodeListInRightDeptno.item(i).getChildNodes().item(7).getTextContent());
                if(max<temp){
                    max=temp;
                    index=i;
                }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return nodeListInRightDeptno.item(index).getChildNodes().item(1).getTextContent();
    }


/*
   public static void main (String...args) throws ParserConfigurationException, IOException, SAXException {
        XPathCaller caller = new XPathCallerImpl();
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dBuilder.parse(new File("D:\\netcrackerJAVA\\Java XML Tasks\\XPath Caller\\emp.xml"));
       // caller.getTopManagement(doc,"emp-hier");
        //caller.getOrdinaryEmployees(doc,"emp-hier");
       caller.getCoworkers(doc,"7521","emp-hier");
    }
*/
    /**
     * Выбрать всех топовых менеджеров (менеджер топовый, если над ним нет менеджера)
     *
     * @param src     XML документ для поиска
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public Element[] getTopManagement(Document src, String docType) {
        XPath path=XPathFactory.newInstance().newXPath();
        NodeList nodeList = null;

        try {
            if(docType.equals("emp-hier")){
              // System.out.println("rere");

                nodeList = (NodeList) path.compile("/employee").evaluate(src, XPathConstants.NODESET);
               // System.out.println(nodeList.getLength());
            }
            if(docType.equals("emp")) {
                nodeList = (NodeList) path.evaluate("/content/emp/employee[not(@mgr)]", src, XPathConstants.NODESET);
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return nodeListToElementArray(nodeList);

    }

    /**
     * Выбрать всех сотрудников, не являющихся менеджерами.
     * Считать, что сотрудник не является менеджером, если у него нет подчиненных.
     *
     * @param src     XML документ для поиска
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public Element[] getOrdinaryEmployees(Document src, String docType) {
        XPath xPath =  XPathFactory.newInstance().newXPath();

        String expression;
        if(docType.equals("emp-hier"))
            expression = "//employee[not(./employee)]";
        else
            expression = "//employee[not(@empno = (//@mgr))]";

        NodeList nodeList = null;

        try {
            nodeList = (NodeList) xPath.compile(expression).evaluate(src, XPathConstants.NODESET);
           // System.out.println(nodeList.getLength());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return nodeListToElementArray(nodeList);
    }

    /**
     * Для заданного сотрудника(empno) найти всех коллег, которые в подчинении у того же менеджера.
     *
     * @param src     XML документ для поиска
     * @param empno   Номер сотрудника empno
     * @param docType "emp" - для файла типа emp.xml; "emp-hier" - для файла типа emp-hier.xml
     */
    @Override
    public Element[] getCoworkers(Document src, String empno, String docType) {
        XPath xPath =  XPathFactory.newInstance().newXPath();

        String expression;
        if(docType.equals("emp-hier"))
            expression = "//employee[@empno = '" + empno + "']/ancestor::*[1]/child::employee[@empno != '" + empno + "']";
        else
            expression = "//employee[@mgr = //employee[@empno = //employee[@empno = '" + empno + "']/@mgr]/@empno and @empno != '" + empno + "']";

        NodeList nodeList = null;

        try {
            nodeList = (NodeList) xPath.compile(expression).evaluate(src, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return nodeListToElementArray(nodeList);
    }
}
