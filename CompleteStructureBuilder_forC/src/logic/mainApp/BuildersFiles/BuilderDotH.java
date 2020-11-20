/*
 * Copyright (C) 2020 FacuFalcone - CaidevOficial.
 * 
 * Author in C: Santiago Herrera.
 * Adaptation in Java: FacuFalcone - CaidevOficial.
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

package logic.mainApp.BuildersFiles;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

import logic.Structures.SubClasses.Parameter;
import logic.consoleUI.Steart;

/**
 * @author CaidevOficial
 *
 */
public class BuilderDotH {

    /**
     * Crea la estructura en el archivo.h
     * 
     * @param parameterList LinkedList with the parameters.
     * @param pw write object.
     * @param paramA The parameter to write.
     * @param auxString Auxiliar String.
     * @param auxString Copy of the structure name.
     */
    private static void createStructInFile(LinkedList<Parameter> parameterList, PrintWriter pw, Parameter paramA, String auxString, String auxStrName) {
	int paramLen = parameterList.size();
	pw.printf("\43ifndef %s_H_INCLUDED\n\43define %s_H_INCLUDED\n", auxString, auxString);
	pw.printf("\43include \"LinkedList.h\"\n");

	pw.printf("\ntypedef struct\173\n");
	for (int i = 0; i < paramLen; i++) {
	    paramA = (Parameter) parameterList.get(i);
	    if (paramA.getLength() == 1) {
		pw.printf("    %s %s;\n", paramA.getParameterType(), paramA.getParameterName());
	    } else {
		pw.printf("    %s %s[%d];\n", paramA.getParameterType(), paramA.getParameterName(), paramA.getLength());
	    }
	}
	pw.printf("\175%s;\n\n", auxStrName);

    }

    /**
     * Write the function comparer in the text file.
     * @param paramA The parameter to write.
     * @param pw write object.
     * @param strName Name of the structure.
     * @param strShort Short name of the structure.
     * @param packsDone Number to calculate.
     * @param fullPackSize Max size of the functions to create.
     * @return Returns the amount of steps done 'till here.
     */
    private static int createComparers(Parameter paramA, PrintWriter pw, String strName, String strShort, int packsDone, int fullPackSize) {
	pw.printf("int %s_compare%s(void* %s1, void* %s2);\n\n", strShort, paramA.getParameterShort(), strName, strName);
	packsDone++;
	Steart.show_progress(Steart.porcentageCalc(fullPackSize, packsDone));

	return packsDone;
    }

    /**
     * Writes the getters in the file.
     * @param paramA The parameter to write.
     * @param pw write object.
     * @param auxString Copy of the structure name.
     * @param strName Name of the structure.
     * @param strShort Short name of the structure.
     * @param packsDone Number to calculate.
     * @param fullPackSize Max size of the functions to create.
     * @return Returns the amount of steps done 'till here.
     */
    private static int createGetters(Parameter paramA, PrintWriter pw, String auxStrName, String strName, String strShort, int packsDone, int fullPackSize) {
	pw.printf("int %s_get%s(%s* %s, %s* %s);\n", strShort, paramA.getParameterShort(), auxStrName, strName, paramA.getParameterType(), paramA.getParameterName());
	packsDone++;
	Steart.show_progress(Steart.porcentageCalc(fullPackSize, packsDone));

	return packsDone;
    }

    /**
     * Write the setters in the file.
     * @param paramA The parameter to write.
     * @param pw write object.
     * @param auxString Copy of the structure name.
     * @param strName Name of the structure.
     * @param strShort Short name of the structure.
     * @param packsDone Number to calculate.
     * @param fullPackSize Max size of the functions to create.
     * @return Returns the amount of steps done 'till here.
     */
    private static int createSetters(Parameter paramA, PrintWriter pw, String auxStrName, String strName, String strShort, int packsDone, int fullPackSize) {
	if (paramA.getLength() == 1) {
	    pw.printf("int %s_set%s(%s* %s, %s %s);\n", strShort, paramA.getParameterShort(), auxStrName, strName, paramA.getParameterType(), paramA.getParameterName());
	} else {
	    pw.printf("int %s_set%s(%s* %s, %s* %s);\n", strShort, paramA.getParameterShort(), auxStrName, strName, paramA.getParameterType(), paramA.getParameterName());
	}
	packsDone++;
	Steart.show_progress(Steart.porcentageCalc(fullPackSize, packsDone));

	return packsDone;
    }

    /**
     * Creates the getters and setters in the file.
     * @param parameterList LinkedList with the parameters.
     * @param paramA The parameter to write.
     * @param pw write object.
     * @param strName Name of the structure.
     * @param strShort Short name of the structure.
     * @param auxString Copy of the structure name.
     * @param packsDone Number to calculate.
     * @param fullPackSize Max size of the functions to create.
     * @return Returns the amount of steps done 'till here.
     */
    private static int createGettersAndSetters(LinkedList<Parameter> parameterList, Parameter paramA, PrintWriter pw, String strName, String strShort, String auxStrName, int packsDone,
	    int fullPackSize) {
	int paramLen = parameterList.size();
	pw.printf("// ## %s: COMPARERS, GETTERS & SETTERS\n",auxStrName);

	for (int i = 0; i < paramLen; i++) {
	    paramA = (Parameter) parameterList.get(i);
	    // Crear Getters
	    packsDone = createGetters(paramA, pw, auxStrName, strName, strShort, packsDone, fullPackSize);
	    
	    // Crear Setters
	    packsDone = createSetters(paramA, pw, auxStrName, strName, strShort, packsDone, fullPackSize);
	    
	    // Crear Comparadores
	    packsDone = createComparers(paramA, pw, strName, strShort, packsDone, fullPackSize);
	}
	return packsDone;
    }

