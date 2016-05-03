ЦЕЛИ ЗАДАЧИ:
- Разобраться с XPath выражениями: общий синтаксис, абсолютные и относительные пути, 
     оси, фильтры, функции.
- Научиться делать произвольные выборки из древовидных структур с перекрестными ссылками.
- Разобраться с Java XPath API.
 
 ЗАДАНИЕ:
 Ознакомиться с двумя XML документами: emp.xml (emp.xsd) и emp-hier.xml. 
 С помощью XPath сделать выборку элемента (элементов) для каждого пункта задания 
   для каждого из двух документов; XPath для каждого из документов может отличаться.
   
   Your class must be placed to the same package as the interface: ru.ncedu.java.tasks.
Your class must be named XPathCallerImpl.
Your class must be public and implement the given interface, i.e. the class definition must be as follows:
   public class XPathCallerImpl implements XPathCaller
   { ... }
Your class must have a public no-argument constructor that means either the default constructor (the absence of any constructors in your code) or the presence of the following code:
   public XPathCallerImpl(){ ... }
Your class must not have compilation errors (write your code in IDE).
Instantiation with the no-argument constructor must not fail (it's recommended to test it in main() method).