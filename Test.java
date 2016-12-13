/**
 * Created by Taocr on 2016/12/9.
 */
public class Test {
    public static class TestClass {
        private static void testMethod(){
            System.out.println("testMethod");
        }
        public static void main(String[] args) {
            ((TestClass)null).testMethod();
        }
    }
}
