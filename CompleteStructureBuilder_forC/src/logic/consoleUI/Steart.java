/*
 * Copyright (C) 2021 <FacuFalcone - CaidevOficial>.
 * 
 * Author in C: Santiago Herrera.
 * Adaptation in Java: <FacuFalcone - CaidevOficial>.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package logic.consoleUI;

/**
 * @author <FacuFalcone - CaidevOficial>
 */
public class Steart {

    /**
     * Muestra un porcentaje calculado.
     * @param base divisor
     * @param top dividendo*100
     * @return retorna el porcentaje.
     */
    public static double porcentageCalc(int base, int top) {
	double result;
	result = (100 * top) / base;
	return result;
    }

    /**
     * Muestra el progreso del programa.
     * 
     * @param d progreso a mostrar.
     */
    public static void show_progress(double d) {
	Steart.progHeader("Struct DotH DotC Builder");
	Steart.subHeader("Step 3: Building Struct Files");
	System.out.printf("\nProgress: [%6.2f]%%\n", d);
    }

    /**
     * Prints characters.
     * 
     * @param length Max length of the line.
     */
    private static void printSymbols(int length, char charact) {
	for (int index = 0; index < length; index++) {
	    System.out.print(charact);
	}
	System.out.println();
    }

    /**
     * Prints the title and decorations of th eprogram.
     * 
     * @param title Title for print.
     */
    public static void progHeader(String title) {
	int length = title.length();
	int lengthLine = (length * 3);

	printSymbols(lengthLine, '=');
	printSymbols(lengthLine, ' ');
	System.out.print(title);
	printSymbols(lengthLine, ' ');
	printSymbols(lengthLine, '=');
    }

    /**
     * Prints on console the subHeader of the program.
     * 
     * @param subHeader Subheader for print.
     */
    public static void subHeader(String subHeader) {
	int subLength = subHeader.length();
	System.out.printf("\n%s\n", subHeader);
	printSymbols(subLength, '-');
    }
}
