public class answerExamples {

    //Question: Get fibonacci number by index:

    //Solution 1 - recursion
    public static int fib1(int i){
        if(i == 0 || i == 1)return i;
        return fib1(i-1) + fib1(i-2);
    }


    //Solution 2 - loop
    public static int fib2(int i){
        if(i==0 || i==1)return i;
        int count=2, n1=0, n2=1;
        while(count<=i){
            count+=1;
            int t = n2;
            n2 = n1+n2;
            n1 = t;
        }
        return n2;
    }

    //Question: Print fibonacci calling: printFib(n1, n2)

    //Solution 1 - Descending order
    public static void printFib1(int n1, int n2){
        System.out.println(n1);
        if(n1 > 0)printFib1(n2-n1, n1);
    }
    //Solution 2 - Ascending order
    public static void printFib2(int n1, int n2){
        if(n1 > 0)printFib1(n2-n1, n1);
        System.out.println(n1);
    }

    //Question: Find missing number in array with values between 1...n (unordered)

    //Solution 1 - Assume only one number is missing and Max value is known for example: 100
    public static int findMissing1(int [] arr){
        int sum = 0;
        for(int n : arr){
            sum += n;
        }
        return (100*(100+1)/2) - sum;
    }

    //Solution 2 - Assume only one number is missing and Max value is unknown
    public static int findMissing2(int [] arr){
        int sum = 0, max = 0;
        for(int n : arr){
            sum += n;
            if(n > max) max = n;
        }
        return (max*(max+1)/2) - sum;
    }

    //Solution 3 - Assume 2 numbers are missing and Max value is unknown
    public static int [] findMissing3(int [] arr){
        int sum = 0, max = 0, misSum;
        int [] misNums = {};//empty by default
        for(int n : arr){
            sum += n;
            if(n > max) max = n;
        }
        misSum = (max*(max+1)/2) - sum;//Sum of two missing numbers
        for(int n = Math.min(misSum, max-1); n > 1; n--){
            if(!contains(n, arr))misNums = new int[]{n, misSum-n};

        }
        return misNums;
    }
    //Utility method - reused in Solution 3 & 4 in finding missing numbers question
    private static boolean contains(int num, int [] arr){
        for(int n : arr){
            if(n == num)return true;
        }
        return false;
    }

    //Solution 4 - Assume unknown amount of numbers are missing and Max value is unknown
    //TODO

    //Solution 5 - Assume unknown amount of numbers are missing and Max value is unknown (using second array)
    //TODO

    //Question: find biggest contiguous sub array
    //TODO

    //Question: Shuffle an array
    //TODO

    //Question: count all digits in a number

    //Solution 1 - dynamic type conversion String -> Number -> String
    //TODO

    //Solution 2 - without type conversion
    //TODO

    //Question: Find duplicated int in array with values between 0...n (unordered). Cannot use additional memory.

    //Solution 1 - Using sorting O(n^2).
    //TODO

    //Solution 2 - Without sorting O(n).
    //TODO
}