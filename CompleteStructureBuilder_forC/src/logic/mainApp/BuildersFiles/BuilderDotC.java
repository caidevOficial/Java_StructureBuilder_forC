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

package logic.mainApp.BuildersFiles;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

import logic.Structures.SubClasses.Parameter;
import logic.consoleUI.Steart;

/**
 * @author <FacuFalcone - CaidevOficial>
 */
public class BuilderDotC {

    /**
     * Creates the builder without parameters.
     * @param pw Writer object.
     * @param auxStrName String to copy the name of the structure.
     * @param strShort Short name of the structure.
     * @param packsDone Steps counter.
     * @param packsDone Max size of the functions to create
     * @return Returns the amount of steps done 'till here.
     */
    private static int createBuilderEmpty(PrintWriter pw, String auxStrName, String strShort, int packsDone, int fullPackSize) {
	pw.printf("\n%s* %s_newEmpty()\173\n", auxStrName, strShort); // sUser* usr_newEmpty()
	pw.printf("    return (%s*) calloc(sizeof(%s),1);\n\175\n\n", auxStrName, auxStrName); // return (sUser*) malloc(sizeof(sUser));
	packsDone++;
	Steart.show_progress(Steart.porcentageCalc(fullPackSize, packsDone));

	return packsDone;
    }

