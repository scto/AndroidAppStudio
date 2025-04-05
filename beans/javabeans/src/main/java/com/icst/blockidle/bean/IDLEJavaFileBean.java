/*
 *  This file is part of Block IDLE.
 *
 *  Block IDLE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Block IDLE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *   along with Block IDLE.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.icst.blockidle.bean;

import java.io.Serializable;

// TODO: Inheritance
public class IDLEJavaFileBean extends IDLEFileBean implements Serializable {
	public static final long serialVersionUID = 1;

	public static final String FILE_TYPE = "folder";

	public static final String JAVA_CLASS = "JavaClass";
	public static final String JAVA_INTERFACE = "JavaInterface";

	public IDLEJavaFileBean(String name) {
		super(name);
	}

	@Override
	public String getFileType() {
		return FILE_TYPE;
	}
}
