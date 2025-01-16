import java.io.*;
import java.util.Stack;
import java.util.stream.IntStream;


class Result {

    /*
     * Complete the 'isBalanced' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isBalanced(String s) {

        if (!validateData(s)){
            return "NO";
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char caractere = s.charAt(i);
            if (caractere == '{' || caractere == '[' || caractere == '('){
                stack.push(caractere);
            }else { // If the current character is not an opening one, then take the last inserted into the stack, invert it, and compare if they are equal.
                char caractereStack = stack.pop();
                if (caractere != returnsInverse(caractereStack)){
                    return "NO";
                }
            }
        }
        return "YES";
    }


    private static Character returnsInverse(Character s){
        if (s == '{') return '}';
        if (s == '[') return ']';
        return ')';
    }

    private static Boolean validateData(String s){
        String regex = "^[{\\[()\\]}]*$";

        // Validation to ensure that only the following characters are accepted: "{}[]()"
        if (!s.matches(regex)){
            return false;
        }

        // It is not possible to combine values if there is only one character.
        if (s.length() == 1){
            return false;
        }

        // The size of the character list must be even, as the validation will be done in pairs.
        if (s.length() % 2 != 0){
            return false;
        }

        char firstCaractere =  s.charAt(0);
        // If the first character is not an opening one, then it is already unbalanced.
        return firstCaractere != '}' && firstCaractere != ']' && firstCaractere != ')';
    }
}

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                String result = Result.isBalanced(s);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        //bufferedWriter.close();
    }
}