    /**
     * Creates the builder with aprameters.
     * 
     * @param parameterList LinkedList of parameters.
     * @param paramA Parameter Object.
     * @param pw Writer object.
     * @param strName Name of the structure.
     * @param auxStrName String to copy the name of the structure.
     * @param strShort Short name of the structure.
     * @param packsDone Steps counter.
     * @param packsDone Max size of the functions to create
     * @return Returns the amount of steps done 'till here.
     */
    private static int createBuilderWithParams(LinkedList<Parameter> parameterList, Parameter paramA, PrintWriter pw, String strName, String auxStrName, String strShort, int packsDone,
	    int fullPackSize) {
	pw.printf("%s* %s_new(", auxStrName, strShort); // auxStrName* strShort_new(parametersLine);
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
		pw.printf(")\173\n");
	    }
	}
	pw.printf("    %s* %s = %s_newEmpty();\n", auxStrName, strName, strShort);
	pw.printf("    if(%s!=NULL)\173\n", strName);

	for (int i = 0; i < paramLen; i++) {
	    paramA = (Parameter) parameterList.get(i);
	    pw.printf("        %s_set%s(%s, %s);\n", strShort, paramA.getParameterShort(), strName, paramA.getParameterName()); // usr_setId(user,id);
	}
	pw.printf("    \175\n    return %s;\n\175\n\n", strName);
	packsDone++;
	Steart.show_progress(Steart.porcentageCalc(fullPackSize, packsDone));

	return packsDone;
    }

    /**
     * Creates a comparer function [No-Char type]
     * @param paramA Parameter Object.
     * @param pw Writer object.
     * @param strName Name of the structure.
     * @param strShort Short name of the structure.
     */
    private static void createCompareNoCharInFile(Parameter paramA, PrintWriter pw, String strName, String strShort) {
	pw.printf("// For use in a sort function - Compare %s->%s\n", strName, paramA.getParameterName());
	pw.printf("int %s_compare%s(void* %s1, void* %s2)\173\n", strShort, paramA.getParameterShort(), strName.toLowerCase(), strName.toLowerCase());
	pw.printf("    int anw = 0;\n");
	// primer variable
	pw.printf("    %s %s1_1;\n", paramA.getParameterType(), paramA.getParameterName());
	// segunda variable
	pw.printf("    %s %s2_2;\n\n", paramA.getParameterType(), paramA.getParameterName());
	// primer getter
	pw.printf("    %s_get%s(%s1, &%s1_1);\n", strShort, paramA.getParameterShort(), strName.toLowerCase(), paramA.getParameterName());
	// segundo getter
	pw.printf("    %s_get%s(%s2, &%s2_2);\n\n", strShort, paramA.getParameterShort(), strName.toLowerCase(), paramA.getParameterName());
	// Seccion comparador
	pw.printf("    if(%s1_1>%s2_2)\173\n", paramA.getParameterName(), paramA.getParameterName());
	pw.printf("        anw=1;\n    \175\n");
	pw.printf("    else if(%s1_1<%s2_2)\173\n", paramA.getParameterName(), paramA.getParameterName());
	pw.printf("        anw=-1;\n    \175\n");
	pw.printf("    return anw;\n\175\n\n");
    }

    /**
     * Creates a comparer function.
     * @param paramA Parameter Object.
     * @param pw Writer object.
     * @param strName Name of the structure.
     * @param strShort Short name of the structure.
     */
    private static void createCompareWithCharInFile(Parameter paramA, PrintWriter pw, String strName, String strShort) {
	pw.printf("// For use in a sort function - Compare %s->%s\n", strName, paramA.getParameterName());
	pw.printf("int %s_compare%s(void* %s1, void* %s2)\173\n", strShort, paramA.getParameterShort(), strName.toLowerCase(), strName.toLowerCase());
	pw.printf("    int anw;\n");
	// primer variable
	pw.printf("    %s %s1_1[%d];\n", paramA.getParameterType(), paramA.getParameterName(), paramA.getLength());
	// segunda variable
	pw.printf("    %s %s2_2[%d];\n\n", paramA.getParameterType(), paramA.getParameterName(), paramA.getLength());
	// primer getter
	pw.printf("    %s_get%s(%s1, %s1_1);\n", strShort, paramA.getParameterShort(), strName.toLowerCase(), paramA.getParameterName());
	// segundo getter
	pw.printf("    %s_get%s(%s2, %s2_2);\n\n", strShort, paramA.getParameterShort(), strName.toLowerCase(), paramA.getParameterName());
	pw.printf("    anw = strcmp(%s1_1,%s2_2);\n", paramA.getParameterName(), paramA.getParameterName());
	pw.printf("    return anw;\n\175\n\n");
    }

    /**
     * Creates a Getter function [NO-CHAR type].
     * @param paramA Parameter Object.
     * @param pw Writer object.
     * @param auxStrName String to copy the name of the structure.
     * @param strName Name of the structure.
     * @param strShort Short name of the structure.
     */
    private static void createGettersNoCharInFile(Parameter paramA, PrintWriter pw, String auxStrName, String strName, String strShort) {
	pw.printf("int %s_get%s(%s* %s, %s* %s)\173\n", strShort, paramA.getParameterShort(), auxStrName, strName.toLowerCase(), paramA.getParameterType(), paramA.getParameterName());
	pw.printf("    int isError = 1;\n");
	pw.printf("    if(%s!=NULL)\173\n", strName.toLowerCase());
	pw.printf("        *%s = %s->%s;\n", paramA.getParameterName(), strName.toLowerCase(), paramA.getParameterName());
	pw.printf("        isError = 0;\n    \175\n");
	pw.printf("    return isError;\n\175\n\n");
    }

    /**
     * Creates a Getter function.
     * @param paramA Parameter Object.
     * @param pw Writer object.
     * @param auxStrName String to copy the name of the structure.
     * @param strName Name of the structure.
     * @param strShort Short name of the structure.
     */
    private static void createGettersWithCharInFile(Parameter paramA, PrintWriter pw, String auxStrName, String strName, String strShort) {
	pw.printf("int %s_get%s(%s* %s, %s* %s)\173\n", strShort, paramA.getParameterShort(), auxStrName, strName.toLowerCase(), paramA.getParameterType(), paramA.getParameterName());
	pw.printf("    int isError = 1;\n");
	pw.printf("    if(%s!=NULL)\173\n", strName.toLowerCase());
	pw.printf("        strcpy(%s,%s->%s);\n", paramA.getParameterName(), strName.toLowerCase(), paramA.getParameterName());
	pw.printf("        isError = 0;\n    \175\n");
	pw.printf("    return isError;\n\175\n\n");
    }

    /**
     * Creates a Setter function [NO-CHAR type]
     * @param paramA Parameter Object.
     * @param pw Writer object.
     * @param auxStrName String to copy the name of the structure.
     * @param strName Name of the structure.
     * @param strShort Short name of the structure.
     */
    private static void createSettersNoCharInFile(Parameter paramA, PrintWriter pw, String auxStrName, String strName, String strShort) {
	pw.printf("int %s_set%s(%s* %s, %s %s)\173\n", strShort, paramA.getParameterShort(), auxStrName, strName.toLowerCase(), paramA.getParameterType(), paramA.getParameterName());
	pw.printf("    int isError = 1;\n");
	pw.printf("    if(%s!=NULL)\173\n", strName.toLowerCase());
	pw.printf("        %s->%s = %s;\n", strName.toLowerCase(), paramA.getParameterName(), paramA.getParameterName());
	pw.printf("        isError = 0;\n    \175\n");
	pw.printf("    return isError;\n\175\n\n");
    }

    /**
     * Creates a setter function.
     * @param paramA Parameter Object.
     * @param pw Writer object.
     * @param auxStrName String to copy the name of the structure.
     * @param strName Name of the structure.
     * @param strShort Short name of the structure.
     */
    private static void createSettersWithCharInFile(Parameter paramA, PrintWriter pw, String auxStrName, String strName, String strShort) {
	pw.printf("int %s_set%s(%s* %s, %s* %s)\173\n", strShort, paramA.getParameterShort(), auxStrName, strName.toLowerCase(), paramA.getParameterType(), paramA.getParameterName());
	pw.printf("    int isError = 1;\n");
	pw.printf("    if(%s!=NULL)\173\n", strName.toLowerCase());
	pw.printf("        strcpy(%s->%s,%s);\n", strName.toLowerCase(), paramA.getParameterName(), paramA.getParameterName());
	pw.printf("        isError = 0;\n    \175\n");
	pw.printf("    return isError;\n\175\n\n");
    }

    /**
     * Writes in the file, the builders.
     * @param parameterList LinkedList of parameters.
     * @param paramA Parameter Object.
     * @param pw Writer object.
     * @param strName Name of the structure.
     * @param auxStrName String to copy the name of the structure.
     * @param strShort Short name of the structure.
     * @param packsDone Steps counter.
     * @param packsDone Max size of the functions to create
     * @param flagStringHNeeded
     * @return Returns the amount of steps done 'till here.
     */
    public static int createBuildersInFile(LinkedList<Parameter> parameterList, Parameter paramA, PrintWriter pw, String strName, String auxStrName, String strShort, int packsDone, int fullPackSize,
	    int flagStringHNeeded) {

	pw.printf("\43include \74stdlib.h\76\n");
	pw.printf("\43include \74stdio.h\76\n");
	if (flagStringHNeeded == 1) {
	    pw.printf("\43include \74string.h\76\n");
	}
	pw.printf("\43include \"LinkedList.h\"\n");
	pw.printf("\43include \"%s.h\"\n", auxStrName);

	// Creo constructor vacio.
	packsDone = createBuilderEmpty(pw, auxStrName, strShort, packsDone, fullPackSize);

	// creo constructor con parametros
	packsDone = createBuilderWithParams(parameterList, paramA, pw, strName, auxStrName, strShort, packsDone, fullPackSize);

	for (int i = 0; i < parameterList.size(); i++) {
	    paramA = (Parameter) parameterList.get(i);
	    if (paramA.getLength() == 1) {

		// Create Getters without char param.
		createGettersNoCharInFile(paramA, pw, auxStrName, strName, strShort);
		// Create Setters without char param.
		createSettersNoCharInFile(paramA, pw, auxStrName, strName, strShort);
		// Create Comparers without char param.
		createCompareNoCharInFile(paramA, pw, strName, strShort);

	    } else if (paramA.getParameterType().equals("char")) {
		// Create Getters with char param.
		createGettersWithCharInFile(paramA, pw, auxStrName, strName, strShort);
		// Create Setter with char param.
		createSettersWithCharInFile(paramA, pw, auxStrName, strName, strShort);
		// Create Comparers with char param.
		createCompareWithCharInFile(paramA, pw, strName, strShort);

	    }
	    packsDone += 3;
	    Steart.show_progress(Steart.porcentageCalc(fullPackSize, packsDone));
	}

	return packsDone;
    }

    /**
     * Writes in the file, the show function.
     * @param parameterList LinkedList of parameters.
     * @param paramA Parameter Object.
     * @param pw Writer object.
     * @param auxStrName String to copy the name of the structure.
     * @param strName Name of the structure.
     * @param strShort Short name of the structure.
     */
    public static void createShowEntity(LinkedList<Parameter> parameterList, Parameter paramA, PrintWriter pw, String auxStrName, String strName, String strShort) {
	String paramName = "";
	String paramType = "";
	int paramLength = 0;
	
	pw.printf("void %s_show(%s* %s)\173\n", strShort, strName, auxStrName); // void usr_show(sUser* user)
	//FIXME agregar auxiliares para los getters
	int paramLen = parameterList.size();
	for (int i = 0; i < paramLen; i++) {
	    paramA = (Parameter) parameterList.get(i);
	    // Case CHAR
	    paramType = paramA.getParameterType();
	    paramName = paramA.getParameterName();
	    
	    // recorrer 1 vez para variables auxiliares.
	    pw.printf(paramType + " " + paramName);
	    if(paramType.equals("char")) {
		paramLength = paramA.getLength();
		pw.printf("["+paramLength+"]");
	    }
	    pw.printf(";\n");
	}
	pw.printf("\n");
	
	// Recorrer por segunda vez para hacer los getters.
	for (Parameter parameter : parameterList) {
	    if(parameter.getParameterType().equals("char")) {
		pw.printf("%s_get%s(%s, %s);\n", strShort, parameter.getParameterShort(), auxStrName, parameter.getParameterName());
	    }else {
		pw.printf("%s_get%s(%s, &%s);\n", strShort, parameter.getParameterShort(), auxStrName, parameter.getParameterName());		
	    }
	}
	pw.printf("\n");
	
	pw.printf("    if(%s!=NULL)\173\n", auxStrName);
	pw.printf("        printf(\"");
	for (Parameter parameter : parameterList) {
	    if (parameter.getParameterType().equals("char")) {
		pw.printf("%%s");
		//CASE INT
	    } else if (parameter.getParameterType().equals("int")) {
		pw.printf("%%d");
		//CASE SHORT SHORT INT
	    } else if (parameter.getParameterType().equals("short short int")) {
		pw.printf("%%h2d");
		//CASE LONG LONG INT
	    } else if (parameter.getParameterType().equals("long long int")) {
		pw.printf("%%l2d");
		//CASE FLOAT
	    } else if (parameter.getParameterType().equals("float")) {
		pw.printf("%%f");
	    }
	    
	    if (parameterList.indexOf(parameter) != (parameterList.size()-1)) {
		pw.printf("|");
	    } else {
		pw.printf("\134n\"");
		for (Parameter thisParameter : parameterList) {
		    pw.printf(",%s", thisParameter.getParameterName());
		}
		
		pw.printf(");\n");
	    }
	}
	pw.printf("        printf(\"-\134n\");\n    \175\n\175\n\n");
    }

    /**
     * Writes in the file, the showAll function.
     * @param parameterList LinkedList of parameters.
     * @param paramA Parameter Object.
     * @param pw Writer object.
     * @param auxStrName String to copy the name of the structure.
     * @param strName Name of the structure.
     * @param strShort Short name of the structure.
     * @param packsDone Steps counter.
     * @param packsDone Max size of the functions to create
     * @return Returns the amount of steps done 'till here.
     */
    public static int createShowAll(LinkedList<Parameter> parameterList, Parameter paramA, PrintWriter pw, String auxStrName, String strName, String strShort, int packsDone, int fullPackSize) {
	int paramLen = parameterList.size();
	String capitalize = "";
	pw.printf("int %s_showAll(LinkedList* this, char* errorMesage)\173\n", strShort);
	pw.printf("    int length;\n");
	pw.printf("    int isError = 1;\n");
	pw.printf("    %s* %s;\n", auxStrName, strName);
	pw.printf("    length = ll_len(this);\n");
	pw.printf("    if(length>0)\173\n");
	pw.printf("        printf(\"");
	for (int i = 0; i < paramLen; i++) {
	    paramA = (Parameter) parameterList.get(i);
	    capitalize = paramA.getParameterName().substring(0, 1) + paramA.getParameterName().substring(1);
	    pw.printf("%s", capitalize);

	    if (paramLen - 1 != i) {
		pw.printf("|");
	    } else {
		pw.printf("\134n\");\n");
	    }
	}
	pw.printf("        printf(\"-\134n\");\n");
	pw.printf("        for(int i=0; i<length; i++)\173\n");
	pw.printf("            %s = (%s*) ll_get(this,i);\n", strName, auxStrName);
	pw.printf("            %s_show(%s);\n        \175\n", strShort, strName);
	pw.printf("        isError = 0;\n    \175\n");
	pw.printf("    else\173\n");
	pw.printf("        printf(errorMesage);\n    \175\n");
	pw.printf("    return isError;\n\175\n");

	packsDone++;
	Steart.show_progress(Steart.porcentageCalc(fullPackSize, packsDone));

	return packsDone;
    }

    /**
     * Builds the entire file.c
     * @param pFileDotC File to build in.
     * @param parameterList LinkedList of parameters.
     * @param paramA Parameter Object.
     * @param pw Writer object.
     * @param strName Name of the structure.
     * @param auxStrName String to copy the name of the structure.
     * @param strShort Short name of the structure.
     * @param auxString
     * @param packsDone Steps counter.
     * @param packsDone Max size of the functions to create
     * @param flagStringHNeeded
     */
    public static void FileConstructorC(File pFileDotC, LinkedList<Parameter> parameterList, Parameter paramA, PrintWriter pw, String strName, String auxStrName, String strShort, String auxString,
	    int packsDone, int fullPackSize, int flagStringHNeeded) {
	try {
	    pFileDotC = new File("./" + auxStrName);

	    if (!pFileDotC.exists()) {
		// if not exist, create the file
		pFileDotC.createNewFile();
		pw = new PrintWriter(new FileWriter(pFileDotC));
	    } else {
		// if exist, override
		pw = new PrintWriter(new FileWriter(pFileDotC));
	    }

	    auxStrName = auxStrName.substring(0, auxStrName.length() - 2);
	    auxString = auxStrName; // copio a otro string para hacer mayusculas y no perder original.
	    auxString = auxString.toUpperCase(); // pongo mayusculas

	    packsDone = createBuildersInFile(parameterList, paramA, pw, strName, auxStrName, strShort, packsDone, fullPackSize, flagStringHNeeded);

	    // SHOW 1
	    createShowEntity(parameterList, paramA, pw, strName, auxStrName, strShort);
	    packsDone = createShowAll(parameterList, paramA, pw, auxStrName, strName, strShort, packsDone, fullPackSize);

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
    }
}
