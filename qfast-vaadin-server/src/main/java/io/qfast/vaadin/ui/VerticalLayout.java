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

/**
 * @author Mawaziny
 */
public class VerticalLayout extends com.vaadin.ui.VerticalLayout {

    private static final long serialVersionUID = -7919090034659433565L;

    {
        setSpacing(false);
        setMargin(false);
    }

    public VerticalLayout() {
    }

    public VerticalLayout(Component... children) {
        super(children);
    }

    public VerticalLayout withSpacing() {
        setSpacing(true);
        return this;
    }

    public VerticalLayout withMarginTop() {
        setMargin(new MarginInfo(true, false, false, false));
        return this;
    }

    public VerticalLayout withMargin() {
        setMargin(true);
        return this;
    }

    public VerticalLayout withFullWidth() {
        setWidth(100, PERCENTAGE);
        return this;
    }

    public VerticalLayout withUndefinedWidth() {
        setWidthUndefined();
        return this;
    }

    public void addComponent(Component c, Alignment alignment) {
        addComponent(c);
        setComponentAlignment(c, alignment);
    }

    public VerticalLayout withMarginBottom() {
        setMargin(new MarginInfo(false, false, true, false));
        return this;
    }

    public VerticalLayout withSizeFull() {
        setSizeFull();
        return this;
    }

    public VerticalLayout withStyleName(String styleName) {
        addStyleName(styleName);
        return this;
    }

    public VerticalLayout withComponentWidth(final String width) {
        this.forEach(c -> c.setWidth(width));
        return this;
    }

    /**
     * @author Mawaziny
     */
    public static class VerticalSpacedLayout extends VerticalLayout {

        private static final long serialVersionUID = 356327059520823234L;

        {
            setSpacing(true);
            setMargin(true);
        }

        public VerticalSpacedLayout() {
            super();
        }

        public VerticalSpacedLayout(Component... children) {
            super(children);
        }
    }
}
