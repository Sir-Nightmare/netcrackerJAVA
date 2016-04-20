ЦЕЛИ ЗАДАЧИ:
- Разобраться с основами ООП в Java: принципами написания класса, методов get/set
- Познакомиться с некоторыми методами класса Object и с интерфейсом Comparable 
- Научиться использовать некоторый API пакетов java.lang и java.util: String, Double, Arrays
- Привыкать читать требования по-английски :)

ЗАДАНИЕ
Реализовать класс комплексного числа с математическими операциями над ним.
Экземпляры этого класса должны уметь сравнивать себя с другими объектами, клонироваться
и представляться в виде строки String (в стандартном для комплексных чисел формате).

ПРИМЕЧАНИЕ
В отличие от русскоязычных задач пакета ru.ncedu.java.tasks, 
эта задача находится в пакете com.netcracker.edu.java.tasks


Common requirements:
Your class must be placed to the same package as the interface: com.netcracker.edu.java.tasks.
Your class must be named ComplexNumberImpl.
Your class must be public and implement the given interface, i.e. the class definition must be as follows:
   public class ComplexNumberImpl implements ComplexNumber
   { ... }
Your class must have a public no-argument constructor that means either the default constructor (the absence of any constructors in your code) or the presence of the following code:
   public ComplexNumberImpl(){ ... }
Your class must not have compilation errors (write your code in IDE).
Instantiation with the no-argument constructor must not fail (it's recommended to test it in main() method).