package codingChallenge3;

import java.util.*;

public class Subsets {

	public static void main(String[] args) 
	{
		int[] subsetArray = {2, 3, 11, 17, 4, 10};
		int targetSum = 5;
		
		System.out.println("Array: " + Arrays.toString(subsetArray));
		System.out.println("Target Sum: " + targetSum);
		
		try {
			List<List<Integer>> subsets = getSubsets(subsetArray, targetSum);
			if (subsets.isEmpty()) 
			{
				System.out.println("Subset doesn't exist.");
			} 
			else 
			{
				int counter = 1;		//Numbers the subsets in the output.
			
				for (List<Integer> subset : subsets) //Prints out all possible subsets that add up to the target sum
				{
					System.out.println("Subset "+ counter + ": ");
					System.out.println(subset + "\n");
					counter++;
				
				}
			}
			
		} catch (Exception e) {		//Catches any potential errors.
			System.out.print("Error, program terminated.");
			e.printStackTrace();	//Lets user know exactly where error is.
		}
	}
	
	//Method to find all subsets of the array that add up to the target sum
    public static void findSubsets(int[] subsetArray, int index, int targetSum, List<Integer> subset, List<List<Integer>> result) 
    {
    	//Base Case #1
        if (targetSum == 0) 		
        {						
            result.add(new ArrayList<>(subset));
            return;
        }
        
        //Base Case #2
        if (index == 0 || targetSum < 0) 
        {
            return;
        }

        //Recursively calls the findSubsets method (to determine whether current element SHOULD NOT be included in subset).
        findSubsets(subsetArray, index - 1, targetSum, subset, result);
        subset.add(subsetArray[index - 1]);		//Adds the current element to the subset
        
        //Recursively calls the findSubsets method (to determine whether current element SHOULD be included in teh subset).
        findSubsets(subsetArray, index - 1, targetSum - subsetArray[index - 1], subset, result);
        subset.remove(subset.size() - 1);		//Removes the last element from the subset.
    }

    //Method that creates and empty list to store all valid subsets
    public static List<List<Integer>> getSubsets(int[] subsetArray, int targetSum) 
    {
        List<List<Integer>> result = new ArrayList<>();		//Creates empty list to store subsets
        
        //Calls findSubsets to find all the possible subsets in the array that add up to the target sum.
        findSubsets(subsetArray, subsetArray.length, targetSum, new ArrayList<>(), result);	
        return result;
    }

}




/**
 * 				*******		OUTPUT:		*******
 * 
 * 
 * 		TEST CASE #1:	(when array has multiple subsets)
 * 
 * 		Array: [2, 0, 3, 11, 17, 5, 10]
		Target Sum: 21
		Subset 1: 
		[5, 11, 3, 2]

		Subset 2: 
		[5, 11, 3, 0, 2]

		Subset 3: 
		[10, 11]
		
		
		
		TEST CASE #2:	(when array has one subset)
		
		Array: [2, 3, 11, 17, 4, 10]
		Target Sum: 5
		Subset 1: 
		[3, 2]
		
		
		TEST CASE #3:	(when array has zero subsets)
		
		Array: [2, 3, 6, 17, 25, 10]
		Target Sum: 1
		Subset doesn't exist.
 */
