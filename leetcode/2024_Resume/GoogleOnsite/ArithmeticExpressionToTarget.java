/*
Given an array nums of positive and negative integers and an int target.
You have +, -, *, / and () operators. Find out if it's possible to build an expression that evaluates to the target value. Each operator can only be used once.
Return any solution or an empty string if it's not possible.

Example 1:

Input: nums = [1, 2, 3, 8, 4], target = 44
Output: "(3+8)*4"
Example 2:

Input: nums = [2, 3, 4, 1, 9, 2], target = 21
Output: "3+2*9"

idea:
https://leetcode.com/discuss/interview-question/351147/google-onsite-arithmetic-expression-to-construct-a-value
*/

import java.util.*;

class ArithmeticExpressionToTarget {
    public static void main(String[] args) {
        // int[] nums = new int[] {1, 2, 3, 8, 4};
        int[] nums = new int[] {2, 3, 4, 1, 9, 2};
        // int target = 44;
        int target = 21;

        ArithmeticExpressionToTarget eg = new ArithmeticExpressionToTarget();
        eg.assignTass(nums, 0, 0, 0, "", target, '+');
    }

    private void assignTass(int [] nums, int start, int curr, int prev, String exp, int target, char op) {
        if (start == nums.length) {
            if (curr == target) {
                System.out.println(exp.toString());
            }

            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start) {
                break;
            }

            if (i == 0) {
                assignTass(nums, start + 1, nums[i], nums[i], exp + (nums[i]), target, op);
            } else {
                assignTass(nums, start + 1, nums[i] + curr, nums[i], exp + ("+") + (nums[i]), target, '+');
                assignTass(nums, start + 1, curr - nums[i] , -nums[i], exp + ("-") + (nums[i]), target, '-');
                assignTass(nums, start + 1, curr - prev + prev * nums[i], nums[i] * curr, exp + ("*") + (nums[i]), target, '*');

                if (nums[i] != 0) {
                    if (op == '+' || op == '-') {
                        assignTass(nums, start + 1, curr - prev + prev / nums[i], nums[i] * curr, exp + ("/") + (nums[i]), target, '/');
                    } else {
                        assignTass(nums, start + 1, curr / nums[i], nums[i] * curr, exp + ("/") + (nums[i]), target, '/');
                    }
                }
            }
        }
    }
}

// def get_expression(  nu , op , i , j ):
//     n = len( nu )
//     m = len( op )
//     if n != m + 1:
//         print("ERROR, operators and operands do not match")
//         return None
//     expression = ""
//     for k in range(n):
//         if k == i:
//             expression = expression + "("
//         expression = expression + str(nu[k])
//         if k == j:
//             expression = expression + ")"
//         if k < n-1:
//             expression = expression + op[k]
//     return expression

// print("********** test for get_expression() ********** ")
// print( get_expression([1,2,3,4,5],["+","*","/","-"],0,2)  )
// print( get_expression([1,2,3],["+","*"],1,2)  )
// print( get_expression([1,2],["+"],0,1)  )
// print( get_expression([1],[],0,0)  )


// def is_match( expression , target ):
//     try:
//         t = eval(expression)
//     except (SyntaxError, NameError, TypeError, ZeroDivisionError):
//         return False
//     return t == target

// print("********** test for is_match() **********")
// print( is_match( "1+2*1", 3)  )
// print( is_match( "(3)", 3)  )
// print( is_match( "(1)", 3)  )
// print( is_match( "3*", 3)  )
// print( is_match( "1+2/0", 3)  )

    

// def permu( n , aset , path , output):
//     if len(path) == n:
//         output.append( path )
//     else:
//         for x in aset:
//             aset.remove(x)
//             permu( n , aset , path + [x] , output)
//             aset.add(x)
//     return None

// print("*********test of permu()*************")
// output = []
// permu( 1 , set( [0,1,2,3,4]  ) , [] , output )
// print( len(output)  )

// ops = []
// permu( 2, set(["+","-","*","/"]) , [] , ops )
// print( ops )


// ops = []
// permu( 6, set(["+","-","*","/"]) , [] , ops )
// print("if permuate n > set size, return empty set")
// print( ops )




// def get_any_solutions( nums , target ):
//     output = []
//     for x in nums:
//         if x == target:
//             output.append( str(x) )
//             return str(x)
    
    
//     for m in range(1,5):
//         ops = []
//         nus = []
//         permu( m , set(["+","-","*","/"]) , [] , ops )
//         permu( m + 1 , set(nums) , [] , nus )
        
//         for op in ops:
//             for nu in nus:
//                 for i in range(m):
//                     for j in range(i+1,m+1):
//                         expression = get_expression( nu , op , i , j )
//                         if is_match( expression , target ):
//                             return expression
//                             output.append( expression )
                
//     return output


// print("\n*********test of get_any_solutions()*************")

// nums = [2, 3, 4, 1, 9, 2]
// target = 21
// print( get_any_solutions( nums , target ) )

// nums = [1, 2, 3, 8, 4]
// target = 44
// print( get_any_solutions( nums , target ) )
    
// nums = [2, -2]
// target = 4
// print( get_any_solutions( nums , target ) )

// nums = [2]
// target = -2
// print( get_any_solutions( nums , target ) )

// nums = [2]
// target = 2
// print( get_any_solutions( nums , target ) )

    
    



// # evaluate(  [1,2,3] , ["+","*"] , 0 , 1 ) =  (1+2)*3 = 0

