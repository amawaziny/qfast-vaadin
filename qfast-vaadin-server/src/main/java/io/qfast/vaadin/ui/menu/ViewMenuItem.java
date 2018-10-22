/*
 * Copyright (c) 2018 QFast
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.qfast.vaadin.ui.menu;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mawaziny
 */
public class ViewMenuItem implements Serializable {

    private static final long serialVersionUID = 8395317133665342755L;

    private int codePoint;
    private String caption;
    private String badge;
    private String viewId;
    private boolean enabled = true;
    private List<ViewMenuItem> viewMenuItems;

    public ViewMenuItem() {
    }

    public int getCodePoint() {
        return codePoint;
    }

    public void setCodePoint(int codePoint) {
        this.codePoint = codePoint;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<ViewMenuItem> getViewMenuItems() {
        return viewMenuItems;
    }

    public void setViewMenuItems(List<ViewMenuItem> viewMenuItems) {
        this.viewMenuItems = viewMenuItems;
    }
}
