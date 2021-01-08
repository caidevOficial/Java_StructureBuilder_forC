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

package logic.mainApp;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

import logic.Structures.SubClasses.Parameter;
import logic.consoleUI.Steart;
import logic.mainApp.BuildersFiles.BuilderDotC;
import logic.mainApp.BuildersFiles.BuilderDotH;

/**
 * @author <FacuFalcone - CaidevOficial>.
 */
public class MainClass {

    // Attributes.
    static Scanner myScanner = new Scanner(System.in);
    static FileWriter pFileC = null;
    static FileWriter pFileH = null;
    static File pFileDotH = null;
    static File pFileDotC = null;
    static PrintWriter pw = null;

    static String auxString; //
    static String auxStrName = "s"; // Size 20
    static String strShort; // Size 4
    static String strName; // Size 20
    static int fullPackSize = 8; // Funciones basicas de un struct newEmpty + new + show + showall
    static int auxParNum = 6; // Funciones Basicas por parametro com + get + set
    static int packsDone = 0;
    static int lengthName = 20;
    static int lengthAlias = 5;

    static Parameter paramA = null;

    static LinkedList<Parameter> parameterList = new LinkedList<Parameter>();
    static int paramLen;

    static int flagStringHNeeded = 0;
    static boolean flagFirstParam = true;

    /**
     * Validates if the input is: 'y'.
     * 
     * @return true if input is: 'y', else returns false.
     */
    private static boolean validateAnswer(String message) {

	String answerFromUser;
	System.out.print(message);
	answerFromUser = myScanner.next();

	if (answerFromUser.equals("y")) {
	    return true;
	}

	return false;
    }

    /**
     * Validates the maximun length of the string.
     * @param maxLength Max length for the string.
     * @param myString String to evaluate.
     * @return The Maximun length of the string.
     */
    private static int validateLengthString(int maxLength, String myString) {
	int maximunLength = myString.length();
	if (myString != null && myString.length() > maxLength) {
	    maximunLength = maxLength;
	}

	return maximunLength;
    }

    /**
     * Assign a new ID.
     * 
     * @param to ID for return.
     * @param last Last assigned id.
     * @return The next ID.
     */
    public static int id_asigner(int to, int last) {
	to = last + 1;
	return to;
    }

    /**
     * if is possible, Puts capital letters.
     * 
     * @param myString String to modify.
     * @return if can, returns the string modified, else returns the same string.
     */
    public static String firstCharUpper(String myString) {
	String newString = myString;
	if (myString != null) {
	    newString = myString.substring(0, 1).toUpperCase() + myString.substring(1).toLowerCase();
	}

	return newString;
    }

    /**
     * Prints and ask the user for the name of the struct.
     * @return the nme of the struct.
     */
    public static String takeNameStruct() {
	String name = "";
	do {
	    System.out.println("===============================================================");
	    System.out.print("# Creation in C Languaje by Santiago Herrera.\n## Adaptation to Java & Upgrade by: CaidevOficial - FacuFalcone.\n");
	    System.out.println("===============================================================\n");
	    System.out.println("Through this program you'll be asked many times for yes or no.");
	    System.out.println("At those times enter y for yes and n for no.");
	    System.out.println("Nothing will be saved if you close the program at a random time.");
	    System.out.println("The saving will only procced after the \"LAST CONFIRMATION\" Question.");
	    System.out.println("(An \"s\" will be added at the beguining) so.. \n");
	    System.out.print("Write the name of the Structure: ");
	    name = myScanner.next();
	    name = firstCharUpper(name.substring(0, validateLengthString(20, name)));

	} while (!validateAnswer("Are you sure? [y/n]: "));

	return name;
    }

    /**
     * Prints on screen and ask the user for the short name of the struct.
     * @return the short name of the struct.
     */
    public static String takeShortNameStruct() {
	String shortName = "";
	do {
	    Steart.progHeader("Struct DotH DotC Builder");
	    Steart.subHeader("Step 1: Name of the Struct");
	    System.out.printf("\nName of the Struct: %s\nWrite a Short Name for the Structure to make a better Get&Set [max 5chars]: ", strName);
	    shortName = MainClass.myScanner.next();
	    shortName = firstCharUpper(shortName.substring(0, validateLengthString(lengthAlias, shortName)));
	} while (!validateAnswer("Are you sure? [y/n]: "));

	return shortName;
    }

    /**
     * Ask the user for the name of a parameter.
     * @return the name of the parameter.
     */
    public static String configParamName() {
	String name = "";
	do {
	    Steart.progHeader("Struct DotH DotC Builder");
	    Steart.subHeader("Step 2: Parameters: [For the structure]");
	    System.out.print("\nWrite the name of the Parameter: ");
	    name = myScanner.next();
	    name = name.substring(0, validateLengthString(lengthName, name));

	} while (!validateAnswer("Are you sure? [y/n]: "));

	return name;
    }

    /**
     * Ask the user for the alias of the parameter for put it on the G&S.
     * @return the alias of the parameter
     */
    public static String configParamAlias() {
	String alias = "";
	do {
	    Steart.progHeader("Struct DotH DotC Builder");
	    Steart.subHeader("Step 2: Name of the Parameters: [Short name for Getters, Setters and Comparers.]");
	    System.out.printf("\nName of the Parameter: %s\nWrite an Alias for the parameter to make a better Get&Sset [max 5 chars]: ", paramA.getParameterName());
	    alias = myScanner.next();
	    alias = firstCharUpper(alias.substring(0, validateLengthString(lengthAlias, alias)));

	} while (!validateAnswer("Are you sure? [y/n]: "));

	return alias;
    }

