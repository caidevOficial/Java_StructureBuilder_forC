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

package logic.Structures.SubClasses;

/**
 * @author CaidevOficial
 *
 */
public class Parameter extends Checker {

    // Attribute
    public String parameterShort; // Size 5

    /**
     * Builds the parameter with default values.
     */
    public Parameter() {
	super();
	this.parameterShort = "";
    }

    /**
     * Builds the Parameter with 1 param.
     * 
     * @param pShort Alias of the parameter.
     */
    public Parameter(String pShort) {
	super();
	if (pShort != null) {
	    this.parameterShort = pShort;
	}
    }

    /**
     * Builds the Parameter with 2 params.
     * 
     * @param pShort Alias of the parameter.
     * @param id ID of the parameter.
     */
    public Parameter(String pShort, int id) {
	super(id);
	if (pShort != null) {
	    this.parameterShort = pShort;
	}

    }

    /**
     * Builds the Parameter with 3 params.
     * 
     * @param pShort Alias of the parameter.
     * @param pName Name of the parameter.
     * @param id ID of the parameter.
     */
    public Parameter(String pShort, String pName, int id) {
	super(id, pName);
	if (pShort != null) {
	    this.parameterShort = pShort;
	}
    }

    /**
     * Builds the Parameter with 4 params.
     * 
     * @param pShort Alias of the parameter.
     * @param pName Name of the parameter.
     * @param pType Type of the parameter.
     * @param id ID of the parameter.
     */
    public Parameter(String pShort, String pName, String pType, int id) {
	super(id, pName, pType);
	if (pShort != null) {
	    this.parameterShort = pShort;
	}
    }

    /**
     * Builds the Parameter with 5 params.
     * 
     * @param pShort Alias of the parameter.
     * @param pName Name of the parameter.
     * @param pType Type of the parameter.
     * @param length Length of the parameter.
     * @param id ID of the parameter.
     */
    public Parameter(String pShort, String pName, String pType, int length, int id) {
	super(id, pName, pType, length);
	if (pShort != null) {
	    this.parameterShort = pShort;
	}
    }

    /**
     * @return the parameterShort
     */
    public String getParameterShort() {
	return parameterShort;
    }

    /**
     * @param parameterShort the parameterShort to set
     */
    public void setParameterShort(String parameterShort) {
	if (parameterShort != null) {
	    this.parameterShort = parameterShort;
	}
    }
}
