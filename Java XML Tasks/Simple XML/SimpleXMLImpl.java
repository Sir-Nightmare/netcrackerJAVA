package ru.ncedu.java.tasks;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by tumas on 28.04.2016.
 */
public class SimpleXMLImpl implements SimpleXML {

    /**
     * С помощью DOM API в Java-коде создать XML документ вида "&lt;tagName&gt;textNode&lt;/tagName&gt;".<br/>
     * В частности, для вызова createXML("root","&lt;R&amp;D&gt;") должно вернуться &lt;root&gt;&amp;lt;R&amp;amp;D&amp;gt;&lt;/root&gt;.<br/>
     * Требования:<br/>
     * - Результат должен быть корректным (well-formed) XML документом.<br/>
     * - Никаких переводов строк или других дополнительных символов не должно быть добавлено в textNode.<br/>
     * Правильный подход к решению:<br/>
     * - Использовать именно DOM, а не писать логику обработки спецсимволов вручную.<br/>
     * - С целью удаления в документе декларации "&lt;?xml...?&gt;" следует использовать метод
     * {@link Transformer#setOutputProperty(String, String)} для свойства OMIT_XML_DECLARATION.
     *
     * @param tagName  Имя тега элемента
     * @param textNode Текстовое содержимое тега.
     * @return Корректный XML документ без декларации "&lt;?xml...?&gt;"
     */
    @Override
    public String createXML(String tagName, String textNode) {
        Element root;
        StringWriter stringWriter=new StringWriter();
        try {
            Document documentXML=DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            root=documentXML.createElement(tagName);
            Node node=documentXML.createTextNode(textNode);
            root.appendChild(node);
            documentXML.appendChild(root);
            Transformer transformer= TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"ДА");
            transformer.transform(new DOMSource(documentXML),new StreamResult(stringWriter));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return stringWriter.toString();
    }

    /**
     * С помощью SAX API проверить, что во входящем потоке содержится корректный (well-formed) XML-документ.<br/>
     * В качестве результата вернуть имя корневого элемента документа,
     * а в случае ошибки (если документ не well-formed) бросить {@link SAXException}.<br/>
     * Требование: Потребляемая память не должна зависеть от размера входящего документа.<br/>
     * Примечание: Не следует требовать от документа корректности пространства имен
     * (в имени элемента может использоваться namespace, но без объявления).
     *
     * @param xmlStream Поток с XML документом
     * @return Имя корневого элемента.
     */

    private String rootElement;
    @Override
    public String parseRootElement(InputStream xmlStream) throws SAXException {
        rootElement="";
        DefaultHandler handler= new DefaultHandler(){
            public void startElement(String url, String lname, String qname, Attributes attr){
                rootElement=qname;
            }
        };
        try {
            SAXParser parser= SAXParserFactory.newInstance().newSAXParser();
            parser.parse(xmlStream,handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootElement;
    }

}