/*
implement DFS on tree with stack
*/

import java.util.*;

public class DepthFirstSearch {
    /** Checks if the specified value exists in the binary <strong class="StrictlyAutoTagBold">tree</strong> */
    public static boolean search( Node node, int value ) {
    	// Check if it's an empty <strong class="StrictlyAutoTagBold">tree</strong>.
        if( node == null ) {
            return false;
        }

        Stack<Node> stack = new Stack<Node>( );
        stack.push( node );

        while( ! stack.isEmpty( ) ) {

            Node currentNode = stack.pop( );

            if ( currentNode.data == value ) {
                // Found the value!
                return true;
            }

            if ( currentNode.right != null ) {
            	stack.push( currentNode.right );
            }

            // As we want to visit left child first, we must push this node last
            if ( currentNode.left != null ) {
                stack.push( currentNode.left );
            }
        }

        // Not found.
        return false;
    }
}