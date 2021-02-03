public class HelloWithTextBlock {
    public static void main(String[] args) {
        String str = """
              Hello, world. 
              This string has been generated from inside of a Java 15 Text Block 
              What do you think of the new feature?
              """;
        System.out.println(str);
    }
}
