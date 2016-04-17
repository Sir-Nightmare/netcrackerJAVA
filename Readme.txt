В этой задаче не совсем корректно работает проверка на href. Проходят такие варианты как :
<a href="www.g"oogle.com">
<A href="www.google.com"\>
      ,хотя не должны.



ЦЕЛЬ ЗАДАЧИ - разобраться с регулярными выражениями и классами пакета java.util.regex. 
 
ЗАДАНИЕ
Реализовать проверку корректности имен в языке PL/SQL, проверку корректности URLов и e-mail-ов, 
нахождение соответствий (matches) в строке.
Common requirements:
Your class must be placed to the same package as the interface: ru.ncedu.java.tasks.
Your class must be named CheckerImpl.
Your class must be public and implement the given interface, i.e. the class definition must be as follows:
   public class CheckerImpl implements Checker
   { ... }
Your class must have a public no-argument constructor that means either the default constructor (the absence of any constructors in your code) or the presence of the following code:
   public CheckerImpl(){ ... }
Your class must not have compilation errors (write your code in IDE).
Instantiation with the no-argument constructor must not fail (it's recommended to test it in main() method).
