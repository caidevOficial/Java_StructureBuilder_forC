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

package logic.Structures.SubClasses;

import logic.Structures.Common;

/**
 * @author <FacuFalcone - CaidevOficial>
 */
public class Checker extends Common {

    // Attribute
    private int length;

    /**
     * Builds the entity with default values: int = 0, Str = ""
     */
    public Checker() {
	
    }

    /**
     * Builds the entity with 1 param.
     * 
     * @param id The id of the checker.
     */
    public Checker(int id) {
	this();
	if (id > 0) {
	    this.id = id;
	}
    }

    /**
     * Builds the entity with 2 params.
     * 
     * @param id The id of the checker.
     * @param pName The name of the checker.
     */
    public Checker(int id, String pName) {
	this(id);
	if (pName != null) {
	    this.parameterName = pName;
	}
    }

    /**
     * Builds the entity with 3 params.
     * 
     * @param id The id of the checker.
     * @param pName The name of the checker.
     * @param pType The type of the checker.
     */
    public Checker(int id, String pName, String pType) {
	this(id, pName);
	if (pType != null) {
	    this.parameterType = pType;
	}
    }

    /**
     * Builds the entity with 4 params.
     * 
     * @param id The id of the checker.
     * @param pName The name of the checker.
     * @param pType The type of the checker.
     * @param length The length of the checker.
     */
    public Checker(int id, String pName, String pType, int length) {
	this(id, pName, pType);
	if (length > 0) {
	    this.length = length;
	}
    }

    /**
     * @return the length
     */
    public int getLength() {
	return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
	if (length >= 1) {
	    this.length = length;
	}
    }
}