    /**
     * Ask the user for the type of the parameter.
     * @return the type of the parameter.
     */
    public static String configParamType() {
	String type = "";
	do {
	    Steart.progHeader("Struct DotH DotC Builder");
	    Steart.subHeader("Step 2: Name of the Parameters: [Select a type of the parameter.]");
	    System.out.printf("\nName of the Parameter: %s\nAlias: %s\nWrite the type between:\n[short, int, char, float, long long int, short short int]\nWrite the type: ", paramA.getParameterName(),
		    paramA.getParameterShort());
	    type = myScanner.next();
	    type = type.substring(0, validateLengthString(lengthAlias, type));

	} while (!validateAnswer("Are you sure? [y/n]: "));

	return type;
    }

    /**
     * Ask the user fir the length of the array of char.
     * @return the length of the array.
     */
    public static int configTypeCharLength() {
	int sizeChar = 1;
	if (paramA.getParameterType().equals("char")) {
	    do {
		Steart.progHeader("Struct DotH DotC Builder");
		Steart.subHeader("Step 2: Name of the Parameters: [Specify size of the char array]");
		System.out.print("\nThe type of the parameter is char, if its a string [char's array] \n" + "Write the length [Default 1]: ");
		sizeChar = myScanner.nextInt();

	    } while (!validateAnswer("Are you sure? [y/n]: "));

	    if (sizeChar <= 0) {
		sizeChar = 1;
	    } else if (sizeChar > 1) {
		flagStringHNeeded = 1;
	    }
	}

	return sizeChar;
    }

    /**
     * Ask the user to put 1 for YES or 0 for NO
     * 
     * @return Returns 1 for YES and o for NO.
     */
    public static int continueAddingParams() {
	int continueAdd = 0;
	Steart.progHeader("Struct DotH DotC Builder");
	Steart.subHeader("Step 2: Name Your Parameters.");
	System.out.printf("\nParameter Saved.");
	System.out.print("\nDo you Want to add more parameters?[ 1 = YES/ 0 = NO]: ");
	continueAdd = myScanner.nextInt();

	if (continueAdd == 1 || continueAdd == 0) {
	    return continueAdd;
	}

	return 0;
    }

    /**
     * Builds the parameter to save it in the LL.
     */
    public static void AssembleParameter() {
	do {
	    // Seteo nombre de parametro
	    paramA.setParameterName(configParamName());
	    // Seteo el alias de parametro
	    paramA.setParameterShort(configParamAlias());
	    // Seteo el tipo de parametro.
	    paramA.setParameterType(configParamType());
	    // Seteo el tamaño del char
	    paramA.setLength(configTypeCharLength());

	    Steart.progHeader("Struct DotH DotC Builder");
	    Steart.subHeader("Step 2: Name Your Parameters");
	    if (paramA.getParameterType().equals("char")) {
		System.out.printf("\nParameter status: \nName: %s\nAlias: %s\nType: %s[%d]\n", paramA.getParameterName(), paramA.getParameterShort(), paramA.getParameterType(), paramA.getLength());
		System.out.println("----------------------------");
		System.out.printf("\nPreview: %s %s[%d];\n\n", paramA.getParameterType(), paramA.getParameterName(), paramA.getLength());
		System.out.println("----------------------------");
	    } else {
		System.out.printf("\nParameter status: \nName: %s\nAlias: %s\nType: %s\n", paramA.getParameterName(), paramA.getParameterShort(), paramA.getParameterType());
		System.out.println("----------------------------");
		System.out.printf("\nPreview: %s %s;\n\n", paramA.getParameterType(), paramA.getParameterName());
		System.out.println("----------------------------");
	    }
	    // Valido que este todo correcto
	} while (!MainClass.validateAnswer("Is it OK? [y/n]: "));
    }

    /**
     * Adds the parameter to the LL.
     */
    public static void addParameterToLL() {
	do {
	    paramA = new Parameter();
	    if (flagFirstParam) {
		paramA.setId(id_asigner(paramA.getId(), 0));
		flagFirstParam = false;
	    } else {
		paramA.setId(id_asigner(paramA.getId(), parameterList.size()));
	    }
	    paramA.setLength(1); // size 1 by default.
	    AssembleParameter();
	    parameterList.add(paramA); // adds the parameter to the LL.

	} while (continueAddingParams() != 0);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

	strName = takeNameStruct();
	strShort = takeShortNameStruct();
	addParameterToLL();

	auxParNum *= parameterList.size();
	fullPackSize += auxParNum;
	Steart.show_progress(Steart.porcentageCalc(fullPackSize, packsDone));

	auxStrName = auxStrName.concat(strName);
	auxStrName = auxStrName.concat(".h");

	// Creacion del archivo.h
	packsDone = BuilderDotH.fileConstructor(parameterList, paramA, pFileDotH, pw, auxStrName, auxString, strShort, strName, packsDone, fullPackSize);

	auxStrName = auxStrName.substring(0, (auxStrName.length() - 2)); // le saco el .h al string
	auxStrName = auxStrName.concat(".c"); // e agrego el .c

	// Creacion del archivo.c
	BuilderDotC.FileConstructorC(pFileDotC, parameterList, paramA, pw, strName, auxStrName, strShort, auxString, packsDone, fullPackSize, flagStringHNeeded);

	System.out.println("Files created! Enjoy the pleassure of the automatization");

	parameterList.clear();
    }
}
