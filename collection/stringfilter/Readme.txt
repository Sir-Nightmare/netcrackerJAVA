ЦЕЛИ ЗАДАЧИ:
 - научиться выбирать подходящий класс-коллекцию и манипулировать им;
 - запомнить, что в коллекциях могут быть значения null, приводящие к NullPointerException;
 - научиться использовать почти все методы класса String (кроме связанных с regex);
 - осознать, что на следующем этапе нужно будет освоить регулярные выражения (regex) :), 
   так как без них задачу "соответствует ли строка шаблону" непросто закодировать
   даже в случае весьма примитивного шаблона;
 - (дополнительно) научиться избегать повторений кода при реализации близких алгоритмов.

ЗАДАНИЕ
 Реализовать класс объекта, который хранит набор строк (String).
 Реализовать фильтрацию строк этого набора, т.е. возвращать итераторы
  с теми строками, которые удовлетворяют нескольким критериям.
  
Common requirements:
Your class must be placed to the same package as the interface: ru.ncedu.java.tasks.
Your class must be named StringFilterImpl.
Your class must be public and implement the given interface, i.e. the class definition must be as follows:
   public class StringFilterImpl implements StringFilter
   { ... }
Your class must have a public no-argument constructor that means either the default constructor (the absence of any constructors in your code) or the presence of the following code:
   public StringFilterImpl(){ ... }
Your class must not have compilation errors (write your code in IDE).
Instantiation with the no-argument constructor must not fail (it's recommended to test it in main() method).