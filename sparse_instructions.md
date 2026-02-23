Sparse Matrix Assignment

A sparse matrix (aka sparse array) is a matrix in which most of the elements are zero.
https://en.wikipedia.org/wiki/Sparse_matrix

Sparse matrices can be represented in a more memory efficient manner than so-called "dense" matrices.

Consider:
5	0	0	0
0	8	0	0
0	0	0	0
0	6	0	0

In the standard format, this matrix comprises 16 numbers and all 16 numbers must be stored in memory.

But instead we could store the same data using only 12 numbers by representing the matrix as follows:
Row count: 4
Column count: 4
Default value: 0
Non-default values:
	5 at 0,0
	8 at 1,1
	6 at 3,1
That's not many savings, but if the matrix was 1000x1000 and 95% of the values were zero, the memory savings would be significant.

Your task is to write a SparseMatrix Java class that can represent a matrix in the efficient sparse format. Your code should assume integer data and a default value of zero. You will need to store the number of rows and columns, then traverse the matrix, recording the row, column, and value of all the non-zero values.

Requirements:
 - A SparseMatrix constructor that takes a 2D array (int[][], the matrix) as input and saves the data in instance variables in efficient sparse format.
 - A public method in SparseMatrix named outputToMatrix that outputs a standard 2D array built from the sparse representation.
 - A static method named matricesAreEqual that takes two 2D arrays as input and returns true if they are the same size and contain the same values.
 - An assert statement at the end of the SparseMatrix constructor that verifies that the output of the outputToMatrix method exactly matches the array given as input. This assert will use the matricesAreEqual method as well as the outputToMatrix method.

Lastly, write a public static void main method to instantiate your SparseMatrix class and test it on the following test cases. Each test must include instantiation of a SparseMatrix object from a standard matrix, as well as converting the SparseMatrix back to a standard matrix and printing out the standard matrix. Test each of the following:
1. The matrix given above:
	5	0	0	0
	0	8	0	0
	0	0	0	0
	0	6	0	0
2. A 10x10 sparse matrix with 5 non-default values. Create your own. Use copy-paste liberally and sprinkle the non-default values throughout.
3. A 20x20 sparse matrix with 10 non-default values. Create your own.


After all that, you must do ONE of the following options. These options are ordered (in my opinion) from easy to hard. I will grade more leniently if you choose one of the harder options:


OPTION 1.
Write a public method named "getSavings" in SparseMatrix that calculates and returns the memory savings (integers in memory in the original representation minus integers in memory in the sparse representation). Keep it simple and don't worry about the overhead of storing the SparseMatrix object in memory.
You must also add a check in the SparseMatrix constructor that prints a warning if the memory savings is negative, that is, if the sparse matrix representation takes more memory than the standard representation.
And finally, you must add a test case of a dense matrix that has negative savings.


OPTION 2.
Write a public method named "add" in SparseMatrix that takes another SparseMatrix as input and adds the values of the input SparseMatrix to the current SparseMatrix. HOWEVER, at no point in the process are you allowed to convert either SparseMatrix to the standard int[][] format.
Implement another test in main to verify that this works correctly by displaying out the two matrices you are adding and the sum that results. Make sure they are labeled clearly.


OPTION 3.
Change your implementation of SparseMatrix to accommodate non-zero default values. The tricky part is determining what the default value is.
You should add a private method named "getMajorityValue" that takes an int[][] as input and returns the most commonly occurring int.
The constructor will need to use getMajorityValue on its input to determine the default value.
An instance variable will be needed to store the default value.
The outputToMatrix method will need to be modified to utilize the default value.
You must add a test case that converts a matrix with a non-zero default value to the sparse format.
(The standard solution to this problem is to use a HashMap to determine the majority value. You may need to look that up, or you can figure out an alternative solution.)
