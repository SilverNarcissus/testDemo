package algorithm;

import java.util.Stack;

public class Calculater {

  public static void main(String[] args) {
    Calculater calculater = new Calculater();
    int res = calculater.calculate("(1+(4+5+2)-3)");
    System.out.println(res);
  }

  int loc = 0;
  int[] priority = new int[128];

  public int calculate(String s) {
    s = s.replaceAll(" ", "");
    priority['+'] = 1;
    priority['-'] = 1;
    priority['*'] = 2;
    priority['/'] = 2;
    priority['m'] = 3;

    Stack<Character> opStack = new Stack<>();
    Stack<Integer> valStack = new Stack<>();
    boolean beforeIsVal = false;

    while (loc < s.length()) {
      System.out.println(opStack);
      System.out.println(valStack);
      System.out.println();
      if (Character.isDigit(s.charAt(loc))) {
        valStack.push(readInt(s));
        beforeIsVal = true;
      } else {
        char curOp = s.charAt(loc);

        if (curOp == ')') {
          while (opStack.peek() != '(') {
            calculate(opStack, valStack);
          }
          opStack.pop();
          beforeIsVal = true;
        }
        else if(curOp == '-'){
          if(beforeIsVal){
            pushOp(opStack, '-', valStack);
          }
          else{
            pushOp(opStack, 'm', valStack);
          }
          beforeIsVal = false;
        }
        else {
          pushOp(opStack, curOp, valStack);
          beforeIsVal = false;
        }

        loc++;
      }
    }

    while(!opStack.isEmpty()){
      calculate(opStack, valStack);
    }

    return valStack.pop();
  }

  private void pushOp(Stack<Character> stack, char cur, Stack<Integer> val){
    if(cur == '('){
      stack.push(cur);
      return;
    }

    while(!stack.isEmpty() && priority[stack.peek()] >= priority[cur]){
      if(cur == 'm'){
        break;
      }
      calculate(stack, val);
    }

    stack.push(cur);
  }

  private int readInt(String s) {
    int res = 0;
    while (loc < s.length() && Character.isDigit(s.charAt(loc))) {
      res *= 10;
      res += s.charAt(loc) - '0';
      loc++;
    }

    return res;
  }

  private void calculate(Stack<Character> op, Stack<Integer> val) {
    char top = op.pop();
    int first = 0;
    int second = 0;
    switch (top) {
      case '+':
        second = val.pop();
        first = val.pop();
        val.push(first + second);
        break;
      case '-':
        second = val.pop();
        first = val.pop();
        val.push(first - second);
        break;
      case '*':
        second = val.pop();
        first = val.pop();
        val.push(first * second);
        break;
      case '/':
        second = val.pop();
        first = val.pop();
        val.push(first / second);
        break;
      case 'm':
        val.push(-val.pop());
        break;
    }
  }
}
