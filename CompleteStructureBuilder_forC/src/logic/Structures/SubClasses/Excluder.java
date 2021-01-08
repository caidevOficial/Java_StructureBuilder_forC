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
public class Excluder extends Common {

    // Attribute
    public String excluderType; // Size 2

    /**
     * Builds the excluder with default values.
     */
    public Excluder() {
	
    }

    /**
     * Builds the excluder with 1 param.
     * 
     * @param pName Name of the excluder.
     */
    public Excluder(String pName) {
	this();
	if (pName != null) {
	    this.parameterName = pName;
	}
    }

    /**
     * Builds the excluder with 2 params.
     * 
     * @param pName Name of the excluder.
     * @param pType Type of the excluder.
     */
    public Excluder(String pName, String pType) {
	this(pName);
	if (pType != null) {
	    this.parameterType = pType;
	}
    }

    /**
     * Builds the excluder with 3 params.
     * 
     * @param pName Name of the excluder.
     * @param pType Type of the excluder.
     * @param id ID of the excluder.
     */
    public Excluder(String pName, String pType, int id) {
	this(pName, pType);
	if (id > 0) {
	    this.id = id;
	}
    }

    /**
     * Builds the excluder with 4 params.
     * 
     * @param pName Name of the excluder.
     * @param pType Type of the excluder.
     * @param id ID of the excluder.
     * @param eType Type of the excluder.
     */
    public Excluder(String pName, String pType, int id, String eType) {
	this(pName, pType, id);
	if (eType != null) {
	    this.excluderType = eType;
	}
    }

    /**
     * @return the excluderType
     */
    public String getExcluderType() {
	return excluderType;
    }

    /**
     * @param excluderType the excluderType to set
     */
    public void setExcluderType(String excluderType) {
	if (excluderType != null) {
	    this.excluderType = excluderType;
	}
    }

}
