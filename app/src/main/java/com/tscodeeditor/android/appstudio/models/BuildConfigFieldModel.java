/*
 *  This file is part of Android AppStudio.
 *
 *  Android AppStudio is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Android AppStudio is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *   along with Android AppStudio.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.tscodeeditor.android.appstudio.models;

import java.io.Serializable;

public class BuildConfigFieldModel implements Serializable {
  private static final long serialVersionUID = 021173502L;

  private String fieldName;
  private String fieldValue;
  private String fieldDataType;
  private String fieldImport;

  public String getFieldName() {
    return this.fieldName == null ? "" : this.fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getFieldValue() {
    return this.fieldValue == null ? "" : this.fieldValue;
  }

  public void setFieldValue(String fieldValue) {
    this.fieldValue = fieldValue;
  }

  public String getFieldDataType() {
    return this.fieldDataType == null ? "" : this.fieldDataType;
  }

  public void setFieldDataType(String fieldDataType) {
    this.fieldDataType = fieldDataType;
  }

  public String getFieldImport() {
    return this.fieldImport == null ? "" : this.fieldImport;
  }

  public void setFieldImport(String fieldImport) {
    this.fieldImport = fieldImport;
  }
}
