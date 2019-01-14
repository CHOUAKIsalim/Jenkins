package com.example.service;

import com.example.exception.NoSquareException;
import com.example.model.Matrix;

public class MatrixMathematics {

	/**
	 * Transpose of a matrix - Swap the columns with rows
	 * @param matrix
	 * @return
	 */
	public static Matrix transpose(Matrix matrix) {
		Matrix transposedMatrix = new Matrix(matrix.getNcols(), matrix.getNrows());
		for (int i=0;i<matrix.getNrows();i++) {
			for (int j=0;j<matrix.getNcols();j++) {
				transposedMatrix.setValueAt(j, i, matrix.getValueAt(i, j));
			}
		}
		return transposedMatrix;
	}
	
	/**
	 * Inverse of a matrix - A-1 * A = I where I is the identity matrix
	 * A matrix that have inverse is called non-singular or invertible. If the matrix does not have inverse it is called singular.
	 * For a singular matrix the values of the inverted matrix are either NAN or Infinity
	 * Only square matrices have inverse and the following method will throw exception if the matrix is not square.
	 * @param matrix
	 * @return
	 * @throws NoSquareException
	 */
	public static Matrix inverse(Matrix matrix) throws NoSquareException {
		return (transpose(cofactor(matrix)).multiplyByConstant(1.0/determinant(matrix)));
	}
	
	


	
	
}
