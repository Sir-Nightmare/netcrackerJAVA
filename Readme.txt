ЦЕЛЬ ЗАДАЧИ - научиться извлекать информацию о классах 
и обращаться к полям и методам объектов с помощью Reflection API.
Дополнительно: научиться упаковывать данные в коллекции (с Generics). 

ЗАДАНИЕ
Реализовать методы данного интерфейса путем вызова Reflection API
 (с минимальным объемом собственной логики в реализуемых методах).
 
Common requirements:
Your class must be placed to the same package as the interface: ru.ncedu.java.tasks.
Your class must be named ReflectionsImpl.
Your class must be public and implement the given interface, i.e. the class definition must be as follows:
   public class ReflectionsImpl implements Reflections
   { ... }
Your class must have a public no-argument constructor that means either the default constructor (the absence of any constructors in your code) or the presence of the following code:
   public ReflectionsImpl(){ ... }
Your class must not have compilation errors (write your code in IDE).
Instantiation with the no-argument constructor must not fail (it's recommended to test it in main() method).