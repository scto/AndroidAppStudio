/*
 * This file is part of Android AppStudio [https://github.com/TS-Code-Editor/AndroidAppStudio].
 *
 * License Agreement
 * This software is licensed under the terms and conditions outlined below. By accessing, copying, modifying, or using this software in any way, you agree to abide by these terms.
 *
 * 1. **  Copy and Modification Restrictions  **
 *    - You are not permitted to copy or modify the source code of this software without the permission of the owner, which may be granted publicly on GitHub Discussions or on Discord.
 *    - If permission is granted by the owner, you may copy the software under the terms specified in this license agreement.
 *    - You are not allowed to permit others to copy the source code that you were allowed to copy by the owner.
 *    - Modified or copied code must not be further copied.
 * 2. **  Contributor Attribution  **
 *    - You must attribute the contributors by creating a visible list within the application, showing who originally wrote the source code.
 *    - If you copy or modify this software under owner permission, you must provide links to the profiles of all contributors who contributed to this software.
 * 3. **  Modification Documentation  **
 *    - All modifications made to the software must be documented and listed.
 *    - the owner may incorporate the modifications made by you to enhance this software.
 * 4. **  Consistent Licensing  **
 *    - All copied or modified files must contain the same license text at the top of the files.
 * 5. **  Permission Reversal  **
 *    - If you are granted permission by the owner to copy this software, it can be revoked by the owner at any time. You will be notified at least one week in advance of any such reversal.
 *    - In case of Permission Reversal, if you fail to acknowledge the notification sent by us, it will not be our responsibility.
 * 6. **  License Updates  **
 *    - The license may be updated at any time. Users are required to accept and comply with any changes to the license.
 *    - In such circumstances, you will be given 7 days to ensure that your software complies with the updated license.
 *    - We will not notify you about license changes; you need to monitor the GitHub repository yourself (You can enable notifications or watch the repository to stay informed about such changes).
 * By using this software, you acknowledge and agree to the terms and conditions outlined in this license agreement. If you do not agree with these terms, you are not permitted to use, copy, modify, or distribute this software.
 *
 * Copyright © 2024 Dev Kumar
 */

package com.tscodeeditor.android.appstudio.vieweditor.models;

import com.blankj.utilcode.util.ResourceUtils;
import java.io.Serializable;
import java.util.ArrayList;

public class LayoutModel implements Serializable {
  public static final long serialVersionUID = 14L;

  private ViewModel view;
  private String layoutName;
  private ArrayList<String> viewIds;
  private boolean isAndroidNameSpaceUsed;
  private boolean isAppNameSpaceUsed;
  private boolean isToolsNameSpaceUsed;

  public LayoutModel clone() {
    LayoutModel clone = new LayoutModel();
    clone.setView(getView() == null ? null : getView().cloneViewModel());
    clone.setLayoutName(getLayoutName() == null ? null : new String(getLayoutName()));

    if (getViewIds() != null) {
      ArrayList<String> clonedViewIds = new ArrayList<String>();

      for (int ids = 0; ids < getViewIds().size(); ++ids) {
        clonedViewIds.add(getViewIds().get(ids) == null ? null : new String(getViewIds().get(ids)));
      }

      clone.setViewIds(clonedViewIds);
    }
    clone.setAndroidNameSpaceUsed(new Boolean(isAndroidNameSpaceUsed()));
    clone.setAppNameSpaceUsed(new Boolean(isAppNameSpaceUsed()));
    clone.setToolsNameSpaceUsed(new Boolean(isToolsNameSpaceUsed()));
    return clone;
  }

  public ViewModel getView() {
    return this.view;
  }

  public void setView(ViewModel view) {
    this.view = view;
  }

  public String getLayoutName() {
    return this.layoutName;
  }

  public void setLayoutName(String layoutName) {
    this.layoutName = layoutName;
  }

  public ArrayList<String> getViewIds() {
    return this.viewIds;
  }

  public void setViewIds(ArrayList<String> viewIds) {
    this.viewIds = viewIds;
  }

  public boolean isAndroidNameSpaceUsed() {
    return this.isAndroidNameSpaceUsed;
  }

  public void setAndroidNameSpaceUsed(boolean isAndroidNameSpaceUsed) {
    this.isAndroidNameSpaceUsed = isAndroidNameSpaceUsed;
  }

  public boolean isAppNameSpaceUsed() {
    return this.isAppNameSpaceUsed;
  }

  public void setAppNameSpaceUsed(boolean isAppNameSpaceUsed) {
    this.isAppNameSpaceUsed = isAppNameSpaceUsed;
  }

  public boolean isToolsNameSpaceUsed() {
    return this.isToolsNameSpaceUsed;
  }

  public void setToolsNameSpaceUsed(boolean isToolsNameSpaceUsed) {
    this.isToolsNameSpaceUsed = isToolsNameSpaceUsed;
  }

  public String getCode() {
    ViewModel view = getView();
    if (view == null) {
	  return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
    }
	return view.getCode("", this);
  }
}
