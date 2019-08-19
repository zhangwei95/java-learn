public class Teacher extends People {
    public Teacher(int age, String name) {
        super(age, name);
        // TODO Auto-generated constructor stub
    }

    int x;
//	public static class MasterTeacher
//	{
//		public static void test()
//		{
//			int a=x;
//			System.out.println(a);
//
//		}
//	}
//	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		String str=new String("abc");
//		String str2="abc";
//		System.out.println(str==str2);
//		System.out.println(str);
////		MasterTeacher.test();
//		
//	}

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
//		 return age>((People)o).age?1:age<((People)o).age?-1:0;	
        return name.compareTo(((Teacher) o).name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + x;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Teacher other = (Teacher) obj;
        if (x != other.x)
            return false;
        return true;
    }
}
