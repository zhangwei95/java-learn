package map.bean;

/**
 * @author zw
 */
public class People implements Work, Comparable {
    public void speak() {

    }
    public People(){

    }
    public People(int age, String name) {
        this.age = age;
        this.name = name;
    }
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String name;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        People other = (People) obj;
        if (age != other.age) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        return age > ((People) o).age ? 1 : age < ((People) o).age ? -1 : 0;
    }
}
