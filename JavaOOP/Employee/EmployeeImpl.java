package ru.ncedu.java.tasks;
public class EmployeeImpl implements Employee {

    private int salary=1000;
    private String fName, lName;
    Employee manager;


    @Override public String getFirstName() {
        return fName;
    }
    @Override public void setFirstName(String firstName) {
        fName=firstName;
    }
    @Override public String getLastName() {
        return lName;
    }
    @Override public void setLastName(String lastName) {
        lName=lastName;
    }
    @Override public String getFullName() {
        return fName+" "+lName;
    }
    @Override public int getSalary() {
        return salary;
    }
    @Override public void increaseSalary(int value) {
        salary+=value;
    }
    @Override public void setManager(Employee manager) {
        this.manager=manager;

    }
    @Override public String getManagerName() {
        if(this.manager==null){
            return "No manager";
        }else return manager.getFullName();
    }
    /**
     * Возвращает Менеджера верхнего уровня, т.е. вершину иерархии сотрудников,
     *   в которую входит данный сотрудник.
     * Если над данным сотрудником нет ни одного менеджера, возвращает данного сотрудника.
     * Замечание: поскольку менеджер, установленный методом {@link #setManager(Employee)},
     *   может быть экзепляром другого класса, при поиске топ-менеджера нельзя обращаться
     *   к полю класса EmployeeImpl. Более того, поскольку в интерфейсе Employee не объявлено
     *   метода getManager(), поиск топ-менеджера невозможно организовать в виде цикла.
     *   Вместо этого нужно использовать рекурсию (и это "более объектно-ориентированно").
     */
    @Override
    public Employee getTopManager() {
        if(this.manager!=null){

            return manager.getTopManager();
        }else{
            return this;
        }

    }
}