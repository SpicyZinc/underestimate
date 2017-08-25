    public static long fibonacciDP(int n) {
        long n1 = 1;
        long n2 = 1;
        long current = 2;
        for (int i = 3; i <= n; i++) {
            current = n1 + n2;
            n1 = n2;
            n2 = current;
        }
        
        return current;
    }