    /**
     * Creates the builders and show functions.
     * @param parameterList List of parameters.
     * @param paramA Class of the parameter.
     * @param pw write object.
     * @param strShort Short name of the structure.
     * @param auxString Copy of the structure name.
     * @param strName Name of the structure.
     * @param packsDone Number to calculate.
     * @param fullPackSize Max size of the functions to create.
     * @return Returns the amount of steps done 'till here.
     */
    private static int createBasicStructFunctions(LinkedList<Parameter> parameterList, Parameter paramA, PrintWriter pw, String strShort, String auxStrName, String strName, int packsDone, int fullPackSize) {
	
	pw.printf("\n// ## %s: BASIC STRUCTURE FUNCTIONS.\n",auxStrName);
	// Empty builder
	pw.printf("%s* %s_newEmpty();\n", auxStrName, strShort); // auxStrName* strShort_newEmpty();
	packsDone++;
	Steart.show_progress(Steart.porcentageCalc(fullPackSize, packsDone));
	
	// Builder with params.
	pw.printf("%s* %s_new(", auxStrName, strShort); // auxStrName* strShort_new(parametersLine);
	addParametersToBuilder(parameterList, paramA, pw);
	
	// Show one
	pw.printf("void %s_show(%s* %s);\n", strShort, auxStrName, strName); // void usr_show(sUser* user)
	packsDone++;
	Steart.show_progress(Steart.porcentageCalc(fullPackSize, packsDone));

	// Show All
	pw.printf("int %s_showAll(LinkedList* this, char* errorMesage);\n\n", strShort);
	packsDone++;
	Steart.show_progress(Steart.porcentageCalc(fullPackSize, packsDone));


	return packsDone;
    }

    /**
     * Adds the parameter to the builder function.
     * 
     * @param parameterList LinkedList with the parameters.
     * @param paramA The parameter to write.
     * @param pw write object.
     */
    private static void addParametersToBuilder(LinkedList<Parameter> parameterList, Parameter paramA, PrintWriter pw) {
	int paramLen = parameterList.size();
	for (int i = 0; i < paramLen; i++) {
	    paramA = (Parameter) parameterList.get(i);
	    if (paramA.getLength() == 1) {
		pw.printf("%s %s", paramA.getParameterType(), paramA.getParameterName());
	    } else {
		pw.printf("%s* %s", paramA.getParameterType(), paramA.getParameterName());
	    }
	    if (paramLen - 1 != i) {
		pw.printf(", ");
	    } else {
		pw.printf(");\n\n"); // this should be EOF of dotH
	    }
	}
    }

    /**
     * Builds the entire file.h
     * @param parameterList LinkedList with the parameters.
     * @param paramA The parameter to write.
     * @param pFileDotH File to build in.
     * @param pw write object.
     * @param auxString Copy of the structure name.
     * @param auxString Auxiliar String.
     * @param strShort Short name of the structure.
     * @param strName Name of the structure.
     * @param packsDone Number to calculate.
     * @param fullPackSize Max size of the functions to create.
     */
    public static int fileConstructor(LinkedList<Parameter> parameterList, Parameter paramA, File pFileDotH, PrintWriter pw, String auxStrName, String auxString, String strShort, String strName,
	    int packsDone, int fullPackSize) {
	try {
	    System.out.println("auxStrName: " + auxStrName);
	    pFileDotH = new File("./" + auxStrName);

	    if (!pFileDotH.exists()) {
		// if not exist, creates the file.
		pFileDotH.createNewFile();
		pw = new PrintWriter(new FileWriter(pFileDotH));
	    } else {
		// if exist, override
		pw = new PrintWriter(new FileWriter(pFileDotH));
	    }

	    auxStrName = auxStrName.substring(0, (auxStrName.length() - 2));
	    auxString = auxStrName; // copio a otro string para hacer mayusculas y no perder original.
	    auxString = auxString.toUpperCase(); // pongo mayusculas
	    strName = strName.toLowerCase();

	    System.out.printf("\nFull Pack Size: %d\nActions completed: %d\n", fullPackSize, packsDone);

	    BuilderDotH.createStructInFile(parameterList, pw, paramA, auxString, auxStrName);

	    pw.printf("\43endif \n");
	    pw.printf("\n// # CREDITS TO:\n");
	    pw.printf("// ## Author in C: Santiago Herrera.\n");
	    pw.printf("// ## Adaptation in Java: FacuFalcone - CaidevOficial.\n");
	    packsDone++;
	    Steart.show_progress(Steart.porcentageCalc(fullPackSize, packsDone));

	    packsDone = BuilderDotH.createBasicStructFunctions(parameterList, paramA, pw, strShort, auxStrName, strName, packsDone, fullPackSize);
	    packsDone = BuilderDotH.createGettersAndSetters(parameterList, paramA, pw, strName, strShort, auxStrName, packsDone, fullPackSize);

	    packsDone++;
	    Steart.show_progress(Steart.porcentageCalc(fullPackSize, packsDone));

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    // Here we close the file if there is an error or not.
	    try {
		if (pw != null) {
		    pw.close();
		}
	    } catch (Exception e2) {
		e2.printStackTrace();
	    }
	}
	
	return packsDone;
    }
}
