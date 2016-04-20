ЦЕЛЬ ЗАДАЧИ - разобраться с основами объектно-ориентированного программирования в Java, 
принципами написания класса, реализации методов get/set, обращения к полям и методам объектов.

ЗАДАНИЕ
Реализовать класс, представляющий собой описание сотрудника компании (Employee).
Реализовать методы get/set для имени (и фамилии), полного имени, зарплаты сотрудника,
а также его непосредственного менеджера и топового (самого вышестоящего) менеджера.

Подробные требования этой задачи оформлены в тексте интерфейса ниже. Cм. также Common requirements.


Common requirements:
Your class must be placed to the same package as the interface: ru.ncedu.java.tasks.
Your class must be named EmployeeImpl.
Your class must be public and implement the given interface, i.e. the class definition must be as follows:
   public class EmployeeImpl implements Employee
   { ... }
Your class must have a public no-argument constructor that means either the default constructor (the absence of any constructors in your code) or the presence of the following code:
   public EmployeeImpl(){ ... }
Your class must not have compilation errors (write your code in IDE).
Instantiation with the no-argument constructor must not fail (it's recommended to test it in main() method).