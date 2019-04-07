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
            if(!contains(n, arr)){
                misNums = new int[]{n, misSum-n};
                break;   
            }
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
    public static ArrayList<Integer> findMissing4(ArrayList<Integer> arr){
        int sum = 0, count = 0, max = 0, misSum;
        ArrayList <Integer> misNums=new ArrayList();
        for(int n : arr){
            count++;
            sum += n;
            if(n > max) max = n;
        }
        misSum = (max*(max+1)/2) - sum;//Sum of all missing numbers
        count = max - count;//How many numbers are missing
        for(int n = Math.min(misSum, max-1); n > 1; n--){
            if(arr.indexOf(n) < 0){
                misNums.add(n);
                misSum -= n;
                if(--count == 1){//only one left
                    misNums.add(misSum);
                    break;
                }
            }
        }
        return misNums;
    }

    //Solution 5 - Assume unknown amount of numbers are missing and Max value is unknown (using second array)
    public static ArrayList<Integer> findMissing5(ArrayList<Integer> arr){
        int max = 0;
        ArrayList<Integer> misNums = new ArrayList();
        int [] neededNums;
        for(int n : arr){
            if(n > max) max = n;
        }
        neededNums = new int[max];//zero for any needed num
        for(int n : arr){//iterate again
            neededNums[n == max ? 0 : n]++;//add one - used as index in second array (convert max to zero)
        }
        for(int i=neededNums.length-1; i>0; i--){
            if(neededNums[i] < 1)misNums.add(i);//if value is zero, than index is a missing number
        }
        return misNums;
    }

    //Question: find biggest contiguous sub array
    public static ArrayList <Integer> biggestSubArr(ArrayList <Integer> arr){
        Integer max = Integer.MIN_VALUE, sum = 0;
        ArrayList <Integer> test = new ArrayList(), found = new ArrayList();
        for(int n : arr){
            sum += n;
            test.add(n);
            if(max < sum ){
                max = sum;
                found = new ArrayList(test);//clone existing test values to found
            }
            if(sum < 0){
                sum = 0;
                test.clear();
            }
        }
        return found;
    }

    //Question: Shuffle an array
    public static int [] shuffle(int [] arr){
        int [] shuffle = Arrays.copyOf(arr, arr.length);
        for(int i=shuffle.length-1; i >= 0; i--){
            int r = (int)(Math.random() * i);
            int t = shuffle[r];
            shuffle[r] = shuffle[i];
            shuffle[i] = t;
        }
        return shuffle;
    }

    //Question: count all digits in a number

    //Solution 1 - dynamic type conversion String -> Number -> String
    public static int [] countDigits1(long n){
        char [] num = (""+n).toCharArray();//stringify the number
        int [] digits = new int [10];//ten zeros
        for(char d : num){
            digits[Integer.parseInt(""+d)]++;
        }
        return digits;
    }

    //Solution 2 - without type conversion
    public static int [] countDigits2(long num){
        int [] digits = new int [10];//ten zeros
        for(int d; num > 0 && 0 <= (d = (int)num%10); ){
            digits[d]++;
            num = num / 10;
        }
        return digits;
    }

    //Question: Find duplicated int in array with values between 0...n (unordered). Cannot use additional memory.

    //Solution 1 - Using sorting O(n^2).
    public static int findDup1(int [] arr){
        Arrays.sort(arr);//Bottle neck of this solution
        for(int i=arr.length-1; i>=0; i--){
            int t = arr[i];
            if(t == arr[i-1])return t;
        }
        return 0;
    }

    //Solution 2 - Without sorting O(n).
    public static int findDup2(int [] arr){
        for(int n : arr){
            int t = n<0 ? n*-1 : n;//if negative turn to positive
            arr[t] *= -1;
        }
        for(int n : arr){
            if(n > 0)return n;
        }
        return 0;
    }

    //Question: Compress a string using the counts of repeated characters. 

    //Solution - Assume the string consist only from letters [A-Za-z] (no numbers)
    public static String compress(String str){
      StringBuilder compressed = new StringBuilder();
      int strLength = str.length();
      for(int i=0, compCount=0; i<strLength; i++){
         compCount++;
         if(i+1 >= strLength || str.charAt(i+1) != str.charAt(i)){
            compressed.append(str.charAt(i)).append(compCount);
            compCount = 0;
         }
      }
      return compressed.length() < strLength ? compressed.toString() : str;//if not smaller - return the original
    }
}
