/*
 * Copyright 2018 QFast
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.qfast.vaadin.ui;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;
import static com.vaadin.ui.themes.ValoTheme.LAYOUT_HORIZONTAL_WRAPPING;

/**
 * @author Mawaziny
 */
public class HorizontalLayout extends com.vaadin.ui.HorizontalLayout {

    private static final long serialVersionUID = -4010234036799029437L;

    {
        setSpacing(false);
        setMargin(false);
    }

    public HorizontalLayout() {
    }

    public HorizontalLayout(Alignment alignment) {
        setDefaultComponentAlignment(alignment);
    }

    public HorizontalLayout(Component... children) {
        super(children);
    }

    public HorizontalLayout(Alignment alignment, Component... children) {
        setDefaultComponentAlignment(alignment);
        addComponents(children);
    }

    public void addComponent(Component component, Alignment alignment) {
        super.addComponent(component);
        super.setComponentAlignment(component, alignment);
    }

    public HorizontalLayout withMarginHorizontal() {
        setMargin(new MarginInfo(false, true));
        return this;
    }

    public HorizontalLayout withFullWidth() {
        setWidth(100, PERCENTAGE);
        return this;
    }

    public HorizontalLayout withUndefinedWidth() {
        setWidthUndefined();
        return this;
    }

    public HorizontalLayout withUndefinedHeight() {
        setHeightUndefined();
        return this;
    }

    public HorizontalLayout withSizeFull() {
        setSizeFull();
        return this;
    }

    public HorizontalLayout withStyleName(String styleName) {
        addStyleName(styleName);
        return this;
    }

    public HorizontalLayout withSpacing() {
        setSpacing(true);
        return this;
    }

    public HorizontalLayout withMargin() {
        setMargin(true);
        return this;
    }

    public HorizontalLayout withWidth(String width) {
        setWidth(width);
        return this;
    }

    public HorizontalLayout withUndefinedSize() {
        setSizeUndefined();
        return this;
    }

    /**
     * @author Mawaziny
     */
    public static class FlowLayout extends HorizontalLayout {

        private static final long serialVersionUID = -8839286570155209873L;

        {
            addStyleName(LAYOUT_HORIZONTAL_WRAPPING);
        }

        public FlowLayout() {
            super();
        }

        public FlowLayout(String caption) {
            setCaption(caption);
        }
    }
}
